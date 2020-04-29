package com.ubo.zyq.util;

import org.springframework.beans.factory.annotation.Value;

public class PARAM {
	
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
	private static String baseUrl;
	
    public static final String NEWSDETAIL_IMG_PATH = "D:/Image/News/";
    
    
    public static final String PRODUCT_IMG_PATH = baseUrl + "/Product/";
    
    
    
    public static final String Enter_IMG_PATH = baseUrl+"/";
    
  
    
    public static final String CONTACTUS_IMG_PATH = "D:/Image/Contactus/";
    public static final String HOMEPAGEADS_IMG_PATH = "D:/Image/Homepageads/";
    
    
}
