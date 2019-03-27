package edu.smxy.associationmanagement.services.wfentity.impl;

import edu.smxy.associationmanagement.domain.WFEntity;
import edu.smxy.associationmanagement.mapper.WFEntityMapper;
import edu.smxy.associationmanagement.services.wfentity.WFEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: associationmanagement
 * @description: 流程实体类实现类
 * @author: SDH
 * @create: 2019-03-27 20:14
 **/
@Service("WFEntityService")
public class WFEntityServiceImpl implements WFEntityService {
    @Autowired
    WFEntityMapper wfEntityMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return wfEntityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(WFEntity record) {
        return wfEntityMapper.insert(record);
    }

    @Override
    public int insertSelective(WFEntity record) {
        return wfEntityMapper.insertSelective(record);
    }

    @Override
    public WFEntity selectByPrimaryKey(Integer id) {
        return wfEntityMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(WFEntity record) {
        return wfEntityMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(WFEntity record) {
        return wfEntityMapper.updateByPrimaryKey(record);
    }
}
