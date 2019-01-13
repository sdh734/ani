package edu.smxy.associationmanagement.services.activity;

import edu.smxy.associationmanagement.domain.Activity;

import java.util.List;

public interface ActivityService {
    int deleteByPrimaryKey(Integer id);

    int insert(Activity record);

    int insertSelective(Activity record);

    Activity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);

    List<Activity> getAllActivityByAssid(Integer assid);

    List<Activity> getAllActivity();
}
