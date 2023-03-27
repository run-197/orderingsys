package com.graduationproject.orderingsys.DAO;
/**
 * @BelongsProject: orderingsys
 * @BelongsPackage: com.graduationproject.orderingsys.DAO
 * @Author: Dongrun Li
 * @Date: 2023/3/20 23:47
 * @Description:
 */
public class Customer {
    private Integer customer_ID ;
    private String customer_nickname;
    private String phone_number;
    private String avatar_address;

    @Override
    public String toString() {
        return "Customer{" +
                "customer_ID=" + customer_ID +
                ", customer_nickname='" + customer_nickname + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", avatar_address='" + avatar_address + '\'' +
                '}';
    }

    public Customer() {
    }

    public Customer(Integer customer_ID, String customer_nickname, String phone_number, String avatar_address) {
        this.customer_ID = customer_ID;
        this.customer_nickname = customer_nickname;
        this.phone_number = phone_number;
        this.avatar_address = avatar_address;
    }

    public Integer getCustomer_ID() {
        return customer_ID;
    }

    public void setCustomer_ID(Integer customer_ID) {
        this.customer_ID = customer_ID;
    }

    public String getCustomer_nickname() {
        return customer_nickname;
    }

    public void setCustomer_nickname(String customer_nickname) {
        this.customer_nickname = customer_nickname;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAvatar_address() {
        return avatar_address;
    }

    public void setAvatar_address(String avatar_address) {
        this.avatar_address = avatar_address;
    }

}
