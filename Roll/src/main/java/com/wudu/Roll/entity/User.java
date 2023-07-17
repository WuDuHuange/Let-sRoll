package com.wudu.Roll.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 用户表对应的实体类
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("usertable")
public class User {
    @TableId(type = com.baomidou.mybatisplus.annotation.IdType.AUTO)
    private Integer userid;
    private String username;
    private String password;
    private String role;
    private Integer r;
    private Integer sr;
    private Integer ssr;

    private Integer ur;
    private String score;
}
