package com.graduationproject.orderingsys.DAO;
/**
 * @BelongsProject: orderingsys
 * @BelongsPackage: com.graduationproject.orderingsys.DAO
 * @Author: Dongrun Li
 * @Date: 2023/3/20 23:47
 * @Description:
 */
public class Order_information {
    private Integer order_ID;
    private String order_status;
    private Float total_amount;

    @Override
    public String toString() {
        return "Order_information{" +
                "order_ID=" + order_ID +
                ", order_status='" + order_status + '\'' +
                ", total_amount=" + total_amount +
                '}';
    }

    public Order_information() {
    }

    public Order_information(Integer order_ID, String order_status, Float total_amount) {
        this.order_ID = order_ID;
        this.order_status = order_status;
        this.total_amount = total_amount;
    }

    public Integer getOrder_ID() {
        return order_ID;
    }

    public void setOrder_ID(Integer order_ID) {
        this.order_ID = order_ID;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public Float getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(Float total_amount) {
        this.total_amount = total_amount;
    }
}
