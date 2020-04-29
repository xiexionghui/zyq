package com.ubo.zyq.util;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class ImageUtil {
    public static void findImageWrite(String fileName, HttpServletResponse response) {
        if (fileName.endsWith(".png")) {
            response.setContentType("image/png");
        } else if (fileName.endsWith(".jpg")) {
            response.setContentType("image/jpeg");
        } else if (fileName.endsWith(".gif")) {
            response.setContentType("image/gif");
        } else if (fileName.endsWith(".bmp")) {
            response.setContentType("image/bmp");
        } else {
            response.setContentType("image/*");
        }
        try (BufferedInputStream is = new BufferedInputStream(new FileInputStream(fileName));
             BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream())) {
            byte[] buf = new byte[8192];
            int b = -1;
            while ((b = is.read(buf)) != -1) {
                out.write(buf, 0, b);
            }
        }catch (Exception ignored){
            log.error("findImageWrite",ignored);
        }

    }

    /**
     * 得到网页中图片的地址
     */
    public static List<String> getImgStr(String htmlStr) {
        List<String> pics = new ArrayList<>();
        String img = "";
        Pattern p_image;
        Matcher m_image;
        String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
        p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
        m_image = p_image.matcher(htmlStr);
        while (m_image.find()) {
            // 得到<img />数据
            img = m_image.group();
            // 匹配<img>中的src数据
            Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
            while (m.find()) {
                pics.add(m.group(1));
            }
        }
        return pics;
    }
}
