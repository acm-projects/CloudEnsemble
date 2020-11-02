package edu.utdallas.pages;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        System.out.println("HERE");
        System.out.println(System.getProperty("S3_ID"));
        System.out.println(System.getProperty("S3_KEY"));
        return application.sources(Pages11Application.class);
    }
}