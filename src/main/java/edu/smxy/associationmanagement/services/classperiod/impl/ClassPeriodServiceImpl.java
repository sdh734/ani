package edu.smxy.associationmanagement.services.classperiod.impl;

import edu.smxy.associationmanagement.domain.ClassPeriod;
import edu.smxy.associationmanagement.mapper.ClassPeriodMapper;
import edu.smxy.associationmanagement.services.classperiod.ClassPeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ClassPeriodService")
public class ClassPeriodServiceImpl implements ClassPeriodService {
	@Autowired
	ClassPeriodMapper classPeriodMapper;

	public int deleteByPrimaryKey(final Integer classperiodId) {
		return this.classPeriodMapper.deleteByPrimaryKey(classperiodId);
	}

	public int insert(final ClassPeriod record) {
		return this.classPeriodMapper.insert(record);
	}

	public int insertSelective(final ClassPeriod record) {
		return this.classPeriodMapper.insertSelective(record);
	}

	public ClassPeriod selectByPrimaryKey(final Integer classperiodId) {
		return this.classPeriodMapper.selectByPrimaryKey(classperiodId);
	}

	public int updateByPrimaryKeySelective(final ClassPeriod record) {
		return this.classPeriodMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(final ClassPeriod record) {
		return this.classPeriodMapper.updateByPrimaryKey(record);
	}

	public List<ClassPeriod> getAllByAssid(final int Assid) {
		return this.classPeriodMapper.getAllByAssid(Assid);
	}
}
