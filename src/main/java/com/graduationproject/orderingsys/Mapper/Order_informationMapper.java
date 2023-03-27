package com.graduationproject.orderingsys.Mapper;

import com.graduationproject.orderingsys.DAO.Order_information;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @BelongsProject: orderingsys
 * @BelongsPackage: com.graduationproject.orderingsys.Mapper
 * @Author: Dongrun Li
 * @Date: 2023/3/20 23:45
 * @Description:
 */
@Mapper
@Repository
public interface Order_informationMapper {

    /**
     * @description: 根据订单编号查询订单信息
     * @param order_ID: 订单ID
     * @return com.graduationproject.orderingsys.DAO.Order_information
     * @author: Dongrun Li
     * @date: 2023/3/27 18:04
     */
    Order_information queryOrderinfoByOrderID(Integer order_ID);

    /**
     * @description: 增加一条订单信息的记录
     * @param order_information: 要增加的订单信息
     * @return int
     * @author: Dongrun Li
     * @date: 2023/3/27 17:56
     */
    int addOrderinfo(Order_information order_information);

    /**
     * @description: 更新订单信息
     * @param order_information:要更新的订单信息
     * @return int
     * @author: Dongrun Li
     * @date: 2023/3/27 18:00
     */
    int updateOrderinfo(Order_information order_information);

    /**
     * @description: 根据订单ID删除订单信息
     * @param order_ID: 订单ID
     * @return int
     * @author: Dongrun Li
     * @date: 2023/3/27 18:05
     */
    int delOrderinfoByOrderID(Integer order_ID);
}
