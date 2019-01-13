package edu.smxy.associationmanagement.domain;

/**
 * @program: associationmanagement
 * @description: SDH
 * @author: SDH
 * @create: 2019-01-13 02:10
 */
public class ActivityResult {
    private Integer id;

    private String activityName;

    private String activityLocation;

    private String activityOrganizer;

    private Integer activityAssid;

    private String activityTime;

    private String assname;

    public ActivityResult(Activity activity) {
        this.activityAssid = activity.getActivityAssid();
        this.activityLocation = activity.getActivityLocation();
        this.activityName = activity.getActivityName();
        this.activityOrganizer = activity.getActivityOrganizer();
        this.id = activity.getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityLocation() {
        return activityLocation;
    }

    public void setActivityLocation(String activityLocation) {
        this.activityLocation = activityLocation;
    }

    public String getActivityOrganizer() {
        return activityOrganizer;
    }

    public void setActivityOrganizer(String activityOrganizer) {
        this.activityOrganizer = activityOrganizer;
    }

    public Integer getActivityAssid() {
        return activityAssid;
    }

    public void setActivityAssid(Integer activityAssid) {
        this.activityAssid = activityAssid;
    }

    public String getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(String activityTime) {
        this.activityTime = activityTime;
    }

    public String getAssname() {
        return assname;
    }

    public void setAssname(String assname) {
        this.assname = assname;
    }
}
