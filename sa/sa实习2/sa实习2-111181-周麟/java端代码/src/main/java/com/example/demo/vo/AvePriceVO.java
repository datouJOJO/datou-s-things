package com.example.demo.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AvePriceVO {

    @JsonProperty("MSG")
    private final String msg = "近2016年均为：";
    @JsonProperty("RESULT")
    private List<Double> result;

    public AvePriceVO(List<Double> result) {
        this.result = new ArrayList<Double>();
        this.result = result;
    }
}
