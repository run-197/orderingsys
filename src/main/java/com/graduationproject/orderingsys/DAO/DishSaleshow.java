package com.graduationproject.orderingsys.DAO;

import java.sql.Timestamp;

/**
 * @BelongsProject: orderingsys
 * @BelongsPackage: com.graduationproject.orderingsys.DAO
 * @Author: Dongrun Li
 * @Date: 2023/5/22 17:14
 * @Description:
 */
public class DishSaleshow {
    private Integer dish_ID;
    private String dish_name;
    private Timestamp sales_time;
    private Integer monthly_sales;

    @Override
    public String toString() {
        return "DishSaleshow{" +
                "dish_ID=" + dish_ID +
                ", dish_name='" + dish_name + '\'' +
                ", sales_time=" + sales_time +
                ", monthly_sales=" + monthly_sales +
                '}';
    }

    public DishSaleshow() {
    }

    public DishSaleshow(Integer dish_ID, String dish_name, Timestamp sales_time, Integer monthly_sales) {
        this.dish_ID = dish_ID;
        this.dish_name = dish_name;
        this.sales_time = sales_time;
        this.monthly_sales = monthly_sales;
    }

    public Integer getDish_ID() {
        return dish_ID;
    }

    public void setDish_ID(Integer dish_ID) {
        this.dish_ID = dish_ID;
    }

    public String getDish_name() {
        return dish_name;
    }

    public void setDish_name(String dish_name) {
        this.dish_name = dish_name;
    }

    public Timestamp getSales_time() {
        return sales_time;
    }

    public void setSales_time(Timestamp sales_time) {
        this.sales_time = sales_time;
    }

    public Integer getMonthly_sales() {
        return monthly_sales;
    }

    public void setMonthly_sales(Integer monthly_sales) {
        this.monthly_sales = monthly_sales;
    }
}
