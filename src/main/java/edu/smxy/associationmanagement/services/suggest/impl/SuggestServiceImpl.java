package edu.smxy.associationmanagement.services.suggest.impl;

import edu.smxy.associationmanagement.domain.Suggest;
import edu.smxy.associationmanagement.mapper.SuggestMapper;
import edu.smxy.associationmanagement.services.suggest.SuggestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: associationmanagement
 * @description: SDH
 * @author: SDH
 * @create: 2019-01-13 05:02
 **/
@Service(value = "SuggestService")
public class SuggestServiceImpl implements SuggestService {
    @Autowired
    SuggestMapper suggestMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return suggestMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Suggest record) {
        return suggestMapper.insert(record);
    }

    @Override
    public int insertSelective(Suggest record) {
        return suggestMapper.insertSelective(record);
    }

    @Override
    public Suggest selectByPrimaryKey(Integer id) {
        return suggestMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Suggest record) {
        return suggestMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(Suggest record) {
        return suggestMapper.updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public int updateByPrimaryKey(Suggest record) {
        return suggestMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Suggest> getAllSuggestByAuthorId(Integer authorid) {
        return suggestMapper.getAllSuggestByAuthorId(authorid);
    }

    @Override
    public List<Suggest> getAllSuggestByReceiveId(Integer receiveid) {
        return suggestMapper.getAllSuggestByReceiveId(receiveid);
    }
}
