package com.graduationproject.orderingsys.Service;

import com.graduationproject.orderingsys.DAO.Customer;

/**
 * @BelongsProject: orderingsys
 * @BelongsPackage: com.graduationproject.orderingsys.Service
 * @Author: Dongrun Li
 * @Date: 2023/3/22 0:13
 * @Description:
 */
public interface CustomerService {
    Customer queryCustomerByID(Integer customer_ID);
    boolean addNewCustomer(String customer_nickname,String phone_number,String avatar_address);
    boolean updateCustomer(Integer customer_ID,String customer_nickname,String phone_number,String avatar_address);
}
