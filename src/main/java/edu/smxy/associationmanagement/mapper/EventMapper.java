package edu.smxy.associationmanagement.mapper;

import java.util.*;
import edu.smxy.associationmanagement.domain.*;

public interface EventMapper
{
    int deleteByPrimaryKey(final Integer eventid);
    
    int insert(final Event record);
    
    int insertFile(final Event record);
    
    int insertSelective(final Event record);
    
    Event selectByPrimaryKey(final Integer eventid);
    
    int updateByPrimaryKeySelective(final Event record);
    
    int updateByPrimaryKey(final Event record);
    
    List<Event> getAllEvent();
    
    List<Event> getAllEventInTime();
    
    List<Event> getAllFileNosubmit(final Integer assid);
    
    List<Event> getAllFileEvent();
    
    List<Association> getAllAssWithoutSubmitByEventId(final Integer eventid);
}
