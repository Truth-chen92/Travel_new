package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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

@WebServlet(name = "RegServlet",urlPatterns = "/reg")
public class RegServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        System.out.println("有吗");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String phone=request.getParameter("phone");
        String code=request.getParameter("code");

            UserDao dao=new UserDao();
            dao.reg(username,password,phone);
            response.sendRedirect("/login.html");



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
