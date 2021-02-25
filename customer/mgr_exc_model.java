package customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Order.ProConnectPool;

public class mgr_exc_model {
	Connection con;

	public mgr_exc_model() throws Exception {
		connectDB();
	}

	void connectDB() throws Exception {
		con = ProConnectPool.getConnection();
	}

	public ArrayList showgoods(String mexc_num) throws SQLException {
		ArrayList list = new ArrayList();
		int num = Integer.parseInt(mexc_num);
		String sql = "select exc.exc_ref_number, sgd.shop_goods_name, exc.exc_ref_payment_amount, exc.exc_ref_reason, "
				+ "exc.exc_ref_request_date from exchange_refund_details exc, order_detail od, shop_goods_details sgd "
				+ "where sgd.shop_goods_no = od.shop_goods_no and od.order_detail_num = exc.order_detail_num and exc.exc_ref_number = "
				+ num;

		Statement sm = con.createStatement();
		ResultSet rs = sm.executeQuery(sql);

		if (rs.next()) {
			// ArrayList tmp = new ArrayList();
			list.add(rs.getInt("exc_ref_number"));
			list.add(rs.getString("shop_goods_name"));
			list.add(rs.getString("exc_ref_payment_amount"));
			list.add(rs.getString("exc_ref_reason"));
			list.add(rs.getString("exc_ref_request_date"));
			// list.add(tmp);
		}
		rs.close();
		sm.close();
		return list;
	}

	public void click_Y(int num, String id) throws Exception {
		String sql = "update exchange_refund_mgr set exc_ref_mgrOK = 'Y', mgr_id = '"+id+"' "
				+ "where exc_ref_mgrOK != 'Y' and exc_ref_number = " + num;

		PreparedStatement ps = con.prepareStatement(sql);
		ps.executeUpdate();
		ps.close();

	}

	public void click_N(int num, String id) throws Exception {
		String sql = "update exchange_refund_mgr set exc_ref_mgrOK = 'N', mgr_id = '"+id+"' "
				+ "where exc_ref_mgrOK = 'L' and exc_ref_number = " + num;

		PreparedStatement ps = con.prepareStatement(sql);
		ps.executeUpdate();
		ps.close();

	}

	public String confirmOK(int num) throws Exception {
		String ok = "";
		String sql = "select exc_ref_mgrOK from exchange_refund_mgr where exc_ref_number = " + num;

		Statement sm = con.createStatement();
		ResultSet rs = sm.executeQuery(sql);

		if (rs.next()) {
			if (rs.getString("exc_ref_mgrOK").equals("Y")) {
				ok = "Y";
			} else if (rs.getString("exc_ref_mgrOK").equals("N")) {
				ok = "N";
			}

		}
		rs.close();
		sm.close();

		return ok;
	}
	public ArrayList recentList() throws SQLException{
	      ArrayList list = new ArrayList();
	      String sql = "select mgr.exc_ref_mgrNo, custy.exc_ref_type_types,cus.exc_ref_number, sgd.shop_goods_name, "
	            + "cus.exc_ref_reason, cus.exc_ref_quantity, cus.exc_ref_request_date, cus.exc_ref_payment_amount, "
	            + "mgr.exc_ref_mgrDate, mgr.exc_ref_mgrOK from exchange_refund_details cus, exchange_refund_mgr mgr ,"
	            + " exchange_refund_type custy, shop_goods_details sgd, order_detail od where cus.exc_ref_number = "
	            + "mgr.exc_ref_number and cus.exc_ref_type_number = custy.exc_ref_type_number and "
	            + "cus.order_detail_num = od.order_detail_num and od.shop_goods_no = sgd.shop_goods_no";
	      
	      Statement sm = con.createStatement();
	      ResultSet rs = sm.executeQuery(sql);
	      
	      while(rs.next()) {
	         ArrayList tmp = new ArrayList();
	         tmp.add(rs.getInt("exc_ref_mgrNo"));
	         tmp.add(rs.getString("exc_ref_type_types"));
	         tmp.add(rs.getInt("exc_ref_number"));
	         tmp.add(rs.getString("shop_goods_name"));
	         tmp.add(rs.getString("exc_ref_reason"));
	         tmp.add(rs.getString("exc_ref_quantity"));
	         tmp.add(rs.getString("exc_ref_request_date"));
	         tmp.add(rs.getString("exc_ref_payment_amount"));
	         tmp.add(rs.getString("exc_ref_mgrDate"));
	         tmp.add(rs.getString("exc_ref_mgrOK"));
	         list.add(tmp);
	      }
	      
	      rs.close();
	      sm.close();
	      return list;
	      
	   }
}