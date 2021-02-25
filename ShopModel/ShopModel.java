package ShopModel;
import java.sql.*;
import java.util.ArrayList;
public class ShopModel {

	Connection con;
	int hostNo =0;
	// constructor
	public ShopModel() throws Exception {
		connectDB();
	}

	void connectDB() throws Exception {
		con = ConnectionPool.getConnection();
	}
	
	public int searchhostno(String hostNo1) throws Exception{
	hostNo = Integer.parseInt(hostNo1);
	return hostNo;
	}
	public String selectShopName(int shopno) throws SQLException {
		String name = null;
		String sql = "select SHOP_NAME from shop where shop_no= " + shopno;

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		rs.next();
			
			name = (rs.getString("SHOP_NAME"));

//System.out.print(list);
		rs.close();
		stmt.close();
		return name;
	}
	
	public String selectHostName(int shopno) throws SQLException {
		String hostname = null;
		String sql = "select SHOP_HOST_NAME from shop where shop_no= " + shopno;
				//returnScheduled
		
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		rs.next();
			
			hostname = (rs.getString("SHOP_HOST_NAME"));

//System.out.print(list);
		rs.close();
		stmt.close();
		return hostname;
	}
	
	
	public ArrayList recentList(int hostnum) throws SQLException {
		ArrayList list = new ArrayList();
		String sql = "select shop_goods_name , shop_goods_stok ,shop_goods_origin ,shop_goods_weight , shop_goods_price  from shop s, shop_goods_details d where s.shop_no = d.shop_no and s.shop_no = " + hostnum;
				//returnScheduled
		
		//PreparedStatement prsd = con.prepareStatement(sql);
		//prsd.setInt(1, hostnum);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getString("shop_goods_name"));
			temp.add(rs.getString("shop_goods_stok"));
			temp.add(rs.getString("shop_goods_origin"));
			temp.add(rs.getString("shop_goods_weight"));
			temp.add(rs.getString("shop_goods_price"));
			list.add(temp);
		}
//System.out.print(list);
		rs.close();
		stmt.close();
		return list;
	}

	public String hookShopNo(String id) throws SQLException {
		String ShopNo = null;
		String sql = "select Shop_no from shop_host_mgr where shop_id = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, id);
		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			ShopNo = rs.getString("Shop_no");
		}

		return ShopNo;
	}
}