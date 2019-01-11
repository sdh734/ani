package edu.smxy.associationmanagement.mapper;

import edu.smxy.associationmanagement.domain.Member;
import edu.smxy.associationmanagement.domain.MemberResult;

import java.util.List;

public interface MemberMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Member record);

    int insertSelective(Member record);

    Member selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);

    List<Member> getAllMemberByAssociationId(Integer id);

    int insertbyexcel(MemberResult result);
}