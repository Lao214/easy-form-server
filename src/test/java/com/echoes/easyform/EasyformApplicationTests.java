package com.echoes.easyform;

import cn.dev33.satoken.secure.SaSecureUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

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

		// 定义秘钥和明文
		String key = "echoes";
		String text = "qwert123";

// 加密
		String ciphertext = SaSecureUtil.aesEncrypt(key, text);
		System.out.println("AES加密后：" + ciphertext);

// 解密
		String text2 = SaSecureUtil.aesDecrypt(key, ciphertext);
		System.out.println("AES解密后：" + text2);
	}

}
