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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * @program: associationmanagement
 * @description: SDH
 * @author: SDH
 * @create: 2019-01-11 20:14
 **/
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

    @RequestMapping("/addprogram")
    public JSONResult addProgram(Program program) {
        int line = programService.insert(program);
        if (line > 0) {
            return JSONResult.build(200, "插入成功", null);
        } else {
            return JSONResult.build(500, "error", null);
        }
    }

    @RequestMapping("/getProgramByEventId")
    public JSONResult getProgramByEventId(int eventid) {
        List<Program> programs = programService.getProgramByEventId(eventid);
        List<ProgramResult> results = new ArrayList<>();
        for (Program i : programs) {
            Association association = associationService.selectByPrimaryKey(i.getAssociationid());
            ProgramResult result = new ProgramResult(i);
            result.setEventname(eventService.selectByPrimaryKey(eventid).getEventName());
            result.setAssociationname(association.getAssociationName());
            results.add(result);
        }
        return JSONResult.build(200, "ok", results);
    }

    @RequestMapping("/getProgramtoExcel")
    public void getProgramtoExcel(int eventid, HttpServletResponse response) {
        List<Program> programs = programService.getProgramByEventId(eventid);
        List<ProgramResult> results = new ArrayList<>();
        for (Program i : programs) {
            Association association = associationService.selectByPrimaryKey(i.getAssociationid());
            ProgramResult result = new ProgramResult(i);
            result.setEventname(eventService.selectByPrimaryKey(eventid).getEventName());
            result.setAssociationname(association.getAssociationName());
            results.add(result);
        }
        ExcelExportUtil.exportToFile(results, "D://" + eventid + ".xls");
        try (
                InputStream inputStream = new FileInputStream(new File("D://" + eventid + ".xls"));
                OutputStream outputStream = response.getOutputStream();
        ) {
            //指明为下载
            response.setContentType("application/x-download");
            // 设置文件名
            response.addHeader("Content-Disposition", "attachment;fileName=" + eventid + ".xls");
            //把输入流copy到输出流
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
