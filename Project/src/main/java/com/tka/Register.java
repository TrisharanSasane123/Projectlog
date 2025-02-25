package com.tka;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/register")
public class Register extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String pass = req.getParameter("pass");
		String city = req.getParameter("city");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc://localhost:3306/204db","root","root");
			PreparedStatement ps = c.prepareStatement("insert into register(name,email,pass,city) values(?,?,?,?)");
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, pass);
			ps.setString(4, city);
			ps.executeUpdate();
			System.out.println("data is inserted..");
			c.close();
			
			System.out.println(name);
			System.out.println(email);
			System.out.println(pass);
			System.out.println(city);
			
			RequestDispatcher rd = req.getRequestDispatcher("/reglogin.html");
			rd.forward(req, resp);
			
		} catch (Exception e) {
			
		}
		
	}
	

}
