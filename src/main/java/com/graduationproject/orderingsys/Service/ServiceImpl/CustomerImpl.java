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

    final
    CustomerMapper customerMapper;

    public CustomerImpl(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    /**
     * @description: 添加新的顾客信息
     * @param customer_nickname: 顾客昵称
     * @param phone_number: 手机号
     * @param avatar_address: 头像地址
     * @return boolean
     * @author: Dongrun Li
     * @date: 2023/4/4 12:55
     */
    @Override
    public boolean addNewCustomer(String customer_nickname, String phone_number, String avatar_address){
        Customer customer=new Customer();
        customer.setCustomer_nickname(customer_nickname);
        customer.setPhone_number(phone_number);
        customer.setAvatar_address(avatar_address);
        return customerMapper.addCustomer(customer)==1;
    }

    /**
     * @description: 修改顾客个人信息
     * @param customer_ID: 顾客ID
     * @param customer_nickname: 顾客昵称
     * @param phone_number: 手机号
     * @param avatar_address: 头像地址
     * @return boolean
     * @author: Dongrun Li
     * @date: 2023/4/4 12:56
     */
    @Override
    public boolean updateCustomer(Integer customer_ID, String customer_nickname, String phone_number, String avatar_address){
        Customer customer=customerMapper.queryCustomerByCustomerID(customer_ID);
        customer.setCustomer_nickname(customer_nickname);
        customer.setPhone_number(phone_number);
        customer.setAvatar_address(avatar_address);
        return customerMapper.addCustomer(customer)==1;
    }
}
