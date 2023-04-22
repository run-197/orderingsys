package com.graduationproject.orderingsys.Controller;

import com.graduationproject.orderingsys.DAO.AllDishOfType;
import com.graduationproject.orderingsys.DAO.Customer;
import com.graduationproject.orderingsys.DAO.Dish;
import com.graduationproject.orderingsys.DAO.Dish_picture;
import com.graduationproject.orderingsys.Paramtype.DishIDList;
import com.graduationproject.orderingsys.Service.ServiceImpl.DishImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: orderingsys
 * @BelongsPackage: com.graduationproject.orderingsys.Controller
 * @Author: Dongrun Li
 * @Date: 2023/3/21 16:23
 * @Description:
 */
@RestController

@CrossOrigin
@RequestMapping("/dish")
public class DishController {

    public final DishImpl dishimpl;

    public DishController(DishImpl dishimpl) {
        this.dishimpl = dishimpl;
    }

    @RequestMapping("/getdishoftype")
    @ResponseBody
    public List<AllDishOfType> dishesOfTypeList(){
        return dishimpl.getAllDishnew();
    }

    @RequestMapping("/getalldish")
    @ResponseBody
    public List<Dish> dishesList(){
        return dishimpl.getAllDish();
    }

    @RequestMapping("/getdishbyID")
    @ResponseBody
    public Dish getDish(Integer dish_ID){
        return dishimpl.getDishByID(dish_ID);
    }

    @RequestMapping("/getdishpicbyID")
    @ResponseBody
    public List<Dish_picture> getDishPic(Integer dish_ID){
        return dishimpl.getDishpicByID(dish_ID);
    }

    @PostMapping("/getdishbyIDList")
    @ResponseBody
    public List<Dish> getDishByIDList(@RequestBody DishIDList dishIDList){
        return dishimpl.getDishByIDList(dishIDList.getIdList());
    }
}
