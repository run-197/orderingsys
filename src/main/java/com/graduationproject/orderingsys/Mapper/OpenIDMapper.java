package com.graduationproject.orderingsys.Mapper;

import com.graduationproject.orderingsys.DAO.OpenID;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @BelongsProject: orderingsys
 * @BelongsPackage: com.graduationproject.orderingsys.Mapper
 * @Author: Dongrun Li
 * @Date: 2023/4/27 20:46
 * @Description:
 */
@Mapper
@Repository
public interface OpenIDMapper {
    Integer queryCustomer_ID(String openID);
    Integer addNewOpenID(OpenID openID);
}
