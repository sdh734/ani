package edu.smxy.associationmanagement.controller;

import com.xuxueli.poi.excel.ExcelExportUtil;
import edu.smxy.associationmanagement.domain.Association;
import edu.smxy.associationmanagement.domain.JSONResult;
import edu.smxy.associationmanagement.domain.Program;
import edu.smxy.associationmanagement.domain.ProgramResult;
import edu.smxy.associationmanagement.services.association.AssociationService;
import edu.smxy.associationmanagement.services.event.EventService;
import edu.smxy.associationmanagement.services.program.ProgramService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@ResponseBody
@EnableAutoConfiguration
public class ProgramController {
    @Autowired
    ProgramService programService;
    @Autowired
    AssociationService associationService;
    @Autowired
    EventService eventService;

    /**
     * 根据通知id 添加节目信息
     *
     * @param program 节目信息
     * @return 是否成功的信息 200 成功 500 失败
     */
    @RequestMapping({"/addprogram"})
    public JSONResult addProgram(final Program program) {
        final int line = this.programService.insert(program);
        if (line > 0) {
            return JSONResult.build(200, "报名成功", null);
        }
        return JSONResult.build(500, "报名失败", null);
    }

    /**
     * 更新已经报名的节目信息
     * @param program 节目类
     * @return 是否成功的信息 200 成功 500 失败
     */
    @PostMapping({"/updateProgram"})
    public JSONResult updateProgram(Program program) {
        int record = programService.updateByPrimaryKey(program);
        if (record > 0) {
            return JSONResult.build(200, "修改成功", null);
        } else {
            return JSONResult.build(500, "修改失败", null);
        }
    }

    /**
     * 获得节目信息 通过通知id和协会id 用来判断是否第一次报名
     * @param eventid 通知id
     * @param assid 协会id
     * @return 200 非第一次报名,返回数据带有之前报名的信息 500 第一次报名
     */
    @PostMapping({"/getProgramByEventIdAndAssId"})
    public JSONResult getProgramByEventIdAndAssId(int eventid, int assid) {
        Program program = new Program();
        program.setAssociationid(assid);
        program.setEventid(eventid);
        Program result = programService.getProgramByEventIdAndAssId(program);
        if (result != null) {
            return JSONResult.build(200, "ok", result);
        } else {
            return JSONResult.build(500, "error", null);
        }
    }

    /**
     * 获得所有节目信息 通过通知id
     * @param eventid 通知id
     * @return 节目信息集合
     */
    @RequestMapping({"/getProgramByEventId"})
    public JSONResult getProgramByEventId(final int eventid) {
        getProgramResults(eventid);
        List<ProgramResult> results = getProgramResults(eventid);
        return JSONResult.build(200, "ok", results);
    }

    /**
     * 根据通知id 获取所有节目信息
     *
     * @param eventid 通知id
     * @return 通知信息的集合
     */
    private List<ProgramResult> getProgramResults(int eventid) {
        final List<Program> programs = this.programService.getProgramByEventId(eventid);
        final List<ProgramResult> results = new ArrayList<>();
        for (final Program i : programs) {
            final Association association =
                    this.associationService.selectByPrimaryKey(i.getAssociationid());
            final ProgramResult result = new ProgramResult(i);
            result.setEventname(this.eventService.selectByPrimaryKey(eventid).getEventName());
            result.setAssociationname(association.getAssociationName());
            results.add(result);
        }
        return results;
    }

    /**
     * 将节目信息集合导出到Excel
     * @param eventid 通知id
     * @param response 返回下载文件的响应
     */
    @RequestMapping({"/getProgramtoExcel"})
    public void getProgramtoExcel(final int eventid, final HttpServletResponse response) {
        // 本地目录
        String path = "G:\\upload\\exceltemp\\";
        // 服务器目录
        // String path = ""/www/wwwroot/ass/upload/";
        List<ProgramResult> results = getProgramResults(eventid);
        ExcelExportUtil.exportToFile(results, path + eventid + ".xls");
        try (final InputStream inputStream = new FileInputStream(new File(path + eventid + ".xls"));
             final OutputStream outputStream = response.getOutputStream()) {
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;fileName=" + eventid + ".xls");
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
