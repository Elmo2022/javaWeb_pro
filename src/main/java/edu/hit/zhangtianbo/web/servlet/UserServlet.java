package edu.hit.zhangtianbo.web.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hit.zhangtianbo.domain.ResultInfo;
import edu.hit.zhangtianbo.domain.User;
import edu.hit.zhangtianbo.service.UserService;
import edu.hit.zhangtianbo.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet{

    private UserService userService = new UserServiceImpl();
    public void register(HttpServletRequest req, HttpServletResponse resp) throws IOException  {
        // 获取数据
        Map<String, String[]> map = req.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        // 调用service 完成注册逻辑
        System.out.println(user);
        boolean flag = userService.register(user);
        ResultInfo resultInfo = new ResultInfo();
        if(flag) {
            resultInfo.setFlag(true);
        }else {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("注册失败");
        }

        writeJsonValue(resp, resultInfo);
    }

    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, String[]> parameterMap = req.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, parameterMap);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        User loginUser = userService.login(user.getUsername(), user.getPassword());
        System.out.println(loginUser);
        ResultInfo resultInfo =  new ResultInfo();

        // 用户不存在或密码错误
        if(loginUser == null) {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("用户不存在或密码错误");

        }else {
            // 用户存在，设置session
            resultInfo.setFlag(true);
            req.getSession().setAttribute("user", loginUser);
            System.out.println(req);
        }
        writeJsonValue(resp, resultInfo);
    }

    public void findOne(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Object user = req.getSession().getAttribute("user");
        System.out.println(req);
        System.out.println(user);
        writeJsonValue(resp, user);
    }

    public void exit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //1.销毁session
        req.getSession().invalidate();
        //2.跳转登录页面
        resp.sendRedirect(req.getContextPath()+"/login.html");
    }
}