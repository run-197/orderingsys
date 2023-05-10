package com.graduationproject.orderingsys.Service.ServiceImpl;

import com.graduationproject.orderingsys.DAO.AllDishOfType;
import com.graduationproject.orderingsys.DAO.Dish;
import com.graduationproject.orderingsys.DAO.Dish_picture;
import com.graduationproject.orderingsys.Mapper.AllDishOfTypeMapper;
import com.graduationproject.orderingsys.Mapper.DishMapper;
import com.graduationproject.orderingsys.Mapper.Dish_pictureMapper;
import com.graduationproject.orderingsys.Service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: orderingsys
 * @BelongsPackage: com.graduationproject.orderingsys.Service.ServiceImpl
 * @Author: Dongrun Li
 * @Date: 2023/3/22 0:24
 * @Description:
 */
@Service
public class DishImpl implements DishService {

    final
    AllDishOfTypeMapper allDishOfTypeMapper;

    final
    Dish_pictureMapper dish_pictureMapper;

    final
    DishMapper dishMapper;

    public DishImpl(DishMapper dishMapper, Dish_pictureMapper dish_pictureMapper, AllDishOfTypeMapper allDishOfTypeMapper) {
        this.dishMapper = dishMapper;
        this.dish_pictureMapper = dish_pictureMapper;
        this.allDishOfTypeMapper = allDishOfTypeMapper;
    }

    @Override
    public List<Dish> getDishByIDList(List<Integer> IDList) {
        List<Dish> dishesList=new ArrayList<>();
        for (Integer ID:IDList) {
            dishesList.add(dishMapper.queryDishByDishID(ID));
        }
        return dishesList;
    }

    @Override
    public List<AllDishOfType> getAllDishnew() {
        List<String> dishTypeList=allDishOfTypeMapper.queryAllType();
        List<AllDishOfType> allDishOfTypeList=new ArrayList<>();
        for (String the_type:dishTypeList) {
            AllDishOfType allDishOfType=allDishOfTypeMapper.queryAllDishOfTypeByType(the_type);
            allDishOfTypeList.add(allDishOfType);
        }
        return allDishOfTypeList;
    }

    /**
     * @description: 获取所有菜品
     * @return java.util.List<com.graduationproject.orderingsys.DAO.Dish>
     * @author: Dongrun Li
     * @date: 2023/4/16 11:16
     */

    @Override
    public List<Dish> getAllDish() {
        return dishMapper.queryDish();
    }
    /**
     * @description: 通过菜品ID获取菜品信息
     * @param dish_ID: 菜品ID
     * @return com.graduationproject.orderingsys.DAO.Dish
     * @author: Dongrun Li
     * @date: 2023/4/16 11:17
     */
    @Override
    public Dish getDishByID(Integer dish_ID) {
        return dishMapper.queryDishByDishID(dish_ID);
    }

    /**
     * @description: 通过菜品ID获取相关的菜品图片地址
     * @param dish_ID: 菜品ID
     * @return java.util.List<com.graduationproject.orderingsys.DAO.Dish_picture>
     * @author: Dongrun Li
     * @date: 2023/4/16 13:40
     */
    @Override
    public List<Dish_picture> getDishpicByID(Integer dish_ID) {
        return dish_pictureMapper.queryDishPicByDishID(dish_ID);
    }

    /**
     * @description: 增加菜品信息
     * @param dish_name: 菜品名称
     * @param dish_nuitprice: 菜品单价
     * @param dish_quantity: 菜品菜量
     * @param dish_description: 菜品描述
     * @return boolean
     * @author: Dongrun Li
     * @date: 2023/4/4 12:58
     */
    @Override
    public boolean addNewDish(String dish_name, Float dish_nuitprice, String dish_quantity, String dish_description) {
        Dish dish=new Dish();
        dish.setDish_name(dish_name);
        dish.setDish_nuitprice(dish_nuitprice);
        dish.setDish_quantity(dish_quantity);
        dish.setDish_description(dish_description);
        return dishMapper.addDish(dish)==1;
    }

