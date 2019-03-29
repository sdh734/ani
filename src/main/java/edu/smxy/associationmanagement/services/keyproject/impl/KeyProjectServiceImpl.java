package edu.smxy.associationmanagement.services.keyproject.impl;

import edu.smxy.associationmanagement.domain.KeyProject;
import edu.smxy.associationmanagement.mapper.KeyProjectMapper;
import edu.smxy.associationmanagement.services.keyproject.KeyProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: associationmanagement
 * @description: 重点立项实现类
 * @author: SDH
 * @create: 2019-03-27 19:50
 */
@Service("KeyProjectService")
public class KeyProjectServiceImpl implements KeyProjectService {
	@Autowired
	KeyProjectMapper keyProjectMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return keyProjectMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(KeyProject record) {
		return keyProjectMapper.insert(record);
	}

	@Override
	public int insertSelective(KeyProject record) {
		return keyProjectMapper.insertSelective(record);
	}

	@Override
	public KeyProject selectByPrimaryKey(Integer id) {
		return keyProjectMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(KeyProject record) {
		return keyProjectMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(KeyProject record) {
		return keyProjectMapper.updateByPrimaryKey(record);
	}
}
