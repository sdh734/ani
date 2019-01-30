package edu.smxy.associationmanagement.services.association.impl;

import edu.smxy.associationmanagement.services.association.*;
import org.springframework.stereotype.*;
import edu.smxy.associationmanagement.mapper.*;
import org.springframework.beans.factory.annotation.*;
import edu.smxy.associationmanagement.domain.*;
import java.util.*;

@Service("AssociationService")
public class AssociationServiceImpl implements AssociationService
{
    @Autowired
    AssociationMapper associationMapper;
    
    @Override
    public int deleteByPrimaryKey(final Integer associationid) {
        return this.associationMapper.deleteByPrimaryKey(associationid);
    }
    
    @Override
    public int insert(final Association record) {
        return this.associationMapper.insert(record);
    }
    
    @Override
    public int insertSelective(final Association record) {
        return this.associationMapper.insertSelective(record);
    }
    
    @Override
    public Association selectByPrimaryKey(final Integer associationid) {
        return this.associationMapper.selectByPrimaryKey(associationid);
    }
    
    @Override
    public int updateByPrimaryKeySelective(final Association record) {
        return this.associationMapper.updateByPrimaryKeySelective(record);
    }
    
    @Override
    public int updateByPrimaryKey(final Association record) {
        return this.associationMapper.updateByPrimaryKey(record);
    }
    
    @Override
    public List<Association> getAll() {
        return this.associationMapper.getAll();
    }
    
    @Override
    public Association query(final Association association) {
        return this.associationMapper.query(association);
    }
}
