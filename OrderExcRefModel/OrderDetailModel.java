package OrderExcRefModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Order.ProConnectPool;
import OrderExcRefDAO.Order_detail_DAO;


public class OrderDetailModel { 
	private static Connection con = null;
	ArrayList<Order_detail_DAO> al = new ArrayList<Order_detail_DAO>(); // order_detail

	static void connectDB() throws Exception {
		con = ProConnectPool.getConnection();

	}

	public OrderDetailModel() {
		try {
			connectDB();
			selectAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void selectAll() throws SQLException {
		String sql = "select * from order_detail";

		Statement ps = con.createStatement();
		ResultSet rs = ps.executeQuery(sql);
		while (rs.next()) {
			Order_detail_DAO dao = new Order_detail_DAO();
			dao.setShop_goods_no(rs.getInt(1)); // 12 33 161 21
			dao.setOrder_no(rs.getInt(2));
			dao.setOrder_detail_quantity(rs.getInt(3));
			dao.setOrder_detal_num(rs.getInt(4));
			// dao.setDelivery_state_num(rs.getInt(5));
			al.add(dao);
		}
		rs.close();
		ps.close();
	}

	public int getSize() {
		return al.size();
	}

	// model
	public String ImgUrl(String menu) throws SQLException {
		String sql = "select goods_img from shop_goods_details sgd, order_detail od where od.shop_goods_no = sgd.shop_goods_no and od.order_detail_num = "+menu;
		String str1 = "";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
			str1 = rs.getString("goods_img");

		}
		rs.close();
		st.close();

		return str1;

	}

	public ArrayList Orderlistview(String name) throws SQLException {
		ArrayList list = new ArrayList();
		String sql =  "select order_detail_num , shop_goods_name , shop_goods_price ,  ot.order_time_end  " + 
				"from shop_goods_details sgd, order_detail od , order_tab ot " + 
				"where ot.order_no = od.order_no and od.shop_goods_no = sgd.shop_goods_no and od.order_detail_num = "+name;

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			list.add(rs.getString("order_detail_num"));
			list.add(rs.getString("shop_goods_name"));
			list.add(rs.getString("shop_goods_price"));
			list.add(rs.getString("order_time_end"));
		}

		return list;

	}

}
