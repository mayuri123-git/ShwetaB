package com.lti.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public EmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       int empid= Integer.parseInt(request.getParameter("empid"));
       String empname= request.getParameter("empname");
		PrintWriter out=response.getWriter();
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("config.properties");
		
		Properties p= new Properties();
		p.load(is);
		String driver=(String)p.get("db.driver");
		String url=(String)p.get("db.url");
		String user=(String)p.get("db.user");
		String pass=(String)p.get("db.pass");
		
		try
		{
			Connection con = null;
	        PreparedStatement prSt = null;
	        
	            Class.forName(driver);
	            System.out.println("ok1");
	            con = DriverManager.getConnection(url,user,pass);
	            System.out.println("ok2");
	            String sql= "insert into Employee"+" values(?,?)";
	            prSt = con.prepareStatement(sql);
	            prSt.setInt(1, empid);
	            prSt.setString(2, empname);
	            int i=prSt.executeUpdate();
	            if(i>0)
	            {
	            System.out.println("Done!!");
	            }
	            else
	            {
	            	response.sendRedirect("index.jsp");
	            }
	            out.close();
	  }
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
