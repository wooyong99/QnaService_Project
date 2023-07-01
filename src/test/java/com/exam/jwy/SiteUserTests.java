package com.exam.jwy;

import com.exam.jwy.user.SiteUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SiteUserTests {
    @Autowired
    SiteUserRepository siteUserRepository;
    @Test
    void test1(){

        System.out.println(siteUserRepository.findByUsername("user").isEmpty());

    }
}
