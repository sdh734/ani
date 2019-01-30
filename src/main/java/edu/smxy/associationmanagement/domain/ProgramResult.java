package edu.smxy.associationmanagement.domain;

import com.xuxueli.poi.excel.annotation.*;

@ExcelSheet(name = "\u8282\u76ee\u5bfc\u51fa")
public class ProgramResult
{
    @ExcelField(name = "\u534f\u4f1a\u540d\u79f0")
    private String associationname;
    @ExcelField(name = "\u4e8b\u52a1\u540d\u79f0")
    private String eventname;
    @ExcelField(name = "\u8282\u76ee\u540d\u79f0")
    private String programname;
    @ExcelField(name = "\u8282\u76ee\u4eba\u6570")
    private Integer programnumber;
    @ExcelField(name = "\u8282\u76ee\u8d1f\u8d23\u4eba")
    private String programmanager;
    @ExcelField(name = "\u8d1f\u8d23\u4eba\u8054\u7cfb\u65b9\u5f0f")
    private String managerphone;
    @ExcelField(name = "\u9053\u5177\u8981\u6c42")
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
