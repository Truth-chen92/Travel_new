package controller;

import org.thymeleaf.context.Context;
import utils.ThUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ShowLoginServlet",urlPatterns = "/showlogin")
public class ShowLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        显示登陆页面
        Context context = new Context();
//        取出Cookie里面的用户名和密码
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
//                取出Cookie中保存的名和值
                String name = c.getName();
                String value = c.getValue();
//                判断是用户名 保存到容器中
                if (name.equals("username")) {
                    context.setVariable("username", value);
                }else if (name.equals("password")) {
                    context.setVariable("password",value);
                }//判断是密码 也保存到容器中
            }
        }
        ThUtils.print("login.html", context, response);
    }
}
