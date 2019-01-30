package edu.smxy.associationmanagement.controller;

import com.xuxueli.poi.excel.ExcelExportUtil;
import edu.smxy.associationmanagement.domain.Association;
import edu.smxy.associationmanagement.domain.ClassPeriod;
import edu.smxy.associationmanagement.domain.ClassPeriodResult;
import edu.smxy.associationmanagement.domain.JSONResult;
import edu.smxy.associationmanagement.services.association.AssociationService;
import edu.smxy.associationmanagement.services.classperiod.ClassPeriodService;
import edu.smxy.associationmanagement.services.teacher.TeacherService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@ResponseBody
@RestController
@EnableAutoConfiguration
public class ClassPeriodController {
    @Autowired
    ClassPeriodService classPeriodService;
    @Autowired
    AssociationService associationService;
    @Autowired
    TeacherService teacherService;

    @RequestMapping({"/addguide"})
    public JSONResult AddGuide(final HttpServletRequest request) {
        final ClassPeriod classPeriod = new ClassPeriod();
        final String assid = request.getParameter("assid");
        final String guidetime = request.getParameter("guidetime");
        final String guidedate = request.getParameter("guidedate");
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        classPeriod.setClassperiodDate(formatter.parse(guidedate, new ParsePosition(0)));
        classPeriod.setClassperiodTime(Double.valueOf(guidetime));
        classPeriod.setClassperiodAssciation(Integer.valueOf(assid));
        classPeriod.setClassperiodTeacher(this.associationService.selectByPrimaryKey(Integer.valueOf(assid)).getTeacher());
        this.classPeriodService.insert(classPeriod);
        return JSONResult.build(200, "ok", (Object) null);
    }

    @RequestMapping({"/getAllGuideByAssid"})
    public JSONResult getAllGuideByAssid(final String assid) {
        final List<ClassPeriod> classPeriods = (List<ClassPeriod>) this.classPeriodService.getAllByAssid((int) Integer.valueOf(assid));
        final List<ClassPeriodResult> classPeriodResults = new ArrayList<ClassPeriodResult>();
        for (final ClassPeriod i : classPeriods) {
            final ClassPeriodResult classPeriodResult = new ClassPeriodResult(i);
            classPeriodResult.setClassperiodAssciation(this.associationService.selectByPrimaryKey(i.getClassperiodAssciation()).getAssociationName());
            classPeriodResult.setClassperiodTeacher(this.teacherService.selectByPrimaryKey(i.getClassperiodTeacher()).getTeacherName());
            classPeriodResults.add(classPeriodResult);
        }
        return JSONResult.build(200, "ok", (Object) classPeriodResults);
    }

    @RequestMapping({"/getAllGuideByAssidtoExcel"})
    public void getAllGuideByAssidtoExcel(final String assid, final HttpServletResponse response) {
        final List<ClassPeriod> classPeriods = (List<ClassPeriod>) this.classPeriodService.getAllByAssid((int) Integer.valueOf(assid));
        final List<ClassPeriodResult> classPeriodResults = new ArrayList<ClassPeriodResult>();
        for (final ClassPeriod i : classPeriods) {
            final ClassPeriodResult classPeriodResult = new ClassPeriodResult(i);
            classPeriodResult.setClassperiodAssciation(this.associationService.selectByPrimaryKey(i.getClassperiodAssciation()).getAssociationName());
            classPeriodResult.setClassperiodTeacher(this.teacherService.selectByPrimaryKey(i.getClassperiodTeacher()).getTeacherName());
            classPeriodResults.add(classPeriodResult);
        }
        final Association association = this.associationService.selectByPrimaryKey(Integer.valueOf(assid));
        final String filepath = "/www/wwwroot/ass/upload/" + association.getAssociationName() + "-指导情况统计.xls";
        final String filename = association.getAssociationName() + "-指导情况统计.xls";
        ExcelExportUtil.exportToFile((List) classPeriodResults, filepath);
        try (final InputStream inputStream = new FileInputStream(new File(filepath));
             final OutputStream outputStream = (OutputStream) response.getOutputStream()) {
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(filename, "UTF-8"));
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
