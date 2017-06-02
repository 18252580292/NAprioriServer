package com.jskj.napriori.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by xuecheng.cui on 2017/5/31.
 */
@WebServlet(name = "UploadApkServlet", urlPatterns = "/upload_apk")
public class UploadApkServlet extends javax.servlet.http.HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();
        ServletContext servletContext = getServletContext();
        String realPath = servletContext.getRealPath("/WEB-INF");
        File file = new File(realPath + "/temp.apk");
        FileOutputStream outputStream = new FileOutputStream(file);
        int len = 0;
        byte[] buff = new byte[1024];
        while ((len = inputStream.read(buff)) != -1) {
            outputStream.write(buff, 0, len);
        }
        Runtime.getRuntime().exec("apktool.bat d -f  "+realPath + "/temp.apk"+"  test");
        outputStream.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
