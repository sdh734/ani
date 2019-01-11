package edu.smxy.associationmanagement.services.event.impl;

import edu.smxy.associationmanagement.domain.Event;
import edu.smxy.associationmanagement.mapper.EventMapper;
import edu.smxy.associationmanagement.services.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: associationmanagement
 * @author: SDH
 * @create: 2019-01-10 16:25
 **/
@Service(value = "EventService")
public class EventServiceImpl implements EventService {
    @Autowired
    EventMapper eventMapper;
    @Override
    public int deleteByPrimaryKey(Integer eventid) {
        return eventMapper.deleteByPrimaryKey(eventid);
    }

    @Override
    public int insert(Event record) {
        return eventMapper.insert(record);
    }

    @Override
    public int insertSelective(Event record) {
        return eventMapper.insertSelective(record);
    }

    @Override
    public Event selectByPrimaryKey(Integer eventid) {
        return eventMapper.selectByPrimaryKey(eventid);
    }

    @Override
    public int updateByPrimaryKeySelective(Event record) {
        return eventMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Event record) {
        return eventMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Event> getAllEvent() {
        return eventMapper.getAllEvent();
    }

    @Override
    public List<Event> getAllEventInTime() {
        return eventMapper.getAllEventInTime();
    }
}
