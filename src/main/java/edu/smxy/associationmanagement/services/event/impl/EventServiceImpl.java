package edu.smxy.associationmanagement.services.event.impl;

import edu.smxy.associationmanagement.domain.Association;
import edu.smxy.associationmanagement.domain.Event;
import edu.smxy.associationmanagement.mapper.EventMapper;
import edu.smxy.associationmanagement.services.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("EventService")
public class EventServiceImpl implements EventService {
    @Autowired
    EventMapper eventMapper;

    @Override
    public int deleteByPrimaryKey(final Integer eventid) {
        return this.eventMapper.deleteByPrimaryKey(eventid);
    }

    @Override
    public int insert(final Event record) {
        return this.eventMapper.insert(record);
    }

    @Override
    public int insertFile(final Event record) {
        return this.eventMapper.insertFile(record);
    }

    @Override
    public int addNotice(Event record) {
        return this.eventMapper.addNotice(record);
    }

    @Override
    public int insertSelective(final Event record) {
        return this.eventMapper.insertSelective(record);
    }

    @Override
    public Event selectByPrimaryKey(final Integer eventid) {
        return this.eventMapper.selectByPrimaryKey(eventid);
    }

    @Override
    public int updateByPrimaryKeySelective(final Event record) {
        return this.eventMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(final Event record) {
        return this.eventMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Event> getAllEvent() {
        return this.eventMapper.getAllEvent();
    }

    @Override
    public List<Event> getAllEventInTime() {
        return this.eventMapper.getAllEventInTime();
    }

    @Override
    public List<Event> getAllFileNosubmit(final Integer assid) {
        return this.eventMapper.getAllFileNosubmit(assid);
    }

    @Override
    public List<Event> getAllFileEvent() {
        return this.eventMapper.getAllFileEvent();
    }

    @Override
    public List<Association> getAllAssWithoutSubmitByEventId(final Integer eventid) {
        return this.eventMapper.getAllAssWithoutSubmitByEventId(eventid);
    }
}
