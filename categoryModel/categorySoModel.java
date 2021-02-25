package categoryModel;

import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import Order.*;

public class categorySoModel {

	static Connection con = null;
public categorySoModel()
{
	try {
		con = ProConnectPool.getConnection();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	
	public ArrayList<String> combobxInsert_dae() {
		ArrayList<String> al = new ArrayList<>(); 
		String sql = "select main_category_name from main_category";
		PreparedStatement ppst;
		try {
			ppst = con.prepareStatement(sql);
		    
		    ResultSet rs =  ppst.executeQuery();
		    while(rs.next())
		    {
		    	al.add(rs.getString("main_category_name"));
		    }
		    System.out.println(al);
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

	public ArrayList<String> combobxInsert_so(String choice) {
		ArrayList<String> al = new ArrayList<>(); 
		System.out.println(choice);
		String sql = "select middle_category_name from middle_category i, main_category m where i.main_category = m.main_category and m.main_category_name = ?" ;
		PreparedStatement ppst;
		try {
			ppst = con.prepareStatement(sql);
		    ppst.setString(1, choice);
		    ResultSet rs =  ppst.executeQuery();
		    while(rs.next())
		    {
		    	al.add(rs.getString("middle_category_name"));
		    }
		    System.out.println(al);
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
	
	public String name_to_id(String cus_id) throws Exception
	   {
		   String name = "";
		      String sql = "select cus_name from customer where cus_id like '" + cus_id + "'";

		      Statement sm = con.createStatement();
		      ResultSet rs = sm.executeQuery(sql);

		      if (rs.next()) {
		    	  name = rs.getString("cus_name");
		      }
		      return name;
		   
	   }
	public ArrayList<String> replace_so(String string) {
		ArrayList<String> al = new ArrayList<>(); 
		String sql = "select middle_category_name from middle_category i, main_category m where i.main_category = m.main_category and m.main_category_name = ?" ;
		;
		try {
			PreparedStatement ppst = con.prepareStatement(sql);
		    ppst.setString(1, string);
		    ResultSet rs =  ppst.executeQuery();
		    while(rs.next())
		    {
		    	al.add(rs.getString("middle_category_name"));
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
	public ArrayList selectTable(String name) {
		ArrayList al = new ArrayList<>();
		String sql = "select shop_goods_no,shop_goods_name, goods_img, shop_name, shop_goods_stok, shop_goods_origin, shop_goods_weight, shop_goods_price from middle_category m, shop_goods_details s, goods g, shop sh where g.middle_category_no = m.middle_category_no and g.goods_no = s.goods_no and sh.shop_no = s.shop_no and m.middle_category_name=?";

		try {
			PreparedStatement ppst = con.prepareStatement(sql);
		    ppst.setString(1, name);
		    ResultSet rs =  ppst.executeQuery();
		    while(rs.next())
		    {
		    	ArrayList imsi = new ArrayList<>();
		    	imsi.add(rs.getInt("shop_goods_no"));
		    	URL url = new URL(rs.getString("goods_img"));
				Image image = ImageIO.read(url);
				Image chimg = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
				
				imsi.add(new ImageIcon(chimg));
		    	imsi.add(rs.getString("shop_name"));
		    	imsi.add(rs.getString("shop_goods_name"));
		    	imsi.add(rs.getInt("shop_goods_stok"));
		    	imsi.add(rs.getString("shop_goods_origin"));
		    	imsi.add(rs.getString("shop_goods_weight"));
		    	imsi.add(rs.getInt("shop_goods_price"));
		    	al.add(imsi);
		    }
		    rs.close();
		    ppst.close();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return al;
	}

	public ArrayList like(String name) {
		ArrayList al = new ArrayList<>();
		String sql = "select shop_goods_no, shop_goods_name, goods_img, shop_name, shop_goods_stok, shop_goods_origin, shop_goods_weight, shop_goods_price from middle_category m, shop_goods_details s, goods g, shop sh where g.middle_category_no = m.middle_category_no and g.goods_no = s.goods_no and sh.shop_no = s.shop_no and s.shop_goods_name like '%"+name+"%'";

		try {
			Statement ppst = con.createStatement();
			ResultSet rs = ppst.executeQuery(sql);
			while (rs.next()) {
				ArrayList imsi = new ArrayList<>();
				imsi.add(rs.getInt("shop_goods_no"));
				URL url = new URL(rs.getString("goods_img"));
				Image image = ImageIO.read(url);
				Image chimg = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
				
				imsi.add(new ImageIcon(chimg));
				imsi.add(rs.getString("shop_name"));
				imsi.add(rs.getString("shop_goods_name"));
				imsi.add(rs.getInt("shop_goods_stok"));
				imsi.add(rs.getString("shop_goods_origin"));
				imsi.add(rs.getString("shop_goods_weight"));
				imsi.add(rs.getInt("shop_goods_price"));
				al.add(imsi);
		    }
		    rs.close();
		    ppst.close();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return al;
	}

	public String getMain_category(int no) {
		// TODO Auto-generated method stub\
		String str = null;
		ArrayList al = new ArrayList<>();
		String sql = "select main_category_name from main_category a, goods g, middle_category m, shop_goods_details d where d.shop_goods_no = 3 and d.goods_no = g.goods_no and g.middle_category_no = m.middle_category_no and m.main_category = a.main_category";

		try {
			Statement ppst = con.createStatement();
		    ResultSet rs =  ppst.executeQuery(sql);
		    rs.next();
		    	str = rs.getString("main_category_name");			    
		    rs.close();
		    ppst.close();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
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

	public String showImage(String str) throws SQLException {
		String al = null;
		//쿼리문 바꾸고 이미지 다시 저장하기
		String sql = "select image_url from images where image_name = '"+str+"'";
		Statement ps = con.createStatement();
		ResultSet rs = ps.executeQuery(sql);
		
		if(rs.next())
		{
			al = rs.getString("image_url");
		}
		return al;
	}
	
	
}
