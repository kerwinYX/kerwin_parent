package com.kerwin.commonutils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 */
public class JwtUtils {


    //设置token过期时间  常量
    public static final long EXPIRE = 1000 * 60 * 60 * 24;
    // 指定的随机字符串加密秘钥
    public static final String APP_SECRET = "ukc8BDbRigUDaY6pZFfWus2jZWLPHO";

    //生成token字符串   根据传入的  id 和  nickname生成token字符串
    public static String getJwtToken(String id, String nickname){
        String JwtToken = Jwts.builder()
                //固定
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                //分类
                .setSubject("kerwin-user")
                .setIssuedAt(new Date())
                //设置过期时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                //设置token的主体信息部分  存储用户信息
                .claim("id", id)
                .claim("nickname", nickname)
                // 生成签名哈希
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                .compact();

        return JwtToken;
    }

    /**
     * 判断token是否存在与有效
     * @param jwtToken
     */
    public static boolean checkToken(String jwtToken) {
        if(StringUtils.isEmpty(jwtToken)) return false;
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 判断token是否存在与有效
     * @param request
     * @return
     */
    public static boolean checkToken(HttpServletRequest request) {
        try {
            String jwtToken = request.getHeader("token");
            if(StringUtils.isEmpty(jwtToken)) return false;
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 根据token获取会员id
     * @param request
     * @return
     */
    public static String getMemberIdByJwtToken(HttpServletRequest request) {
        String jwtToken = request.getHeader("token");
        if(StringUtils.isEmpty(jwtToken)) return "";
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        return (String)claims.get("id");
    }
}
