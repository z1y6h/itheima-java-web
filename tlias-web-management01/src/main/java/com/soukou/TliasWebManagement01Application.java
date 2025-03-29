package com.soukou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan //开启对Servlet组件的支持
@SpringBootApplication
public class TliasWebManagement01Application {

    public static void main(String[] args) {
        SpringApplication.run(TliasWebManagement01Application.class, args);
    }

}
