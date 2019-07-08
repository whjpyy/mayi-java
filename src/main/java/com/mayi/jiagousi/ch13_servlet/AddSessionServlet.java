package com.mayi.jiagousi.ch13_servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/session/add")
public class AddSessionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // true会创建一个新的session，false不会创建，如果没有session则有
        HttpSession session = req.getSession(true);
        session.setAttribute("name", "chen");
        System.out.println("session添加成功");
    }
}
