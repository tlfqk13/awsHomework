package com.example.awshomework.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.Matchers.is;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void helloReturn() throws Exception{
        String hello = "hello";

        this.mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 1000;

        this.mvc.perform(get("/hello/dto")
                .param("name",name)
                // param 은
                // API 테스트할 때  사용될 요청 파라미터를 설정
                // 값은 String만 허용
                .param("amount",String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(name)))
                // jsonPath 는 json 값을 필드별로 검증할 수 있는 메소드
                .andExpect(jsonPath("$.amount",is(amount)));

    }

}