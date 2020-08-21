package com.iiht.evaluation.coronokit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.mysql.cj.util.StringUtils;
@WebFilter("/admin")
public class AdminFilter implements Filter {
	
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		PrintWriter out=response.getWriter();
		String action=request.getParameter("action");

		if(action.equals("login"))
		{
			String username=request.getParameter("loginid");
			String password=request.getParameter("password");
			
			if(username.equals("admin")&& password.equals("admin"))
			{
				chain.doFilter(request, response);
			}
			else 
			{
				out.println("<h3 style=\"color:blue\"> Please provide valid credential</h3>");
				RequestDispatcher rd=req.getRequestDispatcher("index.jsp");
				rd.include(request, response);
			}
		
			
		}
		else
			chain.doFilter(request, response);
			
		}
		
	

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
