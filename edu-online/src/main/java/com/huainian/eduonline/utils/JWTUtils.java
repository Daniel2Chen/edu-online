package com.huainian.eduonline.utils;

import com.huainian.eduonline.bean.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * FileName: JWTUtils
 * Author: huainian.chen
 * Date: 2019/2/15 16:58
 * Description: JWT工具类
 */
public class JWTUtils {

    public static final String SUBJECT = "edu-online";

    public static  final long EXPIRE = 1000*60*60*24*7;//过期时间，毫秒，一周

    public static  final  String APPSECRET = "wxpay666";//秘钥

    /**
     * 生成token
     * @param user
     * @return
     */
    public static  String geneJsonWebToken(User user){
        if (user == null || user.getId() == null || user.getName() == null
        || user.getHeadImg() == null){
            return null;
        }
        String token = Jwts.builder().setSubject(SUBJECT)
                .claim("id",user.getId())
                .claim("name",user.getName())
                .claim("img",user.getHeadImg())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SignatureAlgorithm.HS256,APPSECRET).compact();

        return token;
    }


    public static Claims checkJWT(String token){

        try {
            final Claims claims = Jwts.parser().setSigningKey(APPSECRET).
                    parseClaimsJws(token).getBody();
            return claims;
        }catch (Exception e){
            return null;
        }
    }
}
