package edu.smxy.associationmanagement.services.event;

import edu.smxy.associationmanagement.domain.Event;

import java.util.List;

public interface EventService {
    int deleteByPrimaryKey(Integer eventid);

    int insert(Event record);

    int insertSelective(Event record);

    Event selectByPrimaryKey(Integer eventid);

    int updateByPrimaryKeySelective(Event record);

    int updateByPrimaryKey(Event record);

    List<Event> getAllEvent();

    List<Event> getAllEventInTime();
}
