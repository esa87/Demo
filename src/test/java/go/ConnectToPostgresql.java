package go;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectToPostgresql {
	final String urlString = "jdbc:postgresql://localhost:5437/test_db";
	final String username = "postgres";
	final String password = "secret";

	private Connection connect() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(urlString, username, password);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// TODO: handle exception
		}
		return conn;
	}

	public String getSelect(String tor_id) {
		String dt = null;
		try {
			Statement st = connect().createStatement();
			ResultSet rs = st.executeQuery(
					"SELECT \"Id\", \"CreateDateTime\", \"Data\" FROM public.\"ConfirmationDataQueue\" where \"Data\" like '%"
							+ tor_id + "%';");
			if (rs.next()) {
				dt = rs.getString("Data");

			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dt;
	}
}