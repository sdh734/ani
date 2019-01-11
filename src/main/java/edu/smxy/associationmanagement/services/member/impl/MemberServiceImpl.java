package edu.smxy.associationmanagement.services.member.impl;

import edu.smxy.associationmanagement.domain.Member;
import edu.smxy.associationmanagement.domain.MemberResult;
import edu.smxy.associationmanagement.mapper.MemberMapper;
import edu.smxy.associationmanagement.services.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: associationmanagement
 * @description: SDH
 * @author: SDH
 * @create: 2019-01-12 01:22
 **/
@Service(value = "MemberService")
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberMapper memberMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return memberMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Member record) {
        return memberMapper.insert(record);
    }

    @Override
    public int insertSelective(Member record) {
        return memberMapper.insertSelective(record);
    }

    @Override
    public Member selectByPrimaryKey(Integer id) {
        return memberMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Member record) {
        return memberMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Member record) {
        return memberMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Member> getAllMemberByAssociationId(Integer id) {
        return memberMapper.getAllMemberByAssociationId(id);
    }

    @Override
    public int insertbyexcel(MemberResult result) {
        return memberMapper.insertbyexcel(result);
    }
}
