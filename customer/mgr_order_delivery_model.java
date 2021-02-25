package customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Order.ProConnectPool;

public class mgr_order_delivery_model {

	Connection con;

	public mgr_order_delivery_model() throws Exception {
		connectDB();
	}

	void connectDB() throws Exception {
		con = ProConnectPool.getConnection();
	}

//	public ArrayList orderList() throws SQLException {
//		ArrayList list = new ArrayList();
//
//		String sql = "select ot.order_no, c.cus_name, c.cus_address, pm.payment_types, ot.order_total_amount,"
//				+ "ot.order_time, ds.delivery_state_types, dm.deliveryman_name from order_tab ot, customer c, "
//				+ "payment_method pm, deliveryMan dm, delivery_state ds "
//				+ "where ot.cus_no = c.cus_no and pm.payment_method_num = ot.payment_method_num and ds.delivery"
//				+ "_state_num = ot.delivery_state_num";
//		Statement sm = con.createStatement();
//		ResultSet rs = sm.executeQuery(sql);
//
//		while (rs.next()) {
//			ArrayList tmp = new ArrayList();
//			tmp.add(rs.getInt("order_no"));
//			tmp.add(rs.getString("cus_name"));
//			tmp.add(rs.getString("cus_address"));
//			tmp.add(rs.getString("payment_types"));
//			tmp.add(rs.getInt("order_total_amount"));
//			tmp.add(rs.getString("order_time"));
//			tmp.add(rs.getString("delivery_state_types"));
//			tmp.add(rs.getString("deliveryman_name"));
//			list.add(tmp);
//		}
//
//		rs.close();
//		sm.close();
//		return list;
//	}

	public ArrayList orderListBefore() throws SQLException {
		ArrayList list = new ArrayList();
		int sum = 0;
		String sql = "select ot.order_no, c.cus_name, ot.order_address, pm.payment_types, ot.order_total_amount, "
				+ "ot.order_time, ds.delivery_state_types, nvl2(dm.deliveryman_name, to_char(dm.deliveryman_name),'-') from order_tab ot, customer c, payment_method pm, "
				+ "delivery_state ds , deliveryman dm where ot.cus_no = c.cus_no and pm.payment_method_num = ot.payment_method_num"
				+ " and ds.delivery_state_num = ot.delivery_state_num and ot.deliveryman_num = dm.deliveryman_num(+) and (ot.delivery_state_num = 2 or ot.delivery_state_num = 3)";
		Statement sm = con.createStatement();
		ResultSet rs = sm.executeQuery(sql);

		while (rs.next()) {
			ArrayList tmp = new ArrayList();
			tmp.add(rs.getInt("order_no"));
			tmp.add(rs.getString("cus_name"));
			tmp.add(rs.getString("order_address"));
			tmp.add(rs.getString("payment_types"));
			sum = rs.getInt("order_total_amount");
			tmp.add(sum);
			tmp.add(rs.getString("order_time"));
			tmp.add(rs.getString("delivery_state_types"));
			tmp.add(rs.getString(8));
			list.add(tmp);
		}

		rs.close();
		sm.close();
		return list;
	}

	public boolean chkGo(String str) {
		if (str.equals("-")) {
			return true;
		}
		else
		{
			return false;
		}
	}

	public ArrayList orderListDone() throws SQLException {
		ArrayList list = new ArrayList();

		String sql = "select ot.order_no, c.cus_name, c.cus_address, pm.payment_types, ot.order_total_amount, "
				+ "ot.order_time, ds.delivery_state_types ,dm.deliveryman_name from order_tab ot, customer c, "
				+ "payment_method pm, deliveryMan dm, delivery_state ds where ot.cus_no = c.cus_no and "
				+ "pm.payment_method_num = ot.payment_method_num and ds.delivery_state_num = ot.delivery_state_num "
				+ "and dm.deliveryman_num = ot.deliveryman_num and ot.delivery_state_num = 4";
		Statement sm = con.createStatement();
		ResultSet rs = sm.executeQuery(sql);

		while (rs.next()) {
			ArrayList tmp = new ArrayList();
			tmp.add(rs.getInt("order_no"));
			tmp.add(rs.getString("cus_name"));
			tmp.add(rs.getString("cus_address"));
			tmp.add(rs.getString("payment_types"));
			tmp.add(rs.getInt("order_total_amount"));
			tmp.add(rs.getString("order_time"));
			tmp.add(rs.getString("delivery_state_types"));
			tmp.add(rs.getString("deliveryman_name"));
			list.add(tmp);
		}

		rs.close();
		sm.close();
		return list;
	}

	public ArrayList dList() throws SQLException {
		ArrayList list = new ArrayList();

		String sql = "select * from deliveryMan where deliveryman_state like '대기'";
		Statement sm = con.createStatement();
		ResultSet rs = sm.executeQuery(sql);

		while (rs.next()) {
			ArrayList tmp = new ArrayList();
			tmp.add(rs.getInt(1));
			tmp.add(rs.getString(2));
			tmp.add(rs.getString(3));
			tmp.add(rs.getString(4));
			list.add(tmp);
		}

		rs.close();
		sm.close();
		return list;
	}

	public void delGO(String orderno, String delno) throws Exception {
		// 딜리버리 고
		int order = Integer.parseInt(orderno);
		int del = Integer.parseInt(delno);
		String sql1 = "update order_tab set delivery_state_num = 3, deliveryman_num = " + del + " where order_no = "
				+ order;

		PreparedStatement ps = con.prepareStatement(sql1);
		System.out.println("주문업데이트");
		ps.executeUpdate();
		ps.close();

		String sql2 = "update deliveryman set deliveryman_state = '배달중' where deliveryman_num = " + del;

		PreparedStatement ps2 = con.prepareStatement(sql2);
		System.out.println("배달원업데이트");
		ps2.executeUpdate();
		ps2.close();

	}

	public void completeDel(String orderno) throws Exception {
		// 배송완료!
		int order = Integer.parseInt(orderno);
		int delman = 0;
		int cusno = 0;
		int sum = 0;
		String sql1 = "update order_tab set delivery_state_num = 4 where order_no = " + order;

		PreparedStatement ps = con.prepareStatement(sql1);
		System.out.println("주문됨");
		ps.executeUpdate();
		ps.close();

		String sql2 = "select deliveryman_num, cus_no, order_total_amount from order_tab where order_no = " + order;
		Statement sm = con.createStatement();
		ResultSet rs = sm.executeQuery(sql2);
		if (rs.next()) {
			delman = rs.getInt("deliveryman_num");
			cusno = rs.getInt("cus_no");
			sum = rs.getInt("order_total_amount");
		}

		String sql3 = "update deliveryman set deliveryman_state = '대기' where deliveryman_num = " + delman;
		PreparedStatement ps2 = con.prepareStatement(sql3);
		System.out.println("업뎃됨");
		ps2.executeUpdate();
		ps2.close();
		int imsi = sum/20;
		String sql4 = "insert into point_inout values(point_seq.nextval,?,?,sysdate,?)";
		PreparedStatement ps3 = con.prepareStatement(sql4);
		System.out.println("포인트추가");
		ps3.setInt(1, cusno);
		ps3.setInt(2, imsi);
		ps3.setString(3, sum+" 구입");
		ps3.executeUpdate();
		ps3.close();
	}
}
