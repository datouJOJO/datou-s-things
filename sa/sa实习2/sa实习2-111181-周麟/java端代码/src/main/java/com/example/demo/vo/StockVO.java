package com.example.demo.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StockVO {
    /*
    视图 展示给客户
    返回json数据
    */

    @JsonProperty("TIME")
    private Double time;

    @JsonProperty("AVE_PRICE")

    private Double ave_price;

    @JsonProperty("FINAL_PRICE")
    private Double final_price;

    @JsonProperty("PE_RATION")
    private Double pe_ration;

    public StockVO(Double time, Double ave_price, Double final_price, Double pe_ration) {
        this.time = time;
        this.ave_price = ave_price;
        this.final_price = final_price;
        this.pe_ration = pe_ration;
    }
}
