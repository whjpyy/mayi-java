package com.mayi.jiagousi.ch13_servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "demoServlet", urlPatterns = "/servlet")
public class DemoServlet extends HttpServlet {

    private int i = 1;

    public DemoServlet() {
        System.out.println("DemoServlet");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("初始化");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("处理请求");
        try {
            resp.setCharacterEncoding("utf-8"); // 内容编码，反正出现中文乱码
            resp.setContentType("text/html;charset=utf-8");
            synchronized (DemoServlet.class) {
                resp.getWriter().write("这是第" + i + "次访问");
                Thread.sleep(1000);
                i++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        System.out.println("销毁");
    }
}
