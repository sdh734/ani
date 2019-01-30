package edu.smxy.associationmanagement.controller;

import org.springframework.boot.autoconfigure.*;
import edu.smxy.associationmanagement.services.association.*;
import org.springframework.beans.factory.annotation.*;
import edu.smxy.associationmanagement.services.users.*;
import edu.smxy.associationmanagement.services.teacher.*;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.*;
import edu.smxy.associationmanagement.domain.*;
import java.util.*;

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
    
    @RequestMapping({ "/getassbyid" })
    public JSONResult getAssByid(final int id) {
        final List<Object> list = new ArrayList<Object>();
        final Association association = this.associationService.selectByPrimaryKey(id);
        list.add(association);
        list.add(this.userService.selectUserById((int)association.getPresidentid()));
        list.add(this.teacherService.selectByPrimaryKey(association.getTeacher()));
        return JSONResult.build(200, "ok", (Object)list);
    }
    
    @RequestMapping({ "/getAllAss" })
    public JSONResult getAll() {
        final List<Association> list = (List<Association>)this.associationService.getAll();
        return JSONResult.build(200, "AllAss", (Object)list);
    }
    
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
        return JSONResult.build(200, "ok", (Object)null);
    }
}
