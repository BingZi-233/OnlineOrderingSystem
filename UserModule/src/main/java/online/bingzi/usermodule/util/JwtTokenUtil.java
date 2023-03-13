package online.bingzi.usermodule.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {
    // Token前缀
    public static final String TOKEN_PREFIX = "Bearer ";
    // 认证头
    public static final String HEADER_STRING = "Authorization";
    // 盐
    private static final String SECRET = "yD8hQ0gK8aR6iO0eR3fY0mJ3pF6oJ2zHsL3oU7xD5xL5tB1vL0qP9tL8jB2fX8vSoZ2oQ4bI3gK7zE1nJ9mS7fW4vI7lQ0qJ";
    // 过期时间
    private static final long EXPIRATION_TIME = 864_000_000;

    // 创建一个JWT Token
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    // 从JWT Token中获取用户名
    public String getUsernameFromToken(String token) {
        return Jwts
                .parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // 校验 Token 是否有效
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    // 检查 Token 是否过期
    private boolean isTokenExpired(String token) {
        final Date expiration = Jwts
                .parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiration.before(new Date());
    }
}
