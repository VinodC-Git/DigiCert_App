package com.java.springPro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class SpringBootCrudOperationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCrudOperationApplication.class, args);
    }
}
