package edu.smxy.associationmanagement.services.classperiod.impl;

import edu.smxy.associationmanagement.services.classperiod.*;
import org.springframework.stereotype.*;
import edu.smxy.associationmanagement.mapper.*;
import org.springframework.beans.factory.annotation.*;
import edu.smxy.associationmanagement.domain.*;
import java.util.*;

@Service("ClassPeriodService")
public class ClassPeriodServiceImpl implements ClassPeriodService
{
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
        return (List<ClassPeriod>)this.classPeriodMapper.getAllByAssid(Assid);
    }
}
