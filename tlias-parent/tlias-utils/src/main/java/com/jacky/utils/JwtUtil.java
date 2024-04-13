package com.jacky.utils;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.Map;

public class JwtUtil {
    private static final String SIGN = "jacky lee";
    private static final Long EXPIRED = 12 * 60 * 60 * 1000L;// 12h 后超时 = 12小时 * 60分钟/时 * 60秒/分 * 1000毫秒/秒

    public static String generateJwt(Map<String, Object> claims) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, SIGN)
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRED))
                .compact();
    }


    public static Claims parseJwt(String jwt){
        return Jwts.parser()
                .setSigningKey(SIGN)
                .parseClaimsJws(jwt)
                .getBody();
    }
}
