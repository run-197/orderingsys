package com.graduationproject.orderingsys.Mapper;

import com.graduationproject.orderingsys.DAO.DishSales;
import com.graduationproject.orderingsys.DAO.DishSaleshow;
import com.graduationproject.orderingsys.DAO.FigureData;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * @BelongsProject: orderingsys
 * @BelongsPackage: com.graduationproject.orderingsys.Mapper
 * @Author: Dongrun Li
 * @Date: 2023/5/22 16:39
 * @Description:
 */
@Mapper
@Repository
public interface DishSalesMapper {
    int addSalesrecord(DishSales dishSales);
    List<DishSaleshow> queryRecords(Timestamp sales_time);
    List<Timestamp> queryMonth();
    List<FigureData> getFigureDataBySalesTime(Timestamp sales_time);
    List<FigureData> getDishMonthlySales(Timestamp sales_time,String the_type);
}
