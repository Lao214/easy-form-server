package com.echoes.easyform;

import cn.hutool.core.util.IdUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class EasyformApplicationTests {

	@Autowired
	private JavaMailSender javaMailSender;

	//发送人
	private  String from = "1085444086@qq.com";
	//接收人
	private  String to = "19943070822m@sina.cn";
	//标题
	private  String subject = "easyForm给您发送的注册验证码";
	//正文
	private  String text = "214777";

	@Test
	void contextLoads() {
		long uuid = IdUtil.getSnowflake(1, 1).nextId();
		System.out.println("UUID: " + uuid);
	}

	@Test
	void contextLoads2() {
		// 创建密码解析器
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		// 对密码进行加密
		String bpePassword = bCryptPasswordEncoder.encode("qwer1234");
		System.out.println(bpePassword);
	}

}
