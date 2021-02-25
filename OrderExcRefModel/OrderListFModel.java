package OrderExcRefModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Order.ProConnectPool;

public class OrderListFModel {

	static Connection con = null;

	public OrderListFModel() {
		try {
			con = ProConnectPool.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public ArrayList recentList(String id) throws SQLException {  
		ArrayList list = new ArrayList();
		String sql = "select order_detail_num ,t.order_day, o.order_no, order_detail_num,s.shop_goods_name, order_detail_quantity, t.order_time_end "
				+ "from order_tab t, shop_goods_details s, order_detail o, customer c "
				+ "where t.order_no = o.order_no and s.shop_goods_no = o.shop_goods_no and t.cus_no = c.cus_no and c.cus_id = ? order by t.order_day desc";
	
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, id);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getString("order_day"));
			temp.add(rs.getString("order_no"));
			temp.add(rs.getString("order_detail_num"));
			temp.add(rs.getString("shop_goods_name"));
			temp.add(rs.getString("order_detail_quantity"));
			temp.add(rs.getString("order_time_end"));
			list.add(temp);
		}
		// System.out.print(list);
		rs.close();
		stmt.close();
		return list;
	}



}
