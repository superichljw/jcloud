package com.project.jcloud;

import com.project.jcloud.session.SessionListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.context.annotation.Bean;

import javax.servlet.http.HttpSessionListener;

@SpringBootApplication
public class JcloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(JcloudApplication.class, args);
    }

    @Bean
    public HttpSessionListener httpSessionListener(){
        return new SessionListener();
    }

}
