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

/**
 * @program: associationmanagement
 * @description: SDH
 * @author: SDH
 * @create: 2019-01-12 18:19
 **/
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

    @RequestMapping("/addguide")
    public JSONResult AddGuide(HttpServletRequest request) {
        ClassPeriod classPeriod = new ClassPeriod();
        String assid = request.getParameter("assid");
        String guidetime = request.getParameter("guidetime");
        String guidedate = request.getParameter("guidedate");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        classPeriod.setClassperiodDate(formatter.parse(guidedate, new ParsePosition(0)));
        classPeriod.setClassperiodTime(Double.valueOf(guidetime));
        classPeriod.setClassperiodAssciation(Integer.valueOf(assid));
        classPeriod.setClassperiodTeacher(associationService.selectByPrimaryKey(Integer.valueOf(assid)).getTeacher());
        classPeriodService.insert(classPeriod);
        return JSONResult.build(200, "ok", null);
    }

    @RequestMapping("/getAllGuideByAssid")
    public JSONResult getAllGuideByAssid(String assid) {
        List<ClassPeriod> classPeriods = classPeriodService.getAllByAssid(Integer.valueOf(assid));
        List<ClassPeriodResult> classPeriodResults = new ArrayList<>();
        for (ClassPeriod i : classPeriods) {
            ClassPeriodResult classPeriodResult = new ClassPeriodResult(i);
            classPeriodResult.setClassperiodAssciation(associationService.selectByPrimaryKey(i.getClassperiodAssciation()).getAssociationName());
            classPeriodResult.setClassperiodTeacher(teacherService.selectByPrimaryKey(i.getClassperiodTeacher()).getTeacherName());
            classPeriodResults.add(classPeriodResult);
        }
        return JSONResult.build(200, "ok", classPeriodResults);
    }

    @RequestMapping("/getAllGuideByAssidtoExcel")
    public void getAllGuideByAssidtoExcel(String assid, HttpServletResponse response) {
        List<ClassPeriod> classPeriods = classPeriodService.getAllByAssid(Integer.valueOf(assid));
        List<ClassPeriodResult> classPeriodResults = new ArrayList<>();
        for (ClassPeriod i : classPeriods) {
            ClassPeriodResult classPeriodResult = new ClassPeriodResult(i);
            classPeriodResult.setClassperiodAssciation(associationService.selectByPrimaryKey(i.getClassperiodAssciation()).getAssociationName());
            classPeriodResult.setClassperiodTeacher(teacherService.selectByPrimaryKey(i.getClassperiodTeacher()).getTeacherName());
            classPeriodResults.add(classPeriodResult);
        }
        Association association = associationService.selectByPrimaryKey(Integer.valueOf(assid));
        String filepath = "D://" + association.getAssociationName() + "-指导情况统计.xls";
        String filename = association.getAssociationName() + "-指导情况统计.xls";
        ExcelExportUtil.exportToFile(classPeriodResults, filepath);
        try (
                InputStream inputStream = new FileInputStream(new File(filepath));
                OutputStream outputStream = response.getOutputStream();
        ) {
            //指明为下载
            response.setContentType("application/x-download");
            // 设置文件名
            response.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(filename, "UTF-8"));
            //把输入流copy到输出流
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
