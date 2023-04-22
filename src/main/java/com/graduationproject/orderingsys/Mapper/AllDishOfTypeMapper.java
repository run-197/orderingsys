package com.graduationproject.orderingsys.Mapper;

import com.graduationproject.orderingsys.DAO.AllDishOfType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @BelongsProject: orderingsys
 * @BelongsPackage: com.graduationproject.orderingsys.Mapper
 * @Author: Dongrun Li
 * @Date: 2023/4/16 20:49
 * @Description:
 */
@Mapper
@Repository
public interface AllDishOfTypeMapper {
    List<String> queryAllType();
    AllDishOfType queryAllDishOfTypeByType(String the_type);
}
