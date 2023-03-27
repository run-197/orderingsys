package com.graduationproject.orderingsys.Mapper;


import com.graduationproject.orderingsys.DAO.Comment_picture;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @BelongsProject: orderingsys
 * @BelongsPackage: com.graduationproject.orderingsys.Mapper
 * @Author: Dongrun Li
 * @Date: 2023/3/20 23:39
 * @Description:
 */
@Mapper
@Repository
public interface Comment_pictureMapper {

    /**
     * @description: 根据订单ID查询图片地址，返回符合某个订单ID的所有对象
     * @param order_ID: 订单ID
     * @return java.util.List<com.graduationproject.orderingsys.DAO.Comment_picture>
     * @author: Dongrun Li
     * @date: 2023/3/27 15:33
     */
    List<Comment_picture> queryCommentPicByOrderID(Integer order_ID);

    
    /**
     * @description: 根据某个评论所有上传图片的List新增多条记录
     * @param CommentPicList: C_P实体类列表
     * @return int
     * @author: Dongrun Li
     * @date: 2023/3/27 15:41
     */
    int addCommentPic(List<Comment_picture> CommentPicList);


    /**
     * @description: 根据订单ID删除图片地址，这里只支持在删除评论同时删除图片，暂不实现在其他阶段修改图片地址
     * @param order_ID: 订单ID
     * @return int
     * @author: Dongrun Li
     * @date: 2023/3/27 15:44
     */
    int delCommentPicByOrderID(Integer order_ID);

}
