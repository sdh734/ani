package edu.smxy.associationmanagement.services.classperiod.impl;

import edu.smxy.associationmanagement.domain.ClassPeriod;
import edu.smxy.associationmanagement.mapper.ClassPeriodMapper;
import edu.smxy.associationmanagement.services.classperiod.ClassPeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: associationmanagement
 * @description: SDH
 * @author: SDH
 * @create: 2019-01-12 18:15
 **/
@Service(value = "ClassPeriodService")
public class ClassPeriodServiceImpl implements ClassPeriodService {
    @Autowired
    ClassPeriodMapper classPeriodMapper;

    @Override
    public int deleteByPrimaryKey(Integer classperiodId) {
        return classPeriodMapper.deleteByPrimaryKey(classperiodId);
    }

    @Override
    public int insert(ClassPeriod record) {
        return classPeriodMapper.insert(record);
    }

    @Override
    public int insertSelective(ClassPeriod record) {
        return classPeriodMapper.insertSelective(record);
    }

    @Override
    public ClassPeriod selectByPrimaryKey(Integer classperiodId) {
        return classPeriodMapper.selectByPrimaryKey(classperiodId);
    }

    @Override
    public int updateByPrimaryKeySelective(ClassPeriod record) {
        return classPeriodMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ClassPeriod record) {
        return classPeriodMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<ClassPeriod> getAllByAssid(int Assid) {
        return classPeriodMapper.getAllByAssid(Assid);
    }
}
