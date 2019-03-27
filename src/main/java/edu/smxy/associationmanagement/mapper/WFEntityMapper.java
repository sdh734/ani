package edu.smxy.associationmanagement.mapper;

import edu.smxy.associationmanagement.domain.WFEntity;

import java.util.List;

public interface WFEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WFEntity record);

    int insertSelective(WFEntity record);

    WFEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WFEntity record);

    int updateByPrimaryKey(WFEntity record);

	List<WFEntity> getRunningApply(int typeid, int authorid);
}