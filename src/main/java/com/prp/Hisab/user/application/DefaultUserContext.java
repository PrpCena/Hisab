package com.prp.Hisab.user.application;

import com.prp.Hisab.user.domain.User;
import com.prp.Hisab.common.exception.UserNotAuthenticatedException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DefaultUserContext implements UserContext {
    
    private final UserService userService;
    
    
    @Override
    @Transactional
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        
        if (authentication == null || !(authentication.getPrincipal() instanceof Jwt jwt)) {
            throw new UserNotAuthenticatedException("User can not be authenticated");
        }
        
        String keycloakId = jwt.getClaimAsString("sub");
        String email = jwt.getClaimAsString("email");
        String name = jwt.getClaimAsString("name");
        
        return userService.getOrCreateUser(keycloakId, email, name);
    }
    
}
