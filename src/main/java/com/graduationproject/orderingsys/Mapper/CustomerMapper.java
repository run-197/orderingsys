package com.graduationproject.orderingsys.Mapper;

import com.graduationproject.orderingsys.DAO.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @BelongsProject: orderingsys
 * @BelongsPackage: com.graduationproject.orderingsys.Mapper
 * @Author: Dongrun Li
 * @Date: 2023/3/20 23:40
 * @Description:
 */
@Mapper
@Repository
public interface CustomerMapper {

    /**
     * @description: 通过用户ID查询用户信息，返回该用户的实体类
     * @param customer_ID: 用户ID
     * @return com.graduationproject.orderingsys.DAO.Customer
     * @author: Dongrun Li
     * @date: 2023/3/27 16:29
     */
    Customer queryCustomerByCustomerID(Integer customer_ID);

    /**
     * @description: 更新用户信息，返回更新后的用户信息
     * @param customer: 待更改的用户信息
     * @return int
     * @author: Dongrun Li
     * @date: 2023/3/27 16:33
     */
    int updateCustomer(Customer customer);

    /**
     * @description: 通过用户ID删除用户信息
     * @param customer_ID: 用户ID
     * @return int
     * @author: Dongrun Li
     * @date: 2023/3/27 16:32
     */
    int delCustomerByCustomerID(Integer customer_ID);

    /**
     * @description: 新增一条用户信息的记录
     * @param customer: 用户信息
     * @return int
     * @author: Dongrun Li
     * @date: 2023/3/27 16:36
     */
    int addCustomer(Customer customer);

}
