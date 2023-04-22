package com.graduationproject.orderingsys.Paramtype;

import java.io.Serializable;
import java.util.List;

/**
 * @BelongsProject: orderingsys
 * @BelongsPackage: com.graduationproject.orderingsys.Paramtype
 * @Author: Dongrun Li
 * @Date: 2023/4/22 23:16
 * @Description:
 */
public class DishIDList implements Serializable {

    private List<Integer> idList;

    public List<Integer> getIdList() {
        return idList;
    }

    public void setIdList(List<Integer> idList) {
        this.idList = idList;
    }
}
