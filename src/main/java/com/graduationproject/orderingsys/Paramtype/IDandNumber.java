package com.graduationproject.orderingsys.Paramtype;

import java.io.Serializable;
import java.util.List;

/**
 * @BelongsProject: orderingsys
 * @BelongsPackage: com.graduationproject.orderingsys.Paramtype
 * @Author: Dongrun Li
 * @Date: 2023/4/23 23:16
 * @Description:
 */
public class IDandNumber implements Serializable {

    private Integer table_ID;

    private Integer customer_ID;

    private Float dishAmout;

    private List<Integer> numList;

    private List<Integer> idList;

    public Integer getTable_ID() {
        return table_ID;
    }

    public void setTable_ID(Integer table_ID) {
        this.table_ID = table_ID;
    }

    public List<Integer> getIdList() {
        return idList;
    }

    public void setIdList(List<Integer> idList) {
        this.idList = idList;
    }

    public List<Integer> getNumList() {
        return numList;
    }

    public void setNumList(List<Integer> numList) {
        this.numList = numList;
    }

    public Integer getCustomer_ID() {
        return customer_ID;
    }

    public void setCustomer_ID(Integer customer_ID) {
        this.customer_ID = customer_ID;
    }

    public Float getDishAmout() {
        return dishAmout;
    }

    public void setDishAmout(Float dishAmout) {
        this.dishAmout = dishAmout;
    }
}

