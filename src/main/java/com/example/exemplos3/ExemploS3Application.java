package com.example.exemplos3;

import com.example.exemplos3.service.AwsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExemploS3Application {

    public static void main(String[] args) {
        AwsService service = new AwsService();


        SpringApplication.run(ExemploS3Application.class, args);
    }

}
