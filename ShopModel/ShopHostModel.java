package ShopModel;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.*;
import java.text.*;

public class ShopHostModel {

	Connection con;
	int hostNo = 0;

	// constructor
	public ShopHostModel() throws Exception {
		connectDB();
	}

	void connectDB() throws Exception {
		con = ConnectionPool.getConnection();
	}

	public int searchhostno(String hostNo1) throws Exception {
		hostNo = Integer.parseInt(hostNo1);
		return hostNo;
	}
	public void hostupdate(String shopsignno, String shopphone, String shopname, String opentime, String startdate, String closetime, String accountno, String hostname, int shopno) throws Exception{
		String sql = "update shop set SHOP_SIGN_NO = ?, SHOP_PHONE = ?, SHOP_NAME = ?, SHOP_OPEN_TIME = ?, SHOP_START_DATE = to_date(?,'yy/mm/dd'), SHOP_CLOSE_TIME = ?,SHOP_ACCOUNT_NO = ?,SHOP_HOST_NAME = ? where SHOP_NO = " + shopno ;
	//1.상품명 2.원산지 3.재고 4.가격 5.무게 6.상품상세명 7.점포제품번호	
		
		//Date date = Date.valueOf(startdate);
		//SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh24:mi:ss");
		
		//java.util.Date u = new java.util.Date(startdate);
	//	Date d = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(startdate);

		String  day = "2016-11-22"; // 형식을 지켜야 함
		java.sql.Date d = java.sql.Date.valueOf(startdate);


		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, shopsignno);
		ps.setString(2, shopphone);
		ps.setString(3, shopname);
		ps.setString(4, opentime);
		ps.setDate(5, d);
		ps.setString(6, closetime);
		ps.setString(7, accountno);
		ps.setString(8, hostname);
		//ps.setInt(9, shopno);
		ps.executeUpdate();
		ps.close();
	}

	public ArrayList selectHostName(int shopno) throws SQLException {
		ArrayList list = new ArrayList();
		String sql = "select SHOP_NO, SHOP_HOST_NAME, SHOP_SIGN_NO, SHOP_PHONE, SHOP_NAME, SHOP_OPEN_TIME,SHOP_CLOSE_TIME , SHOP_START_DATE,SHOP_ACCOUNT_NO  from shop where shop_no= "
				+ shopno;
		// returnScheduled

		// PreparedStatement prsd = con.prepareStatement(sql);
		// prsd.setInt(1, hostnum);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			// hostname = (rs.getString("SHOP_HOST_NAME"));
			list.add(rs.getString("SHOP_HOST_NAME"));
			list.add(rs.getString("SHOP_SIGN_NO"));
			list.add(rs.getString("SHOP_PHONE"));
			list.add(rs.getString("SHOP_NAME"));
			list.add(rs.getString("SHOP_OPEN_TIME"));
			list.add(rs.getString("SHOP_CLOSE_TIME"));
			list.add(rs.getString("SHOP_START_DATE"));
			list.add(rs.getString("SHOP_ACCOUNT_NO"));

//System.out.print(list);


		}
		rs.close();
			stmt.close();
		return list;
	}
}
