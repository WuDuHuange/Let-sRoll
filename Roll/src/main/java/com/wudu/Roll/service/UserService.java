package com.wudu.Roll.service;
import com.wudu.Roll.entity.User;

import java.lang.reflect.Array;
import java.util.List;

/**
 * 用户表对应的业务层接口
 */
public interface UserService {
    public String login(String username, String password);
    public String register(String username, String password);
    public List listUser();
    public String deleteUser(int userid);
    public String updateUser(User user);
}
