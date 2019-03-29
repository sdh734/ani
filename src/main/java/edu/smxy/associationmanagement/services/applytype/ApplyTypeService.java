package edu.smxy.associationmanagement.services.applytype;

import edu.smxy.associationmanagement.domain.ApplyType;

/**
 * 申请类型Service接口
 */
public interface ApplyTypeService {
  int deleteByPrimaryKey(Integer id);

  int insert(ApplyType record);

  int insertSelective(ApplyType record);

  ApplyType selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(ApplyType record);

  int updateByPrimaryKey(ApplyType record);

  ApplyType isInTime(Integer id);
}
