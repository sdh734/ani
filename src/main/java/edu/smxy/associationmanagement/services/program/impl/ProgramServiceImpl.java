package edu.smxy.associationmanagement.services.program.impl;

import edu.smxy.associationmanagement.domain.Program;
import edu.smxy.associationmanagement.mapper.ProgramMapper;
import edu.smxy.associationmanagement.services.program.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ProgramService")
public class ProgramServiceImpl implements ProgramService {
	@Autowired
	ProgramMapper programMapper;

	public int deleteByPrimaryKey(final Integer id) {
		return this.programMapper.deleteByPrimaryKey(id);
	}

	public int insert(final Program record) {
		return this.programMapper.insert(record);
	}

	public int insertSelective(final Program record) {
		return this.programMapper.insertSelective(record);
	}

	public Program selectByPrimaryKey(final Integer id) {
		return this.programMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(final Program record) {
		return this.programMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(final Program record) {
		return this.programMapper.updateByPrimaryKey(record);
	}

	public List<Program> getProgramByEventId(final int eventid) {
		return this.programMapper.getProgramByEventId(eventid);
	}

	@Override
	public Program getProgramByEventIdAndAssId(Program program) {
		return programMapper.getProgramByEventIdAndAssId(program);
	}
}
