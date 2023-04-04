package com.graduationproject.orderingsys.Mapper;

import com.graduationproject.orderingsys.DAO.Dish;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @BelongsProject: orderingsys
 * @BelongsPackage: com.graduationproject.orderingsys.Mapper
 * @Author: Dongrun Li
 * @Date: 2023/3/20 23:48
 * @Description:
 */
@Mapper
@Repository
public interface DishMapper {
    
    /**
     * @description: 查询所有的菜品
     * @return java.util.List<com.graduationproject.orderingsys.DAO.Dish>
     * @author: Dongrun Li
     * @date: 2023/3/27 17:46
     */
    List<Dish> queryDish();
    
    /**
     * @description: 通过菜品ID查询菜品信息
     * @param dish_ID: 菜品ID
     * @return com.graduationproject.orderingsys.DAO.Dish
     * @author: Dongrun Li
     * @date: 2023/3/27 17:49
     */
    Dish queryDishByDishID(Integer dish_ID);

    /**
     * @description: 更新菜品信息
     * @param dish: 待更新的菜品
     * @return int
     * @author: Dongrun Li
     * @date: 2023/3/27 17:53
     */
    int updateDish(Dish dish);

    /**
     * @description: 增加一条餐品信息的记录
     * @param dish: 菜品
     * @return int
     * @author: Dongrun Li
     * @date: 2023/3/27 17:50
     */
    int addDish(Dish dish);

    /**
     * @description: 根据菜品ID删除记录
     * @param dish_ID: 菜品ID
     * @return int
     * @author: Dongrun Li
     * @date: 2023/3/27 17:51
     */
    int delDishByDishID(Integer dish_ID);

    /**
     * @description: 更新月销量
     * @return int
     * @author: Dongrun Li
     * @date: 2023/3/31 18:30
     */
    int setSaleszero();

}
