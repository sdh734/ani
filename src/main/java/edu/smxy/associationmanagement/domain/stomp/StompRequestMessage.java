package edu.smxy.associationmanagement.domain.stomp;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: associationmanagement
 * @description: Stomp服务, 前端传入消息构造类
 * @author: SDH
 * @create: 2019-03-31 20:39
 */
public class StompRequestMessage {
  /** 创建人ID */
  private int createId;
  /** 创建人名称 */
  private String createName;
  /** 接收人ID */
  private int receiveId;
  /** 接收人名称 */
  private String reveiveName;
  /** 消息主体内容 */
  private String message;
  /** 消息类型 0 通知 1 聊天 */
  private int type;
  /** 话题URL */
  private String topicUrl;
  /** 创建时间 自动生成 */
  private String createTime;

  public StompRequestMessage(
      int createId,
      String createName,
      int receiveId,
      String reveiveName,
      String message,
      int type,
      String topicUrl) {
    this.createId = createId;
    this.createName = createName;
    this.receiveId = receiveId;
    this.reveiveName = reveiveName;
    this.message = message;
    this.type = type;
    this.topicUrl = topicUrl;
    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    this.createTime = sd.format(new Date());
  }

  public StompRequestMessage() {
    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    this.createTime = sd.format(new Date());
  }

  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

  public int getCreateId() {
    return createId;
  }

  public void setCreateId(int createId) {
    this.createId = createId;
  }

  public String getCreateName() {
    return createName;
  }

  public void setCreateName(String createName) {
    this.createName = createName;
  }

  public int getReceiveId() {
    return receiveId;
  }

  public void setReceiveId(int receiveId) {
    this.receiveId = receiveId;
  }

  public String getReveiveName() {
    return reveiveName;
  }

  public void setReveiveName(String reveiveName) {
    this.reveiveName = reveiveName;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public String getTopicUrl() {
    return topicUrl;
  }

  public void setTopicUrl(String topicUrl) {
    this.topicUrl = topicUrl;
  }
}
