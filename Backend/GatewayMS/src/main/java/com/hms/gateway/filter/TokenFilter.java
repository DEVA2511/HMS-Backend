package com.hms.gateway.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class TokenFilter  extends AbstractGatewayFilterFactory<TokenFilter.Config> {
    private static final String SECRET_KEY="H+JO7BtZ/zs9RCJjdUE442ciYuHsYOQHg82pXH37FDsuyLU/ZRMMSDv46TAglf2TqyIyUKVw38PfeS0+a1UQHA=dfgsdigjwpoiejpwjrpo324i590sdmngfwiopjfgoptj20394ijsdkgfjsdgfs=";

    public TokenFilter() {
        super(Config.class);
    }
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {

           String path= exchange.getRequest().getPath().toString();
            System.out.println("Requested Path: "+path);
            if(path.equals("/api/users/login") || path.equals("/api/users/register") || path.startsWith("/api/media/")){
                return chain.filter(exchange.mutate().request(r -> r.header("X-Secret-key", "SECRET")).build());
            }
            HttpHeaders headers= exchange.getRequest().getHeaders();
            if(!headers.containsKey(HttpHeaders.AUTHORIZATION)){
                throw new RuntimeException("Missing Authorization Header");
            }
        String authHeader= headers.getFirst(HttpHeaders.AUTHORIZATION);
            if(authHeader==null || !authHeader.startsWith("Bearer")){
                throw new RuntimeException("Invalid Authorization Header");
            }
            String token= authHeader.substring(7);
            System.out.println("Extracted Token: "+token);
            // Here you can add logic to validate the token if needed
            try{
               Claims claims= Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
               exchange=exchange.mutate().request(r->r.header("X-Secret-key","SECRET")).build();

            }catch (Exception e){
                throw new RuntimeException("Invalid Token");
            }
            return chain.filter(exchange);
        };
    }

    public static class Config{

    }
}
