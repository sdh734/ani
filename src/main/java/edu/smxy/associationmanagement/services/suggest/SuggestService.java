package edu.smxy.associationmanagement.services.suggest;

import edu.smxy.associationmanagement.domain.Suggest;

import java.util.List;

public interface SuggestService {
    int deleteByPrimaryKey(Integer id);

    int insert(Suggest record);

    int insertSelective(Suggest record);

    Suggest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Suggest record);

    int updateByPrimaryKeyWithBLOBs(Suggest record);

    int updateByPrimaryKey(Suggest record);

    List<Suggest> getAllSuggestByAuthorId(Integer authorid);

    List<Suggest> getAllSuggestByReceiveId(Integer receiveid);
}
