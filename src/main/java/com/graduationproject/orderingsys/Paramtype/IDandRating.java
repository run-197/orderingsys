package com.graduationproject.orderingsys.Paramtype;

import java.io.Serializable;
import java.util.List;

/**
 * @BelongsProject: orderingsys
 * @BelongsPackage: com.graduationproject.orderingsys.Paramtype
 * @Author: Dongrun Li
 * @Date: 2023/5/3 20:29
 * @Description:
 */
public class    IDandRating implements Serializable {
    private Integer customer_ID;
    private Integer order_ID;
    private List<Integer> dish_ID;
    private List<Float> rating;

    @Override
    public String toString() {
        return "IDandRating{" +
                "customer_ID=" + customer_ID +
                ", order_ID=" + order_ID +
                ", dish_ID=" + dish_ID +
                ", rating=" + rating +
                '}';
    }

    public IDandRating() {
    }

    public IDandRating(Integer customer_ID, Integer order_ID, List<Integer> dish_ID, List<Float> rating) {
        this.customer_ID = customer_ID;
        this.order_ID = order_ID;
        this.dish_ID = dish_ID;
        this.rating = rating;
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

    public List<Integer> getDish_ID() {
        return dish_ID;
    }

    public void setDish_ID(List<Integer> dish_ID) {
        this.dish_ID = dish_ID;
    }

    public List<Float> getRating() {
        return rating;
    }

    public void setRating(List<Float> rating) {
        this.rating = rating;
    }
}
