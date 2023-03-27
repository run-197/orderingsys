package com.graduationproject.orderingsys.DAO;
/**
 * @BelongsProject: orderingsys
 * @BelongsPackage: com.graduationproject.orderingsys.DAO
 * @Author: Dongrun Li
 * @Date: 2023/3/20 23:47
 * @Description:
 */
public class Dish_picture {
    private Integer dish_ID;
    private String picture_address;

    @Override
    public String toString() {
        return "Dish_picture{" +
                "dish_ID=" + dish_ID +
                ", picture_address='" + picture_address + '\'' +
                '}';
    }

    public Dish_picture() {
    }

    public Dish_picture(Integer dish_ID, String picture_address) {
        this.dish_ID = dish_ID;
        this.picture_address = picture_address;
    }

    public Integer getDish_ID() {
        return dish_ID;
    }

    public void setDish_ID(Integer dish_ID) {
        this.dish_ID = dish_ID;
    }

    public String getPicture_address() {
        return picture_address;
    }

    public void setPicture_address(String picture_address) {
        this.picture_address = picture_address;
    }
}
