package com.graduationproject.orderingsys.Controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

/**
 * @BelongsProject: orderingsys
 * @BelongsPackage: com.graduationproject.orderingsys.Controller
 * @Author: Dongrun Li
 * @Date: 2023/4/27 19:43
 * @Description:
 */

@RestController
@CrossOrigin
public class uploadfileController {
    /**
     　　* @description: 图片上传
     　　* @date 2020-11-17 16:13
     　　*/
    @Value("${upload-path}")
    private String realPath;
    @ResponseBody
    @RequestMapping(value ="upload", method = RequestMethod.POST)
//    图片是以content-type为multipart/form-data的格式上传的，所以使用spring-mvc可以通过使用参数的形式以二进制的格式获取到该图片。
    public String upload(HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        System.out.println("执行upload");
        request.setCharacterEncoding("UTF-8");
        String pdNo = request.getParameter("pdNo");
        System.out.println(pdNo);
        String path ;
        String type ;
        String avator;
        if(!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            System.out.println(fileName);
            type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
            String frontname=fileName.substring(0,fileName.lastIndexOf("."));
            System.out.println(type);
            if (type != null) {
                if ("GIF".equals(type.toUpperCase())||"PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())||"JPEG".equals(type.toUpperCase())) {

                    // 自定义的文件名称
                    Calendar rightNow=Calendar.getInstance();
                    Integer year = rightNow.get(Calendar.YEAR);
                    Integer month = rightNow.get(Calendar.MONTH)+1; //第一个月从0开始，所以得到月份＋1
                    Integer day = rightNow.get(rightNow.DAY_OF_MONTH);

                    String date=+year+"-"+month+"-"+day;
//                    String trueFileName =pdNo+"-"+date+fileName.substring(fileName.lastIndexOf("."));
                    String trueFileName =frontname+"-"+date+".jpg";//把图片都变成jpg格式，按需求决定该不该格式
                    // 设置存放图片文件的路径
                    path = realPath +trueFileName;
                    //判断文件父目录是否存在
                    File dest=new File(path);
                    if(!dest.getParentFile().exists()){
                        dest.getParentFile().mkdir();
                    }
                    //保存文件
                    file.transferTo(new File(path));
                    avator=trueFileName;

                }else {
                    return "error1";
                }
            }else {
                return "error2";
            }
        }else {
            return "error3";
        }
        System.out.println(avator);
        return avator;//返回图片访问路径，可以把这个连接存到数据库里，小程序端以后就可以直接访问图片了
    }

}

