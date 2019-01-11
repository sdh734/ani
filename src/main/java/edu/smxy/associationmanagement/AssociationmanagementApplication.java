package edu.smxy.associationmanagement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@MapperScan("edu.smxy.associationmanagement.mapper")
public class AssociationmanagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(AssociationmanagementApplication.class, args);
    }
}
