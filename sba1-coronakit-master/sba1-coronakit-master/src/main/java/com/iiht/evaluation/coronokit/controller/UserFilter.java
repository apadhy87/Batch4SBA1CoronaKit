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
import javax.servlet.http.HttpSession;

import com.mysql.cj.util.StringUtils;

/**
 * Servlet Filter implementation class UserFilter
 */
@WebFilter("/user")
public class UserFilter implements Filter {
	

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req=(HttpServletRequest) request;
		PrintWriter out=response.getWriter();
		String action=request.getParameter("action");
		
		
		if(action.equals("insertuser"))
		{
			String username=request.getParameter("username");
			String email=request.getParameter("email");
			String phone=request.getParameter("phone");
			if(!StringUtils.isNullOrEmpty(username)&& !StringUtils.isNullOrEmpty(email) && !StringUtils.isNullOrEmpty(phone))
			{
				chain.doFilter(request, response);
			}
			else
			{
				out.println("<h3 style=\"color:blue\"> Please enter the value in all the fields</h3>");
				RequestDispatcher rd=req.getRequestDispatcher("newuser.jsp");
				rd.include(request, response);
			}
			
		}
		else if(action.equals("saveorder"))
		{
			String address= request.getParameter("address");
			if(!StringUtils.isNullOrEmpty(address))
			{
				chain.doFilter(request, response);
			}
			else
			{
				out.write("<p style=\"color:blue\">Please enter delivery address details to confirm order</p>");
				RequestDispatcher rd=req.getRequestDispatcher("user?action=placeorder");
				rd.include(request, response);
			}
			
		}
		else {
			chain.doFilter(request, response);
			
		}
		
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
