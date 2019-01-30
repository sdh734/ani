package edu.smxy.associationmanagement.controller;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.event.AnalysisEventListener;
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

    @RequestMapping({"/getAllMembertoExcelByid"})
    public String getAllMembertoExcel(final Integer id, final HttpServletResponse response) {
        final List<Member> members = this.memberService.getAllMemberByAssociationId(id);
        final List<MemberResult> memberResults = new ArrayList<MemberResult>();
        for (final Member i : members) {
            final MemberResult memberResult = new MemberResult(i);
            final Association association = this.associationService.selectByPrimaryKey(i.getAssociationid());
            memberResult.setAssociationname(association.getAssociationName());
            memberResults.add(memberResult);
        }
        final String name = this.associationService.selectByPrimaryKey(id).getAssociationName();
        if (memberResults.size() > 0) {
            ExcelExportUtil.exportToFile((List) memberResults, "/www/wwwroot/ass/upload/" + name + ".xls");
            try (final InputStream inputStream = new FileInputStream(new File("/www/wwwroot/ass/upload/" + name + ".xls"));
                 final OutputStream outputStream = (OutputStream) response.getOutputStream()) {
                response.setContentType("application/x-download");
                response.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(name + ".xls", "UTF-8"));
                IOUtils.copy(inputStream, outputStream);
                outputStream.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            return "ok";
        }
        return "\u65e0\u6210\u5458";
    }

    @RequestMapping({"/uploadFiletoMember"})
    public JSONResult uploadFiletoMember(@RequestParam("file") final MultipartFile file) {
        final String filename = file.getOriginalFilename();
        final String path = "/www/wwwroot/ass/upload/";
        try {
            file.transferTo(new File(path + filename));
            final File file2 = new File(path + filename);
            final InputStream inputStream = new FileInputStream(file2);
            EasyExcelFactory.readBySax((InputStream) new BufferedInputStream(inputStream), new Sheet(1, 1), (AnalysisEventListener) new ExcelListener());
            return JSONResult.build(200, "ok", null);
        } catch (IOException e) {
            e.printStackTrace();
            return JSONResult.build(500, "error", null);
        }
    }
}
