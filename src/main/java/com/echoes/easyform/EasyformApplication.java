package com.echoes.easyform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.echoes")
@MapperScan("com.echoes.easyform.mapper")
@SpringBootApplication
public class EasyformApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyformApplication.class, args);
	}

}
