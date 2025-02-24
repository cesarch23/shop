package com.edu.shop.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {
    private static final String SECRET_KEY = "INGRESAR_TU_CLAVE_AQUI_123456";
    private static Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);

    public String createToken(String username){
        return JWT.create()
                .withSubject(username)
                .withIssuer("this-is-issuer")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis()+ TimeUnit.MINUTES.toMillis(5)))
                .sign(ALGORITHM);

    }
    public boolean isValid(String token){
        try{
            JWT.require(ALGORITHM)
                    .build()
                    .verify(token);
            return true;

        }
        catch (JWTVerificationException e){
            return false;
        }
    }
    public String getUsername(String jwt){
        return JWT.require(ALGORITHM)
                .build()
                .verify(jwt)
                .getSubject();
    }
}
