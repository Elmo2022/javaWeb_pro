package edu.hit.zhangtianbo.dao;
import edu.hit.zhangtianbo.domain.User;
public interface UserDao {

     User findByUsername(String username);
     void  save(User user);
     User findUser(String username ,String password);
}
