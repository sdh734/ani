package edu.smxy.associationmanagement.mapper;

import edu.smxy.associationmanagement.domain.Event;

import java.util.List;

public interface EventMapper {
    int deleteByPrimaryKey(Integer eventid);

    int insert(Event record);

    int insertSelective(Event record);

    Event selectByPrimaryKey(Integer eventid);

    int updateByPrimaryKeySelective(Event record);

    int updateByPrimaryKey(Event record);

    List<Event> getAllEvent();

    List<Event> getAllEventInTime();
}