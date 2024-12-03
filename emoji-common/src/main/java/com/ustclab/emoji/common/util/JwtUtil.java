package com.ustclab.emoji.common.util;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Claim;
import com.ustclab.emoji.common.model.dao.User;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author TZSXFJH
 * @date 2024/11/27
 */
public class JwtUtil {

    /**
     签发对象：这个用户的id
     签发时间：现在
     有效时间：30分钟
     载荷内容：暂时设计为：这个人的名字，这个人的昵称
     加密密钥：这个人的id加上一串字符串
     */
    public static String createToken(User user) {

        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE,30);
        Date expiresDate = nowTime.getTime();

        return JWT.create()
                .withAudience(user.getUserId().toString())   //签发对象
                .withIssuedAt(new Date())    //发行时间
                .withExpiresAt(expiresDate)  //有效时间
                .withClaim("user", JSON.toJSONString(user))
                .sign(Algorithm.HMAC256(user.getUserId().toString()+"ustcLab"));   //加密
    }

    /**
     * 检验合法性，其中secret参数就应该传入的是用户的id
     * @param token
     */
    public static void verifyToken(String token, String secret) throws Exception {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret+"ustcLab")).build();
        verifier.verify(token);
    }

    /**
     * 获取签发对象
     */
    public static String getAudience(String token, Integer index) throws Exception{
        String audience = JWT.decode(token).getAudience().get(index);
        return audience;
    }


    /**
     * 通过载荷名字获取载荷的值
     */
    public static Claim getClaimByName(String token, String name){
        return JWT.decode(token).getClaim(name);
    }
}
