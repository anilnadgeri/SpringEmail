package com.example.demo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.jvnet.mock_javamail.Mailbox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.*;
import java.io.IOException;
import java.util.Properties;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private EmailService emailService;

	@Test
	public void contextLoads() {
	}

	@Before
	public void setUp(){
		Mailbox.clearAll();
	}

	@Test
	public void shouldSendSimpleEmail() throws MessagingException, IOException{
		String subject = "Hi again";
		String text = "This is simple text";
		emailService.sendSimpleEmail("anil@demo.com", subject, text);

		Session session = Session.getDefaultInstance(new Properties());
		Store store = session.getStore("pop3");
		store.connect("demo.com", "anil", "password");

		Folder folder = store.getFolder("inbox");

		folder.open(Folder.READ_ONLY);
		Message[] msg = folder.getMessages();

		Assert.assertEquals(1, msg.length);
		Assert.assertEquals(subject, msg[0].getSubject());
		Assert.assertEquals(text, msg[0].getContent());
		folder.close(true);
		store.close();
	}

}
