package com.example.demo.service;

import com.example.demo.dao.StockDao;
import com.example.demo.entity.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class StockServiceImpl implements StockService{
    @Autowired
    private StockDao stockDao;

    @Override
    public Stock findStockByTime(double time) {
        return stockDao.findStockByTime(time);
    }

    @Override
    public List<Stock> findAll() {
        return stockDao.findAll();
    }
}
