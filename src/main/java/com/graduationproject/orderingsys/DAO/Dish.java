package com.graduationproject.orderingsys.DAO;
/**
 * @BelongsProject: orderingsys
 * @BelongsPackage: com.graduationproject.orderingsys.DAO
 * @Author: Dongrun Li
 * @Date: 2023/3/20 23:47
 * @Description:
 */
public class Dish {
    private Integer dish_ID;
    private String dish_name;
    private  Float dish_nuitprice;
    private String dish_quantity;
    private Integer monthly_sales;
    private Float dish_rating;

    @Override
    public String toString() {
        return "Dish{" +
                "dish_ID=" + dish_ID +
                ", dish_name='" + dish_name + '\'' +
                ", dish_nuitprice=" + dish_nuitprice +
                ", dish_quantity='" + dish_quantity + '\'' +
                ", monthly_sales=" + monthly_sales +
                ", dish_rating=" + dish_rating +
                '}';
    }

    public Dish() {
    }

    public Dish(Integer dish_ID, String dish_name, Float dish_nuitprice, String dish_quantity, Integer monthly_sales, Float dish_rating) {
        this.dish_ID = dish_ID;
        this.dish_name = dish_name;
        this.dish_nuitprice = dish_nuitprice;
        this.dish_quantity = dish_quantity;
        this.monthly_sales = monthly_sales;
        this.dish_rating = dish_rating;
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

    public Float getDish_nuitprice() {
        return dish_nuitprice;
    }

    public void setDish_nuitprice(Float dish_nuitprice) {
        this.dish_nuitprice = dish_nuitprice;
    }

    public String getDish_quantity() {
        return dish_quantity;
    }

    public void setDish_quantity(String dish_quantity) {
        this.dish_quantity = dish_quantity;
    }

    public Integer getMonthly_sales() {
        return monthly_sales;
    }

    public void setMonthly_sales(Integer monthly_sales) {
        this.monthly_sales = monthly_sales;
    }

    public Float getDish_rating() {
        return dish_rating;
    }

    public void setDish_rating(Float dish_rating) {
        this.dish_rating = dish_rating;
    }
}
