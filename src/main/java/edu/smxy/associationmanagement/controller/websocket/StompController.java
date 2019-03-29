package edu.smxy.associationmanagement.controller.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
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

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@MessageMapping("/sendTest")
	public String sendDemo(String message) {
		logger.info("接收到了信息" + message);
		this.messagingTemplate.convertAndSend("/ms/subscribeTest1", "guguggugu");
		return "你发送的服务返回消息为:" + message;
	}

	@SubscribeMapping("/subscribeTest1")
	public String sub2() {
		logger.info("订阅111111");
		return "订阅1111";
	}

	@SubscribeMapping("/subscribeTest2")
	public String sub() {
		logger.info("订阅2222");
		return "订阅2222。。。";
	}

	@RequestMapping("/startStomp.do")
	public String startStomp() throws InterruptedException {
		final int counter = 20;
		int index = 0;
		while (index++ < counter) {
			this.messagingTemplate.convertAndSend("/ms/subscribeTest1", "服务器主动推送的消息");
			Thread.sleep((long) Math.ceil(Math.random() * 1000));
		}
		return "ok";
	}
}
