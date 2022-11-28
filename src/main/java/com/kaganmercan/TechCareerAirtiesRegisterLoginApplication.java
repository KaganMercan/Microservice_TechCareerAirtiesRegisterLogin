package com.kaganmercan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

// Auditor
@EnableJpaAuditing(auditorAwareRef = "auditorAwareMethod")
@SpringBootApplication(exclude = {
		SecurityAutoConfiguration.class,
		//org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
		//org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class
}
)
public class TechCareerAirtiesRegisterLoginApplication {
	@PostConstruct
	public void init(){
		TimeZone.setDefault(TimeZone.getTimeZone("IST"));
	}
	public static void main(String[] args) {
		//devtool active inactive
		System.setProperty("spring.devtools.restart.enabled","true");

		//Disables headless JOptionPane
		System.setProperty("java.awt.headless", "false");
		SpringApplication.run(TechCareerAirtiesRegisterLoginApplication.class, args);
	}

}
