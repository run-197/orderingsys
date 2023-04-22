package com.graduationproject.orderingsys.Service;

import com.graduationproject.orderingsys.DAO.AllDishOfType;
import com.graduationproject.orderingsys.DAO.Dish;
import com.graduationproject.orderingsys.DAO.Dish_picture;

import java.util.List;

/**
 * @BelongsProject: orderingsys
 * @BelongsPackage: com.graduationproject.orderingsys.Service
 * @Author: Dongrun Li
 * @Date: 2023/3/22 0:16
 * @Description:
 */
public interface DishService {
    List<Dish> getDishByIDList(List<Integer> IDList);
    List<Dish_picture> getDishpicByID(Integer dish_ID);
    List<AllDishOfType> getAllDishnew();
    List<Dish> getAllDish();
    Dish getDishByID(Integer dish_ID);
    boolean addNewDish(String dish_name,Float dish_nuitprice,String dish_quantity,String dish_description);
    boolean addNewDish(String dish_name,Float dish_nuitprice,String descriptionORquantity,boolean isDescription);
    boolean addNewDish(String dish_name,Float dish_nuitprice);
    boolean updateDishPrice(Integer dish_ID,Float dish_nuitprice);
    boolean updateDishQuantity(Integer dish_ID,String dish_quantity);
    boolean updateDishDescription(Integer dish_ID,String dish_description);
    void updateDishSales();
    boolean updateDishRating(Integer dish_ID,Float dish_rating);
    boolean delDish(Integer dish_ID);
}
