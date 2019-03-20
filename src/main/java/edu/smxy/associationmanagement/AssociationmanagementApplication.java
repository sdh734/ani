package edu.smxy.associationmanagement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author SDH
 */
@SpringBootApplication
@MapperScan({"edu.smxy.associationmanagement.mapper"})
public class AssociationmanagementApplication {
    public static void main(final String[] args) {
        SpringApplication.run(AssociationmanagementApplication.class, args);
    }
}
