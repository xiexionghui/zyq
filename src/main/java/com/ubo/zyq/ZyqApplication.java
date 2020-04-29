package com.ubo.zyq;

import java.io.File;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.unit.DataSize;

import com.ubo.zyq.util.PARAM;

@SpringBootApplication
/*
 * @ComponentScan(value={"com.ubo.zyq.controller.admin","com.ubo.zyq.controller"
 * ,"com.ubo.zyq.repository","com.ubo.zyq.service"})
 */

public class ZyqApplication {

    public static void main(String[] args) {
        File file1=new File(PARAM.NEWSDETAIL_IMG_PATH);
        if(!file1.exists()){
            file1.mkdirs();
        }
        File file2 = new File(PARAM.PRODUCT_IMG_PATH);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        SpringApplication.run(ZyqApplication.class, args);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 单个数据大小
        factory.setMaxFileSize(DataSize.ofMegabytes(1));
        // 总上传数据大小
        factory.setMaxRequestSize(DataSize.ofMegabytes(10));
        return factory.createMultipartConfig();
    }
}
