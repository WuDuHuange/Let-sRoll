package com.wudu.Roll.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Map;

/**
 * @program: Roll
 * @description: Jwt工具类，用于生成token和验证token
 * @create: 2023-7-10 10:00
 **/
@Slf4j
public class JwtUtils {
    private static String signKey="abcdefg";
    private static long expire=43200000L;

    /**
     * 生成token
     * @param claims
     * @return
     */
    public static String generateJwt(Map<String,Object> claims){
        //生成token
        String jwt = Jwts.builder()
                .addClaims(claims)//设置载荷
                .signWith(SignatureAlgorithm.HS256, signKey)//设置加密方式和加密盐
                .setExpiration(new Date(System.currentTimeMillis()+expire))//设置过期时间
                .compact();
        return jwt;
    }

    /**
     * 解析token
     * @param jwt
     * @return
     */
    public static Claims parseJwt(String jwt){
        //解析token
        Claims claims = Jwts.parser()
                .setSigningKey(signKey)//设置加密盐
                .parseClaimsJws(jwt)//设置token
                .getBody();//获取载荷
        return claims;
    }
}
