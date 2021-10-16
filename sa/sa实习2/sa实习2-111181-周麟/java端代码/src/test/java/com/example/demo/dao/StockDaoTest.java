package com.example.demo.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StockDaoTest {

    @Autowired
    StockDao stockDao;

    @Test
    public void test(){
        System.out.println(stockDao.findPeRation(10.0,20.0));
    }
}