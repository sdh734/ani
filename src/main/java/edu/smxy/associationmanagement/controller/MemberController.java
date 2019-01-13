package edu.smxy.associationmanagement.controller;

import com.xuxueli.poi.excel.ExcelExportUtil;
import com.xuxueli.poi.excel.ExcelImportUtil;
import edu.smxy.associationmanagement.domain.Association;
import edu.smxy.associationmanagement.domain.JSONResult;
import edu.smxy.associationmanagement.domain.Member;
import edu.smxy.associationmanagement.domain.MemberResult;
import edu.smxy.associationmanagement.services.association.AssociationService;
import edu.smxy.associationmanagement.services.member.MemberService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: associationmanagement
 * @description: SDH
 * @author: SDH
 * @create: 2019-01-12 01:25
 **/
@RestController
@ResponseBody
@EnableAutoConfiguration
public class MemberController {
    @Autowired
    MemberService memberService;
    @Autowired
    AssociationService associationService;

    @RequestMapping("/getAllMemberByAssId")
    public JSONResult getAllMemberByAssId(Integer id) {
        List<Member> members = memberService.getAllMemberByAssociationId(id);
        return JSONResult.build(200, "ok", members);
    }

    @RequestMapping("/getAllMembertoExcelByid")
    public String getAllMembertoExcel(Integer id, HttpServletResponse response) {
        List<Member> members = memberService.getAllMemberByAssociationId(id);
        List<MemberResult> memberResults = new ArrayList<>();
        for (Member i : members) {
            MemberResult memberResult = new MemberResult(i);
            Association association = associationService.selectByPrimaryKey(i.getAssociationid());
            memberResult.setAssociationname(association.getAssociationName());
            memberResults.add(memberResult);
        }
        String name = associationService.selectByPrimaryKey(id).getAssociationName();
        if (memberResults.size() > 0) {
            ExcelExportUtil.exportToFile(memberResults, "D://" + name + ".xls");
            try (
                    InputStream inputStream = new FileInputStream(new File("D://" + name + ".xls"));
                    OutputStream outputStream = response.getOutputStream();
            ) {
                //指明为下载
                response.setContentType("application/x-download");
                // 设置文件名
                response.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(name + ".xls", "UTF-8"));
                //把输入流copy到输出流
                IOUtils.copy(inputStream, outputStream);
                outputStream.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "ok";
        } else {
            return "无成员";
        }

    }

    @RequestMapping("/uploadFiletoMember")
    public JSONResult uploadFiletoMember(@RequestParam("file") MultipartFile file){
        String filename = file.getOriginalFilename();
        String path = "G:\\upload\\";
        try {
            file.transferTo(new File(path + filename));
            List<Object> memberResults = ExcelImportUtil.importExcel(MemberResult.class, path+filename);
            for (Object i : memberResults){
                MemberResult memberResult = (MemberResult) i;
                memberService.insertbyexcel(memberResult);
            }
            return  JSONResult.build(200, "ok", null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  JSONResult.build(500, "error", null);
    }
}
