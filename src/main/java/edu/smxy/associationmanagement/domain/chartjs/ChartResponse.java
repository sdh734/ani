package edu.smxy.associationmanagement.domain.chartjs;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: associationmanagement
 * @description: Chartjs构造数据类
 * @author: SDH
 * @create: 2019-03-25 23:21
 **/
public class ChartResponse {
    //标签集合
    private List<String> labels;
    //数据集合
    private List<String> values;
    //颜色集合
    private List<String> colors;

    private String[] colorss = new String[]{"rgba(255, 99, 132, 0.2)",
            "rgba(54, 162, 235, 0.2)", "rgba(255, 206, 86, 0.2)",
            "rgba(75, 192, 192, 0.2)", "rgba(153, 102, 255, 0.2)",
            "rgba(255, 159, 64, 0.2)", "rgba(1,147,154,0.2)",
            "rgba(0,255,127,0.2)", "rgba(255,215,0,0.2)",
            "rgba(143,188,143,0.2)", "rgba(192,192,192,0.2)",
            "rgba(9,105,162,0.2)", "rgba(255,0,255,0.2)"};

    public ChartResponse(List<String> labels, List<String> values) {
        this.labels = labels;
        this.values = values;
        this.colors = getColorsList();
    }

    public ChartResponse() {
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    private List<String> getColorsList() {
        List<String> temp = new ArrayList<>();
        for (int i = 0; i < this.labels.size(); i++) {
            temp.add(colorss[randomInt()]);
        }
        return temp;
    }

    private int randomInt() {
        //0 - 12 的随机数
        double e = (Math.random() * 12);
        int i = Integer.valueOf(Double.toString(e).substring(0, Double.toString(e).indexOf(".")));
        if (i > 12) {
            i = 12;
        }
        return i;
    }
}
