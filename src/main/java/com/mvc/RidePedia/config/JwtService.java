package com.mvc.RidePedia.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class JwtService
{
    @Value("${jwt.secret.key}")
    private  String jwtSecretKey;
    public String generateToken(UserDetails user) {
        return Jwts.builder().claim("authorities",populateAuthorities(user.getAuthorities())).setSubject(user.getUsername()).
                setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*24)).signWith(getSigninKey(), SignatureAlgorithm.HS256).compact();

    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities)
    {
        if (authorities == null || authorities.isEmpty()) {
            return ""; // Boş bir string döndürme
        }
        Set<String> authoritiesList=authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());

        return String.join(",",authoritiesList);

    }

    private static Set<String> getAuthoritiesList(Set<String> authoritiesList) {
        return authoritiesList;
    }

    private Key getSigninKey()
    {
        byte[] keyBytes= Decoders.BASE64.decode(jwtSecretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
