package edu.hit.zhangtianbo.web.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


import edu.hit.zhangtianbo.domain.PageBean;
import edu.hit.zhangtianbo.domain.Route;
import edu.hit.zhangtianbo.service.RouteService;
import edu.hit.zhangtianbo.service.impl.RouteServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {

    private RouteService routeService = new RouteServiceImpl();

    public void pageQuery(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 获取参数
        int cid = 0;
        String cidStr = req.getParameter("cid");
        if (cidStr != null && cidStr.length() > 0) {
            cid = Integer.parseInt(cidStr);
        }

        int currentPage = 0;
        String currentPageStr = req.getParameter("currentPage");
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }

        int pageSize = 0;
        String pageSizeStr = req.getParameter("pageSize");
        if (pageSizeStr != null && pageSizeStr.length() > 0) {
            pageSize = Integer.parseInt(pageSizeStr);
        } else {
            pageSize = 5;
        }
        String rname = req.getParameter("rname");
        rname = new String(rname.getBytes("iso-8859-1"), "utf-8");

        System.out.println("cid: " + cid + "currentPage: " + currentPage + "pageSize: " + pageSize + "rname: " + rname);
        PageBean<Route> pb = routeService.pageQuery(cid, currentPage, pageSize, rname);

        writeJsonValue(resp, pb);

    }
}