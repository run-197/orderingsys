package com.graduationproject.orderingsys.Service;

import com.graduationproject.orderingsys.DAO.Comment_picture;

import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: orderingsys
 * @BelongsPackage: com.graduationproject.orderingsys.Service
 * @Author: Dongrun Li
 * @Date: 2023/3/28 11:55
 * @Description:
 */
public interface CommentService {
    boolean alterNewComment(List<String> picAddressList,String comment,Integer Order_ID);
//    boolean alterComment(List<String> picAddressList,String comment,Integer Order_ID);//和上一个功能似乎重复了
    String getCommentByOrderID(Integer order_ID);
    List<String> gerPicaddressByorderID(Integer order_ID);
    Map<Integer,String> showCommentByDishID(Integer dish_ID);

    boolean addNewRating(Integer customer_ID,Integer order_ID,Integer dish_ID,Float rating);
    Float calculateRatingByDishID(Integer dish_ID);
}
