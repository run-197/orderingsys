package com.graduationproject.orderingsys.DAO;

import java.util.List;

/**
 * @BelongsProject: orderingsys
 * @BelongsPackage: com.graduationproject.orderingsys.DAO
 * @Author: Dongrun Li
 * @Date: 2023/4/16 20:49
 * @Description:联合查询菜品表和类型表的视图，用于向前端菜单的数据显示
 */
public class AllDishOfType {
    private String the_type;
    private List<Dish> dishList;

    @Override
    public String toString() {
        return "AllDishOfType{" +
                "the_type='" + the_type + '\'' +
                ", dishList=" + dishList +
                '}';
    }

    public AllDishOfType() {
    }

    public AllDishOfType(String the_type, List<Dish> dishList) {
        this.the_type = the_type;
        this.dishList = dishList;
    }

    public String getThe_type() {
        return the_type;
    }

    public void setThe_type(String the_type) {
        this.the_type = the_type;
    }

    public List<Dish> getDishList() {
        return dishList;
    }

    public void setDishList(List<Dish> dishList) {
        this.dishList = dishList;
    }
}
