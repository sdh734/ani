package edu.smxy.associationmanagement.utils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import edu.smxy.associationmanagement.domain.MemberResult;
import edu.smxy.associationmanagement.services.member.MemberService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.util.List;

@EnableAutoConfiguration
public class ExcelListener extends AnalysisEventListener {

    MemberService memberService = SpringContextUtils.getBean(MemberService.class);

    @Override
    public void invoke(final Object result, final AnalysisContext analysisContext) {
        final MemberResult memberResult = new MemberResult();
        final List<String> list = (List<String>) result;
        memberResult.setAssociationname((String) list.get(0));
        memberResult.setName((String) list.get(1));
        memberResult.setGender((String) list.get(2));
        memberResult.setCollege((String) list.get(3));
        memberResult.setStudentid((String) list.get(4));
        memberResult.setPhone((String) list.get(5));
        memberResult.setDepartment((String) list.get(6));
        memberResult.setPosition((String) list.get(7));
        this.memberService.insertbyexcel(memberResult);
    }

    @Override
    public void doAfterAllAnalysed(final AnalysisContext analysisContext) {
    }
}
