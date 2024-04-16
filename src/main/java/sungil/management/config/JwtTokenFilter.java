package sungil.management.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import sungil.management.jwt.JwtTokenProvider;
import sungil.management.jwt.JwtTokenValidator;

import java.io.IOException;
import java.util.Arrays;

public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenValidator jwtTokenValidator;

    public JwtTokenFilter(JwtTokenValidator jwtTokenValidator) {
        this.jwtTokenValidator = jwtTokenValidator;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String bearerToken = extractTokenFromCookie(request.getHeader("Cookie"));
        System.out.println(Arrays.stream(request.getCookies()).toList());
//        String token = "";
//        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
//            token = bearerToken.substring(7);
//        } else {
//            token = null;
//        }


        if (bearerToken != null && jwtTokenValidator.validateToken(bearerToken)) {
            Authentication auth = jwtTokenValidator.getAuthentication(bearerToken);
            if (auth != null) {
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(request, response);
    }


    private String extractTokenFromCookie(String cookie) {
        System.out.println(cookie);
        String[] cookies = cookie.split(";");


        System.out.println(Arrays.stream(cookies).toList());

        for(String c : cookies) {
            if(c.split("=")[0].equals("token")) {
                return c.split("=")[1];
            }
        }

        return null;
    }

}