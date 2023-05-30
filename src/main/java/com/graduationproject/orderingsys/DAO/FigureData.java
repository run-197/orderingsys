package com.graduationproject.orderingsys.DAO;

/**
 * @BelongsProject: orderingsys
 * @BelongsPackage: com.graduationproject.orderingsys.DAO
 * @Author: Dongrun Li
 * @Date: 2023/5/23 16:37
 * @Description:
 */
public class FigureData {
    private Integer value;
    private String name;

    public FigureData() {
    }

    @Override
    public String toString() {
        return "FigureData{" +
                "value=" + value +
                ", name='" + name + '\'' +
                '}';
    }

    public FigureData(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
