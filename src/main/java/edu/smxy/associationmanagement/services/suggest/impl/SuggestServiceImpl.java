package edu.smxy.associationmanagement.services.suggest.impl;

import edu.smxy.associationmanagement.domain.Suggest;
import edu.smxy.associationmanagement.mapper.SuggestMapper;
import edu.smxy.associationmanagement.services.suggest.SuggestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SuggestService")
public class SuggestServiceImpl implements SuggestService {
	@Autowired
	SuggestMapper suggestMapper;

	public int deleteByPrimaryKey(final Integer id) {
		return this.suggestMapper.deleteByPrimaryKey(id);
	}

	public int insert(final Suggest record) {
		return this.suggestMapper.insert(record);
	}

	public int insertSelective(final Suggest record) {
		return this.suggestMapper.insertSelective(record);
	}

	public Suggest selectByPrimaryKey(final Integer id) {
		return this.suggestMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(final Suggest record) {
		return this.suggestMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKeyWithBLOBs(final Suggest record) {
		return this.suggestMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	public int updateByPrimaryKey(final Suggest record) {
		return this.suggestMapper.updateByPrimaryKey(record);
	}

	public List<Suggest> getAllSuggestByAuthorId(final Integer authorid) {
		return this.suggestMapper.getAllSuggestByAuthorId(authorid);
	}

	public List<Suggest> getAllSuggestByReceiveId(final Integer receiveid) {
		return this.suggestMapper.getAllSuggestByReceiveId(receiveid);
	}
}
