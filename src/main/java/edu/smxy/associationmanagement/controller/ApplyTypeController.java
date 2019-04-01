package edu.smxy.associationmanagement.controller;

import edu.smxy.associationmanagement.domain.ApplyType;
import edu.smxy.associationmanagement.domain.File;
import edu.smxy.associationmanagement.domain.JSONResult;
import edu.smxy.associationmanagement.services.applytype.ApplyTypeService;
import edu.smxy.associationmanagement.services.file.FileService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;

/**
 * @program: associationmanagement
 * @description: 申请类型Controller
 * @author: SDH
 * @create: 2019-03-27 20:45
 */
@RestController
@EnableAutoConfiguration
public class ApplyTypeController {
  @Autowired private ApplyTypeService applyTypeService;
  @Autowired private FileService fileService;

  /**
   * 判断申请类型是否在申请时间内
   *
   * @param applytype 申请类型id
   * @return
   */
  @PostMapping({"/applyIsInTime"})
  public JSONResult isInTime(int applytype) {
    ApplyType record = applyTypeService.isInTime(applytype);
    if (record != null) {
      return JSONResult.build(200, "ok", record);
    } else {
      return JSONResult.build(500, "error", null);
    }
  }
  
  /**
   * 根据申请类型下载模板
   * @param applytype
   * @param response
   */
  @GetMapping("/getTemplateFileById")
  public void getTemplateFileById(int applytype, HttpServletResponse response) {
    ApplyType applyType = applyTypeService.getTemplateFileById(applytype);
    int fileid = applyType.getTemplateFile();
    File file = fileService.searchFileById(fileid);
    if (file != null) {
      final String filepath = file.getFilepath();
      String filename = file.getFilename();
      try (final InputStream inputStream =
              new FileInputStream(new java.io.File(filepath + filename));
          final OutputStream outputStream = response.getOutputStream()) {
        response.setContentType("application/x-download");
        filename = URLEncoder.encode(file.getFilename(), "UTF-8");
        response.addHeader("Content-Disposition", "attachment;fileName=" + filename);
        IOUtils.copy(inputStream, outputStream);
        outputStream.flush();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
 
  /**
   * 管理员设置申请发起时间段
   *
   * @param startTime 起始时间
   * @param endTime 结束时间
   * @param applyType 申请类型
   * @return
   */
  @PostMapping({"/setApplyTime"})
  public JSONResult setApplyTime(String startTime, String endTime, int applyType) {
    ApplyType applyType1 = applyTypeService.selectByPrimaryKey(applyType);
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    applyType1.setStartTime(formatter.parse(startTime, new ParsePosition(0)));
    applyType1.setEndTime(formatter.parse(endTime, new ParsePosition(0)));
    int record = applyTypeService.updateByPrimaryKey(applyType1);
    if (record > 0) {
      return JSONResult.build(200, "ok", null);
    } else {
      return JSONResult.build(500, "error", null);
    }
  }
}
