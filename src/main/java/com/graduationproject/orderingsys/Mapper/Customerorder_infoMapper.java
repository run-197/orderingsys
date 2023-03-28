package com.graduationproject.orderingsys.Mapper;

import com.graduationproject.orderingsys.DAO.Customerorder_info;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @BelongsProject: orderingsys
 * @BelongsPackage: com.graduationproject.orderingsys.Mapper
 * @Author: Dongrun Li
 * @Date: 2023/3/20 23:42
 * @Description: 对用户和账单之间的关系管理，只支持新增记录和查询记录
 */
@Mapper
@Repository
public interface Customerorder_infoMapper {

    /**
     * @description: 增加一条用户和账单的记录
     * @param customerorder_info: 用户账单信息
     * @return int
     * @author: Dongrun Li
     * @date: 2023/3/27 16:52
     */
    int addCustomerOrder(Customerorder_info customerorder_info);

    /**
     * @description: 通过用户ID查找该用户产生的所有账单ID
     * @param customer_ID: 用户ID
     * @return java.util.List<java.lang.Integer>
     * @author: Dongrun Li
     * @date: 2023/3/27 16:42
     */
    List<Integer> queryOrderIDByCustomerID(Integer customer_ID);

    /**
     * @description: 通过用户ID查找该用户产生的所有账单ID
     * @param order_ID: 用户ID
     * @return java.util.List<java.lang.Integer>
     * @author: Dongrun Li
     * @date: 2023/3/27 16:42
     */
    Integer queryCustomerIDByOrderID(Integer order_ID);
}
