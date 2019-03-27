package edu.smxy.associationmanagement.services.applytype.impl;

import edu.smxy.associationmanagement.domain.ApplyType;
import edu.smxy.associationmanagement.mapper.ApplyTypeMapper;
import edu.smxy.associationmanagement.services.applytype.ApplyTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: associationmanagement
 * @description: 申请类型实现类
 * @author: SDH
 * @create: 2019-03-27 19:56
 **/
@Service("ApplyTypeService")
public class ApplyTypeServiceImpl implements ApplyTypeService {
    @Autowired
    ApplyTypeMapper applyTypeMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return applyTypeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ApplyType record) {
        return applyTypeMapper.insert(record);
    }

    @Override
    public int insertSelective(ApplyType record) {
        return applyTypeMapper.insertSelective(record);
    }

    @Override
    public ApplyType selectByPrimaryKey(Integer id) {
        return applyTypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ApplyType record) {
        return applyTypeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ApplyType record) {
        return applyTypeMapper.updateByPrimaryKey(record);
    }
}
