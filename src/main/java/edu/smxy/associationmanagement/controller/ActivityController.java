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
 * 事务 Controller 主要方法: 添加事务 查询所有事务
 */
@RestController
@ResponseBody
@EnableAutoConfiguration
public class ActivityController {
    @Autowired
    ActivityService activityService;
    @Autowired
    AssociationService associationService;

    /**
     * 添加事务
     *
     * @param request 请求体 从请求体中获取各个参数
     * @return 成功消息
     */
    @RequestMapping({"/addActivity"})
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

    /**
     * 获得所有事项
     *
     * @return 返回所有活动
     */
    @RequestMapping({"/getAllActivity"})
    public JSONResult getAllActivity() {
        final List<Activity> activities = this.activityService.getAllActivity();
        return getJsonResultFromActivities(activities);
    }

    private JSONResult getJsonResultFromActivities(List<Activity> activities) {
        final List<ActivityResult> activityResults = new ArrayList<>();
        for (final Activity activity : activities) {
            final ActivityResult activityResult = new ActivityResult(activity);
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            activityResult.setActivityTime(simpleDateFormat.format(activity.getActivityTime()));
            activityResult.setAssname(
                    this.associationService
                            .selectByPrimaryKey(activity.getActivityAssid())
                            .getAssociationName());
            activityResults.add(activityResult);
        }
        return JSONResult.build(200, "ok", activityResults);
    }

    @RequestMapping({"/getAllActivityByAssId"})
    public JSONResult getAllActivityByAssId(final int assid) {
        final List<Activity> activities = this.activityService.getAllActivityByAssid(assid);
        return getJsonResultFromActivities(activities);
    }

    @RequestMapping({"/updateActivity"})
    public JSONResult updateActivity(final HttpServletRequest request) {
        final Activity activity = new Activity();
        activity.setId(Integer.valueOf(request.getParameter("id")));
        activity.setActivityOrganizer(request.getParameter("activityOrganizer"));
        activity.setActivityName(request.getParameter("activityName"));
        activity.setActivityAssid(Integer.valueOf(request.getParameter("activityAssid")));
        activity.setActivityLocation(request.getParameter("activityLocation"));
        activity.setActivityTime(
                new SimpleDateFormat("yyyy-MM-dd HH:mm")
                        .parse(request.getParameter("activityTime").replace("T", " "), new ParsePosition(0)));
        final int result = this.activityService.updateByPrimaryKey(activity);
        if (result > 0) {
            return JSONResult.build(200, "ok", null);
        }
        return JSONResult.build(500, "error", null);
    }

    @RequestMapping({"/deleteActivityByid"})
    public JSONResult deleteActivityByid(final int id) {
        final int result = this.activityService.deleteByPrimaryKey(id);
        if (result > 0) {
            return JSONResult.build(200, "ok", null);
        }
        return JSONResult.build(500, "error", null);
    }
}
