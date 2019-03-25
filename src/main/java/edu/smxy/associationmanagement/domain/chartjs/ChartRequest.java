package edu.smxy.associationmanagement.domain.chartjs;

/**
 * @program: associationmanagement
 * @description: Chartjs请求数据
 * @author: SDH
 * @create: 2019-03-25 23:47
 **/

public class ChartRequest {
    private String startTime;
    private String endTime;
    private int assId;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getAssId() {
        return assId;
    }

    public void setAssId(int assId) {
        this.assId = assId;
    }
}
