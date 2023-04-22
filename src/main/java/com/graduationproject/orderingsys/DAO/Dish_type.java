package com.graduationproject.orderingsys.DAO;

/**
 * @BelongsProject: orderingsys
 * @BelongsPackage: com.graduationproject.orderingsys.DAO
 * @Author: Dongrun Li
 * @Date: 2023/4/16 20:24
 * @Description:
 */
public class Dish_type {
    private Integer related_ID;
    private String the_type;
    private Integer dish_ID;

    public Dish_type() {
    }

    public Dish_type(Integer related_ID, String the_type, Integer dish_ID) {
        this.related_ID = related_ID;
        this.the_type = the_type;
        this.dish_ID = dish_ID;
    }

    @Override
    public String toString() {
        return "Dish_type{" +
                "related_ID=" + related_ID +
                ", type='" + the_type + '\'' +
                ", dish_ID=" + dish_ID +
                '}';
    }

    public Integer getRelated_ID() {
        return related_ID;
    }

    public void setRelated_ID(Integer related_ID) {
        this.related_ID = related_ID;
    }

    public String getThe_type() {
        return the_type;
    }

    public void setThe_type(String the_type) {
        this.the_type = the_type;
    }

    public Integer getDish_ID() {
        return dish_ID;
    }

    public void setDish_ID(Integer dish_ID) {
        this.dish_ID = dish_ID;
    }
}
