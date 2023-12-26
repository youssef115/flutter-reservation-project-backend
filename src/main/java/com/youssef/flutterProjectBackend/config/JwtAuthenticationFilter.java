package com.youssef.flutterProjectBackend.config;

import com.youssef.flutterProjectBackend.handler.InvalidTokenException;
import com.youssef.flutterProjectBackend.handler.ReservationApiException;
import com.youssef.flutterProjectBackend.service.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(  @NonNull HttpServletRequest request,
                                      @NonNull   HttpServletResponse response,
                                      @NonNull FilterChain filterChain) throws ServletException, IOException {



        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        try{
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {

            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7);
        if(StringUtils.hasText(jwt) && jwtService.validateToken(jwt)){
        userEmail = jwtService.extractUsername(jwt);
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            if (jwtService.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }}
        filterChain.doFilter(request, response);


        } catch (MalformedJwtException e) {
            throw new ReservationApiException(HttpStatus.BAD_REQUEST, "Invalid Token");
        } catch (ExpiredJwtException e) {
            throw new ReservationApiException(HttpStatus.BAD_REQUEST, "Token Expired");
        } catch (UnsupportedJwtException e) {
            throw new ReservationApiException(HttpStatus.BAD_REQUEST, "Unsupported token");
        } catch (IllegalArgumentException e) {
            throw new ReservationApiException(HttpStatus.BAD_REQUEST, "Invalid argument");
        }
    }
}
