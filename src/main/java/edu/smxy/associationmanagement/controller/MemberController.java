package edu.smxy.associationmanagement.controller;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import com.xuxueli.poi.excel.ExcelExportUtil;
import edu.smxy.associationmanagement.domain.Association;
import edu.smxy.associationmanagement.domain.JSONResult;
import edu.smxy.associationmanagement.domain.Member;
import edu.smxy.associationmanagement.domain.MemberResult;
import edu.smxy.associationmanagement.services.association.AssociationService;
import edu.smxy.associationmanagement.services.member.MemberService;
import edu.smxy.associationmanagement.utils.ExcelListener;
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
 * @author SDH
 */
@RestController
@ResponseBody
@EnableAutoConfiguration
public class MemberController {
    @Autowired
    MemberService memberService;
    @Autowired
    AssociationService associationService;

    @RequestMapping({"/getAllMemberByAssId"})
    public JSONResult getAllMemberByAssId(final Integer id) {
        final List<Member> members = this.memberService.getAllMemberByAssociationId(id);
        return JSONResult.build(200, "ok", members);
    }

    /**
     * 获得指定协会的所有协会成员excel表
     *
     * @param id
     * @param response
     * @return
     */
    @RequestMapping({"/getAllMembertoExcelByid"})
    public String getAllMembertoExcel(final Integer id, final HttpServletResponse response) {
        final List<Member> members = this.memberService.getAllMemberByAssociationId(id);
        final List<MemberResult> memberResults = new ArrayList<>();
        for (final Member i : members) {
            final MemberResult memberResult = new MemberResult(i);
            final Association association =
                    this.associationService.selectByPrimaryKey(i.getAssociationid());
            memberResult.setAssociationname(association.getAssociationName());
            memberResults.add(memberResult);
        }
        final String name = this.associationService.selectByPrimaryKey(id).getAssociationName();
        if (memberResults.size() > 0) {
            // 本地目录
            String path = "G:\\upload\\exceltemp\\";
            // 服务器目录
            // String path = ""/www/wwwroot/ass/upload/";
            ExcelExportUtil.exportToFile(memberResults, path + name + ".xls");
            File file = new File(path + name + ".xls");
            try (final InputStream inputStream = new FileInputStream(file);
                 final OutputStream outputStream = response.getOutputStream()) {
                response.setContentType("application/x-download");
                response.addHeader(
                        "Content-Disposition",
                        "attachment;fileName=" + URLEncoder.encode(name + ".xls", "UTF-8"));
                IOUtils.copy(inputStream, outputStream);
                outputStream.flush();
            } catch (IOException e2) {
                e2.printStackTrace();
            } finally {
                if (file.exists()) {
                    boolean delete = file.delete();
                }
            }
            return "ok";
        }
        return "无成员";
    }

    /**
     * 上传excel文件导入协会成员
     *
     * @param file
     * @return
     */
    @RequestMapping({"/uploadFiletoMember"})
    public JSONResult uploadFiletoMember(@RequestParam("file") final MultipartFile file) {
        final String filename = file.getOriginalFilename();
        // 本地目录
        String path = "G:\\upload\\exceltemp\\";
        File file2 = null;
        // 服务器目录
        // String path = ""/www/wwwroot/ass/upload/";
        try {
            file.transferTo(new File(path + filename));
            file2 = new File(path + filename);
            final InputStream inputStream = new FileInputStream(file2);
            EasyExcelFactory.readBySax(
                    new BufferedInputStream(inputStream), new Sheet(1, 1), new ExcelListener());
            return JSONResult.build(200, "ok", null);
        } catch (IOException e) {
            e.printStackTrace();
            return JSONResult.build(500, "error", null);
        } finally {
            if (file2 != null) {
                boolean delete = file2.delete();
            }
        }
  }
}
