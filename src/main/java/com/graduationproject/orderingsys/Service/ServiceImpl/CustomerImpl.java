package com.graduationproject.orderingsys.Service.ServiceImpl;

import com.graduationproject.orderingsys.DAO.Customer;
import com.graduationproject.orderingsys.Mapper.CustomerMapper;
import com.graduationproject.orderingsys.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: orderingsys
 * @BelongsPackage: com.graduationproject.orderingsys.Service.ServiceImpl
 * @Author: Dongrun Li
 * @Date: 2023/3/22 0:23
 * @Description:
 */
@Service
public class CustomerImpl implements CustomerService {

    @Autowired
    CustomerMapper customerMapper;

    public boolean addNewCustomer(String customer_nickname, String phone_number, String avatar_address){
        Customer customer=new Customer();
        customer.setCustomer_nickname(customer_nickname);
        customer.setPhone_number(phone_number);
        customer.setAvatar_address(avatar_address);
        return customerMapper.addCustomer(customer)==1;
    }
    public boolean updateCustomer(Integer customer_ID, String customer_nickname, String phone_number, String avatar_address){
        Customer customer=customerMapper.queryCustomerByCustomerID(customer_ID);
        customer.setCustomer_nickname(customer_nickname);
        customer.setPhone_number(phone_number);
        customer.setAvatar_address(avatar_address);
        return customerMapper.addCustomer(customer)==1;
    }
}
