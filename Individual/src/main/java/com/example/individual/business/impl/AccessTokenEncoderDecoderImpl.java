package com.example.individual.business.impl;

import com.example.individual.business.AccessTokenDecoder;
import com.example.individual.business.AccessTokenEncoder;
import com.example.individual.business.exceptions.InvalidAccessTokenException;
import com.example.individual.domain.AccessToken;
import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwt;
//import io.jsonwebtoken.JwtException;
//import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AccessTokenEncoderDecoderImpl
       // implements AccessTokenEncoder, AccessTokenDecoder
{
//    private final Key key;
//
//    public AccessTokenEncoderDecoderImpl(@Value("${jwt.secret}") String secretKey) {
//        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
//        this.key = Keys.hmacShaKeyFor(keyBytes);
//    }
//
//    @Override
//    public String encode(AccessToken accessToken) {
//        Map<String, Object> claimsMap = new HashMap<>();
//        if (accessToken.getRole() != null) {
//            claimsMap.put("role", accessToken.getRole());
//        }
//        if (accessToken.getUserId() != null) {
//            claimsMap.put("userId", accessToken.getUserId());
//        }
//
//        Instant now = Instant.now();
//        return Jwts.builder()
//                .setSubject(accessToken.getEmail())
//                .setIssuedAt(Date.from(now))
//                .setExpiration(Date.from(now.plus(30, ChronoUnit.MINUTES)))
//                .addClaims(claimsMap)
//                .signWith(key)
//                .compact();
//    }
//
//    @Override
//    public AccessToken decode(String accessTokenEncoded) {
//        try {
////            Jwt jwt =  Jwts.parserBuilder().setSigningKey(key).build().parse(accessTokenEncoded);
////            Claims claims = (Claims) jwt.getBody();
////
////            return AccessToken.builder()
////                    .email(claims.getSubject())
////                    .role(claims.get("role")
////                    .userId(claims.get("userId", Long.class))
////                    .build();
//            return AccessToken.builder().build();
//        } catch (JwtException e) {
//            throw new InvalidAccessTokenException(e.getMessage());
//        }
//    }
}