package edu.smxy.associationmanagement.mapper;

import edu.smxy.associationmanagement.domain.ApplyType;
import edu.smxy.associationmanagement.domain.File;

/** 申请类型 Mapper */
public interface ApplyTypeMapper {
  int deleteByPrimaryKey(Integer id);

  int insert(ApplyType record);

  int insertSelective(ApplyType record);

  ApplyType selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(ApplyType record);

  int updateByPrimaryKey(ApplyType record);

  ApplyType isInTime(Integer id);

  ApplyType getTemplateFileById(Integer applytype);
}