    /**
     * @description: 增加菜品信息（方法重写）
     * @param dish_name: 菜品名称
     * @param dish_nuitprice: 菜品单价
     * @param descriptionORquantity: 描述or菜量
     * @param isDescription: 是否为描述，或是菜量
     * @return boolean
     * @author: Dongrun Li
     * @date: 2023/4/4 12:59
     */
    @Override
    public boolean addNewDish(String dish_name, Float dish_nuitprice, String descriptionORquantity, boolean isDescription) {
        Dish dish=new Dish();
        dish.setDish_name(dish_name);
        dish.setDish_nuitprice(dish_nuitprice);
        if(isDescription)
            dish.setDish_description(descriptionORquantity);
        else
            dish.setDish_quantity(descriptionORquantity);
        return dishMapper.addDish(dish)==1;
    }

    /**
     * @description: 增加菜品信息（重写）
     * @param dish_name: 菜品名称
     * @param dish_nuitprice: 菜品单价
     * @return boolean
     * @author: Dongrun Li
     * @date: 2023/4/4 13:01
     */
    @Override
    public boolean addNewDish(String dish_name, Float dish_nuitprice) {
        Dish dish=new Dish();
        dish.setDish_name(dish_name);
        dish.setDish_nuitprice(dish_nuitprice);
        return dishMapper.addDish(dish)==1;
    }

    /**
     * @description: 更新菜品价格
     * @param dish_ID: 菜品ID
     * @param dish_nuitprice:菜品单价
     * @return boolean
     * @author: Dongrun Li
     * @date: 2023/4/4 13:01
     */
    @Override
    public boolean updateDishPrice(Integer dish_ID, Float dish_nuitprice) {
        Dish dish =dishMapper.queryDishByDishID(dish_ID);
        dish.setDish_nuitprice(dish_nuitprice);
        return dishMapper.updateDish(dish)==1;
    }

    /**
     * @description: 更新菜品菜量
     * @param dish_ID: 菜品ID
     * @param dish_quantity: 菜量
     * @return boolean
     * @author: Dongrun Li
     * @date: 2023/4/4 13:01
     */
    @Override
    public boolean updateDishQuantity(Integer dish_ID, String dish_quantity) {
        Dish dish =dishMapper.queryDishByDishID(dish_ID);
        dish.setDish_quantity(dish_quantity);
        return dishMapper.updateDish(dish)==1;
    }

    /**
     * @description: 更新菜品描述
     * @param dish_ID: 菜品ID
     * @param dish_description: 菜品描述
     * @return boolean
     * @author: Dongrun Li
     * @date: 2023/4/4 13:02
     */
    @Override
    public boolean updateDishDescription(Integer dish_ID, String dish_description) {
        Dish dish =dishMapper.queryDishByDishID(dish_ID);
        dish.setDish_description(dish_description);
        return dishMapper.updateDish(dish)==1;
    }

    /**
     * @description: 更新月销量（根据cron表达式，每个月的一号自动将所有菜品的月销量置为0）
     * @author: Dongrun Li
     * @date: 2023/4/4 13:02
     */
    @Override
    @Scheduled(cron = "0 0 0 1 * ?")
    public void updateDishSales() {
        dishMapper.setSaleszero();
    }

    /**
     * @description: 更新菜品评分（不推荐使用，人为修改菜品的评价）
     * @param dish_ID: 菜品ID
     * @param dish_rating: 菜品评分
     * @return boolean
     * @author: Dongrun Li
     * @date: 2023/4/4 13:03
     */
    @Override
    public boolean updateDishRating(Integer dish_ID, Float dish_rating) {
        Dish dish =dishMapper.queryDishByDishID(dish_ID);
        dish.setDish_rating(dish_rating);
        return dishMapper.updateDish(dish)==1;
    }

    /**
     * @description: 删除菜品
     * @param dish_ID: 菜品ID
     * @return boolean
     * @author: Dongrun Li
     * @date: 2023/4/4 13:04
     */
    @Override
    public boolean delDish(Integer dish_ID) {
        return dishMapper.delDishByDishID(dish_ID)==1;
    }

}
