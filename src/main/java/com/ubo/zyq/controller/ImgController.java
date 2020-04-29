package com.ubo.zyq.controller;

import com.ubo.zyq.util.PARAM;
import com.ubo.zyq.util.ImageUtil;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;

@Controller
public class ImgController {

	@Value("${FTP.ADDRESS}")
	private String host;
	// 端口
	@Value("${FTP.PORT}")
	private int port;
	// ftp用户名
	@Value("${FTP.USERNAME}")
	private String userName;
	// ftp用户密码
	@Value("${FTP.PASSWORD}")
	private String passWord;
	// 文件在服务器端保存的主目录
	@Value("${FTP.BASEPATH}")
	private String basePath;
	// 访问图片时的基础url
	@Value("${IMAGE.BASE.URL}")
	private String baseUrl;
	
	
    @GetMapping("/getImg/{filename}")
    public void getBiImg(HttpServletResponse response, @PathVariable("filename") String filename) {
        ImageUtil.findImageWrite(PARAM.NEWSDETAIL_IMG_PATH + filename, response);
    }

    
    @GetMapping("/getProductImg/{filename}")
    public void getProductImg(HttpServletResponse response, @PathVariable("filename") String filename) {
        ImageUtil.findImageWrite(PARAM.PRODUCT_IMG_PATH + filename, response);
    }
    
    @GetMapping("/getEnter/{filename}")
    public void getEnter(HttpServletResponse response, @PathVariable("filename") String filename) {
        ImageUtil.findImageWrite(PARAM.Enter_IMG_PATH + filename, response);
    }
    
    @GetMapping("/getContactus/{filename}")
    public void getContactus(HttpServletResponse response, @PathVariable("filename") String filename) {
        ImageUtil.findImageWrite(PARAM.CONTACTUS_IMG_PATH + filename, response);
    }
    
    @GetMapping("/getHomepageads/{filename}")
    public void getHomepageads(HttpServletResponse response, @PathVariable("filename") String filename) {
        ImageUtil.findImageWrite(PARAM.HOMEPAGEADS_IMG_PATH + filename, response);
    }
}
