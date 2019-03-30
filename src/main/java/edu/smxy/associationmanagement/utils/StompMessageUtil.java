package edu.smxy.associationmanagement.utils;

import edu.smxy.associationmanagement.domain.User;
import edu.smxy.associationmanagement.services.users.UserService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 消息发送工具类 封装多个发送方式,支持多种发送方案
 * 调用须知:
 * 必须在Controller中调用. 需要什么Service的方法自行添加Service 注意 必须使用SpringContextUtils.getBean() 来为Service赋值
 * 以防万一,建议在调用的Controller中自动注入一个SimpMessagingTemplate对象
 * 先做一下判断,如果Util中messageTemplate为null,先将对象传入后再行调用
 * @program: associationmanagement
 * @description: 用于发送消息的工具类
 * @author: SDH
 * @createTime: 2019-03-31 01:06
 */
@Component
public class StompMessageUtil {
  private static SimpMessagingTemplate messageTemplate = null;
  private static UserService userService = SpringContextUtils.getBean(UserService.class);

  public static SimpMessagingTemplate getMessageTemplate() {
    return messageTemplate;
  }

  public static void setMessageTemplate(SimpMessagingTemplate messageTemplate) {
    StompMessageUtil.messageTemplate = messageTemplate;
  }
    
    /**
     *给指定用户类别的所有用户发送传入消息
     * @param type 用户类别
     * @param message
     */
  public static void sendToUserByType(int type, String message) {
    List<User> users = userService.getAllByType(type);
    for (User user : users) {
      messageTemplate.convertAndSendToUser(user.getId() + "", "/message", message);
    }
  }
}
