package com.graduationproject.orderingsys.Service.ServiceImpl;

import com.graduationproject.orderingsys.DAO.*;
import com.graduationproject.orderingsys.Mapper.*;
import com.graduationproject.orderingsys.Service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

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
    DishSalesMapper dishSalesMapper;
    final
    Orderinfo_dishesMapper orderinfo_dishesMapper;
    final
    Dish_typeMapper dish_typeMapper;
    final
    AllDishOfTypeMapper allDishOfTypeMapper;

    final
    Dish_pictureMapper dish_pictureMapper;

    final
    DishMapper dishMapper;

    public DishImpl(DishMapper dishMapper, Dish_pictureMapper dish_pictureMapper, AllDishOfTypeMapper allDishOfTypeMapper, Dish_typeMapper dish_typeMapper, Orderinfo_dishesMapper orderinfo_dishesMapper, DishSalesMapper dishSalesMapper) {
        this.dishMapper = dishMapper;
        this.dish_pictureMapper = dish_pictureMapper;
        this.allDishOfTypeMapper = allDishOfTypeMapper;
        this.dish_typeMapper = dish_typeMapper;
        this.orderinfo_dishesMapper = orderinfo_dishesMapper;
        this.dishSalesMapper = dishSalesMapper;
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

    @Override
    public Boolean addDishPic(Integer dish_ID, String pic_address) {
        Dish_picture dish_picture=new Dish_picture();
        dish_picture.setDish_ID(dish_ID);
        dish_picture.setPicture_address(pic_address);
        return dish_pictureMapper.addDishPic(dish_picture)==1;
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
    public Integer addNewDish(String dish_name, Float dish_nuitprice, String dish_quantity, String dish_description,String picture_address,String type) {
        Dish dish=new Dish();
        dish.setDish_name(dish_name);
        dish.setDish_nuitprice(dish_nuitprice);
        dish.setDish_quantity(dish_quantity);
        dish.setMonthly_sales(0);
        dish.setDish_rating((float) 5.0);
        dish.setDish_description(dish_description);
        if(Objects.equals(picture_address, ""))
            picture_address="/iamges/dishimg0.jpg";
        dish.setPicture_address(picture_address);
        System.out.println(dish);
        dishMapper.addDish(dish);
        Dish_type dish_type =new Dish_type();
        dish_type.setDish_ID(dish.getDish_ID());
        dish_type.setThe_type(type);
        dish_typeMapper.addNewRelation(dish_type);
        return dish.getDish_ID();
    }

    @Override
    public Boolean updateDish(Integer dish_ID, String dish_name, Float dish_nuitprice, String dish_description, String picture_address, String type) {
        Dish dish =dishMapper.queryDishByDishID(dish_ID);
        dish.setDish_name(dish_name);
        dish.setDish_nuitprice(dish_nuitprice);
        if(!Objects.equals(dish_description, ""))
            dish.setDish_description(dish_description);
        if(!Objects.equals(picture_address, ""))
            dish.setPicture_address(picture_address);
        Dish_type dish_type=dish_typeMapper.queryRelationByDishID(dish_ID);
        dish_type.setThe_type(type);
        dish_typeMapper.updateRelationByDishID(dish_type);
        return dishMapper.updateDish(dish)==1;
    }
    //
//    /**
//     * @description: 增加菜品信息（方法重写）
//     * @param dish_name: 菜品名称
//     * @param dish_nuitprice: 菜品单价
//     * @param descriptionORquantity: 描述or菜量
//     * @param isDescription: 是否为描述，或是菜量
//     * @return boolean
//     * @author: Dongrun Li
//     * @date: 2023/4/4 12:59
//     */
//    @Override
//    public boolean addNewDish(String dish_name, Float dish_nuitprice, String descriptionORquantity, boolean isDescription) {
//        Dish dish=new Dish();
//        dish.setDish_name(dish_name);
//        dish.setDish_nuitprice(dish_nuitprice);
//        if(isDescription)
//            dish.setDish_description(descriptionORquantity);
//        else
//            dish.setDish_quantity(descriptionORquantity);
//        return dishMapper.addDish(dish)==1;
//    }
//
//    /**
//     * @description: 增加菜品信息（重写）
//     * @param dish_name: 菜品名称
//     * @param dish_nuitprice: 菜品单价
//     * @return boolean
//     * @author: Dongrun Li
//     * @date: 2023/4/4 13:01
//     */
//    @Override
//    public boolean addNewDish(String dish_name, Float dish_nuitprice) {
//        Dish dish=new Dish();
//        dish.setDish_name(dish_name);
//        dish.setDish_nuitprice(dish_nuitprice);
//        return dishMapper.addDish(dish)==1;
//    }

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
        // 获取当前日期
        Calendar calendar = Calendar.getInstance();
// 将小时、分钟、秒数设置为12:00:00
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
// 创建Timestamp对象
        Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());
//       存储上月销量信息
        List<Dish> dishList1=dishMapper.queryDish();
        List<Dish> dishList = dishList1.subList(1, dishList1.size());
        for (Dish dish:dishList) {
            DishSales dishSales=new DishSales(dish.getDish_ID(),timestamp,dish.getMonthly_sales());
            dishSalesMapper.addSalesrecord(dishSales);
        }
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
        orderinfo_dishesMapper.delDishID(dish_ID);
        return dishMapper.delDishByDishID(dish_ID)==1;
    }

    @Override
    public List<String> getAlltype() {
        return allDishOfTypeMapper.queryAllType();
    }

    @Override
    public List<FigureData> getMonthsales(Timestamp sales_time) {
        List<DishSaleshow> dishSaleshowList=dishSalesMapper.queryRecords(sales_time);
        List<FigureData> figureDataList=new ArrayList<>();
        for (DishSaleshow dishSaleshow:dishSaleshowList) {
            FigureData figureData =new FigureData();
            figureData.setValue(dishSaleshow.getMonthly_sales());
            figureData.setName(dishSaleshow.getDish_name());
            figureDataList.add(figureData);
        }
        return figureDataList;
    }

    @Override
    public List<Timestamp> getMonth() {
        return dishSalesMapper.queryMonth();
    }
}
