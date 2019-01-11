package edu.smxy.associationmanagement.services.program;

import edu.smxy.associationmanagement.domain.Program;

import java.util.List;

public interface ProgramService {
    int deleteByPrimaryKey(Integer id);

    int insert(Program record);

    int insertSelective(Program record);

    Program selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Program record);

    int updateByPrimaryKey(Program record);
    List<Program> getProgramByEventId(int eventid);
}
