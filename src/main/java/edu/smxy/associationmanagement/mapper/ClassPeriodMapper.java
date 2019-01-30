package edu.smxy.associationmanagement.mapper;

import edu.smxy.associationmanagement.domain.*;
import java.util.*;

public interface ClassPeriodMapper
{
    int deleteByPrimaryKey(final Integer classperiodId);
    
    int insert(final ClassPeriod record);
    
    int insertSelective(final ClassPeriod record);
    
    ClassPeriod selectByPrimaryKey(final Integer classperiodId);
    
    int updateByPrimaryKeySelective(final ClassPeriod record);
    
    int updateByPrimaryKey(final ClassPeriod record);
    
    List<ClassPeriod> getAllByAssid(final int Assid);
}
