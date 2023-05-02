package com.graduationproject.orderingsys.DAO;

import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * @BelongsProject: orderingsys
 * @BelongsPackage: com.graduationproject.orderingsys.DAO
 * @Author: Dongrun Li
 * @Date: 2023/4/27 20:42
 * @Description:
 */

public class OpenID {
    private String openID;
    private Integer customer_ID;

    @Override
    public String toString() {
        return "openID{" +
                "openID='" + openID + '\'' +
                ", customer_ID=" + customer_ID +
                '}';
    }

    public OpenID() {
    }

    public OpenID(String openID, Integer customer_ID) {
        this.openID = openID;
        this.customer_ID = customer_ID;
    }

    public String getOpenID() {
        return openID;
    }

    public void setOpenID(String openID) {
        this.openID = openID;
    }

    public Integer getCustomer_ID() {
        return customer_ID;
    }

    public void setCustomer_ID(Integer customer_ID) {
        this.customer_ID = customer_ID;
    }
}
