package edu.hit.zhangtianbo.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hit.zhangtianbo.domain.ResultInfo;
import edu.hit.zhangtianbo.domain.User;
import edu.hit.zhangtianbo.service.UserService;
import edu.hit.zhangtianbo.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet(name = "LoginUserServlet", value = "/LoginUserServlet")
public class LoginUserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取用户名和密码
        Map<String, String[]> parameterMap = req.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, parameterMap);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        UserService userService = new UserServiceImpl();
        User loginUser = userService.login(user.getUsername(), user.getPassword());

        ResultInfo resultInfo =  new ResultInfo();

        // 用户不存在或密码错误
        if(loginUser == null) {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("用户不存在或密码错误");

        }else {
            // 用户存在，设置session
            resultInfo.setFlag(true);
            req.getSession().setAttribute("user", loginUser);
        }

        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json;charset=utf-8");
        mapper.writeValue(resp.getOutputStream(), resultInfo);
    }
    }


