package edu.smxy.associationmanagement.controller.websocket;

import edu.smxy.associationmanagement.domain.stomp.StompRequestMessage;
import edu.smxy.associationmanagement.domain.stomp.StompResponseMessage;
import edu.smxy.associationmanagement.utils.StompMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: associationmanagement
 * @description: Stopm控制器
 * @author: SDH
 * @create: 2019-03-22 14:10
 */
@RestController
@ResponseBody
public class StompController {

  @Autowired private SimpMessagingTemplate messagingTemplate;

  /**
   * 发送给群组的消息接口 群组URL由message 传入
   *
   * @param message
   */
  @PostMapping({"/sendToTopic"})
  public void sendToTopic(StompRequestMessage message) {
    StompResponseMessage message1 = getResponseFromRequest(message);
    StompMessageUtil.sendToTopic(message1);
  }

  /**
   * 发送给指定用户的接口 用户id由message传入
   *
   * @param message
   */
  @PostMapping({"/sendToUser"})
  public void sendToUser(StompRequestMessage message) {
    StompResponseMessage message1 = getResponseFromRequest(message);
    StompMessageUtil.sendToUser(message1);
  }

  private StompResponseMessage getResponseFromRequest(StompRequestMessage message) {
    StompResponseMessage message1 = new StompResponseMessage();
    message1.setReceiveId(message.getReceiveId());
    message1.setType(message.getType());
    message1.setMessage(message.getMessage());
    message1.setCreateId(message.getCreateId());
    message1.setCreateName(message.getCreateName());
    message1.setTopicUrl(message.getTopicUrl());
    return message1;
  }

  /**
   * 测试方法
   *
   * @return
   */
  @RequestMapping("/startStomp.do")
  public String startStomp() {
    final int counter = 20;
    int index = 0;
    while (index++ < counter) {
      StompResponseMessage message = new StompResponseMessage();
      message.setCreateId(11);
      message.setReceiveId(12);
      message.setMessage("12333");
      message.setType(0);
      message.setTopicUrl("/topic/ass");
      message.setCreateName("SDH");
      message.setReveiveName("ZXX");
      StompMessageUtil.sendToUserByType(1, message);
    }
    return "ok";
  }
}
