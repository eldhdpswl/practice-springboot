package dev.eldhdpswl.auth.infra;

import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade {
    public String getUserName(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
