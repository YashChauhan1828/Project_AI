package com.Config;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Entity.UserEntity;
import com.Repository.EcomUserRepository;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Component
public class TokenConfig implements Filter
{
	@Autowired
	EcomUserRepository userdao;
	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		
		String url = req.getRequestURI().toString();
		System.out.println(url);
		if(url.contains("public")||url.contains("admin"))
		{
			chain.doFilter(request, response);
		}
		else
		{
			System.out.println("Private");
		String token = req.getHeader("authToken");
		System.out.println(token);
		Optional<UserEntity> op = userdao.findByToken(token);
		if(op.isPresent())
		{
			HttpSession session = req.getSession(true); // true = create if doesn't exist
			session.setAttribute("user", op.get());

			chain.doFilter(request, response);
		}
		else
		{
			response.getWriter().print("please Login");
		}
		}
	}
}
