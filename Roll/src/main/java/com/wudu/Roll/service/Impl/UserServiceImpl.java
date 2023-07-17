package com.wudu.Roll.service.Impl;
import java.lang.reflect.Array;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wudu.Roll.entity.User;
import com.wudu.Roll.mapper.UserMapper;
import com.wudu.Roll.service.UserService;
import com.wudu.Roll.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public String login(String username, String password) {
        QueryWrapper<User> qw=new QueryWrapper<>();// 创建条件构造器
        qw.eq("username",username);// 添加条件
        User user=userMapper.selectOne(qw);// 根据条件查询
        String encoding  = DigestUtil.md5Hex(password);// 对密码进行加密

        if(user!=null) {
            if (user.getPassword().equals(encoding)) {
                // 通过username,id和role生成jwt令牌并返回
                HashMap<String, Object> claims = new HashMap<>();
                claims.put("username", username);
                claims.put("id", user.getUserid());
                claims.put("role", user.getRole());
                String jwt= JwtUtils.generateJwt(claims);
                return jwt;
            } else {
                throw new RuntimeException("密码错误");
            }
        }
        throw new RuntimeException("用户名不存在");
    }

    /*
    * 注册
     */
    public String register(String username, String password) {
        QueryWrapper<User> qw=new QueryWrapper<>();// 创建条件构造器
        qw.eq("username",username);// 添加条件
        User user=userMapper.selectOne(qw);// 根据条件查询
        if(user!=null) {
            throw new RuntimeException("用户名已存在");
        }
        String encoding  = DigestUtil.md5Hex(password);// 对密码进行加密
        User user1=new User();
        user1.setUsername(username);
        user1.setPassword(encoding);
        user1.setRole("user");
        user1.setR(0);
        user1.setSr(0);
        user1.setSsr(0);
        user1.setUr(0);
        user1.setScore("0");
        userMapper.insert(user1);
        return "注册成功";
    }

    public List listUser() {
        QueryWrapper<User> qw=new QueryWrapper<>();// 创建条件构造器
        //全部查询

        return userMapper.selectList(qw);
    }

    public String deleteUser(int userid) {
        QueryWrapper<User> qw=new QueryWrapper<>();// 创建条件构造器
        qw.eq("userid",userid);// 添加条件
        User user=userMapper.selectOne(qw);// 根据条件查询
        if(user==null) {
            throw new RuntimeException("用户不存在");
        }
        userMapper.deleteById(userid);
        return "删除成功";
    }

    public String updateUser(User user) {
        QueryWrapper<User> qw=new QueryWrapper<>();// 创建条件构造器
        qw.eq("userid",user.getUserid());// 添加条件
        User user1=userMapper.selectOne(qw);// 根据条件查询
        if(user1==null) {
            throw new RuntimeException("用户不存在");
        }
        userMapper.updateById(user);
        return "更新成功";
    }
}
