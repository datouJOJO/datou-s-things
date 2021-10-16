package com.example.demo.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "stock")
public class Stock {

    @Id
    @Column(name="TIME")
    private Double time;
    @Column(name="AVE_PRICE")
    private Double ave_price;
    @Column(name="FINAL_PRICE")
    private Double final_price;
    @Column(name="PE_RATION")
    private Double pe_ration;

    @Override
    public String toString() {
        return "Stock{" +
                "time=" + time +
                ", ave_price=" + ave_price +
                ", final_price=" + final_price +
                ", pe_price=" + pe_ration +
                '}';
    }
}
