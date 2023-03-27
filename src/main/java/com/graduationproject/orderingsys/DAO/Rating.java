package com.graduationproject.orderingsys.DAO;
/**
 * @BelongsProject: orderingsys
 * @BelongsPackage: com.graduationproject.orderingsys.DAO
 * @Author: Dongrun Li
 * @Date: 2023/3/20 23:47
 * @Description:
 */
public class Rating {
    private Integer customer_ID;
    private Integer order_ID;
    private Integer dish_ID;
    private Float rating;

    @Override
    public String toString() {
        return "Rating{" +
                "customer_ID=" + customer_ID +
                ", order_ID=" + order_ID +
                ", dish_ID=" + dish_ID +
                ", rating=" + rating +
                '}';
    }

    public Rating() {
    }

    public Rating(Integer customer_ID, Integer order_ID, Integer dish_ID, Float rating) {
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

    public Integer getDish_ID() {
        return dish_ID;
    }

    public void setDish_ID(Integer dish_ID) {
        this.dish_ID = dish_ID;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }
}
