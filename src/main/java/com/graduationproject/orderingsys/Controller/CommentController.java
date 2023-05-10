package com.graduationproject.orderingsys.Controller;

import com.graduationproject.orderingsys.Paramtype.IDandNumber;
import com.graduationproject.orderingsys.Paramtype.IDandRating;
import com.graduationproject.orderingsys.Service.ServiceImpl.CommentImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @BelongsProject: orderingsys
 * @BelongsPackage: com.graduationproject.orderingsys.Controller
 * @Author: Dongrun Li
 * @Date: 2023/3/28 11:52
 * @Description:
 */

@RestController
@CrossOrigin
@RequestMapping("/comment")
public class CommentController {
    final
    CommentImpl commentimpl;

    public CommentController(CommentImpl commentimpl) {
        this.commentimpl = commentimpl;
    }

    @RequestMapping(value = "/getcommentbyID",produces = "application/json; charset=utf-8")
    @ResponseBody

    public String getcomment(Integer order_ID){
        System.out.println(commentimpl.getCommentByOrderID(order_ID));
        return  commentimpl.getCommentByOrderID(order_ID);
    }

    @RequestMapping("/addcomment")
    @ResponseBody
    public Boolean addcomment(Integer order_ID,String comment){
        return  commentimpl.addComment(order_ID,comment);
    }

    @PostMapping("/addRatings")
    @ResponseBody
    public Boolean addRating(@RequestBody IDandRating iDandRating){
        Map<Integer, Float> idandrating = IntStream.range(0, iDandRating.getDish_ID().size())
                .boxed()
                .collect(Collectors.toMap(iDandRating.getDish_ID()::get, iDandRating.getRating()::get));
        System.out.println(idandrating);
        return commentimpl.addNewRating(iDandRating.getCustomer_ID(),iDandRating.getOrder_ID(),idandrating);
    }
}
