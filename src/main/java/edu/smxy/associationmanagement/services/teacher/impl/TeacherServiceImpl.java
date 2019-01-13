package edu.smxy.associationmanagement.services.teacher.impl;

import edu.smxy.associationmanagement.domain.Teacher;
import edu.smxy.associationmanagement.mapper.TeacherMapper;
import edu.smxy.associationmanagement.services.teacher.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: associationmanagement
 * @description: SDH
 * @author: SDH
 * @create: 2019-01-12 16:16
 **/
@Service(value = "TeacherService")
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherMapper teacherMapper;

    @Override
    public int deleteByPrimaryKey(Integer teacherId) {
        return teacherMapper.deleteByPrimaryKey(teacherId);
    }

    @Override
    public int insert(Teacher record) {
        return teacherMapper.insert(record);
    }

    @Override
    public int insertSelective(Teacher record) {
        return teacherMapper.insertSelective(record);
    }

    @Override
    public Teacher selectByPrimaryKey(Integer teacherId) {
        return teacherMapper.selectByPrimaryKey(teacherId);
    }

    @Override
    public int updateByPrimaryKeySelective(Teacher record) {
        return teacherMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Teacher record) {
        return teacherMapper.updateByPrimaryKey(record);
    }

    @Override
    public Teacher query(Teacher teacher) {
        return teacherMapper.query(teacher);
    }
}
