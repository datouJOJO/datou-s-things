package com.example.demo.service;

import com.example.demo.entity.Stock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class StockServiceImplTest {


    @Autowired
    StockService stockService;

    @Test
    void test(){
        Stock s = stockService.findStockByTime(19991110000000.0);
        System.out.println(s.toString());
    }
}