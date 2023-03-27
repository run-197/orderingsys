package com.graduationproject.orderingsys.DAO;
/**
 * @BelongsProject: orderingsys
 * @BelongsPackage: com.graduationproject.orderingsys.DAO
 * @Author: Dongrun Li
 * @Date: 2023/3/20 23:47
 * @Description:
 */
public class Comment_picture {
    private Integer order_ID;
    private String picture_address;

    @Override
    public String toString() {
        return "Comment_picture{" +
                ", dish_ID=" + order_ID +
                ", picture_address='" + picture_address + '\'' +
                '}';
    }

    public Comment_picture() {
    }

    public Comment_picture( Integer order_ID, String picture_address) {
        this.order_ID = order_ID;
        this.picture_address = picture_address;
    }

    public Integer getOrder_ID() {
        return order_ID;
    }

    public void setOrder_ID(Integer order_ID) {
        this.order_ID = order_ID;
    }

    public String getPicture_address() {
        return picture_address;
    }

    public void setPicture_address(String picture_address) {
        this.picture_address = picture_address;
    }
}
