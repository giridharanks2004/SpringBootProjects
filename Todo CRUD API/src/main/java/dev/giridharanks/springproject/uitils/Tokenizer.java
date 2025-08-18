package dev.giridharanks.springproject.uitils;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class Tokenizer {
    private final String secure = "my-super-secret-key-that-is-at-least-32-chars-long";
    private final long Exp = 1000*60;
    private final Key SecretKey = Keys.hmacShaKeyFor(secure.getBytes(StandardCharsets.UTF_8));
    
    public String GenerateToken(String email){
        return Jwts.builder()
                    .setSubject(email)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis()+Exp))
                    .signWith(SecretKey,SignatureAlgorithm.HS384)
                    .compact();
    }
    public String ExtractEmail(String token){
        return Jwts.parserBuilder()
                .setSigningKey(SecretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    public boolean ValidateToken(String token){
        try{
                ExtractEmail(token);
                return true;
        }
        catch(JwtException exception){
            return false;
        }
    }
}
