package edu.smxy.associationmanagement.controller;

import edu.smxy.associationmanagement.domain.Activity;
import edu.smxy.associationmanagement.domain.ActivityResult;
import edu.smxy.associationmanagement.domain.JSONResult;
import edu.smxy.associationmanagement.services.activity.ActivityService;
import edu.smxy.associationmanagement.services.association.AssociationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: associationmanagement
 * @description: SDH
 * @author: SDH
 * @create: 2019-01-13 01:49
 */
@RestController
@ResponseBody
@EnableAutoConfiguration
public class ActivityController {
    @Autowired
    ActivityService activityService;
    @Autowired
    AssociationService associationService;

    @RequestMapping("/addActivity")
    public JSONResult addActivity(HttpServletRequest request) {
        String activityname = request.getParameter("activityname");
        String activitylocation = request.getParameter("activitylocation");
        String activitytime = request.getParameter("activitytime");
        activitytime = activitytime.replace("T", " ");
        String activityorganizer = request.getParameter("activityorganizer");
        String assid = request.getParameter("assid");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = simpleDateFormat.parse(activitytime, new ParsePosition(0));
        Activity activity = new Activity();
        activity.setActivityAssid(Integer.valueOf(assid));
        activity.setActivityLocation(activitylocation);
        activity.setActivityName(activityname);
        activity.setActivityOrganizer(activityorganizer);
        activity.setActivityTime(date);
        activityService.insert(activity);
        return JSONResult.build(200, "ok", null);
    }

    @RequestMapping("/getAllActivity")
    public JSONResult getAllActivity() {
        List<Activity> activities = activityService.getAllActivity();
        List<ActivityResult> activityResults = new ArrayList<>();
        for (Activity activity : activities) {
            ActivityResult activityResult = new ActivityResult(activity);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            activityResult.setActivityTime(simpleDateFormat.format(activity.getActivityTime()));
            activityResult.setAssname(
                    associationService.selectByPrimaryKey(activity.getActivityAssid()).getAssociationName());
            activityResults.add(activityResult);
        }
        return JSONResult.build(200, "ok", activityResults);
    }

    @RequestMapping("/getAllActivityByAssId")
    public JSONResult getAllActivityByAssId(int assid) {
        List<Activity> activities = activityService.getAllActivityByAssid(assid);
        List<ActivityResult> activityResults = new ArrayList<>();
        for (Activity activity : activities) {
            ActivityResult activityResult = new ActivityResult(activity);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            activityResult.setActivityTime(simpleDateFormat.format(activity.getActivityTime()));
            activityResult.setAssname(
                    associationService.selectByPrimaryKey(activity.getActivityAssid()).getAssociationName());
            activityResults.add(activityResult);
        }
        return JSONResult.build(200, "ok", activityResults);
    }

    @RequestMapping("/updateActivity")
    public JSONResult updateActivity(HttpServletRequest request) {
        Activity activity = new Activity();
        activity.setId(Integer.valueOf(request.getParameter("id")));
        activity.setActivityOrganizer(request.getParameter("activityOrganizer"));
        activity.setActivityName(request.getParameter("activityName"));
        activity.setActivityAssid(Integer.valueOf(request.getParameter("activityAssid")));
        activity.setActivityLocation(request.getParameter("activityLocation"));
        activity.setActivityTime(
                new SimpleDateFormat("yyyy-MM-dd HH:mm")
                        .parse(request.getParameter("activityTime").replace("T", " "), new ParsePosition(0)));
        int result = activityService.updateByPrimaryKey(activity);
        if (result > 0) {
            return JSONResult.build(200, "ok", null);
        } else {
            return JSONResult.build(500, "error", null);
        }
    }

    @RequestMapping("/deleteActivityByid")
    public JSONResult deleteActivityByid(int id) {
        int result = activityService.deleteByPrimaryKey(id);
        if (result > 0) {
            return JSONResult.build(200, "ok", null);
        } else {
            return JSONResult.build(500, "error", null);
        }
    }
}
