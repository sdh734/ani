package edu.smxy.associationmanagement.services.member.impl;

import edu.smxy.associationmanagement.services.member.*;
import org.springframework.stereotype.*;
import edu.smxy.associationmanagement.mapper.*;
import org.springframework.beans.factory.annotation.*;
import java.util.*;
import edu.smxy.associationmanagement.domain.*;

@Service("MemberService")
public class MemberServiceImpl implements MemberService
{
    @Autowired
    MemberMapper memberMapper;
    
    public int deleteByPrimaryKey(final Integer id) {
        return this.memberMapper.deleteByPrimaryKey(id);
    }
    
    public int insert(final Member record) {
        return this.memberMapper.insert(record);
    }
    
    public int insertSelective(final Member record) {
        return this.memberMapper.insertSelective(record);
    }
    
    public Member selectByPrimaryKey(final Integer id) {
        return this.memberMapper.selectByPrimaryKey(id);
    }
    
    public int updateByPrimaryKeySelective(final Member record) {
        return this.memberMapper.updateByPrimaryKeySelective(record);
    }
    
    public int updateByPrimaryKey(final Member record) {
        return this.memberMapper.updateByPrimaryKey(record);
    }
    
    public List<Member> getAllMemberByAssociationId(final Integer id) {
        return (List<Member>)this.memberMapper.getAllMemberByAssociationId(id);
    }
    
    public int insertbyexcel(final MemberResult result) {
        return this.memberMapper.insertbyexcel(result);
    }
}
