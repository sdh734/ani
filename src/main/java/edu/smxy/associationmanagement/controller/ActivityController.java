package edu.smxy.associationmanagement.controller;

import org.springframework.boot.autoconfigure.*;
import edu.smxy.associationmanagement.services.activity.*;
import org.springframework.beans.factory.annotation.*;
import edu.smxy.associationmanagement.services.association.*;
import javax.servlet.http.*;
import java.text.*;
import org.springframework.web.bind.annotation.*;
import edu.smxy.associationmanagement.domain.*;
import java.util.*;

@RestController
@ResponseBody
@EnableAutoConfiguration
public class ActivityController
{
    @Autowired
    ActivityService activityService;
    @Autowired
    AssociationService associationService;
    
    @RequestMapping({ "/addActivity" })
    public JSONResult addActivity(final HttpServletRequest request) {
        final String activityname = request.getParameter("activityname");
        final String activitylocation = request.getParameter("activitylocation");
        String activitytime = request.getParameter("activitytime");
        activitytime = activitytime.replace("T", " ");
        final String activityorganizer = request.getParameter("activityorganizer");
        final String assid = request.getParameter("assid");
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        final Date date = simpleDateFormat.parse(activitytime, new ParsePosition(0));
        final Activity activity = new Activity();
        activity.setActivityAssid(Integer.valueOf(assid));
        activity.setActivityLocation(activitylocation);
        activity.setActivityName(activityname);
        activity.setActivityOrganizer(activityorganizer);
        activity.setActivityTime(date);
        this.activityService.insert(activity);
        return JSONResult.build(200, "ok", null);
    }
    
    @RequestMapping({ "/getAllActivity" })
    public JSONResult getAllActivity() {
        final List<Activity> activities = (List<Activity>)this.activityService.getAllActivity();
        final List<ActivityResult> activityResults = new ArrayList<ActivityResult>();
        for (final Activity activity : activities) {
            final ActivityResult activityResult = new ActivityResult(activity);
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            activityResult.setActivityTime(simpleDateFormat.format(activity.getActivityTime()));
            activityResult.setAssname(this.associationService.selectByPrimaryKey(activity.getActivityAssid()).getAssociationName());
            activityResults.add(activityResult);
        }
        return JSONResult.build(200, "ok", activityResults);
    }
    
    @RequestMapping({ "/getAllActivityByAssId" })
    public JSONResult getAllActivityByAssId(final int assid) {
        final List<Activity> activities = (List<Activity>)this.activityService.getAllActivityByAssid(assid);
        final List<ActivityResult> activityResults = new ArrayList<ActivityResult>();
        for (final Activity activity : activities) {
            final ActivityResult activityResult = new ActivityResult(activity);
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            activityResult.setActivityTime(simpleDateFormat.format(activity.getActivityTime()));
            activityResult.setAssname(this.associationService.selectByPrimaryKey(activity.getActivityAssid()).getAssociationName());
            activityResults.add(activityResult);
        }
        return JSONResult.build(200, "ok", activityResults);
    }
    
    @RequestMapping({ "/updateActivity" })
    public JSONResult updateActivity(final HttpServletRequest request) {
        final Activity activity = new Activity();
        activity.setId(Integer.valueOf(request.getParameter("id")));
        activity.setActivityOrganizer(request.getParameter("activityOrganizer"));
        activity.setActivityName(request.getParameter("activityName"));
        activity.setActivityAssid(Integer.valueOf(request.getParameter("activityAssid")));
        activity.setActivityLocation(request.getParameter("activityLocation"));
        activity.setActivityTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(request.getParameter("activityTime").replace("T", " "), new ParsePosition(0)));
        final int result = this.activityService.updateByPrimaryKey(activity);
        if (result > 0) {
            return JSONResult.build(200, "ok", null);
        }
        return JSONResult.build(500, "error", null);
    }
    
    @RequestMapping({ "/deleteActivityByid" })
    public JSONResult deleteActivityByid(final int id) {
        final int result = this.activityService.deleteByPrimaryKey(id);
        if (result > 0) {
            return JSONResult.build(200, "ok", null);
        }
        return JSONResult.build(500, "error", null);
    }
}
