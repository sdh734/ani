package edu.smxy.associationmanagement.mapper;

import edu.smxy.associationmanagement.domain.Program;

import java.util.List;

public interface ProgramMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Program record);

    int insertSelective(Program record);

    Program selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Program record);

    List<Program> getProgramByEventId(int eventid);

    int updateByPrimaryKey(Program record);
}