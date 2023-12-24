package com.ecommerce.user.launcher;

import com.ecommerce.user.service.authentication.JwtTokenFilter;
import com.ecommerce.user.service.authentication.JwtTokenProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ecommerce"})
public class USLauncher {

	public static void main(String[] args) {
		SpringApplication.run(USLauncher.class, args);
	}

	@Bean
	public FilterRegistrationBean<JwtTokenFilter> jwtFilter(JwtTokenProvider jwtTokenProvider) {
		FilterRegistrationBean<JwtTokenFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new JwtTokenFilter(jwtTokenProvider));
		registrationBean.addUrlPatterns("/api/*"); // Add URL patterns for which the filter should be applied
		return registrationBean;
	}

}
