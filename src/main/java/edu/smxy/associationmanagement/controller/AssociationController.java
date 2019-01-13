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
 * @program: associationmanagement
 * @description: SDH
 * @author: SDH
 * @create: 2019-01-12 16:03
 **/
@RestController
@ResponseBody
@EnableAutoConfiguration
public class AssociationController {
    @Autowired
    AssociationService associationService;
    @Autowired
    UserService userService;
    @Autowired
    TeacherService teacherService;

    @RequestMapping("/getassbyid")
    public JSONResult getAssByid(int id) {
        List<Object> list = new ArrayList<>();
        Association association = associationService.selectByPrimaryKey(id);
        list.add(association);
        list.add(userService.selectUserById(association.getPresidentid()));
        list.add(teacherService.selectByPrimaryKey(association.getTeacher()));
        return JSONResult.build(200, "ok", list);
    }

    @RequestMapping("/getAllAss")
    public JSONResult getAll() {
        List<Association> list = associationService.getAll();
        return JSONResult.build(200, "AllAss", list);
    }

    @RequestMapping("/addNewAss")
    public JSONResult addNewAss(HttpServletRequest request) {
        Association association = new Association();
        User user = new User();
        Teacher teacher = new Teacher();

        association.setAssociationName(request.getParameter("assname"));
        association.setCount(Integer.valueOf(request.getParameter("assnumber")));
        association.setCreationTime(new Date());
        association.setIsRegistered(true);
        associationService.insert(association);
        Association association1 = associationService.query(association);
        teacher.setTeacherName(request.getParameter("assteachername"));
        teacher.setTeacherCollege(request.getParameter("assteachercollege"));
        teacher.setTeacherGender(request.getParameter("assteachergender"));
        teacher.setTeacherPhone(request.getParameter("assteacherphone"));
        teacher.setTeacherAssociation(association1.getAssociationid());
        teacherService.insert(teacher);
        user.setName(request.getParameter("assprename"));
        user.setAccount(request.getParameter("asspreaccount"));
        user.setPassword(request.getParameter("assprepwd"));
        user.setPhone(request.getParameter("assprephone"));
        user.setType(0);
        user.setAssociationid(association1.getAssociationid());
        userService.insert(user);
        User user1 = userService.query(user);
        association1.setPresidentid(user1.getId());


        Teacher teacher1 = teacherService.query(teacher);

        association1.setTeacher(teacher1.getTeacherId());
        associationService.updateByPrimaryKey(association1);
        return JSONResult.build(200, "ok", null);
    }
}
