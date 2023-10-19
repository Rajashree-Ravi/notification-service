package com.banking.notification.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfiguration {

	@Value("${mail.host}")
	private String host;

	@Value("${mail.port}")
	private int port;

	@Value("${mail.transport.protocol}")
	private String protocol;

	@Value("${mail.smtp.auth}")
	private String auth;

	@Value("${mail.smtp.starttls.enable}")
	private String ttls;

	@Value("${mail.debug}")
	private String debug;

	@Bean
	public JavaMailSender javaMailSender() {

		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(host);
		mailSender.setPort(port);

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", protocol);
		props.put("mail.smtp.auth", auth);
		props.put("mail.smtp.starttls.enable", ttls);
		props.put("mail.debug", debug);

		return mailSender;
	}
}
