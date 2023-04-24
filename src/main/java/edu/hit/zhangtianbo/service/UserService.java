package edu.hit.zhangtianbo.service;
import edu.hit.zhangtianbo.domain.User;
public interface UserService {
    boolean register(User user);

    User login(String username, String password);
}
