package com.graduationproject.orderingsys.DAO;
/**
 * @BelongsProject: orderingsys
 * @BelongsPackage: com.graduationproject.orderingsys.DAO
 * @Author: Dongrun Li
 * @Date: 2023/3/20 23:47
 * @Description:
 */
public class Customerorder_info {
    private Integer customer_ID;
    private Integer order_ID;

    @Override
    public String toString() {
        return "Customerorder_info{" +
                "customer_ID=" + customer_ID +
                ", order_ID=" + order_ID +
                '}';
    }

    public Customerorder_info() {
    }

    public Customerorder_info(Integer customer_ID, Integer order_ID) {
        this.customer_ID = customer_ID;
        this.order_ID = order_ID;
    }

    public Integer getCustomer_ID() {
        return customer_ID;
    }

    public void setCustomer_ID(Integer customer_ID) {
        this.customer_ID = customer_ID;
    }

    public Integer getOrder_ID() {
        return order_ID;
    }

    public void setOrder_ID(Integer order_ID) {
        this.order_ID = order_ID;
    }
}
