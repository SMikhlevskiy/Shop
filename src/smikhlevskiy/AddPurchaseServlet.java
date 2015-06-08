package smikhlevskiy;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.http.*;

import java.sql.PreparedStatement;


@SuppressWarnings("serial")
public class AddPurchaseServlet extends HttpServlet {
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

		final String idClient = req.getParameter("idClient");
		final String idMerchandise = req.getParameter("idMerchandise");


		Connection conn = getDBConnection();
		String S = "";
		try {
		if (conn != null) {
			 PreparedStatement ps = conn .prepareStatement(
			 "INSERT INTO Purchase (idClient, idMerchandise) VALUES(?, ?)"
			  ); try { ps.setString(1,idClient); ps.setString(2,
					  idMerchandise);
			  
			 ps.executeUpdate(); } finally { if (ps != null) ps.close(); }
		}

		

		
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	

		resp.sendRedirect("/AddPurchase.jsp");
	}
}
