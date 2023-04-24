package edu.hit.zhangtianbo.dao.impl;

import edu.hit.zhangtianbo.dao.UserDao;
import edu.hit.zhangtianbo.domain.User;
import edu.hit.zhangtianbo.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public User findByUsername(String username){
        User user = null;
        String sql = "select * from tab_user where username = ?";
        try {
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
            System.out.println(user);
        } catch (DataAccessException e) {

        }
        return user ;
    }

    public User findUser(String username ,String password){
        User user;
        String sql = "select * from tab_user where username = ? and password = ?";
        user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
        return user;
    }
    @Override
   public  void  save(User user) {
        String sql = "insert into tab_user" +
                "(username, password, name, birthday, sex, telephone, email, status, code) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        template.update(sql, user.getUsername(), user.getPassword(), user.getName(), user.getBirthday(),
                user.getSex(), user.getTelephone(), user.getEmail(), user.getState(), user.getCode());
    }
}
