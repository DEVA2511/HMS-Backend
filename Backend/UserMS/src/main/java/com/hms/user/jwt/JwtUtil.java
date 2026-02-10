package com.hms.user.jwt;

import com.hms.user.Exception.HMSException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    private static final Long JWT_TOKEN_VALIDITY=5*60*60L; //5 hours
    private static final String SECRET_KEY="H+JO7BtZ/zs9RCJjdUE442ciYuHsYOQHg82pXH37FDsuyLU/ZRMMSDv46TAglf2TqyIyUKVw38PfeS0+a1UQHA=dfgsdigjwpoiejpwjrpo324i590sdmngfwiopjfgoptj20394ijsdkgfjsdgfs=";

//    here we can add methods related to JWT token generation and validation
    public String generateToken(UserDetails userDetails) throws HMSException {
        Map<String,Object> claims = new HashMap<>();
        CustomUserDetails user= (CustomUserDetails) userDetails;
        claims.put("id",user.getId());
        claims.put("role",user.getRole());
        claims.put("email",user.getUsername());
        claims.put("name",user.getName());
        claims.put("profileId",user.getProfileId());
//        System.out.println("Authorities = " + userDetails.getAuthorities());

        return doGenerateToken(claims,user.getUsername());
    }

    public String doGenerateToken(Map<String,Object> claims, String subject) {
        return  Jwts.builder().setClaims(claims
                ).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY*1000))
                .signWith(SignatureAlgorithm.HS512,SECRET_KEY).compact();
    }
}
