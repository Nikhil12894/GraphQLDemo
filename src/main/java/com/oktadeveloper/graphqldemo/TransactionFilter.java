package com.oktadeveloper.graphqldemo;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class TransactionFilter implements Filter {
	@Autowired
	public UserService userService;
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    	System.out.println(httpServletRequest.getHeader("su"));
    	String su  = httpServletRequest.getHeader("su");
    	if(userService.findByEmail(su).isEmpty())
    		return;
        chain.doFilter(request, response);
    }
 
}