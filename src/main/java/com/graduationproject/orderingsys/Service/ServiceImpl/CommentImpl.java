package com.graduationproject.orderingsys.Service.ServiceImpl;

import com.graduationproject.orderingsys.DAO.*;
import com.graduationproject.orderingsys.Mapper.*;
import com.graduationproject.orderingsys.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @BelongsProject: orderingsys
 * @BelongsPackage: com.graduationproject.orderingsys.Service.ServiceImpl
 * @Author: Dongrun Li
 * @Date: 2023/3/28 11:55
 * @Description:
 */
@Service
public class CommentImpl implements CommentService {

    final
    DishMapper dishMapper;

    final
    Comment_pictureMapper comment_pictureMapper;

    final
    Order_informationMapper order_informationMapper;

    final
    Orderinfo_dishesMapper orderinfo_dishesMapper;

    final
    RatingMapper ratingMapper;

    final
    Customerorder_infoMapper customerorder_infoMapper;

    public CommentImpl(Comment_pictureMapper comment_pictureMapper, Order_informationMapper order_informationMapper, Orderinfo_dishesMapper orderinfo_dishesMapper, RatingMapper ratingMapper, Customerorder_infoMapper customerorder_infoMapper,DishMapper dishMapper) {
        this.comment_pictureMapper = comment_pictureMapper;
        this.order_informationMapper = order_informationMapper;
        this.orderinfo_dishesMapper = orderinfo_dishesMapper;
        this.ratingMapper = ratingMapper;
        this.customerorder_infoMapper = customerorder_infoMapper;
        this.dishMapper = dishMapper;
    }

    /**
     * @description: 根据评论上传的图片地址，评论，和订单号完成评论相关的数据存储
     * @param picAddressList: 评论图片地址
     * @param comment: 评论
     * @param Order_ID: 订单ID
     * @return boolean
     * @author: Dongrun Li
     * @date: 2023/3/28 14:45
     */
    @Override
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

    /**
     * @description: 获取某个订单的评论
     * @param order_ID: 订单ID
     * @return java.lang.String
     * @author: Dongrun Li
     * @date: 2023/4/4 12:50
     */
    @Override
    public String getCommentByOrderID(Integer order_ID){
        return order_informationMapper.queryOrderinfoByOrderID(order_ID).getComment();
    }

    /**
     * @description: 根据订单ID获取评论的图片地址
     * @param order_ID: 订单ID
     * @return java.util.List<java.lang.String>
     * @author: Dongrun Li
     * @date: 2023/4/4 12:51
     */
    @Override
    public List<String> getPicaddressByorderID(Integer order_ID){
        List<Comment_picture> CommentPictureList=comment_pictureMapper.queryCommentPicByOrderID(order_ID);
        List<String> picAddressList=new ArrayList<>();
        for (Comment_picture comment_picture : CommentPictureList) {
            String picAddress = comment_picture.getPicture_address();
            picAddressList.add(picAddress);
        }
        return picAddressList;
    }

    /**
     * @description: 获得某个菜品的所有评论（由于顾客一般是会对于订单而非单独对菜品评价，这里获取的评价是包含菜品的订单的评价）
     * @param dish_ID: 菜品ID
     * @return java.util.Map<java.lang.Integer,java.lang.String>
     * @author: Dongrun Li
     * @date: 2023/4/4 12:53
     */
    @Override
    public Map<Integer,String> showCommentByDishID(Integer dish_ID){
        List<Orderinfo_dishes> OrderinfodishesList=orderinfo_dishesMapper.queryOrderinfoDishesByDishID(dish_ID);
        Map<Integer,String> customerIDcommentMap=new HashMap<>();
        for (Orderinfo_dishes orderinfo_dishes : OrderinfodishesList) {
            Integer customer_ID = customerorder_infoMapper.queryCustomerIDByOrderID(orderinfo_dishes.getOrder_ID());
            String comment = order_informationMapper.queryOrderinfoByOrderID(orderinfo_dishes.getOrder_ID()).getComment();
            customerIDcommentMap.put(customer_ID, comment);
        }
        return customerIDcommentMap;

    }

    /**
     * @description: 添加评分
     * @param customer_ID: 顾客ID
     * @param order_ID: 订单ID
     * @return boolean
     * @author: Dongrun Li
     * @date: 2023/4/4 12:54
     */
    @Override
    public boolean addNewRating(Integer customer_ID, Integer order_ID, Map<Integer,Float> dish_IDandRating){
        Iterator<Map.Entry<Integer, Float>> entries = dish_IDandRating.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<Integer, Float> entry = entries.next();
            Integer dish_ID = entry.getKey();
            Float rating = entry.getValue();
            ratingMapper.addRating(new Rating(customer_ID,order_ID,dish_ID,rating));
            List<Rating> ratingList=ratingMapper.queryRatingByDishID(dish_ID);
            int i=0;
            Float avgrating=(float) 0.0;
            for(;i < ratingList.size();i++)
                avgrating+=ratingList.get(i).getRating();
            avgrating/=i;
            Dish dish=dishMapper.queryDishByDishID(dish_ID);
            dish.setDish_rating(avgrating);
            dishMapper.updateDish(dish);
        }
        return true;
    }

    /**
     * @description: 计算菜品评分
     * @param dish_ID: 菜品ID
     * @return java.lang.Float
     * @author: Dongrun Li
     * @date: 2023/4/4 12:55
     */
    @Override
    public Float calculateRatingByDishID(Integer dish_ID){
        Float averating= (float) 0.0;
        int i=0;
        List<Rating> ratingList=ratingMapper.queryRatingByDishID(dish_ID);
        for(;i < ratingList.size();i++)
            averating+=ratingList.get(i).getRating();
        return averating/i;
    }

    @Override
    public boolean addComment(Integer order_ID, String comment) {
        Order_information order_information= order_informationMapper.queryOrderinfoByOrderID(order_ID);
        order_information.setComment(comment);
        order_information.setOrder_status("commented");
        return order_informationMapper.updateOrderinfo(order_information)==1;
    }
}
