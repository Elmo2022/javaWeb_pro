package edu.hit.zhangtianbo.dao.impl;

import edu.hit.zhangtianbo.dao.CategoryDao;
import edu.hit.zhangtianbo.domain.Category;
import edu.hit.zhangtianbo.util.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Category> findAll(){
        String sql = "select * from tab_category;";


        return template.query(sql, new BeanPropertyRowMapper<Category>(Category.class));

    }

}
