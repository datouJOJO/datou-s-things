package com.example.demo.vo;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FinalPriceVO {

    @JsonProperty("MSG")
    private final String msg = "1999-2016收盘价分布数据为：";
    @JsonProperty("RESULT")
    private List<Double> result;

    public FinalPriceVO(List<Double> result) {
        this.result = new ArrayList<Double>();
        this.result = result;
    }
}
