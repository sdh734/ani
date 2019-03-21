package edu.smxy.associationmanagement.mapper;

import edu.smxy.associationmanagement.domain.Program;

import java.util.List;

public interface ProgramMapper {
    int deleteByPrimaryKey(final Integer id);

    int insert(final Program record);

    int insertSelective(final Program record);

    Program selectByPrimaryKey(final Integer id);

    int updateByPrimaryKeySelective(final Program record);

    List<Program> getProgramByEventId(final int eventid);

    int updateByPrimaryKey(final Program record);

    Program getProgramByEventIdAndAssId(Program program);
}
