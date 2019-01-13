package edu.smxy.associationmanagement.controller;

import edu.smxy.associationmanagement.domain.JSONResult;
import edu.smxy.associationmanagement.domain.Suggest;
import edu.smxy.associationmanagement.domain.SuggestResult;
import edu.smxy.associationmanagement.domain.User;
import edu.smxy.associationmanagement.services.association.AssociationService;
import edu.smxy.associationmanagement.services.suggest.SuggestService;
import edu.smxy.associationmanagement.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: associationmanagement
 * @description: SDH
 * @author: SDH
 * @create: 2019-01-13 05:55
 */
@ResponseBody
@RestController
@EnableAutoConfiguration
public class SuggestController {
    @Autowired
    SuggestService suggestService;
    @Autowired
    AssociationService associationService;
    @Autowired
    UserService userService;

    /**
     * @param type 0 表示获取所有协会 1 表示获取所有社联管理人员
     * @return 所有协会或者社联管理人员的集合经由JSONResult包装后的对象
     */
    @RequestMapping("/getSelectListByRadioValue")
    public JSONResult getSelectListByRadioValue(int type) {
        if (type == 0) {
            return JSONResult.build(200, "0", associationService.getAll());
        } else {
            List<User> userList = userService.getAll();
            List<User> userList1 = new ArrayList<>();
            for (User user : userList) {
                if (user.getType() == 1) {
                    userList1.add(user);
                }
            }
            return JSONResult.build(200, "1", userList1);
        }
    }

    // 0
    @RequestMapping("/addSuggestAssToSL")
    public JSONResult addSuggestAssToSL(Suggest suggest) {
        int result = suggestService.insert(suggest);
        if (result > 0) {
            return JSONResult.build(200, "ok", null);
        } else {
            return JSONResult.build(500, "error", null);
        }
    }

    // 1
    @RequestMapping("/addSuggestSLToAss")
    public JSONResult addSuggestSLToAss(Suggest suggest) {
        int result = suggestService.insert(suggest);
        if (result > 0) {
            return JSONResult.build(200, "ok", null);
        } else {
            return JSONResult.build(500, "error", null);
        }
    }

    // 2
    @RequestMapping("/addSuggestAssToAss")
    public JSONResult addSuggestAssToAss(Suggest suggest) {
        int result = suggestService.insert(suggest);
        if (result > 0) {
            return JSONResult.build(200, "ok", null);
        } else {
            return JSONResult.build(500, "error", null);
        }
    }

    @RequestMapping("/getAllSuggestByAuthorId")
    public JSONResult getAllSuggestByAuthorId(Integer authorid) {
        List<Suggest> suggests = suggestService.getAllSuggestByAuthorId(authorid);
        List<SuggestResult> suggestResults = new ArrayList<>();
        for (Suggest suggest : suggests) {
            SuggestResult suggestResult = new SuggestResult(suggest);
            switch (suggest.getType()) {
                case 0:
                    // 0 协会对社联 作者为协会id 接收者为社联id
                    suggestResult.setAuthorname(
                            associationService.selectByPrimaryKey(suggest.getAuthorid()).getAssociationName());
                    suggestResult.setReveivename(
                            userService.selectUserById(suggest.getReceiveid()).getName());
                    break;
                case 1:
                    // 1 社联对协会 作者为社联id 接收者为协会id
                    suggestResult.setAuthorname(userService.selectUserById(suggest.getAuthorid()).getName());
                    suggestResult.setReveivename(
                            associationService.selectByPrimaryKey(suggest.getReceiveid()).getAssociationName());
                    break;
                case 2:
                    // 2 协会对协会 作者为协会id 接收者为协会id
                    suggestResult.setAuthorname(
                            associationService.selectByPrimaryKey(suggest.getAuthorid()).getAssociationName());
                    suggestResult.setReveivename(
                            associationService.selectByPrimaryKey(suggest.getReceiveid()).getAssociationName());
                    break;
                default:
                    break;
            }
            suggestResults.add(suggestResult);
        }
        return JSONResult.build(200, "ok", suggestResults);
    }

    @RequestMapping("/getAllSuggestByReceiveId")
    public JSONResult getAllSuggestByReceiveId(Integer receiveid) {
        List<Suggest> suggests = suggestService.getAllSuggestByReceiveId(receiveid);
        List<SuggestResult> suggestResults = new ArrayList<>();
        for (Suggest suggest : suggests) {
            SuggestResult suggestResult = new SuggestResult(suggest);
            switch (suggest.getType()) {
                case 0:
                    // 0 协会对社联 作者为协会id 接收者为社联id
                    suggestResult.setAuthorname(
                            associationService.selectByPrimaryKey(suggest.getAuthorid()).getAssociationName());
                    suggestResult.setReveivename(
                            userService.selectUserById(suggest.getReceiveid()).getName());
                    break;
                case 1:
                    // 1 社联对协会 作者为社联id 接收者为协会id
                    suggestResult.setAuthorname(userService.selectUserById(suggest.getAuthorid()).getName());
                    suggestResult.setReveivename(
                            associationService.selectByPrimaryKey(suggest.getReceiveid()).getAssociationName());
                    break;
                case 2:
                    // 2 协会对协会 作者为协会id 接收者为协会id
                    suggestResult.setAuthorname(
                            associationService.selectByPrimaryKey(suggest.getAuthorid()).getAssociationName());
                    suggestResult.setReveivename(
                            associationService.selectByPrimaryKey(suggest.getReceiveid()).getAssociationName());
                    break;
                default:
                    break;
            }
            suggestResults.add(suggestResult);
        }
        return JSONResult.build(200, "ok", suggestResults);
    }

    @RequestMapping("/updateSuggest")
    public JSONResult updateSuggest(HttpServletRequest request) {
        Suggest suggest = new Suggest();
        // 2019-01-12T23:48:02.000+0000
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        suggest.setCreatetime(
                simpleDateFormat.parse(request.getParameter("createtime").replace("T", " "), new ParsePosition(0)));
        suggest.setId(Integer.valueOf(request.getParameter("id")));
        suggest.setAuthorid(Integer.valueOf(request.getParameter("authorid")));
        suggest.setReceiveid(Integer.valueOf(request.getParameter("receiveid")));
        suggest.setContent(request.getParameter("content"));
        suggest.setType(Integer.valueOf(request.getParameter("type")));
        int result = suggestService.updateByPrimaryKeyWithBLOBs(suggest);
        if (result > 0) {
            return JSONResult.build(200, "ok", null);
        } else {
            return JSONResult.build(500, "error", null);
        }
    }
}
