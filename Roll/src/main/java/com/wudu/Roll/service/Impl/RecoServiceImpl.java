package com.wudu.Roll.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wudu.Roll.entity.Reco;
import com.wudu.Roll.entity.User;
import com.wudu.Roll.mapper.RecoMapper;
import com.wudu.Roll.mapper.UserMapper;
import com.wudu.Roll.service.RecoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RecoServiceImpl implements RecoService {

    @Autowired
    private RecoMapper recoMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public String roll(String username, int userid) {
        User user = userMapper.selectById(userid);
        // ur的概率是2%，ssr的概率是8%，sr的概率是30%,r的概率是60%
        // 生成一个0-100的随机数
        int random=(int)(Math.random()*100);
        // 根据随机数判断掉落的是什么,将user对应的掉落数的字段自增加一,(mybatisplus自带的方法)
        String result="";
        if(random<=2){
            user.setUr(user.getUr()+1);
            result="ur";
        }else if(random<=10) {
            user.setSsr(user.getSsr() + 1);
            result = "ssr";
        }else if(random<=40) {
            user.setSr(user.getSr() + 1);
            result = "sr";
        }else {
            user.setR(user.getR() + 1);
            result = "r";
        }
        userMapper.updateById(user);
        // 将掉落的结果插入到reco表中
        Reco reco = new Reco();
        reco.setUserid(userid);
        reco.setUsername(username);
        reco.setRarity(result);
        recoMapper.insert(reco);

        return result;
    }

    @Override
    public List listReco(String username, int userid) {
        QueryWrapper<Reco> qw= Wrappers.query();
        qw.eq("userid",userid);
        return recoMapper.selectList(qw);
    }

    @Override
    public String deleteReco(int userid) {
        QueryWrapper<Reco> qw= Wrappers.query();
        qw.eq("userid",userid);
        Reco reco = recoMapper.selectList(qw).get(0);
        if(reco!=null){
            // 删除所有userid=userid的记录
            recoMapper.delete(qw);
            return "删除成功";
        }else{
            throw new RuntimeException("用户不存在");
        }
    }
}
