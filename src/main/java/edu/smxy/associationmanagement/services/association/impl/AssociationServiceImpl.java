package edu.smxy.associationmanagement.services.association.impl;

import edu.smxy.associationmanagement.domain.Association;
import edu.smxy.associationmanagement.mapper.AssociationMapper;
import edu.smxy.associationmanagement.services.association.AssociationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: associationmanagement
 * @description: SDH
 * @author: SDH
 * @create: 2019-01-11 23:49
 **/
@Service(value = "AssociationService")
public class AssociationServiceImpl implements AssociationService {
    @Autowired
    AssociationMapper associationMapper;
    @Override
    public int deleteByPrimaryKey(Integer associationid) {
        return associationMapper.deleteByPrimaryKey(associationid);
    }

    @Override
    public int insert(Association record) {
        return associationMapper.insert(record);
    }

    @Override
    public int insertSelective(Association record) {
        return associationMapper.insertSelective(record);
    }

    @Override
    public Association selectByPrimaryKey(Integer associationid) {
        return associationMapper.selectByPrimaryKey(associationid);
    }

    @Override
    public int updateByPrimaryKeySelective(Association record) {
        return associationMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Association record) {
        return associationMapper.updateByPrimaryKey(record);
    }
}
