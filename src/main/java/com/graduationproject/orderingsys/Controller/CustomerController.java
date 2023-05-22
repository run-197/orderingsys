package com.graduationproject.orderingsys.Controller;

import com.alibaba.fastjson.JSON;

import com.graduationproject.orderingsys.DAO.Customer;
import com.graduationproject.orderingsys.DAO.UserInfo;
import com.graduationproject.orderingsys.Service.ServiceImpl.CustomerImpl;
import com.graduationproject.orderingsys.utils.HttpRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @BelongsProject: orderingsys
 * @BelongsPackage: com.graduationproject.orderingsys.Controller
 * @Author: Dongrun Li
 * @Date: 2023/3/21 16:23
 * @Description:
 */
@RestController
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController {
    @Value(value = "${appid}")
    String appid;
    @Value(value = "${secret}")
    String secret;
    @Value(value = "${grant_type}")
    String grant_type;

    final
    CustomerImpl customerimpl;

    public CustomerController(CustomerImpl customerimpl) {
        this.customerimpl = customerimpl;
    }
    /**
     * 获取小程序openid
     *
     * @param code 小程序code换取openid和sessionKey
     */
    @RequestMapping("/getOpenID")
    @ResponseBody
    public Object getOpenId(@RequestParam("code") String code) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid + "&secret=" + secret + "&js_code=" + code + "&grant_type=" + grant_type;
        String data = HttpRequest.get(url);
        return JSON.parse(data);
    }

    @RequestMapping("/getCustomerinfo")
    @ResponseBody
    public Customer getCustomer(String openid) {
        return customerimpl.queryCustomerByID(customerimpl.queryCustomerIDByOpenID(openid));
    }

    @RequestMapping("/addNewCustomer")
    @ResponseBody
    public Boolean addCustomer(String customer_nickname,String phone_number,String avatar_address,String openID){
        if(customerimpl.queryCustomerIDByOpenID(openID)!=null)
            return false;
        return customerimpl.addNewCustomer(customer_nickname,phone_number,avatar_address,openID);
    }

    @RequestMapping("/updateCustomer")
    @ResponseBody
    public Boolean updateCustomer(Integer customer_ID, String customer_nickname, String phone_number, String avatar_address){
        return customerimpl.updateCustomer(customer_ID,customer_nickname,phone_number,avatar_address);
    }
    @RequestMapping("/managelogin")
    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    public String logIn(String username,String password){
        System.out.println(username+' '+password);
        if(Objects.equals(username, "admin") && Objects.equals(password, "100518"))
            return "Admin";
        else if(Objects.equals(username, "assistant") && Objects.equals(password, "100518"))
            return "Assistant";
        else
            return "invalid";
    }

    @RequestMapping("/getuserinfo")
    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    public UserInfo getInfo(String token){
        UserInfo userInfo=new UserInfo();
        if(Objects.equals(token, "Admin")) {
            userInfo.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
            userInfo.setIntroduction("管理员用户");
            userInfo.setName("管理员");
            userInfo.setRoles("admin");
        }
        else if(Objects.equals(token, "Assistant")) {
            userInfo.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
            userInfo.setIntroduction("普通用户");
            userInfo.setName("用户");
            userInfo.setRoles("editor");
        }
        return userInfo;
    }
}
