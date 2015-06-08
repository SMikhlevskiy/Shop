package smikhlevskiy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class ClientsView {
	public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	public static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/shop";
	public static final String DB_USER = "smikhlevskiy";
	public static final String DB_PASSWORD = "termocont";

	private static final ClientsView singleton = new ClientsView();

	private List<String> list = new ArrayList<String>();

	public static ClientsView getInstance() {
		return singleton;
	}

	private ClientsView() {

	} // private constructor

	public void add(String s) {
		list.add(s);
	}

	private Connection getDBConnection() {
		Connection dbConnection = null;

		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}

		try {
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,
					DB_PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return dbConnection;
	}

	public String PrintResult(ResultSet rs) throws SQLException {
		// #5
		StringBuilder s = new StringBuilder("");
		ResultSetMetaData md = rs.getMetaData();

		// table of data representing a database result set,

		for (int i = 1; i <= md.getColumnCount(); i++)
			s.append(md.getColumnName(i) + "\t\t");
		s.append("<br>");

		while (rs.next()) {
			for (int i = 1; i <= md.getColumnCount(); i++) {
				s.append(rs.getString(i) + "\t\t");
				
			}
			s.append("<br>");
		}

		return s.toString();

	}

	@Override
	public String toString() {
		
		Connection conn = getDBConnection();
		String S = "";
		
		
		


		if (conn == null) {

			return "Error creating connection!";
		}
		try {
			try {

				PreparedStatement ps = conn
						.prepareStatement("SELECT * FROM CLIENT");
	
				try {
					// table of data representing a database result set,
					ResultSet rs = ps.executeQuery();
					try {
						S = PrintResult(rs); //

					} finally {
						rs.close(); // rs can't be null according to the docs
					}
				} finally {
					if (ps != null)
						ps.close();
				}
	
				StringBuilder sb = new StringBuilder();

				for (String s : list)
					sb.append("<br>").append(s);
			} finally {
				conn.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			return ex.toString();
		}

		return S;// sb.toString();
	}

}
