package com.cranberries.register.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurittyConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // 关闭csrf
       httpSecurity.csrf().disable();
//       super.configure(httpSecurity);
       // 支持httpBasic
        httpSecurity.authorizeRequests().anyRequest().permitAll().and().httpBasic();

//        httpSecurity.formLogin().loginPage("/login").permitAll().usernameParameter("dreamli").passwordParameter("369254")
//                .failureForwardUrl("/login");
    }
}
