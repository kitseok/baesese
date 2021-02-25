package ShopModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class ReviewComentModel {
	Connection con;
	int hostNo = 0;

	// constructor
	public ReviewComentModel() throws Exception {
		connectDB();
	}

	void connectDB() throws Exception {
		con = ConnectionPool.getConnection();
	}

	public int searchhostno(String hostNo1) throws Exception {
		hostNo = Integer.parseInt(hostNo1);
		return hostNo;
		
	}
	public String selectShopName(String shopno) throws SQLException {
		String name = null;
		int no = Integer.parseInt(shopno);
		String sql = "select SHOP_NAME from shop where shop_no= " + no;

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		if(rs.next())
		{
			name = rs.getString("SHOP_NAME");
		}
			
			

//System.out.print(list);
		rs.close();
		stmt.close();
		return name;
	}
	public String selectHostName(String shopno) throws SQLException {
		String hostname = null;
		String sql = "select SHOP_HOST_NAME from shop where shop_no= " + Integer.parseInt(shopno);
				//returnScheduled
		
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		rs.next();
			
			hostname = rs.getString("SHOP_HOST_NAME");

//System.out.print(list);
		rs.close();
		stmt.close();
		return hostname;
	}
	public ArrayList reviewList(String shopno) throws SQLException {
		ArrayList list = new ArrayList();
		//int i = Integer.parseInt(shopno);
		String sql = "select s.REVIEW_NO,  s.SHOP_GOODS_NO, g.SHOP_GOODS_name, s.REVIEW_RATING,  c.CUS_NAME , s.REVIEW_COMENT, s.REVIEW_UPTIME,  s.ANS_COMENT,  s.ANS_UPTIME \r\n" + 
				"from shop_review s, SHOP_GOODS_DETAILS g, customer c where c.cus_no = s.cus_no and s.SHOP_GOODS_NO = g.SHOP_GOODS_NO and s.shop_no  ="
				+shopno;
		// returnScheduled

		// PreparedStatement prsd = con.prepareStatement(sql);
		// prsd.setInt(1, hostnum);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getString("REVIEW_NO"));
			temp.add(rs.getString("SHOP_GOODS_NO"));
			temp.add(rs.getString("SHOP_GOODS_name"));
			temp.add(rs.getString("REVIEW_RATING"));
			temp.add(rs.getString("CUS_NAME"));
			//temp.add(rs.getString("REVIEW_PICTURE"));
			temp.add(rs.getString("REVIEW_COMENT"));
			temp.add(rs.getString("REVIEW_UPTIME"));
			temp.add(rs.getString("ANS_COMENT"));
			temp.add(rs.getString("ANS_UPTIME"));
			list.add(temp);
		}
//System.out.print(list);
		rs.close();
		stmt.close();
		return list;
	}
	public void areainsert(int shopno, String area) throws Exception {
		String sql = "update shop_review set ANS_COMENT = ?, ANS_UPTIME = sysdate where review_no = ?";
		// values(1상품넘버, 2점포넘버, shop_goods_no_seq.nextval,
		// 3상세정보, 4재고, 5원산지, 6가격, 7무게, 8상품상세명)
		System.out.println("실행됨");
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, area);
		ps.setInt(2, shopno);
		
		ps.executeUpdate();
		ps.close();
	}
}
