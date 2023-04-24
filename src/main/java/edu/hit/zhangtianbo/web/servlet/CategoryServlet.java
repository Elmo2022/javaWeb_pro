package edu.hit.zhangtianbo.web.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import edu.hit.zhangtianbo.service.CategoryService;
import edu.hit.zhangtianbo.service.impl.CategoryServiceImpl;
import edu.hit.zhangtianbo.domain.Category;
import java.util.List;
import edu.hit.zhangtianbo.web.servlet.BaseServlet;
import javax.servlet.annotation.WebServlet;


@WebServlet("/categroy/*")
public class CategoryServlet extends BaseServlet {
    private CategoryService categoryService = new CategoryServiceImpl();

    public void findAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Category> all = categoryService.findAll();
        writeJsonValue(resp, all);
    }
}
