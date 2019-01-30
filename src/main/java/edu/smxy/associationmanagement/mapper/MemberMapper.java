package edu.smxy.associationmanagement.mapper;

import java.util.*;
import edu.smxy.associationmanagement.domain.*;

public interface MemberMapper
{
    int deleteByPrimaryKey(final Integer id);
    
    int insert(final Member record);
    
    int insertSelective(final Member record);
    
    Member selectByPrimaryKey(final Integer id);
    
    int updateByPrimaryKeySelective(final Member record);
    
    int updateByPrimaryKey(final Member record);
    
    List<Member> getAllMemberByAssociationId(final Integer id);
    
    int insertbyexcel(final MemberResult result);
}
