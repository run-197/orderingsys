package com.graduationproject.orderingsys.DAO;

import java.sql.Timestamp;

/**
 * @BelongsProject: orderingsys
 * @BelongsPackage: com.graduationproject.orderingsys.DAO
 * @Author: Dongrun Li
 * @Date: 2023/5/22 13:18
 * @Description:
 */
public class DishSales {
    private Integer dish_ID;
    private Timestamp sales_time;
    private Integer monthly_sales;

    @Override
    public String toString() {
        return "DishSales{" +
                "dish_ID=" + dish_ID +
                ", order_time=" + sales_time +
                ", monthly_sales=" + monthly_sales +
                '}';
    }

    public DishSales() {
    }

    public DishSales(Integer dish_ID, Timestamp sales_time, Integer monthly_sales) {
        this.dish_ID = dish_ID;
        this.sales_time = sales_time;
        this.monthly_sales = monthly_sales;
    }

    public Integer getDish_ID() {
        return dish_ID;
    }

    public void setDish_ID(Integer dish_ID) {
        this.dish_ID = dish_ID;
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
