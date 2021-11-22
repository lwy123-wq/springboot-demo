package com.example.dididemo;

import com.example.dididemo.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DidiDemoApplicationTests {
    @Autowired
    private UserDao userDao;
    @Test
    void test() {
       //userDao.create("aa","111");
        /*List list=userDao.aa();
        list.get(1);
        System.out.println(list.get(1));*/

    }

}
