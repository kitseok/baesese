package Order;

import java.sql.Connection;
import java.sql.DriverManager;

public class ProConnectPool {

	static Connection con;
	String url = "jdbc:oracle:thin:@61.81.99.78:1521:bigdata";
	String user = "potato";
	String pass = "pass";
	String driver = "oracle.jdbc.driver.OracleDriver";

	private ProConnectPool() throws Exception {
		Class.forName(driver);
		con = DriverManager.getConnection(url, user, pass);

	}

	public static Connection getConnection() throws Exception {
		if (con == null) {
			ProConnectPool cp = new ProConnectPool();
		}
		return con;
	}

}
