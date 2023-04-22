package com.graduationproject.orderingsys.Mapper;

import com.graduationproject.orderingsys.DAO.Dish_type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @BelongsProject: orderingsys
 * @BelongsPackage: com.graduationproject.orderingsys.Mapper
 * @Author: Dongrun Li
 * @Date: 2023/4/16 20:27
 * @Description:这里不提供删除菜品类型的方法，因为要确保存在的每一个菜品都需要属于一种类型，仅在删除菜品时级联删除关系
 */

@Mapper
@Repository
public interface Dish_typeMapper {
    int addNewRelation(Dish_type dish_type);
    int updateRelationByDishID(Dish_type dish_type);
    String queryRelationByDishID(Integer dish_Id);
}
