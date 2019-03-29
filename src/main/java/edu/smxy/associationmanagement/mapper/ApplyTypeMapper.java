package edu.smxy.associationmanagement.mapper;

import edu.smxy.associationmanagement.domain.ApplyType;

/**
 * 申请类型 Mapper
 */
public interface ApplyTypeMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(ApplyType record);

	int insertSelective(ApplyType record);

	ApplyType selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(ApplyType record);

	int updateByPrimaryKey(ApplyType record);

	ApplyType isInTime(Integer id);
}
