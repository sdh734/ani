package edu.smxy.associationmanagement.domain;

import com.xuxueli.poi.excel.annotation.ExcelField;
import com.xuxueli.poi.excel.annotation.ExcelSheet;

@ExcelSheet(name = "节目导出")
public class ProgramResult
{
    @ExcelField(name = "协会名称")
    private String associationname;
    @ExcelField(name = "事务名称")
    private String eventname;
    @ExcelField(name = "节目名称")
    private String programname;
    @ExcelField(name = "节目人数")
    private Integer programnumber;
    @ExcelField(name = "负责人")
    private String programmanager;
    @ExcelField(name = "负责人联系方式")
    private String managerphone;
    @ExcelField(name = "节目道具")
    private String programprops;
    
    public ProgramResult(final Program program) {
        this.managerphone = program.getManagerphone();
        this.programname = program.getProgramname();
        this.programnumber = program.getProgramnumber();
        this.programprops = program.getProgramprops();
        this.programmanager = program.getProgrammanager();
    }
    
    public String getEventname() {
        return this.eventname;
    }
    
    public void setEventname(final String eventname) {
        this.eventname = eventname;
    }
    
    public String getProgramname() {
        return this.programname;
    }
    
    public void setProgramname(final String programname) {
        this.programname = programname;
    }
    
    public Integer getProgramnumber() {
        return this.programnumber;
    }
    
    public void setProgramnumber(final Integer programnumber) {
        this.programnumber = programnumber;
    }
    
    public String getProgrammanager() {
        return this.programmanager;
    }
    
    public void setProgrammanager(final String programmanager) {
        this.programmanager = programmanager;
    }
    
    public String getManagerphone() {
        return this.managerphone;
    }
    
    public void setManagerphone(final String managerphone) {
        this.managerphone = managerphone;
    }
    
    public String getProgramprops() {
        return this.programprops;
    }
    
    public void setProgramprops(final String programprops) {
        this.programprops = programprops;
    }
    
    public String getAssociationname() {
        return this.associationname;
    }
    
    public void setAssociationname(final String associationname) {
        this.associationname = associationname;
    }
}
