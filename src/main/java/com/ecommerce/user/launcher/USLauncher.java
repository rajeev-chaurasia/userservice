package com.ecommerce.user.launcher;

import com.ecommerce.user.service.authentication.JwtTokenFilter;
import com.ecommerce.user.service.authentication.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ecommerce"})
@EnableJpaRepositories(basePackages = {"com.ecommerce.user.repo"})
@EntityScan(basePackages = {"com.ecommerce.user.beans"})
@Import(SecurityConfig.class)
public class USLauncher {

	public static void main(String[] args) {
		SpringApplication.run(USLauncher.class, args);
	}

}
