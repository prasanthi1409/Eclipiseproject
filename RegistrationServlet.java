package com.registrationform;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	private static final long SerialVersionUID=1L;
	protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws IOException,ServletException
	{
		String uname=req.getParameter("name");
		String uemail=req.getParameter("email");
		String upwd=req.getParameter("pass");
		String umobile=req.getParameter("contact");
		RequestDispatcher dispatcher=null;
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/youtube","root","root");
			PreparedStatement ps=con.prepareStatement("insert into users(uname,uemail,upwd,umobile) values(?,?,?,?)");
			ps.setString(1, uname);
			ps.setString(2,uemail);
			ps.setString(3, upwd);
			ps.setString(4, umobile);
			
			int rowCount=ps.executeUpdate();
			dispatcher =req.getRequestDispatcher("registration.jsp");
			if(rowCount>0)
			{
				req.setAttribute("status","success");
			}
			else
			{
				req.setAttribute("status","failed");
			}
			dispatcher.forward(req, resp);
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		
			
	
		


		
}


}
