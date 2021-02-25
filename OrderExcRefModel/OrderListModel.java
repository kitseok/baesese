package OrderExcRefModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Order.ProConnectPool;

public class OrderListModel {
	static Connection con = null;

	public OrderListModel() {
		try {
			con = ProConnectPool.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList recentList(String num, String name) throws SQLException { 
		ArrayList list = new ArrayList();
		int no = Integer.parseInt(num);
		String sql = "select o.order_detail_num, s.shop_goods_name, t.order_total_amount, sh.shop_name, s.shop_goods_price, o.order_detail_quantity,sh.shop_phone  "
				+ "from order_tab t, shop_goods_details s, order_detail o, shop sh "
				+ "where s.shop_goods_no = o.shop_goods_no and s.shop_no = sh.shop_no and o.order_detail_num = " + no
				+ " and s.shop_goods_name = '" + name + "'";
		
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			// ArrayList temp = new ArrayList();
			list.add(rs.getString("order_detail_num"));
			list.add(rs.getString("shop_goods_name"));
			list.add(rs.getString("shop_name"));
			list.add(rs.getString("shop_goods_price"));
			list.add(rs.getString("order_detail_quantity"));
			list.add(rs.getString("order_total_amount"));
			list.add(rs.getString("shop_phone"));

//			jubun.setText(list.get(0).toString());
//			goodsname.setText(list.get(1).toString());
//			seller.setText(list.get(2).toString());
//			goodsprice.setText(list.get(3).toString());
//			order_quantity.setText(list.get(5).toString());
//			totamount.setText(list.get(6).toString());

			// list.add(temp);
		}
		// System.out.print(list);
		rs.close();
		stmt.close();
		return list;
	}

	public ArrayList recentList2(String num, String name) throws SQLException { // 
		ArrayList list = new ArrayList();
		int no = Integer.parseInt(num);
		String sql = "insert in o.order_detail_num, s.shop_goods_name, t.order_total_amount, sh.shop_name, s.shop_goods_price, o.order_detail_quantity  "
				+ "from order_tab t, shop_goods_details s, order_detail o, shop sh "
				+ "where t.order_no = o.order_detail_num and s.shop_goods_no = o.shop_goods_no and s.shop_no = sh.shop_no and o.order_detail_num = "
				+ no + " and s.shop_goods_name = '" + name + "'";
		
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			// ArrayList temp = new ArrayList();
			list.add(rs.getString("order_detail_num")); 
			list.add(rs.getString("shop_goods_name"));
			list.add(rs.getString("shop_name"));
			list.add(rs.getString("shop_goods_price"));
			list.add(rs.getString("order_detail_quantity"));
			list.add(rs.getString("order_total_amount"));

//			jubun.setText(list.get(0).toString());
//			goodsname.setText(list.get(1).toString());
//			seller.setText(list.get(2).toString());
//			goodsprice.setText(list.get(3).toString());
//			order_quantity.setText(list.get(5).toString());
//			totamount.setText(list.get(6).toString());

			// list.add(temp);
		}
		// System.out.print(list);
		rs.close();
		stmt.close();
		return list;
	}

	public void insertEx_ref(String jubun, String count12, String comboBox, String ref_text, String tf_ref_money) {
		ArrayList al = new ArrayList();
		String sql = "insert into exchange_refund_details values(?,'301',exc_ref_number.nextval,sysdate,? ,?, 401,?,?)";
		try {
			PreparedStatement ppst = con.prepareStatement(sql);
			ppst.setInt(1, Integer.parseInt(jubun));
			ppst.setInt(2, Integer.parseInt(count12));
			ppst.setString(3, comboBox);
			ppst.setString(4, ref_text);
			ppst.setInt(5, Integer.parseInt(tf_ref_money));
			// ppst.setString(4, );

			ppst.executeUpdate();
			ppst.close();
			
			String sql2 = "insert into exchange_refund_mgr values(exchange_refund_mgr_seq.nextval,exc_ref_number.currval,'kyw',sysdate,'L')";// 
	        PreparedStatement ppst2 = con.prepareStatement(sql2);
	        ppst2.executeUpdate();
	        ppst2.close();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void review(String jubun, String count12, String comboBox, String ref_text, String tf_ref_money) {
		ArrayList al = new ArrayList();
		String sql = "insert into exchange_refund_details values(?,'301',exc_ref_number.nextval,sysdate,? ,?, 401,?,?)";
		try {
			PreparedStatement ppst = con.prepareStatement(sql);
			ppst.setInt(1, Integer.parseInt(jubun));
			ppst.setInt(2, Integer.parseInt(count12));
			ppst.setString(3, comboBox);
			ppst.setString(4, ref_text);
			ppst.setInt(5, Integer.parseInt(tf_ref_money));
			// ppst.setString(4, );

			ppst.executeUpdate();
			ppst.close();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
