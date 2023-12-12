package com.xyz66.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class WebUtils {
    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param string   待渲染的字符串
     * @return null
     */
    public static void renderString(HttpServletResponse response, String string) {
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setDownLoadHeader(String filename, HttpServletResponse response) throws UnsupportedEncodingException {
        // 设置文件类型,防止中文乱码
        //https://easyexcel.opensource.alibaba.com/docs/current/quickstart/write#web%E4%B8%AD%E7%9A%84%E5%86%99
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("UTF-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
        // 设置其它响应头
        response.addHeader("Access-Control-Expose-Headers", "Content-disposition");
        // 设置文件名
        // content-disposition:"attachment;filename*=''%E5%88%86%E7%B1%BB.xlsx"
        response.setHeader("Content-disposition", "attachment;filename*=" + fileName);
    }

}
