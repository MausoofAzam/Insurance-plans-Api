package com.plan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class DemoApplication {

	public static void main(String[] args) {
		Logger log = LoggerFactory.getLogger("DemoApplication");
		SpringApplication.run(DemoApplication.class, args);
		log.info("Contact Api is Started Running");
		
	}

}
