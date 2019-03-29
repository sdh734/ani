package edu.smxy.associationmanagement.domain;

public class ActivityResult {
	private Integer id;
	private String activityName;
	private String activityLocation;
	private String activityOrganizer;
	private Integer activityAssid;
	private String activityTime;
	private String assname;

	public ActivityResult(final Activity activity) {
		this.activityAssid = activity.getActivityAssid();
		this.activityLocation = activity.getActivityLocation();
		this.activityName = activity.getActivityName();
		this.activityOrganizer = activity.getActivityOrganizer();
		this.id = activity.getId();
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getActivityName() {
		return this.activityName;
	}

	public void setActivityName(final String activityName) {
		this.activityName = activityName;
	}

	public String getActivityLocation() {
		return this.activityLocation;
	}

	public void setActivityLocation(final String activityLocation) {
		this.activityLocation = activityLocation;
	}

	public String getActivityOrganizer() {
		return this.activityOrganizer;
	}

	public void setActivityOrganizer(final String activityOrganizer) {
		this.activityOrganizer = activityOrganizer;
	}

	public Integer getActivityAssid() {
		return this.activityAssid;
	}

	public void setActivityAssid(final Integer activityAssid) {
		this.activityAssid = activityAssid;
	}

	public String getActivityTime() {
		return this.activityTime;
	}

	public void setActivityTime(final String activityTime) {
		this.activityTime = activityTime;
	}

	public String getAssname() {
		return this.assname;
	}

	public void setAssname(final String assname) {
		this.assname = assname;
	}
}
