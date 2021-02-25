package categoryModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Order.*;

public class tacegory_detailModel {
	static Connection con = null;
public tacegory_detailModel()
{
	try {
		con = ProConnectPool.getConnection();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	public ArrayList choiceData(String shop_goods_no) {
		ArrayList al = new ArrayList();
		String sql = "select s.shop_name, g.shop_goods_name, g.shop_goods_stok, g.shop_goods_origin, g.shop_goods_weight, g.shop_goods_price, g.SHOP_GOODS_INFORMATION, g.goods_img from shop_goods_details g, shop s where s.shop_no = g.shop_no and g.shop_goods_no = ?";
		try {
			PreparedStatement ppst = con.prepareStatement(sql);
		    ppst.setInt(1, Integer.parseInt(shop_goods_no));
		    ResultSet rs =  ppst.executeQuery();
		    while(rs.next())
		    {
		    	al.add(rs.getString("shop_name"));
		    	al.add(rs.getString("shop_goods_name"));
		    	al.add(rs.getInt("shop_goods_stok"));
		    	al.add(rs.getString("shop_goods_origin"));
		    	al.add(rs.getString("SHOP_GOODS_INFORMATION"));
		    	al.add(rs.getString("shop_goods_weight"));
		    	al.add(rs.getInt("shop_goods_price"));
		    	al.add(rs.getString("goods_img"));
		    	
		    }
		    rs.close();
		    ppst.close();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return al;
	}
	
	public int CustnoFind(String id) throws SQLException
	{
		String sql = "select cus_no from customer where cus_id = '" + id+"'";
		int num = 0;
		Statement ps = con.createStatement();
		ResultSet rs = ps.executeQuery(sql);
		
		if(rs.next())
		{
			num = rs.getInt("cus_no");
		}
		
		return num;
	}
	
	public void BasketAdd(int shopno,int num,int price,int qty) throws SQLException
	{
		

		String sql = "insert into basket(custnum,goodsnum,price,qty) values(?,?,?,?)";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setInt(1, num);
		ps.setInt(2, shopno);
		ps.setInt(3, price);
		ps.setInt(4, qty);
		
		ps.executeUpdate();
		
		ps.close();
		
		
	}
	public ArrayList selectCusShopReview(String shopGoodsNo, String string) {
		//상품번호, 유저번호
				ArrayList al = new ArrayList();
				String sql1 = "select s.shop_no from shop s, shop_goods_details d where s.shop_no = d.shop_no and d.shop_goods_no =?";
				try {
					int shop_no = -1;
					PreparedStatement ppst1 = con.prepareStatement(sql1);
				    ppst1.setInt(1, Integer.parseInt(shopGoodsNo));
				    ResultSet rs1 = ppst1.executeQuery();
				    if(rs1.next())
				    {
				    	shop_no = rs1.getInt("shop_no");
				    }
				    rs1.close();
				    ppst1.close();
				    String sql5 = "select cus_no from customer where cus_id = ?";
					int cus_no = -1;
					PreparedStatement ppst5 = con.prepareStatement(sql5);
					ppst5.setString(1, string);
					ResultSet rs5 = ppst5.executeQuery();
					if (rs5.next()) {
						cus_no = rs5.getInt("cus_no");
					}
					rs5.close();
					ppst5.close();
				  
				String sql = "select rating, review from cus_shop_review where cus_no = ? and shop_no = ?";
					PreparedStatement ppst = con.prepareStatement(sql);
				    ppst.setInt(1, cus_no);
				    ppst.setInt(2, shop_no);
				    ResultSet rs = ppst.executeQuery();
				    if(rs.next())
				    {
				    	al.add(rs.getInt(1));
				    	al.add(rs.getString(2));
				    } else {
				    	al.add(1);
				    	al.add("기입해주세요");
				    }
				    
				    rs.close();
				    ppst.close();
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return al;
	}
	public void insertCusView(String shopGoodsNo, String id, String rating, String review) {
		String sql1 = "select s.shop_no from shop s, shop_goods_details d where s.shop_no = d.shop_no and d.shop_goods_no =?";
		try {
			int shop_no = -1;
			PreparedStatement ppst1 = con.prepareStatement(sql1);
		    ppst1.setInt(1, Integer.parseInt(shopGoodsNo));
		    ResultSet rs = ppst1.executeQuery();
		    if(rs.next())
		    {
		    	shop_no = rs.getInt("shop_no");
		    }
		    rs.close();
		    ppst1.close();
			String sql5 = "select cus_no from customer where cus_id = ?";
			int cus_no = -1;
			PreparedStatement ppst5 = con.prepareStatement(sql5);
			ppst5.setString(1, id);
			ResultSet rs5 = ppst5.executeQuery();
			if (rs5.next()) {
				cus_no = rs5.getInt("cus_no");
			}
			rs5.close();
			ppst5.close();

		    //만약 이미 저장되있다면 업데이트
		    String sql2 = "select rating, review from cus_shop_review where cus_no = ? and shop_no = ?";
			PreparedStatement ppst2 = con.prepareStatement(sql2);
		    ppst2.setInt(1, cus_no);
		    ppst2.setInt(2, shop_no);
		    ResultSet rs2 = ppst2.executeQuery();
		    if(rs2.next())
		    {
		    	String sql3 = "update cus_shop_review set rating = ?, review = ? where cus_no = ? and shop_no = ?";
				PreparedStatement ppst3 = con.prepareStatement(sql3);
			    ppst3.setInt(1, Integer.parseInt(rating));
			    ppst3.setString(2, review);
			    ppst3.setInt(3, cus_no);
			    ppst3.setInt(4, shop_no);
			    ppst3.executeUpdate();
			    ppst3.close();
		    } else {
		    
		    String sql = "insert into cus_shop_review values(cus_shop_review_seq.nextval, ?, ?, ?, ?)";
		    
			PreparedStatement ppst = con.prepareStatement(sql);
		    ppst.setInt(1, cus_no);
		    ppst.setInt(2, shop_no);
		    ppst.setInt(3, Integer.parseInt(rating));
		    ppst.setString(4, review);
		    
		    ppst.executeUpdate();
		    ppst.close();
		    }
		    rs.close();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
