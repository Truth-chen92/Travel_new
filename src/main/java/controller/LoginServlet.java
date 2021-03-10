package controller;

import dao.UserDao;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet",urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        设置字符集
        request.setCharacterEncoding("utf-8");
//        获取参数
        String username=request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username+','+password);
//        创建Dao并调用登陆方法
        UserDao dao = new UserDao();
        User user = dao.login(username,password);
        if (user != null) {
//            判断是否需要记住用户名和密码
            String rem=request.getParameter("rem");
            System.out.println("rem:"+rem);
            if (rem != null) {//需要记住用户名和密码
//                创建Cookie 把用户名和密码装进去
                Cookie c1=new Cookie("username",username);
                Cookie c2=new Cookie("password",password);
//                把cookie下发到客户端
                response.addCookie(c1);
                response.addCookie(c2);
            }
//            通过Session记住登录状态
            HttpSession session=request.getSession();
//            如果通过SessionId找到了曾经创建的对象会直接返回 否则创建一个新的并返回
//            把user对象放进session里面
            session.setAttribute("user",user);
            response.sendRedirect("/home");
        }else {
            response.sendRedirect("/showlogin");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
