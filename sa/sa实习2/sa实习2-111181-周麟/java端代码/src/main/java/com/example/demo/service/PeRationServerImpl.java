package com.example.demo.service;

import com.example.demo.dao.StockDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeRationServerImpl implements PeRationServer{

    @Autowired
    StockDao stockDao;

    @Override
    public Long getPeRation(Double lower,Double upper) {
        return stockDao.findPeRation(lower,upper);
    }
}
