package edu.smxy.associationmanagement.controller;

import org.springframework.boot.autoconfigure.*;
import edu.smxy.associationmanagement.services.suggest.*;
import org.springframework.beans.factory.annotation.*;
import edu.smxy.associationmanagement.services.association.*;
import edu.smxy.associationmanagement.services.users.*;
import java.util.*;
import org.springframework.web.bind.annotation.*;
import edu.smxy.associationmanagement.domain.*;
import javax.servlet.http.*;
import java.text.*;

@ResponseBody
@RestController
@EnableAutoConfiguration
public class SuggestController
{
    @Autowired
    SuggestService suggestService;
    @Autowired
    AssociationService associationService;
    @Autowired
    UserService userService;
    
    @RequestMapping({ "/getSelectListByRadioValue" })
    public JSONResult getSelectListByRadioValue(final int type) {
        if (type == 0) {
            return JSONResult.build(200, "0", (Object)this.associationService.getAll());
        }
        final List<User> userList = (List<User>)this.userService.getAll();
        final List<User> userList2 = new ArrayList<User>();
        for (final User user : userList) {
            if (user.getType() == 1) {
                userList2.add(user);
            }
        }
        return JSONResult.build(200, "1", (Object)userList2);
    }
    
    @RequestMapping({ "/addSuggestAssToSL" })
    public JSONResult addSuggestAssToSL(final Suggest suggest) {
        final int result = this.suggestService.insert(suggest);
        if (result > 0) {
            return JSONResult.build(200, "ok", (Object)null);
        }
        return JSONResult.build(500, "error", (Object)null);
    }
    
    @RequestMapping({ "/addSuggestSLToAss" })
    public JSONResult addSuggestSLToAss(final Suggest suggest) {
        final int result = this.suggestService.insert(suggest);
        if (result > 0) {
            return JSONResult.build(200, "ok", (Object)null);
        }
        return JSONResult.build(500, "error", (Object)null);
    }
    
    @RequestMapping({ "/addSuggestAssToAss" })
    public JSONResult addSuggestAssToAss(final Suggest suggest) {
        final int result = this.suggestService.insert(suggest);
        if (result > 0) {
            return JSONResult.build(200, "ok", (Object)null);
        }
        return JSONResult.build(500, "error", (Object)null);
    }
    
    @RequestMapping({ "/getAllSuggestByAuthorId" })
    public JSONResult getAllSuggestByAuthorId(final Integer authorid) {
        final List<Suggest> suggests = (List<Suggest>)this.suggestService.getAllSuggestByAuthorId(authorid);
        final List<SuggestResult> suggestResults = new ArrayList<SuggestResult>();
        for (final Suggest suggest : suggests) {
            final SuggestResult suggestResult = new SuggestResult(suggest);
            switch (suggest.getType()) {
                case 0: {
                    suggestResult.setAuthorname(this.associationService.selectByPrimaryKey(suggest.getAuthorid()).getAssociationName());
                    suggestResult.setReveivename(this.userService.selectUserById((int)suggest.getReceiveid()).getName());
                    break;
                }
                case 1: {
                    suggestResult.setAuthorname(this.userService.selectUserById((int)suggest.getAuthorid()).getName());
                    suggestResult.setReveivename(this.associationService.selectByPrimaryKey(suggest.getReceiveid()).getAssociationName());
                    break;
                }
                case 2: {
                    suggestResult.setAuthorname(this.associationService.selectByPrimaryKey(suggest.getAuthorid()).getAssociationName());
                    suggestResult.setReveivename(this.associationService.selectByPrimaryKey(suggest.getReceiveid()).getAssociationName());
                    break;
                }
            }
            suggestResults.add(suggestResult);
        }
        return JSONResult.build(200, "ok", (Object)suggestResults);
    }
    
    @RequestMapping({ "/getAllSuggestByReceiveId" })
    public JSONResult getAllSuggestByReceiveId(final Integer receiveid) {
        final List<Suggest> suggests = (List<Suggest>)this.suggestService.getAllSuggestByReceiveId(receiveid);
        final List<SuggestResult> suggestResults = new ArrayList<SuggestResult>();
        for (final Suggest suggest : suggests) {
            final SuggestResult suggestResult = new SuggestResult(suggest);
            switch (suggest.getType()) {
                case 0: {
                    suggestResult.setAuthorname(this.associationService.selectByPrimaryKey(suggest.getAuthorid()).getAssociationName());
                    suggestResult.setReveivename(this.userService.selectUserById((int)suggest.getReceiveid()).getName());
                    break;
                }
                case 1: {
                    suggestResult.setAuthorname(this.userService.selectUserById((int)suggest.getAuthorid()).getName());
                    suggestResult.setReveivename(this.associationService.selectByPrimaryKey(suggest.getReceiveid()).getAssociationName());
                    break;
                }
                case 2: {
                    suggestResult.setAuthorname(this.associationService.selectByPrimaryKey(suggest.getAuthorid()).getAssociationName());
                    suggestResult.setReveivename(this.associationService.selectByPrimaryKey(suggest.getReceiveid()).getAssociationName());
                    break;
                }
            }
            suggestResults.add(suggestResult);
        }
        return JSONResult.build(200, "ok", (Object)suggestResults);
    }
    
    @RequestMapping({ "/updateSuggest" })
    public JSONResult updateSuggest(final HttpServletRequest request) {
        final Suggest suggest = new Suggest();
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        suggest.setCreatetime(simpleDateFormat.parse(request.getParameter("createtime").replace("T", " "), new ParsePosition(0)));
        suggest.setId(Integer.valueOf(request.getParameter("id")));
        suggest.setAuthorid(Integer.valueOf(request.getParameter("authorid")));
        suggest.setReceiveid(Integer.valueOf(request.getParameter("receiveid")));
        suggest.setContent(request.getParameter("content"));
        suggest.setType(Integer.valueOf(request.getParameter("type")));
        final int result = this.suggestService.updateByPrimaryKeyWithBLOBs(suggest);
        if (result > 0) {
            return JSONResult.build(200, "ok", (Object)null);
        }
        return JSONResult.build(500, "error", (Object)null);
    }
    
    @RequestMapping({ "/deleteSuggestById" })
    public JSONResult deleteSuggestById(final int id) {
        this.suggestService.deleteByPrimaryKey(id);
        return JSONResult.build(200, "ok", (Object)null);
    }
}
