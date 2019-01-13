package edu.smxy.associationmanagement.services.activity.impl;

import edu.smxy.associationmanagement.domain.Activity;
import edu.smxy.associationmanagement.mapper.ActivityMapper;
import edu.smxy.associationmanagement.services.activity.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: associationmanagement
 * @description: SDH
 * @author: SDH
 * @create: 2019-01-13 01:45
 **/
@Service(value = "ActivityService")
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    ActivityMapper activityMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return activityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Activity record) {
        return activityMapper.insert(record);
    }

    @Override
    public int insertSelective(Activity record) {
        return activityMapper.insertSelective(record);
    }

    @Override
    public Activity selectByPrimaryKey(Integer id) {
        return activityMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Activity record) {
        return activityMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Activity record) {
        return activityMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Activity> getAllActivityByAssid(Integer assid) {
        return activityMapper.getAllActivityByAssid(assid);
    }

    @Override
    public List<Activity> getAllActivity() {
        return activityMapper.getAllActivity();
    }
}
