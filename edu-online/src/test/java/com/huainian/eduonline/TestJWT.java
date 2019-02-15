package com.huainian.eduonline;

import com.huainian.eduonline.bean.entity.User;
import com.huainian.eduonline.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import org.junit.Test;

/**
 * FileName: TestJWT
 * Author: huainian.chen
 * Date: 2019/2/15 17:20
 * Description: 测试JWT工具类
 */
public class TestJWT {

    @Test
    public void testGeneToken(){
        User user = new User();
        user.setId(100);
        user.setName("怀念");
        user.setHeadImg("www.huainian.com");
        String token = JWTUtils.geneJsonWebToken(user);
        System.out.println(token);
    }

    @Test
    public void  testCheckToken(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlZHUtb25saW5lIiwiaWQiOjEwMCwibmFtZSI6IuaAgOW_tSIsImltZyI6Ind3dy5odWFpbmlhbi5jb20iLCJpYXQiOjE1NTAyMjMzOTMsImV4cCI6MTU1MDgyODE5M30.SFAPieIUAgemUaOeqj1hWeH76kNKvsNhOuzS1NBSmP8";
        Claims claims = JWTUtils.checkJWT(token);
        if (claims != null){
            int id = (Integer)claims.get("id");
            String name = (String) claims.get("name");
            String img = (String) claims.get("img");
            System.out.println("用户Id:   "+id);
            System.out.println("用户name:     "+name);
            System.out.println("用户headImg:      "+img);

        }else {
            System.out.print("非法token");
        }
    }

}
