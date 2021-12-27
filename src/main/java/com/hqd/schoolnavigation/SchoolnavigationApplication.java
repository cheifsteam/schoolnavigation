package com.hqd.schoolnavigation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.util.pattern.PathPatternParser;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SpringBootApplication

@MapperScan( basePackages = { "com.hqd.schoolnavigation.mapper"}, sqlSessionFactoryRef = "sqlSessionFactory")
public class SchoolnavigationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolnavigationApplication.class, args);
    }



}


