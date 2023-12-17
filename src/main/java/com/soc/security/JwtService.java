package com.soc.security;



import java.util.Date;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.soc.entity.User;


@Component
public class JwtService {

	
	private final String secretKey = "$ec^@tKe&3452";
    private static final long EXPIRATION_TIME = 864_000_000; // 10 days
    Algorithm algorithm = Algorithm.HMAC512(secretKey);
	
	public String generateToken(User user)
	{
	    
	    String token = JWT.create()
	        .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
	        .withSubject(user.getId().toString())
	        .sign(algorithm);
		return token;
	}
	
	
	
	public DecodedJWT validateToken(String jwt) {
		DecodedJWT decodedJWT;
		try {
		    JWTVerifier verifier = JWT.require(algorithm)
		        .build();
		    decodedJWT = verifier.verify(jwt);
		    return decodedJWT;
		} catch (JWTVerificationException exception){
			return null;
		}
                
    }
}
