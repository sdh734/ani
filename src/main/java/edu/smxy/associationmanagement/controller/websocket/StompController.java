package edu.smxy.associationmanagement.controller.websocket;

import com.alibaba.fastjson.JSONObject;
import edu.smxy.associationmanagement.utils.StompMessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: associationmanagement
 * @description: Stopm控制器
 * @author: SDH
 * @create: 2019-03-22 14:10
 */
@Controller
@ResponseBody
public class StompController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired private SimpMessagingTemplate messagingTemplate;
    
    /**
     * 发送消息接口,将传入消息发送给所有订阅了"/topic/admin"的客户端
     *
     * @param userMessage 消息内容
     * @return
     */
    @MessageMapping("/sendtoadmin")
    @SendTo("/topic/admin")
    public String adminMessage(String userMessage) {
        return userMessage;
    }
    
    /**
     * 将传入消息发送给指定用户客户端
     *
     * @param json 传入数据 格式: { "id":接收人id, "message":消息内容 }
     */
    @MessageMapping("/sendtouser")
    public void userMessage(String json) {
        JSONObject object = JSONObject.parseObject(json);
        messagingTemplate.convertAndSendToUser(
                object.getString("id"), "/message", object.getString("message"));
    }
    
    /**
     * 测试方法
     *
     * @return
     */
    @RequestMapping("/startStomp.do")
    public String startStomp() {
        if (StompMessageUtil.getMessageTemplate() == null) {
            StompMessageUtil.setMessageTemplate(this.messagingTemplate);
        }
        final int counter = 20;
        int index = 0;
        while (index++ < counter) {
            StompMessageUtil.sendToUserByType(1, "服务器推送的消息" + index);
        }
        return "ok";
    }
}
