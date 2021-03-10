package controller;

import com.zhenzi.sms.ZhenziSmsClient;
import entity.Config;
import utils.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "GetCodeServlet",urlPatterns = "/getCode")
public class GetCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //获取电话号码
            String phone = request.getParameter("phone");
            System.out.println("电话号码:"+phone);
            //短信验证
            ZhenziSmsClient client = new ZhenziSmsClient(Config.apiUrl,Config.appId,Config.appSecret);
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("number", phone);
            params.put("templateId", "3870");
            String code=(int)((Math.random()*1000000))+"";
            System.out.println("验证码为:"+code);
            String[] templateParams = new String[2];
            templateParams[0] = code;
            templateParams[1] = "5分钟";
            params.put("templateParams", templateParams);
            String result = client.send(params);
            System.out.println(result);
            //将验证码存到session中,同时存入创建时间
            HttpSession session = request.getSession();
            SessionUtil.save(session, phone, code, 5 * 60);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
