package com.app.studentnotes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("userService")
    @Autowired
    UserDetailsService userDetailsService;

    String[] mentorApis ={
            "/**"
    };
    String[] reviewerApis ={
            "/",
            "/search",
            "/statistics",
            "/showListOfNotes/**",
            "/searchStudentById/**",
            "/more-than-4-notes/**"
    };
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable()
                .and()
                .csrf().ignoringAntMatchers("/h2-console/**","/h2/**")
                .and()
                .cors().disable();
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers( "/*.html","/h2/**","/post-user").permitAll()
                .antMatchers(reviewerApis).access("hasAnyRole('ROLE_REVIEWER','ROLE_MENTOR')")
                .antMatchers(mentorApis).access("hasRole('ROLE_MENTOR')")
                .anyRequest().authenticated().and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll();

    }


    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

}
