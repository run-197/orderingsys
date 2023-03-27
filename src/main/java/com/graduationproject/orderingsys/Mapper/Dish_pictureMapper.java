package com.graduationproject.orderingsys.Mapper;

import com.graduationproject.orderingsys.DAO.Dish_picture;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @BelongsProject: orderingsys
 * @BelongsPackage: com.graduationproject.orderingsys.Mapper
 * @Author: Dongrun Li
 * @Date: 2023/3/20 23:47
 * @Description:
 */
@Mapper
@Repository
public interface Dish_pictureMapper {
    /**
     * @description: 根据菜品ID查询图片地址，返回符合某个菜品ID的所有对象
     * @param dish_ID: 菜品ID
     * @return java.util.List<com.graduationproject.orderingsys.DAO.Comment_picture>
     * @author: Dongrun Li
     * @date: 2023/3/27 15:33
     */
    List<Dish_picture> queryDishPicByDishID(Integer dish_ID);


    /**
     * @description: 上传菜品图片的List，新增多条记录，可用于管理员界面上传图片
     * @param DishPicList: Dish_pic实体类列表
     * @return int
     * @author: Dongrun Li
     * @date: 2023/3/27 15:41
     */
    int addDishPic(List<Dish_picture> DishPicList);


    /**
     * @description: 根据菜品ID删除图片地址，可用于管理员界面删除图片
     * @param dish_ID: 菜品ID
     * @return int
     * @author: Dongrun Li
     * @date: 2023/3/27 15:44
     */
    int delDishPicByDishID(Integer dish_ID);
}
