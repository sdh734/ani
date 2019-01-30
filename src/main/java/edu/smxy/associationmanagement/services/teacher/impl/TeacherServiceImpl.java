package edu.smxy.associationmanagement.services.teacher.impl;

import edu.smxy.associationmanagement.services.teacher.*;
import org.springframework.stereotype.*;
import edu.smxy.associationmanagement.mapper.*;
import org.springframework.beans.factory.annotation.*;
import edu.smxy.associationmanagement.domain.*;

@Service("TeacherService")
public class TeacherServiceImpl implements TeacherService
{
    @Autowired
    TeacherMapper teacherMapper;
    
    public int deleteByPrimaryKey(final Integer teacherId) {
        return this.teacherMapper.deleteByPrimaryKey(teacherId);
    }
    
    public int insert(final Teacher record) {
        return this.teacherMapper.insert(record);
    }
    
    public int insertSelective(final Teacher record) {
        return this.teacherMapper.insertSelective(record);
    }
    
    public Teacher selectByPrimaryKey(final Integer teacherId) {
        return this.teacherMapper.selectByPrimaryKey(teacherId);
    }
    
    public int updateByPrimaryKeySelective(final Teacher record) {
        return this.teacherMapper.updateByPrimaryKeySelective(record);
    }
    
    public int updateByPrimaryKey(final Teacher record) {
        return this.teacherMapper.updateByPrimaryKey(record);
    }
    
    public Teacher query(final Teacher teacher) {
        return this.teacherMapper.query(teacher);
    }
}
