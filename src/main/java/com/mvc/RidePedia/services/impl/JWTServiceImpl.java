package com.mvc.RidePedia.services.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JWTServiceImpl {

    @Value("${jwt.secret.key}")
    private String secretKey;
    private String generateToken(UserDetails userDetails)
    {
        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24)).signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private <T> T extractClaim(String token, Function<Claims,T> claimsResolvers)
    {

        final Claims claims= extractAllClaims(token);
        return claimsResolvers.apply(claims);

    }

    public String extractUserName(String token)
    {
        return extractClaim(token,Claims::getSubject);
    }

    private Key getSigninKey()
    {
        byte[] key= Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(key);
    }

    private Claims extractAllClaims(String token)
    {
        return Jwts.parserBuilder().setSigningKey(getSigninKey()).build().parseClaimsJws(token).getBody();
    }
}
