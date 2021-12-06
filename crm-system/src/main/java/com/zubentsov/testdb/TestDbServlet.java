package com.zubentsov.testdb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestDbServlet
 */
@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// setup connection variable
		String user = "springstudent";
		String password = "springstudent";

		String JdbcUrl = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false&ServerTimezone=UTC";
		String driver = "com.mysql.cj.jdbc.Driver";

		// get connection to DB

		try {
			PrintWriter out = response.getWriter();

			Class.forName(driver);

			Connection conn = DriverManager.getConnection(JdbcUrl, user, password);

			System.out.println("Connect to DB");

			conn.close();
		} catch (Exception exp) {

			exp.printStackTrace();
			throw new ServletException(exp);

		}
	}

}
