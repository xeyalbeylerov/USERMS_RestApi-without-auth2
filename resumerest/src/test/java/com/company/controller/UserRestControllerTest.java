package com.company.controller;

import com.company.ResumerestapiApplication;
import com.company.dto.ResponseDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ResumerestapiApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserRestControllerTest {

    @LocalServerPort
    private int port;

    public int getPort(){
        return port;
    }



    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getUsers() {
        String url = "http://localhost:" + port + "/users";
        ResponseDto resp = this.restTemplate.getForObject(url, ResponseDto.class);
        System.out.println(resp);
    }

    @Test
    public void getUser() {
    }

    @Test
    public void deleteUser() {
    }

    @Test
    public void addUser() {
    }

    @Test
    public void updateUser() {
    }
}