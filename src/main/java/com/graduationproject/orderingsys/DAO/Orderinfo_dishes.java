package com.graduationproject.orderingsys.DAO;
/**
 * @BelongsProject: orderingsys
 * @BelongsPackage: com.graduationproject.orderingsys.DAO
 * @Author: Dongrun Li
 * @Date: 2023/3/20 23:47
 * @Description:
 */
public class Orderinfo_dishes {
    private Integer order_ID;
    private Integer dish_ID;
    private Integer dish_number;

    @Override
    public String toString() {
        return "Orderinfo_dishes{" +
                "order_ID=" + order_ID +
                ", dish_ID=" + dish_ID +
                ", dish_number=" + dish_number +
                '}';
    }

    public Orderinfo_dishes() {
    }

    public Orderinfo_dishes(Integer order_ID, Integer dish_ID, Integer dish_number) {
        this.order_ID = order_ID;
        this.dish_ID = dish_ID;
        this.dish_number = dish_number;
    }

    public Integer getOrder_ID() {
        return order_ID;
    }

    public void setOrder_ID(Integer order_ID) {
        this.order_ID = order_ID;
    }

    public Integer getDish_ID() {
        return dish_ID;
    }

    public void setDish_ID(Integer dish_ID) {
        this.dish_ID = dish_ID;
    }

    public Integer getDish_number() {
        return dish_number;
    }

    public void setDish_number(Integer dish_number) {
        this.dish_number = dish_number;
    }
}
