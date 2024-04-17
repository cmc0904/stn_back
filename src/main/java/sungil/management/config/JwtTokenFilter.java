package sungil.management.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import sungil.management.jwt.JwtTokenValidator;

import java.io.IOException;

public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenValidator jwtTokenValidator;

    public JwtTokenFilter(JwtTokenValidator jwtTokenValidator) {
        this.jwtTokenValidator = jwtTokenValidator;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String bearerToken = extractTokenFromCookie(request);

        if (bearerToken != null && jwtTokenValidator.validateToken(bearerToken)) {
            Authentication auth = jwtTokenValidator.getAuthentication(bearerToken);
            if (auth != null) {
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(request, response);
    }

    private String extractTokenFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if(cookies == null) return null;

        for(Cookie c : cookies) {
            if(c.getName().equals("token")) {
                return c.getValue();
            }
        }

        return null;

    }

}