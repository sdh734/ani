package edu.smxy.associationmanagement.controller;

import edu.smxy.associationmanagement.domain.Association;
import edu.smxy.associationmanagement.domain.JSONResult;
import edu.smxy.associationmanagement.domain.Teacher;
import edu.smxy.associationmanagement.domain.User;
import edu.smxy.associationmanagement.services.association.AssociationService;
import edu.smxy.associationmanagement.services.teacher.TeacherService;
import edu.smxy.associationmanagement.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 协会Controller 主要功能:获取协会信息 创建新协会
 */
@RestController
@ResponseBody
@EnableAutoConfiguration
public class AssociationController
{
    @Autowired
    AssociationService associationService;
    @Autowired
    UserService userService;
    @Autowired
    TeacherService teacherService;

    /**
     * 根据协会id 获取协会信息
     *
     * @param id
     * @return
     */
    @RequestMapping({ "/getassbyid" })
    public JSONResult getAssByid(final int id) {
        final List<Object> list = new ArrayList<Object>();
        final Association association = this.associationService.selectByPrimaryKey(id);
        list.add(association);
        list.add(this.userService.selectUserById(association.getPresidentid()));
        list.add(this.teacherService.selectByPrimaryKey(association.getTeacher()));
        return JSONResult.build(200, "ok", list);
    }

    /**
     * 获得所有协会信息
     * @return
     */
    @RequestMapping({ "/getAllAss" })
    public JSONResult getAll() {
        final List<Association> list = this.associationService.getAll();
        return JSONResult.build(200, "AllAss", list);
    }

    /**
     * 添加协会
     * @param request 请求体 获取各种参数
     * @return
     */
    @RequestMapping({ "/addNewAss" })
    public JSONResult addNewAss(final HttpServletRequest request) {
        final Association association = new Association();
        final User user = new User();
        final Teacher teacher = new Teacher();
        association.setAssociationName(request.getParameter("assname"));
        association.setCount(Integer.valueOf(request.getParameter("assnumber")));
        association.setCreationTime(new Date());
        association.setIsRegistered(true);
        this.associationService.insert(association);
        final Association association2 = this.associationService.query(association);
        teacher.setTeacherName(request.getParameter("assteachername"));
        teacher.setTeacherCollege(request.getParameter("assteachercollege"));
        teacher.setTeacherGender(request.getParameter("assteachergender"));
        teacher.setTeacherPhone(request.getParameter("assteacherphone"));
        teacher.setTeacherAssociation(association2.getAssociationid());
        this.teacherService.insert(teacher);
        user.setName(request.getParameter("assprename"));
        user.setAccount(request.getParameter("asspreaccount"));
        user.setPassword(request.getParameter("assprepwd"));
        user.setPhone(request.getParameter("assprephone"));
        user.setType(0);
        user.setAssociationid(association2.getAssociationid());
        this.userService.insert(user);
        final User user2 = this.userService.query(user);
        association2.setPresidentid(user2.getId());
        final Teacher teacher2 = this.teacherService.query(teacher);
        association2.setTeacher(teacher2.getTeacherId());
        this.associationService.updateByPrimaryKey(association2);
        return JSONResult.build(200, "ok", null);
    }
}
