package edu.smxy.associationmanagement.services.activity.impl;

import edu.smxy.associationmanagement.domain.Activity;
import edu.smxy.associationmanagement.mapper.ActivityMapper;
import edu.smxy.associationmanagement.services.activity.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ActivityService")
public class ActivityServiceImpl implements ActivityService {
	@Autowired
	ActivityMapper activityMapper;

	@Override
	public int deleteByPrimaryKey(final Integer id) {
		return this.activityMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(final Activity record) {
		return this.activityMapper.insert(record);
	}

	@Override
	public int insertSelective(final Activity record) {
		return this.activityMapper.insertSelective(record);
	}

	@Override
	public Activity selectByPrimaryKey(final Integer id) {
		return this.activityMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(final Activity record) {
		return this.activityMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(final Activity record) {
		return this.activityMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Activity> getAllActivityByAssid(final Integer assid) {
		return this.activityMapper.getAllActivityByAssid(assid);
	}

	@Override
	public List<Activity> getAllActivity() {
		return this.activityMapper.getAllActivity();
	}
}
