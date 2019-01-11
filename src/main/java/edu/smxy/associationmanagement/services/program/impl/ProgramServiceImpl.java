package edu.smxy.associationmanagement.services.program.impl;

import edu.smxy.associationmanagement.domain.Program;
import edu.smxy.associationmanagement.mapper.ProgramMapper;
import edu.smxy.associationmanagement.services.program.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: associationmanagement
 * @description: SDH
 * @author: SDH
 * @create: 2019-01-11 20:12
 **/
@Service(value = "ProgramService")
public class ProgramServiceImpl implements ProgramService {
    @Autowired
    ProgramMapper programMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return programMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Program record) {
        return programMapper.insert(record);
    }

    @Override
    public int insertSelective(Program record) {
        return programMapper.insertSelective(record);
    }

    @Override
    public Program selectByPrimaryKey(Integer id) {
        return programMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Program record) {
        return programMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Program record) {
        return programMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Program> getProgramByEventId(int eventid) {
        return programMapper.getProgramByEventId(eventid);
    }
}
