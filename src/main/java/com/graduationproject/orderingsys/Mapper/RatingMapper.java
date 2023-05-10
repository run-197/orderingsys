package com.graduationproject.orderingsys.Mapper;

import com.graduationproject.orderingsys.DAO.Rating;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @BelongsProject: orderingsys
 * @BelongsPackage: com.graduationproject.orderingsys.Mapper
 * @Author: Dongrun Li
 * @Date: 2023/3/20 23:47
 * @Description:
 */
@Mapper
@Repository
public interface RatingMapper {

    /**
     * @description: 增加评分记录
     * @param rating: 评分信息
     * @return int
     * @author: Dongrun Li
     * @date: 2023/3/27 20:05
     */
    int addRating(Rating rating);

    /**
     * @description: 查询某个菜品的所有评分信息
     * @param dish_ID: 菜品ID
     * @return java.util.List<com.graduationproject.orderingsys.DAO.Rating>
     * @author: Dongrun Li
     * @date: 2023/3/27 20:08
     */
    List<Rating> queryRatingByDishID(Integer dish_ID);


    /**
     * @description: 根据菜品ID查找评分条数
     * @param dish_ID:
     * @return int
     * @author: Dongrun Li
     * @date: 2023/5/4 14:54
     */
    //int getCountofDish(Integer dish_ID);

    /**
     * @description: 更新评分信息
     * @param rating: 评分信息
     * @return int
     * @author: Dongrun Li
     * @date: 2023/3/27 20:09
     */
    int updateRating(Rating rating);
}
