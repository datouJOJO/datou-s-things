package com.example.demo.controller;

import com.example.demo.entity.Stock;
import com.example.demo.service.AvePriceServer;
import com.example.demo.service.FinalPriceServer;
import com.example.demo.service.PeRationServer;
import com.example.demo.service.StockService;
import com.example.demo.vo.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/")
public class StockController {

    @Autowired
    StockService stockService;

    @Autowired
    AvePriceServer avePriceServer;

    @Autowired
    FinalPriceServer finalPriceServer;

    @Autowired
    PeRationServer peRationServer;
    //面向接口编程

    @GetMapping("/findAll")
    public List<Stock>findAll(){
        return stockService.findAll();
    }

    @GetMapping("/test")
    public String test(){
        return "hello";
    }

    //返回所有数据
    @GetMapping("/getAllData")
    public StockListVO getAll(){
        List<Stock>addData = stockService.findAll();
        StockListVO listVO = new StockListVO();

        for(Stock temp:addData){
            listVO.getList().add(new StockVO(temp.getTime(),temp.getAve_price(),temp.getFinal_price(),temp.getPe_ration()));
        }

        return listVO;
    }

    //根据时间去获取数据
    @GetMapping("/getByTime")
    public StockVO searchTime(@RequestParam("time") Double time){
        Stock stock = stockService.findStockByTime(time);
        StockVO stockVO = new StockVO(stock.getTime(),stock.getAve_price(),stock.getFinal_price(),stock.getPe_ration());
        return stockVO;
    }

    @GetMapping("/ave")
    public AvePriceVO getAve(){
        List<Double>result = avePriceServer.getAve();
        AvePriceVO avePriceVO = new AvePriceVO(result);
        return avePriceVO;
    }

    @GetMapping("/final")
    public FinalPriceVO getFinal(){
        List<Double>result = finalPriceServer.getFinal();
        FinalPriceVO finalPriceVO = new FinalPriceVO(result);
        return finalPriceVO;
    }

    @GetMapping("/pe")
//    @Param("lower") Double lower,@Param("upper") Double upper
    public PeRationVO getPe(){
        PeRationVO peRationVO = new PeRationVO();
        String range = "";
        Map<String, Long> map = new HashMap<String, Long>();
        Long result = null;

        result = peRationServer.getPeRation(0.0,10.0);
        range = "0-10";
        map.put(range,result);
        result = peRationServer.getPeRation(10.0,20.0);
        range = "10-20";
        map.put(range,result);
        result = peRationServer.getPeRation(20.0,30.0);
        range = "20-30";
        map.put(range,result);
        result = peRationServer.getPeRation(30.0,40.0);
        range = "30-40";
        map.put(range,result);
        result = peRationServer.getPeRation(40.0,50.0);
        range = "40-50";
        map.put(range,result);
        result = peRationServer.getPeRation(50.0,60.0);
        range = "50-60";
        map.put(range,result);
        result = peRationServer.getPeRation(60.0,70.0);
        range = "60-70";
        map.put(range,result);
        result = peRationServer.getPeRation(70.0,80.0);
        range = "70-80";
        map.put(range,result);
        JSONObject js = JSONObject.fromObject(map);
        peRationVO.getResult().add(js);
        return peRationVO;
    }
}
