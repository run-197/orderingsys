package com.graduationproject.orderingsys.DAO;

/**
 * @BelongsProject: orderingsys
 * @BelongsPackage: com.graduationproject.orderingsys.DAO
 * @Author: Dongrun Li
 * @Date: 2023/5/16 18:40
 * @Description:
 */
public class OrderdishInfo {
    private Integer dish_ID;
    private String dish_name;
    private  Float dish_nuitprice;
    private Integer dish_num;

    @Override
    public String toString() {
        return "OrderdishInfo{" +
                "dish_ID=" + dish_ID +
                ", dish_name='" + dish_name + '\'' +
                ", dish_nuitprice=" + dish_nuitprice +
                ", dish_num=" + dish_num +
                '}';
    }

    public OrderdishInfo(Integer dish_ID, String dish_name, Float dish_nuitprice, Integer dish_num) {
        this.dish_ID = dish_ID;
        this.dish_name = dish_name;
        this.dish_nuitprice = dish_nuitprice;
        this.dish_num = dish_num;
    }

    public OrderdishInfo() {
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

    public Integer getDish_num() {
        return dish_num;
    }

    public void setDish_num(Integer dish_num) {
        this.dish_num = dish_num;
    }
}
