package com.graduationproject.orderingsys.Controller;

import com.graduationproject.orderingsys.DAO.*;
import com.graduationproject.orderingsys.Paramtype.DishIDList;
import com.graduationproject.orderingsys.Paramtype.IDandNumber;
import com.graduationproject.orderingsys.Service.ServiceImpl.CustomerImpl;
import com.graduationproject.orderingsys.Service.ServiceImpl.OrderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @BelongsProject: orderingsys
 * @BelongsPackage: com.graduationproject.orderingsys.Controller
 * @Author: Dongrun Li
 * @Date: 2023/3/21 16:23
 * @Description:
 */
@RestController
@CrossOrigin
@RequestMapping("/order")
public class OrderController {

    final
    CustomerImpl customerimpl;

    final
    OrderImpl orderimpl;

    public OrderController(CustomerImpl customerimpl, OrderImpl orderimpl) {
        this.customerimpl = customerimpl;
        this.orderimpl = orderimpl;
    }


    @RequestMapping("/getallorderbyID")
    @ResponseBody
    public List<Order_information> getOrderList(Integer customer_ID){
        return orderimpl.getAllOrder(customer_ID);
    }

    @RequestMapping("/getallorder")
    @ResponseBody
    public List<Order_information> getAllOrderList(){
        return orderimpl.getAllOrder();
    }

    @RequestMapping("/getorderbyorderID")
    @ResponseBody
    public Order_information getOrderInfo(Integer order_ID){
        return orderimpl.getOrderInfo(order_ID);
    }

    @RequestMapping("/getdishinfobyorderID")
    @ResponseBody
    public List<Orderinfo_dishes> getdishesinfo(Integer order_ID){
        return orderimpl.getOrderDishes(order_ID);
    }

    @PostMapping("/submitorder")
    @ResponseBody
    public Boolean submitOrder(@RequestBody IDandNumber iDandNumber){
        Map<Integer, Integer> idandnum = IntStream.range(0, iDandNumber.getIdList().size())
                .boxed()
                .collect(Collectors.toMap(iDandNumber.getIdList()::get, iDandNumber.getNumList()::get));
        System.out.println(idandnum);
        return orderimpl.addNewOrderInfomation(iDandNumber.getCustomer_ID(),iDandNumber.getTable_ID(),idandnum,iDandNumber.getDishAmout());
    }

    @RequestMapping("/serving")
    @ResponseBody
    public Boolean dishServing(Integer order_ID){
        return orderimpl.updateOrderStatus(order_ID,"settled");
    }

    @RequestMapping("/getdishoforder")
    @ResponseBody
    public List<OrderdishInfo> getDishofOrder(Integer order_ID){
        return orderimpl.getDishofOrder(order_ID);
    }
}
