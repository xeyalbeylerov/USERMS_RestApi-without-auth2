package com.company;


import com.company.entity.User;
import com.company.service.inter.UserServiceInter;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceIT {
    @Autowired
    UserServiceInter userDao;

    @Before
    public void before() {
        User u = new User();
        u.setName("Test");
        u.setSurname("TestSurname");
        u.setEmail("test@mail.ru");
        u.setPassword("123123");

        User u2 = new User();
        u2.setName("Test2");
        u2.setSurname("TestSurname2");
        u2.setEmail("test2@mail.ru");
        u2.setPassword("123123123");

        userDao.addUser(u);
        userDao.addUser(u2);
    }

    @After
    public void after() {
        User u = userDao.findByEmail("test@mail.ru");
        User u2 = userDao.findByEmail("test2@mail.ru");
        userDao.removeUser(u.getId());
        userDao.removeUser(u2.getId());
    }

    @Test
    public void testGivenNullParameters_thenGetAllList() {
        List<User> list = userDao.getAll(null, null);
        Assert.assertEquals("size must be 2", 2, list.size());
        System.out.println("list " + list);
    }
    @Test
    public void testGivenTest2Parameter_thenCheckListSize() {
        List<User> list = userDao.getAll("Test2", null);
        Assert.assertEquals("Name of Test2 must be 1", 1, list.size());
        System.out.println("list " + list);
    }

    @Test
    public void testGivenTestParameter_thenCheckListSize() {
        List<User> list = userDao.getAll("Test", null);
        Assert.assertEquals("List size must be 2 while search only Test", 2, list.size());
        Assert.assertEquals("List must be contain Test name while search only Test", "Test", list.get(0).getName());
        Assert.assertEquals("List must be contain Test2 name while search only Test", "Test2", list.get(1).getName());
        System.out.println("list " + list);
    }

    @Test
    public void testUpdateUser_thenCheckUpdatedUser() {
        if(!userDao.isEmailExists("test@mail.ru"))return;
        User u = userDao.findByEmail("test@mail.ru");
        u.setName("Test3");
        u.setSurname("TestSurname3");
        u.setAddress("TestAdress3");
        userDao.updateUser(u);
        User u2 = userDao.findByEmail("test@mail.ru");
        Assert.assertEquals("User name must be Test3", "Test3", u2.getName());
        Assert.assertEquals("User surname must be TestSurname3", "TestSurname3", u2.getSurname());
        Assert.assertEquals("User address must be TestAdress3", "TestAdress3", u2.getAddress());
    }
}
