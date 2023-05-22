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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<AllDishOfType> dishesOfTypeList() {
        return dishimpl.getAllDishnew();
    }

    @RequestMapping("/getalldish")
    @ResponseBody
    public List<Dish> dishesList() {
        return dishimpl.getAllDish();
    }

    @RequestMapping("/getdishbyID")
    @ResponseBody
    public Dish getDish(Integer dish_ID) {
        return dishimpl.getDishByID(dish_ID);
    }

    @RequestMapping("/getdishpicbyID")
    @ResponseBody
    public List<Dish_picture> getDishPic(Integer dish_ID) {
        return dishimpl.getDishpicByID(dish_ID);
    }

    @RequestMapping("/adddishpic")
    @ResponseBody
    public Boolean addDishPic(Integer dish_ID,String pic_address) {
        return dishimpl.addDishPic(dish_ID,pic_address);
    }

    @PostMapping("/getdishbyIDList")
    @ResponseBody
    public List<Dish> getDishByIDList(@RequestBody DishIDList dishIDList) {
        return dishimpl.getDishByIDList(dishIDList.getIdList());
    }

    @RequestMapping("/updateDishByID")
    @ResponseBody
    public Boolean updateDish(Integer dish_ID, String dish_name, Float dish_nuitprice,
                              String dish_description, String type) {
        return dishimpl.updateDish(dish_ID,dish_name,dish_nuitprice,dish_description,"",type);
    }

    @RequestMapping("/updateDishstatus")
    @ResponseBody
    public Boolean updateDishstatus(Integer dish_ID, String dish_quantity) {
        return dishimpl.updateDishQuantity(dish_ID, dish_quantity);
    }

    @RequestMapping("/delDishbyID")
    @ResponseBody
    public Boolean delDishbyID(Integer dish_ID) {
        return dishimpl.delDish(dish_ID);
    }

    @RequestMapping("/getalltype")
    @ResponseBody
    public List<String> getAlldishType() {
        return dishimpl.getAlltype();
    }

    @RequestMapping("/addnewdish")
    @ResponseBody
    public Integer addNewDish(String dish_name, Float dish_nuitprice, String dish_quantity,
                              String dish_description, String picture_address, String type) {
        return dishimpl.addNewDish(dish_name,dish_nuitprice,dish_quantity,dish_description,picture_address,type);
    }

}
