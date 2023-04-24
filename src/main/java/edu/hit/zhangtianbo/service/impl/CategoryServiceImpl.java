package edu.hit.zhangtianbo.service.impl;

import edu.hit.zhangtianbo.dao.CategoryDao;
import edu.hit.zhangtianbo.dao.impl.CategoryDaoImpl;
import edu.hit.zhangtianbo.domain.Category;
import edu.hit.zhangtianbo.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = new CategoryDaoImpl();
    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }
}

