package com.ey.todo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
@Profile(value = {"development", "production"})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(final HttpSecurity http) throws Exception {  
        http 
        .httpBasic()
        .and()
        .authorizeRequests()
        .anyRequest().authenticated();
    } 
}
