package edu.smxy.associationmanagement.services.activity.impl;

import edu.smxy.associationmanagement.services.activity.*;
import org.springframework.stereotype.*;
import edu.smxy.associationmanagement.mapper.*;
import org.springframework.beans.factory.annotation.*;
import edu.smxy.associationmanagement.domain.*;
import java.util.*;

@Service("ActivityService")
public class ActivityServiceImpl implements ActivityService
{
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
