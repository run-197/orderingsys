package com.graduationproject.orderingsys.DAO;

import java.sql.Timestamp;

/**
 * @BelongsProject: orderingsys
 * @BelongsPackage: com.graduationproject.orderingsys.DAO
 * @Author: Dongrun Li
 * @Date: 2023/3/20 23:47
 * @Description:
 */
public class Order_information {
    private Integer order_ID;
    private Integer table_ID;
    private String order_status;
    private Integer item_quantity;
    private Float total_amount;
    private Timestamp order_time;



    private String comment;

    @Override
    public String toString() {
        return "Order_information{" +
                "order_ID=" + order_ID +
                ", table_ID=" + table_ID +
                ", order_status='" + order_status + '\'' +
                ", item_quantity=" + item_quantity +
                ", total_amount=" + total_amount +
                ", order_time=" + order_time +
                ", comment='" + comment + '\'' +
                '}';
    }

    public Order_information() {
    }

    public Order_information(Integer order_ID, Integer table_ID, String order_status, Integer item_quantity,
                             Float total_amount, Timestamp order_time, String comment) {
        this.order_ID = order_ID;
        this.table_ID = table_ID;
        this.order_status = order_status;
        this.item_quantity = item_quantity;
        this.total_amount = total_amount;
        this.order_time = order_time;
        this.comment = comment;
    }

    public Integer getOrder_ID() {
        return order_ID;
    }

    public void setOrder_ID(Integer order_ID) {
        this.order_ID = order_ID;
    }

    public Integer getTable_ID() {
        return table_ID;
    }

    public void setTable_ID(Integer table_ID) {
        this.table_ID = table_ID;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public Integer getItem_quantity() {
        return item_quantity;
    }

    public void setItem_quantity(Integer item_quantity) {
        this.item_quantity = item_quantity;
    }

    public Float getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(Float total_amount) {
        this.total_amount = total_amount;
    }

    public Timestamp getOrder_time() {
        return order_time;
    }

    public void setOrder_time(Timestamp order_time) {
        this.order_time = order_time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
