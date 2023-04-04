package com.graduationproject.orderingsys.Service;


import com.graduationproject.orderingsys.DAO.Order_information;
import com.graduationproject.orderingsys.DAO.Orderinfo_dishes;

import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: orderingsys
 * @BelongsPackage: com.graduationproject.orderingsys.Service
 * @Author: Dongrun Li
 * @Date: 2023/3/28 11:56
 * @Description:
 */
public interface OrderService {
    boolean addNewOrderInfomation(Integer customer_ID, Map<Integer,Integer> dish_IDandNumber,Float total_amount);
    boolean updateOrderStatus(Integer order_ID,String order_status);
    List<Order_information> getAllOrder(Integer customer_ID);
    List<Orderinfo_dishes> getOrderDishes(Integer order_ID);
    Order_information getOrderInfo(Integer order_ID);
}
