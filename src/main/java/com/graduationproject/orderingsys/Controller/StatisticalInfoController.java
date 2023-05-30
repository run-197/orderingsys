package com.graduationproject.orderingsys.Controller;

import com.graduationproject.orderingsys.DAO.FigureData;
import com.graduationproject.orderingsys.Mapper.DishSalesMapper;
import com.graduationproject.orderingsys.Service.ServiceImpl.DishImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

/**
 * @BelongsProject: orderingsys
 * @BelongsPackage: com.graduationproject.orderingsys.Controller
 * @Author: Dongrun Li
 * @Date: 2023/5/23 16:40
 * @Description:
 */
@RestController
@CrossOrigin
@RequestMapping("/statistic")
public class StatisticalInfoController {
    final
    DishSalesMapper dishSalesMapper;
    final
    DishImpl dishimpl;

    public StatisticalInfoController(DishImpl dishimpl, DishSalesMapper dishSalesMapper) {
        this.dishimpl = dishimpl;
        this.dishSalesMapper = dishSalesMapper;
    }
    @RequestMapping("/getmonth")
    @ResponseBody
    public List<Timestamp> getmonth(){
        return dishimpl.getMonth();
    }

    @RequestMapping("/getmonthsalesbytype")
    @ResponseBody
    public List<FigureData> getMonthsalesBytype(Timestamp sales_time){
        return dishimpl.getMonthsales(sales_time);
    }

    @RequestMapping("/getmonthsales")
    @ResponseBody
    public List<FigureData> getMonthsales(Timestamp sales_time){
        return dishSalesMapper.getFigureDataBySalesTime(sales_time);
    }

    @RequestMapping("/getDishmonthsales")
    @ResponseBody
    public List<FigureData> getDishmonthsales(Timestamp sales_time,String the_type){
        return dishSalesMapper.getDishMonthlySales(sales_time,the_type);
    }
}
