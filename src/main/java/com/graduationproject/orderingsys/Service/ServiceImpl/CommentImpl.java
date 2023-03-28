package com.graduationproject.orderingsys.Service.ServiceImpl;

import com.graduationproject.orderingsys.DAO.Comment_picture;
import com.graduationproject.orderingsys.DAO.Order_information;
import com.graduationproject.orderingsys.DAO.Orderinfo_dishes;
import com.graduationproject.orderingsys.DAO.Rating;
import com.graduationproject.orderingsys.Mapper.*;
import com.graduationproject.orderingsys.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: orderingsys
 * @BelongsPackage: com.graduationproject.orderingsys.Service.ServiceImpl
 * @Author: Dongrun Li
 * @Date: 2023/3/28 11:55
 * @Description:
 */
@Service
public class CommentImpl implements CommentService {

    @Autowired
    Comment_pictureMapper comment_pictureMapper;

    @Autowired
    Order_informationMapper order_informationMapper;

    @Autowired
    Orderinfo_dishesMapper orderinfo_dishesMapper;

    @Autowired
    RatingMapper ratingMapper;

    @Autowired
    Customerorder_infoMapper customerorder_infoMapper;

    /**
     * @description: 根据评论上传的图片地址，评论，和订单号完成评论相关的数据存储
     * @param picAddressList: 评论图片地址
     * @param comment: 评论
     * @param Order_ID: 订单ID
     * @return boolean
     * @author: Dongrun Li
     * @date: 2023/3/28 14:45
     */
    public boolean alterNewComment(List<String> picAddressList, String comment, Integer Order_ID){
        boolean isAddpic,isAddcomment;//标志是否成功完成添加图片和评论

        if(picAddressList.isEmpty())//无图片，无需添加
            isAddpic=true;

        else {//有图片，在C_P表中添加记录
            List<Comment_picture> CommentPicList = new ArrayList<>();
            for (String s : picAddressList) {
                Comment_picture comment_picture = new Comment_picture(Order_ID, s);
                CommentPicList.add(comment_picture);
            }
            isAddpic = comment_pictureMapper.addCommentPic(CommentPicList) == 1;
        }

        //更新订单记录，其实只是更新了评论，该方法确保在订单信息表中已存在记录并取出订单号后执行
        Order_information order_information=order_informationMapper.queryOrderinfoByOrderID(Order_ID);
        order_information.setComment(comment);
        isAddcomment=order_informationMapper.updateOrderinfo(order_information)==1;
        return isAddpic && isAddcomment;
    }

//    public boolean alterComment(List<String> picAddressList, String comment, Integer Order_ID){
//
//        return true;
//    }

    public String getCommentByOrderID(Integer order_ID){
        return order_informationMapper.queryOrderinfoByOrderID(order_ID).getComment();
    }

    public List<String> gerPicaddressByorderID(Integer order_ID){
        List<Comment_picture> CommentPictureList=comment_pictureMapper.queryCommentPicByOrderID(order_ID);
        List<String> picAddressList=new ArrayList<>();
        for (Comment_picture comment_picture : CommentPictureList) {
            String picAddress = comment_picture.getPicture_address();
            picAddressList.add(picAddress);
        }
        return picAddressList;
    }

    public Map<Integer,String> showCommentByDishID(Integer dish_ID){
        List<Orderinfo_dishes> OrderinfodishesList=orderinfo_dishesMapper.queryOrderinfoDishesByDishID(dish_ID);
        Map<Integer,String> customerIDcommentMap=new HashMap<>();
        for (Orderinfo_dishes orderinfo_dishes : OrderinfodishesList) {
            Integer order_ID = customerorder_infoMapper.queryCustomerIDByOrderID(orderinfo_dishes.getOrder_ID());
            String comment = order_informationMapper.queryOrderinfoByOrderID(orderinfo_dishes.getOrder_ID()).getComment();
            customerIDcommentMap.put(order_ID, comment);
        }
        return customerIDcommentMap;

    }

    public boolean addNewRating(Integer customer_ID, Integer order_ID, Integer dish_ID, Float rating){
        Rating rating0=new Rating(customer_ID,order_ID,dish_ID,rating);
        return ratingMapper.addRating(rating0)==1;
    }

    public Float calculateRatingByDishID(Integer dish_ID){
        Float averating= (float) 0.0;
        int i=0;
        List<Rating> ratingList=ratingMapper.queryRatingByDishID(dish_ID);
        for(;i < ratingList.size();i++)
            averating+=ratingList.get(i).getRating();
        return averating/i;
    }
}
