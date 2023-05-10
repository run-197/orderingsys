package com.graduationproject.orderingsys.Service.ServiceImpl;

import com.graduationproject.orderingsys.DAO.Customerorder_info;
import com.graduationproject.orderingsys.DAO.Dish;
import com.graduationproject.orderingsys.DAO.Order_information;
import com.graduationproject.orderingsys.DAO.Orderinfo_dishes;
import com.graduationproject.orderingsys.Mapper.Customerorder_infoMapper;
import com.graduationproject.orderingsys.Mapper.DishMapper;
import com.graduationproject.orderingsys.Mapper.Order_informationMapper;
import com.graduationproject.orderingsys.Mapper.Orderinfo_dishesMapper;
import com.graduationproject.orderingsys.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: orderingsys
 * @BelongsPackage: com.graduationproject.orderingsys.Service.ServiceImpl
 * @Author: Dongrun Li
 * @Date: 2023/3/28 11:56
 * @Description:
 */
@Service
public class OrderImpl implements OrderService {

    final
    DishMapper dishMapper;
    final
    Order_informationMapper order_informationMapper;

    final
    Orderinfo_dishesMapper orderinfo_dishesMapper;

    final
    Customerorder_infoMapper customerorder_infoMapper;

    public OrderImpl(Order_informationMapper order_informationMapper, Orderinfo_dishesMapper orderinfo_dishesMapper, Customerorder_infoMapper customerorder_infoMapper, DishMapper dishMapper) {
        this.order_informationMapper = order_informationMapper;
        this.orderinfo_dishesMapper = orderinfo_dishesMapper;
        this.customerorder_infoMapper = customerorder_infoMapper;
        this.dishMapper = dishMapper;
    }


    /**
     * @description: 增加新的订单信息，需要在三个表中添加记录。首先在订单信息中添加一条记录并且获取订单ID，
     *               之后在顾客订单这个关联表中添加记录，然后根据菜品数量信息在订单菜品表中添加信息
     * @param customer_ID: 顾客ID
     * @param dish_IDandNumber: 菜品数量信息
     * @param total_amount: 订单总金额
     * @return boolean
     * @author: Dongrun Li
     * @date: 2023/4/4 13:06
     */
    @Override
    public boolean addNewOrderInfomation(Integer customer_ID,Integer table_ID, Map<Integer, Integer> dish_IDandNumber,Float total_amount) {
        //先在订单信息表中添加记录，并获得订单ID
        Order_information order_information=new Order_information();
        order_information.setTable_ID(table_ID);
        order_information.setOrder_status("submitted");
        order_information.setItem_quantity(dish_IDandNumber.size());
        order_information.setTotal_amount(total_amount);
        order_information.setOrder_time(new Timestamp(System.currentTimeMillis()));
        order_informationMapper.addOrderinfo(order_information);
        Integer order_ID=order_information.getOrder_ID();

        //在顾客订单表中添加记录
        Customerorder_info customerorder_info=new Customerorder_info(customer_ID,order_ID);
        customerorder_infoMapper.addCustomerOrder(customerorder_info);

        //在订单菜品表中添加记录
        Iterator<Map.Entry<Integer, Integer>> entries = dish_IDandNumber.entrySet().iterator();
        List<Orderinfo_dishes> OrderdishesList =new ArrayList<>();
        while (entries.hasNext()) {
            Map.Entry<Integer, Integer> entry = entries.next();
            Integer dish_ID = entry.getKey();
            Integer dish_number = entry.getValue();
            OrderdishesList.add(new Orderinfo_dishes(order_ID,dish_ID,dish_number));
            Dish dish=dishMapper.queryDishByDishID(dish_ID);
            dish.setMonthly_sales(dish.getMonthly_sales()+dish_number);
            dishMapper.updateDish(dish);
        }
        System.out.println(OrderdishesList);
        for (Orderinfo_dishes it:OrderdishesList) {
            System.out.println(it);

        }
        orderinfo_dishesMapper.addOrderinfoDishes(OrderdishesList);

        return true;
    }

    /**
     * @description: 更新订单状态
     * @param order_ID: 订单ID
     * @param order_status: 订单状态
     * @return boolean
     * @author: Dongrun Li
     * @date: 2023/4/4 13:08
     */
    @Override
    public boolean updateOrderStatus(Integer order_ID, String order_status) {
        Order_information order_information=order_informationMapper.queryOrderinfoByOrderID(order_ID);
        order_information.setOrder_status(order_status);
        return order_informationMapper.updateOrderinfo(order_information)==1;
    }

    /**
     * @description: 根据顾客ID获取所有的订单信息
     * @param customer_ID: 顾客ID
     * @return java.util.List<com.graduationproject.orderingsys.DAO.Order_information>
     * @author: Dongrun Li
     * @date: 2023/4/4 13:08
     */
    @Override
    public List<Order_information> getAllOrder(Integer customer_ID) {
        List<Order_information> Order_informationList=new ArrayList<>();
        List<Integer> orderIDList=customerorder_infoMapper. queryOrderIDByCustomerID(customer_ID);
        for (Integer order_ID:  orderIDList) {
            Order_informationList.add(order_informationMapper.queryOrderinfoByOrderID(order_ID));
        }
        return Order_informationList;
    }

    /**
     * @description: 获取某个订单的所有菜品信息
     * @param order_ID: 订单ID
     * @return java.util.List<com.graduationproject.orderingsys.DAO.Orderinfo_dishes>
     * @author: Dongrun Li
     * @date: 2023/4/4 13:13
     */
    @Override
    public List<Orderinfo_dishes> getOrderDishes(Integer order_ID) {
        return orderinfo_dishesMapper.queryOrderinfoDishesByOrderID(order_ID);
    }

    /**
     * @description: 根据订单号获取订单信息
     * @param order_ID: 订单ID
     * @return com.graduationproject.orderingsys.DAO.Order_information
     * @author: Dongrun Li
     * @date: 2023/4/4 13:13
     */
    @Override
    public Order_information getOrderInfo(Integer order_ID) {
        return order_informationMapper.queryOrderinfoByOrderID(order_ID);
    }

}
