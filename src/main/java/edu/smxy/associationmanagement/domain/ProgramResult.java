package edu.smxy.associationmanagement.domain;

import com.xuxueli.poi.excel.annotation.ExcelField;
import com.xuxueli.poi.excel.annotation.ExcelSheet;
import org.apache.poi.hssf.util.HSSFColor;

/**
 * @program: associationmanagement
 * @description: SDH
 * @author: SDH
 * @create: 2019-01-11 23:44
 **/
@ExcelSheet(name = "节目导出")
public class ProgramResult {
    @ExcelField(name = "协会名称")
    private String associationname;
    @ExcelField(name = "事务名称")
    private String eventname;
    @ExcelField(name = "节目名称")
    private String programname;
    @ExcelField(name = "节目人数")
    private Integer programnumber;
    @ExcelField(name = "节目负责人")
    private String programmanager;
    @ExcelField(name = "负责人联系方式")
    private String managerphone;
    @ExcelField(name = "道具要求")
    private String programprops;

    public ProgramResult(Program program) {
        this.managerphone = program.getManagerphone();
        this.programname = program.getProgramname();
        this.programnumber = program.getProgramnumber();
        this.programprops = program.getProgramprops();
        this.programmanager = program.getProgrammanager();
    }


    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getProgramname() {
        return programname;
    }

    public void setProgramname(String programname) {
        this.programname = programname;
    }

    public Integer getProgramnumber() {
        return programnumber;
    }

    public void setProgramnumber(Integer programnumber) {
        this.programnumber = programnumber;
    }

    public String getProgrammanager() {
        return programmanager;
    }

    public void setProgrammanager(String programmanager) {
        this.programmanager = programmanager;
    }

    public String getManagerphone() {
        return managerphone;
    }

    public void setManagerphone(String managerphone) {
        this.managerphone = managerphone;
    }

    public String getProgramprops() {
        return programprops;
    }

    public void setProgramprops(String programprops) {
        this.programprops = programprops;
    }

    public String getAssociationname() {
        return associationname;
    }

    public void setAssociationname(String associationname) {
        this.associationname = associationname;
    }
}
