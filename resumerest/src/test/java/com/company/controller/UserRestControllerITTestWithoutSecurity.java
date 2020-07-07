package com.company.controller;

import com.company.ResumerestapiApplication;
import com.company.dto.ResponseDto;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ResumerestapiApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)

//@DataJpaTest
public class UserRestControllerITTestWithoutSecurity {


    @LocalServerPort
    private int port;//burada yuxarida random olaraq verilmis portu aliriq

    public int getPort(){
        return port;
    }


    @Autowired
    private TestRestTemplate restTemplate;//bu oz restful apime sorgu gondermeye imkan verecek


    @Test
    public void shouldReturnControllersMessage() {
        String url = "http://localhost:" + port + "/users";
        ResponseDto resp = this.restTemplate.getForObject(url, ResponseDto.class);
        System.out.println(resp);
    }


}

