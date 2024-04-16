package sungil.management.dto;

import org.springframework.security.core.context.SecurityContextHolder;

public class LoginedUser {
    protected final String userId = SecurityContextHolder.getContext().getAuthentication().getName();
}
