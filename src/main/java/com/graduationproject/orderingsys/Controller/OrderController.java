package com.graduationproject.orderingsys.Controller;

import com.graduationproject.orderingsys.DAO.Customer;
import com.graduationproject.orderingsys.Service.ServiceImpl.CustomerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

    public OrderController(CustomerImpl customerimpl) {
        this.customerimpl = customerimpl;
    }

    @RequestMapping("/test")
    @ResponseBody
    public Customer hello(){

        System.out.println("Hello world");
        return customerimpl.queryCustomerByID(1);
    }
}
