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

    @RequestMapping({"/addprogram"})
    public JSONResult addProgram(final Program program) {
        final int line = this.programService.insert(program);
        if (line > 0) {
            return JSONResult.build(200, "\u63d2\u5165\u6210\u529f", null);
        }
        return JSONResult.build(500, "error", null);
    }

    @RequestMapping({"/getProgramByEventId"})
    public JSONResult getProgramByEventId(final int eventid) {
        final List<Program> programs = this.programService.getProgramByEventId(eventid);
        final List<ProgramResult> results = new ArrayList<ProgramResult>();
        for (final Program i : programs) {
            final Association association = this.associationService.selectByPrimaryKey(i.getAssociationid());
            final ProgramResult result = new ProgramResult(i);
            result.setEventname(this.eventService.selectByPrimaryKey(eventid).getEventName());
            result.setAssociationname(association.getAssociationName());
            results.add(result);
        }
        return JSONResult.build(200, "ok", results);
    }

    @RequestMapping({"/getProgramtoExcel"})
    public void getProgramtoExcel(final int eventid, final HttpServletResponse response) {
        final List<Program> programs = this.programService.getProgramByEventId(eventid);
        final List<ProgramResult> results = new ArrayList<ProgramResult>();
        for (final Program i : programs) {
            final Association association = this.associationService.selectByPrimaryKey(i.getAssociationid());
            final ProgramResult result = new ProgramResult(i);
            result.setEventname(this.eventService.selectByPrimaryKey(eventid).getEventName());
            result.setAssociationname(association.getAssociationName());
            results.add(result);
        }
        ExcelExportUtil.exportToFile((List) results, "/www/wwwroot/ass/upload/" + eventid + ".xls");
        try (final InputStream inputStream = new FileInputStream(new File("/www/wwwroot/ass/upload/" + eventid + ".xls"));
             final OutputStream outputStream = (OutputStream) response.getOutputStream()) {
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;fileName=" + eventid + ".xls");
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }
}
