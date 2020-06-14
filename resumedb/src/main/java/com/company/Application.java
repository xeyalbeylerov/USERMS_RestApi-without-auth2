package com.company;


import com.company.service.inter.UserSkillServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


//    @Autowired
//    @Qualifier(value="userService")
//    private UserServiceInter userDao;
@Autowired
@Qualifier(value="userSkillService")
private UserSkillServiceInter userSkillDao;
//    @Autowired
//@Qualifier(value="skillService")
//private SkillServiceInter skillDao;




    @Bean
    public CommandLineRunner run() {
        CommandLineRunner clr = new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {

//
//boolean s=userSkillDao.isIdExists(699);
//                System.out.println("S "+s);
//
//                List<User> list = userDao.getAll("x", null);
//                System.out.println(list);
//                for(User u:list){System.out.println("List "+u);}
//userDao.removeUser(46);
//                u.setName(u.getName().toLowerCase());
//                userDao.updateUser(u);
//                System.out.println("Name " + u.getName()+" "+u.getName());
//                User u=new User();
//                u.setName("Xeyal");
//                u.setEmail("xeyalbeylerov@gmail.com");
//                BCrypt.Hasher h=BCrypt.withDefaults();
//                String pass=h.hashToString(4,"123123".toCharArray());
//                u.setPassword(pass);
//                userDao.addUser(u);

            }

        };
        return clr;
    }


}
