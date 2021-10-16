package com.example.demo.service;

import com.example.demo.dao.StockDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvePriceServerImpl implements AvePriceServer{

    @Autowired
    StockDao stockDao;

    @Override
    public List<Double> getAve() {
        return stockDao.findAvePrice();
    }
}
