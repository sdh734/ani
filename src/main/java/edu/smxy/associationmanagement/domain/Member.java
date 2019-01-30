package edu.smxy.associationmanagement.domain;

public class Member
{
    private Integer id;
    private Integer associationid;
    private String name;
    private String gender;
    private String college;
    private String phone;
    private String position;
    private String studentid;
    private String department;
    
    public Integer getId() {
        return this.id;
    }
    
    public void setId(final Integer id) {
        this.id = id;
    }
    
    public Integer getAssociationid() {
        return this.associationid;
    }
    
    public void setAssociationid(final Integer associationid) {
        this.associationid = associationid;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = ((name == null) ? null : name.trim());
    }
    
    public String getGender() {
        return this.gender;
    }
    
    public void setGender(final String gender) {
        this.gender = ((gender == null) ? null : gender.trim());
    }
    
    public String getCollege() {
        return this.college;
    }
    
    public void setCollege(final String college) {
        this.college = ((college == null) ? null : college.trim());
    }
    
    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(final String phone) {
        this.phone = ((phone == null) ? null : phone.trim());
    }
    
    public String getPosition() {
        return this.position;
    }
    
    public void setPosition(final String position) {
        this.position = ((position == null) ? null : position.trim());
    }
    
    public String getStudentid() {
        return this.studentid;
    }
    
    public void setStudentid(final String studentid) {
        this.studentid = ((studentid == null) ? null : studentid.trim());
    }
    
    public String getDepartment() {
        return this.department;
    }
    
    public void setDepartment(final String department) {
        this.department = ((department == null) ? null : department.trim());
    }
}
