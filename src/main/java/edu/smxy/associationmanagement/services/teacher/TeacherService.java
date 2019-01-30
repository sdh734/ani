package edu.smxy.associationmanagement.services.teacher;

import edu.smxy.associationmanagement.domain.*;

public interface TeacherService
{
    int deleteByPrimaryKey(final Integer teacherId);
    
    int insert(final Teacher record);
    
    int insertSelective(final Teacher record);
    
    Teacher selectByPrimaryKey(final Integer teacherId);
    
    int updateByPrimaryKeySelective(final Teacher record);
    
    int updateByPrimaryKey(final Teacher record);
    
    Teacher query(final Teacher teacher);
}
