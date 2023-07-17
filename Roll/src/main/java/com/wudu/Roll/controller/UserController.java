package com.wudu.Roll.controller;

import com.wudu.Roll.entity.User;
import com.wudu.Roll.service.UserService;
import com.wudu.Roll.service.RecoService;
import com.wudu.Roll.util.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.List;


@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RecoService recoService;

    @PostMapping("/login")
    public CommonResult login(@RequestBody User user) {
        try {
            String username = user.getUsername();
            String password = user.getPassword();
            String jwt = userService.login(username, password);
            return CommonResult.success(jwt);

        }catch (Exception e) {
            log.error("登录失败",e);
            return CommonResult.fail(e.getMessage());
        }

    }
    @PostMapping("/register")
    public CommonResult register(@RequestBody User user) {
        try {
            String username = user.getUsername();
            String password = user.getPassword();
            String result = userService.register(username, password);
            return CommonResult.success(result);

        }catch (Exception e) {
            log.error("注册失败",e);
            return CommonResult.fail(e.getMessage());
        }

    }
    @GetMapping("/listUser")
    public CommonResult listUser() {
        try {
            List result = userService.listUser();
            return CommonResult.success(result);

        }catch (Exception e) {
            log.error("获取用户列表失败",e);
            return CommonResult.fail(e.getMessage());
        }

    }

    @GetMapping("/deleteUser")
    public CommonResult deleteUser(@RequestParam int userid) {
        try {
            String result1 = recoService.deleteReco(userid);
            String result = userService.deleteUser(userid);
            log.info(result1);
            return CommonResult.success(result);

        }catch (Exception e) {
            log.error("删除用户失败",e);
            return CommonResult.fail(e.getMessage());
        }

    }

    @PostMapping("/updateUser")
    public CommonResult updateUser(@RequestBody User user) {
        try {
            String result = userService.updateUser(user);
            return CommonResult.success(result);

        }catch (Exception e) {
            log.error("更新用户失败",e);
            return CommonResult.fail(e.getMessage());
        }

    }
}
