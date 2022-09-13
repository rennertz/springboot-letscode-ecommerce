package com.letscode.ecommerce.configuration.security;

import java.util.Date;
import java.util.List;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtils {
    
    public static String getEmail(String token) {
        return Jwts.parser()
            .setSigningKey(SecurityConstants.SECRET)
            .parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, ""))
            .getBody()
            .getSubject();
    }

    public static List<String> getRoles(String token) {
        return Jwts.parser()
            .setSigningKey(SecurityConstants.SECRET)
            .parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, ""))
            .getBody()
            .get("roles", List.class);
    }

    public static String createToken(String email, List<String> roles) {
        return Jwts.builder()
            .setSubject(email)
            .claim("roles", roles)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
            .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
            .compact();
    }
}
