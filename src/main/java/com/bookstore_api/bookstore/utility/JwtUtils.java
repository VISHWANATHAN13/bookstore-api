package com.bookstore_api.bookstore.utility;

import com.bookstore_api.bookstore.common.AccessDeniedException;
import com.bookstore_api.bookstore.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtils {

    private static final String SECRET_STRING = "my-super-secret-key-that-is-long-enough";
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET_STRING.getBytes(StandardCharsets.UTF_8));
    private static final long EXPIRY_DURATION = 60 * 60 * 1000; // 1 hour in ms

    // Generate JWT for a user
    public String generateJwt(User user) {
        long now = System.currentTimeMillis();
        Date issuedAt = new Date(now);
        Date expiryAt = new Date(now + EXPIRY_DURATION);

        return Jwts.builder()
                .setSubject(user.getId().toString())
                .setIssuer("bookstore-api")
                .setIssuedAt(issuedAt)
                .setExpiration(expiryAt)
                .claim("userId", user.getId())
                .claim("type", user.getUserType())
                .claim("name", user.getName())
                .claim("email", user.getEmailId())
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    // Verify JWT and return claims
    public Claims verify(String token) {
        try {
            token = token.replace("Bearer ", "").trim();
            return Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new AccessDeniedException("Access denied : invalid Token");
        }
    }
}
