

package com.ipsoft.apigateway.filter;

import com.ipsoft.apigateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
//
@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    @Autowired
    private RouteValidator routeValidator;
    @Autowired
    private JwtUtil jwtUtil;
    public AuthenticationFilter(){
        super(Config.class);
    }
//check the header
    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (routeValidator.isSecured.test(exchange.getRequest())){
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    throw new RuntimeException("Missing auth header..!!");
                }

                String authHeader=exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader!=null && authHeader.startsWith("Bearer ")){
                    authHeader=authHeader.substring(7);
                }
                try{
                    System.out.println(authHeader);
                    String userName = jwtUtil.extractUsername(authHeader);
                    System.out.println(userName);
                    RestTemplate restTemplate = new RestTemplate();
                    Boolean existsByEmail = restTemplate.getForObject("http://localhost:9085/nextTravelPVTLtdUserService/api/v1/user/existsByEmail?email="+userName, Boolean.class);
                    System.out.println(existsByEmail);
                    if (existsByEmail) {
                        jwtUtil.validateToken(authHeader);
                    }
                }catch (Exception e){
                    throw new RuntimeException("Unauthorized access to application..!!");
                }
            }

            return chain.filter(exchange);
        });
    }

    public static class Config{

    }
}
