package com.example.demo.dao;

import com.example.demo.entity.Stock;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface StockDao extends JpaRepository<Stock,Double> {

    //根据time来查询
    Stock findStockByTime(double time);

    //查询2016年均价
    @Query("select ave_price from Stock where substring(cast(time as string),1,4)='2016'")
    List<Double>findAvePrice();

    //查询2016年的收盘价
    @Query("select final_price from Stock where substring(cast(time as string),1,4)='2016'")
    List<Double>findFinalPrice();

    //查询不同范围的市盈率
    @Transactional
    @Query("select count(pe_ration) from Stock where :lower<=pe_ration and pe_ration<=:upper")
    Long findPeRation(@Param("lower") Double lower,@Param("upper") Double upper);
}
