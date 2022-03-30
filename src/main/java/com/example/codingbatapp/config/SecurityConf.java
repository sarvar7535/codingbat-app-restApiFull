package com.example.codingbatapp.config;

import com.example.codingbatapp.payload.HasRole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
@EnableWebSecurity
public class SecurityConf extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("director").password(passwordEncoder().encode("123")).roles(HasRole.DIRECTOR.name())
                .and()
                .withUser("manager").password(passwordEncoder().encode("12")).roles(HasRole.MANAGER.name())
                .and()
                .withUser("user").password(passwordEncoder().encode("1")).roles(HasRole.USER.name());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
//                .antMatchers(HttpMethod.GET,"/api/theme/*").hasRole(HasRole.USER.name())
//                .antMatchers(HttpMethod.GET,"/api/theme/**").hasAnyRole(HasRole.DIRECTOR.name(), HasRole.MANAGER.name())
//                .antMatchers("/api/theme/**").hasRole(HasRole.DIRECTOR.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
