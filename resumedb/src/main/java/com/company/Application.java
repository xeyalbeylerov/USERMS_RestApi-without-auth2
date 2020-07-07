package com.company;


import com.company.entity.User;
import com.company.service.inter.UserServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
/**
 * @author Khayal Baylarov
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Autowired
    @Qualifier(value = "userService")
    private UserServiceInter userDao;


    @Bean
    public CommandLineRunner run() {
        CommandLineRunner clr = args -> {

//            List<User> list = userDao.getAll("x", null);
//
//            list.forEach(u->{
//                System.out.println("List " + u);
//                System.out.println(u.getName());
//            });
        };
        return clr;
    }


}
