package edu.smxy.associationmanagement;

import org.springframework.boot.autoconfigure.*;
import org.mybatis.spring.annotation.*;
import org.springframework.boot.*;

@SpringBootApplication
@MapperScan({ "edu.smxy.associationmanagement.mapper" })
public class AssociationmanagementApplication
{
    public static void main(final String[] args) {
        SpringApplication.run((Class)AssociationmanagementApplication.class, args);
    }
}
