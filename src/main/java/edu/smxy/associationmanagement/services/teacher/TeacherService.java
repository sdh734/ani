package edu.smxy.associationmanagement.services.teacher;

import edu.smxy.associationmanagement.domain.Teacher;

public interface TeacherService {
    int deleteByPrimaryKey(Integer teacherId);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(Integer teacherId);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);

    Teacher query(Teacher teacher);
}
