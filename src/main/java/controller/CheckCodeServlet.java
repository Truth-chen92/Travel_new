package controller;

import dao.UserDao;
import utils.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CheckCodeServlet",urlPatterns = "/checkcode")
public class CheckCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String phone=request.getParameter("phone");
        String code=request.getParameter("code");

        HttpSession session = request.getSession();
        String result= SessionUtil.validate(session,phone,code);
        System.out.println("result"+result);
        if (result.equals("未生成验证码")||result.equals("验证码错误")||result.equals("验证码已过期")) {
            response.setContentType("text/html;charset=utf-8");
            PrintWriter pw=response.getWriter();
            pw.write(result);
            pw.close();
        }else{
            UserDao dao=new UserDao();
            dao.reg(username,password,phone);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter pw=response.getWriter();
            pw.write("success");
            pw.close();
//            response.sendRedirect("/login.html");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
