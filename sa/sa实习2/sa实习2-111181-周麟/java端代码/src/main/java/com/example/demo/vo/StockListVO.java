package com.example.demo.vo;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class StockListVO {

    @JsonProperty("DATA")
    List<StockVO> list = new ArrayList<StockVO>();
}
