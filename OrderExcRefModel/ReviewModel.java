package OrderExcRefModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Order.ProConnectPool;

public class ReviewModel {

	static Connection con = null;

	public ReviewModel() {
		try {
			con = ProConnectPool.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList recentList2(String jubun, String i) throws SQLException {
		ArrayList list2 = new ArrayList();

		int no = Integer.parseInt(i);
		String sql = "select sp.shop_no, gs.goods_name from order_detail od, shop_goods_details sgd, goods gs, shop sp "
				+ "where od.shop_goods_no = sgd.shop_goods_no and sgd.shop_no = sp.shop_no and sgd.goods_no = gs.goods_no "
				+ "and od.order_detail_num = " + jubun;

		String sgn = "";
		String sn = "";

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			// ArrayList temp = new ArrayList();
			list2.add(rs.getInt("shop_no")); // 二쇰Ц踰덊샇 �긽�뭹紐� �뙋留ㅼ옄 寃곗젣湲덉븸 援щℓ�닔�웾 援щℓ珥앹븸 援먰솚�닔�웾
			list2.add(rs.getString("goods_name"));

		}
		rs.close();
		stmt.close();
		return list2;
	}

	public void insertEx_ref(String lb_rating, String textArea, String tfshopNo, String id, String jubun) {
		ArrayList al = new ArrayList();
		String sql = "insert into shop_review " + "values(review_no_seq.nextval," + "?," + "?," + "sysdate,"
				+ "(select cus_no from customer where cus_id = ?),"
				+ "(select shop_goods_no from order_detail where order_detail_num = ?)," + "null,null,?)"; // �긽�꽭�궡�뿭

		try {
			PreparedStatement ppst = con.prepareStatement(sql);
			ppst.setInt(1, Integer.parseInt(lb_rating));
			ppst.setString(2, textArea);//
			ppst.setString(3, id);
			ppst.setString(4, jubun);
			ppst.setString(5, tfshopNo);


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