package OrderExcRefModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Order.ProConnectPool;
public class ExchangeModel {
	static Connection con = null;

	public ExchangeModel() {
		try {
			con = ProConnectPool.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList recentList1(String num, String name) throws SQLException {  // 
		ArrayList list1 = new ArrayList();
		int no = Integer.parseInt(num);
		String sql = "select o.order_detail_num, s.shop_goods_name, t.order_total_amount, sh.shop_name, s.shop_goods_price, o.order_detail_quantity,sh.shop_phone "
				+ "from order_tab t, shop_goods_details s, order_detail o, shop sh "
				+ "where s.shop_goods_no = o.shop_goods_no and s.shop_no = sh.shop_no and o.order_detail_num = " + no+" and s.shop_goods_name = '"+name+ "'";
	
		
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			//ArrayList temp = new ArrayList();
			list1.add(rs.getString("order_detail_num"));  // 
			list1.add(rs.getString("shop_goods_name"));
			list1.add(rs.getString("shop_name"));
			list1.add(rs.getString("shop_goods_price"));
			list1.add(rs.getString("order_detail_quantity"));
			list1.add(rs.getString("order_total_amount"));
			list1.add(rs.getString("shop_phone"));
			
//			jubun.setText(list.get(0).toString());
//			goodsname.setText(list.get(1).toString());
//			seller.setText(list.get(2).toString());
//			goodsprice.setText(list.get(3).toString());
//			order_quantity.setText(list.get(5).toString());
//			totamount.setText(list.get(6).toString());
			
			//list.add(temp);
		}
		// System.out.print(list);
		rs.close();
		stmt.close();
		return list1;
	}
	
	public void insertEx_ref(String jubun,String count12, String comboBox,String ref_text,String tf_exmoney) {
	ArrayList al = new ArrayList();
    String sql = "insert into exchange_refund_details values(?,'302',exc_ref_number.nextval,sysdate,? ,?, 401,?,?)"; 
   
    
    try {
       PreparedStatement ppst = con.prepareStatement(sql);
        ppst.setInt(1, Integer.parseInt(jubun));
        ppst.setInt(2, Integer.parseInt(count12));
        ppst.setString(3, comboBox);
        ppst.setString(4, ref_text);
        ppst.setInt(5, Integer.parseInt(tf_exmoney));
       // ppst.setString(4, );
        
        ppst.executeUpdate();
        ppst.close();
        
        String sql2 = "insert into exchange_refund_mgr values(exchange_refund_mgr_seq.nextval,exc_ref_number.currval,'kyw',sysdate,'L' )";// 
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
	
	

}

