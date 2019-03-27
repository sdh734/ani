package edu.smxy.associationmanagement.domain;

public class KeyProject {
    private Integer id;

    private Integer assId;

    private Integer status;

    private String name;

    private String manager;

    private String managerPhone;

    private Integer applyfileId;

    private Integer finishfileId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAssId() {
        return assId;
    }

    public void setAssId(Integer assId) {
        this.assId = assId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager == null ? null : manager.trim();
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone == null ? null : managerPhone.trim();
    }

    public Integer getApplyfileId() {
        return applyfileId;
    }

    public void setApplyfileId(Integer applyfileId) {
        this.applyfileId = applyfileId;
    }

    public Integer getFinishfileId() {
        return finishfileId;
    }

    public void setFinishfileId(Integer finishfileId) {
        this.finishfileId = finishfileId;
    }
}