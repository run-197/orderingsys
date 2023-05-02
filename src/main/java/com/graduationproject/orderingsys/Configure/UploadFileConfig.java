package com.graduationproject.orderingsys.Configure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @BelongsProject: orderingsys
 * @BelongsPackage: com.graduationproject.orderingsys.Configure
 * @Author: Dongrun Li
 * @Date: 2023/4/27 19:45
 * @Description:
 */

@Configuration
public class UploadFileConfig extends WebMvcConfigurationSupport {
    @Value("${upload-path}")
    private String path;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         * 配置资源映射
         * 意思是：如果访问的资源路径是以“/images/”开头的，
         * 就给我映射到path这个文件夹内，去找你要的资源
         **/
        registry.addResourceHandler("/images/**").
                addResourceLocations("file:" + path);
        System.out.print("上传配置类path=" + path + "\n");
        super.addResourceHandlers(registry);

    }
}