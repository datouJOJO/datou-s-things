package com.example.demo.service;

import com.example.demo.entity.Stock;

import java.util.List;

public interface StockService {

    Stock findStockByTime(double time);

    List<Stock> findAll();
}
