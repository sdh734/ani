package edu.smxy.associationmanagement.services.program;

import edu.smxy.associationmanagement.domain.Program;

import java.util.List;

public interface ProgramService {
	int deleteByPrimaryKey(final Integer id);

	int insert(final Program record);

	int insertSelective(final Program record);

	Program selectByPrimaryKey(final Integer id);

	int updateByPrimaryKeySelective(final Program record);

	int updateByPrimaryKey(final Program record);

	List<Program> getProgramByEventId(final int eventid);

	Program getProgramByEventIdAndAssId(Program program);
}
