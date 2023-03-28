package com.graduationproject.orderingsys.Mapper;

import com.graduationproject.orderingsys.DAO.Orderinfo_dishes;
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
public interface Orderinfo_dishesMapper {

    /**
     * @description: 添加几条订单菜品数量的记录
     * @param OrderdishesList: 订单菜品数量信息
     * @return int
     * @author: Dongrun Li
     * @date: 2023/3/27 19:46
     */
    int addOrderinfoDishes(List<Orderinfo_dishes> OrderdishesList);

    /**
     * @description: 根据订单ID找到相关的菜品数量信息
     * @param order_ID: 订单ID
     * @return java.util.List<com.graduationproject.orderingsys.DAO.Orderinfo_dishes>
     * @author: Dongrun Li
     * @date: 2023/3/27 19:53
     */
    List<Orderinfo_dishes> queryOrderinfoDishesByOrderID(Integer order_ID);

    /**
     * @description: 根据菜品ID找到相关的订单
     * @param dish_ID: 菜品ID
     * @return java.util.List<com.graduationproject.orderingsys.DAO.Orderinfo_dishes>
     * @author: Dongrun Li
     * @date: 2023/3/27 19:53
     */
    List<Orderinfo_dishes> queryOrderinfoDishesByDishID(Integer dish_ID);

    /**
     * @description: 根据订单ID删除相关的记录
     * @param order_ID:订单ID
     * @return int
     * @author: Dongrun Li
     * @date: 2023/3/27 19:54
     */
    int delOrderinfoDishesByOrderID(Integer order_ID);
}
