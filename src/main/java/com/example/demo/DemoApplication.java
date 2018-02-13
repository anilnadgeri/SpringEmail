package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements ApplicationRunner{

    @Autowired
    private EmailService emailService;

	public static void main(String[] args) {
		System.out.println("********Hello world");
		SpringApplication.run(DemoApplication.class, args);
        System.out.println("********End world");
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
//        emailService.sendSimpleEmail("anil@demo.com", "Hi", "this is test message");
        emailService.sendEmailAttachment("anil@demo.com", "Hi", "this is test message");
    }
}
