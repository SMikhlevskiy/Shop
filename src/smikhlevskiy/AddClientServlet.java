package smikhlevskiy;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.http.*;

import java.sql.PreparedStatement;


@SuppressWarnings("serial")
public class AddClientServlet extends HttpServlet {
	//private Users userList = Users.getInstance();
	private ClientsView mlist = ClientsView.getInstance();
	private Connection getDBConnection() {
		Connection dbConnection = null;

		try {
			Class.forName(ClientsView.DB_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}

		try {
			dbConnection = DriverManager.getConnection(ClientsView.DB_CONNECTION, ClientsView.DB_USER,
					ClientsView.DB_PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return dbConnection;
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		final String FIO = req.getParameter("FIO");
		final String email = req.getParameter("email");
		final String phone = req.getParameter("phone");
		/*
		User user=new User();
		user.setAge(age);
		user.setName(name);
		user.setName(password);
		user.setName(login);
		userList.add(user);
		*/
		Connection conn = getDBConnection();
		String S = "";
		try {
		if (conn != null) {
			 PreparedStatement ps = conn .prepareStatement(
			 "INSERT INTO Client (FIO, Phone, EMAIL) VALUES(?, ?, ?)"
			  ); try { ps.setString(1, FIO); ps.setString(2,
			 phone); ps.setString(3, email);
			  
			 ps.executeUpdate(); } finally { if (ps != null) ps.close(); }
		}

		
		//mlist.add(FIO+phone+email);
		
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		
		resp.sendRedirect("/AddClient.jsp");
	}
}
