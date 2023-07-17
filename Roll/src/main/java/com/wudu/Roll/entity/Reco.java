package com.wudu.Roll.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("recotable")
public class Reco {
    @TableId(type = com.baomidou.mybatisplus.annotation.IdType.AUTO)
    private Integer recoid;
    private String username;
    private Integer userid;
    private String rarity;
}
