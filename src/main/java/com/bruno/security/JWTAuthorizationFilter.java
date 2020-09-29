package com.bruno.security;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;



public class JWTAuthorizationFilter extends BasicAuthenticationFilter{
	
	
	
	private JWTUtil jwtUtil;
	
	
	private UserDetailsService userDetailsService;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager,JWTUtil jtwUtil,UserDetailsService userDetailsService) {
		super(authenticationManager);
		this.jwtUtil = jtwUtil;
		this.userDetailsService = userDetailsService;
	}
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

		if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
			response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, PATCH, DELETE, OPTIONS");
			chain.doFilter(request, response);
		} else {
			String servletPath = request.getServletPath();
			String header = request.getHeader("Authorization");
			if (!StringUtils.isEmpty(header) && header.startsWith("Bearer ")) {
				UsernamePasswordAuthenticationToken auth = this.getAuthentication(header.substring(7));
				if (Objects.nonNull(auth)) {
					SecurityContextHolder.getContext().setAuthentication(auth);
					chain.doFilter(request, response);
				} else {
					
					chain.doFilter(request, response);
				}
			} else if (!StringUtils.isEmpty(servletPath) && servletPath.equals("/auth/login") && Objects.nonNull(header) && header.startsWith("Basic ")) {
				chain.doFilter(request, response);
			} else if (!StringUtils.isEmpty(servletPath) && servletPath.equals("/auth/refresh_token")) {
				chain.doFilter(request, response);
			} else if (!StringUtils.isEmpty(servletPath) && servletPath.equals("/auth/reset_password")) {
				chain.doFilter(request, response);
			} else {
				// Deixar sem Header
				chain.doFilter(request, response);
			}
		}
		
	}

	private UsernamePasswordAuthenticationToken getAuthentication(String token) {
		if(jwtUtil.tokenValido(token)) {
			String username = jwtUtil.getUsername(token);
			UserDetails user = userDetailsService.loadUserByUsername(username);
			return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		}
		return null;
	}

}
