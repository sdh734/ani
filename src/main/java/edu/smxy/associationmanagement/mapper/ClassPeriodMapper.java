package edu.smxy.associationmanagement.mapper;

import edu.smxy.associationmanagement.domain.ClassPeriod;

public interface ClassPeriodMapper {
    int deleteByPrimaryKey(Integer classperiodId);

    int insert(ClassPeriod record);

    int insertSelective(ClassPeriod record);

    ClassPeriod selectByPrimaryKey(Integer classperiodId);

    int updateByPrimaryKeySelective(ClassPeriod record);

    int updateByPrimaryKey(ClassPeriod record);
}