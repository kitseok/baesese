package ShopModel;

import java.sql.*;
import java.util.ArrayList;

public class ShopGoodsModel {
	Connection con;
	int hostNo = 0;

	// constructor
	public ShopGoodsModel() throws Exception {
		connectDB();
	}

	void connectDB() throws Exception {
		con = ConnectionPool.getConnection();
	}

	public int searchhostno(String hostNo1) throws Exception {
		hostNo = Integer.parseInt(hostNo1);
		return hostNo;
	}

	public String selectShopName(int shopno) throws SQLException {
		String name = null;
		String sql = "select SHOP_NAME from shop where shop_no= " + shopno;
		// returnScheduled

		// PreparedStatement prsd = con.prepareStatement(sql);
		// prsd.setInt(1, hostnum);
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
		// returnScheduled

		// PreparedStatement prsd = con.prepareStatement(sql);
		// prsd.setInt(1, hostnum);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		rs.next();

		hostname = (rs.getString("SHOP_HOST_NAME"));

//System.out.print(list);
		rs.close();
		stmt.close();
		return hostname;
	}

	// insert into shop_goods_detail
	public void goodsupdate(String goodsname, String origin, int stok, int price, String Weight, String infor,
			int shopgoodsno) throws Exception {
		String sql = "update shop_goods_details set shop_goods_name = ?, shop_goods_origin = ?, shop_goods_stok = ?, shop_goods_price = ?, shop_goods_weight = ?, shop_goods_information = ? where shop_goods_no = ?";
		// 1.상품명 2.원산지 3.재고 4.가격 5.무게 6.상품상세명 7.점포제품번호
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, goodsname);
		ps.setString(2, origin);
		ps.setInt(3, stok);
		ps.setInt(4, price);
		ps.setString(5, Weight);
		ps.setString(6, infor);
		ps.setInt(7, shopgoodsno);
		ps.executeUpdate();
		ps.close();
	}

	public void goodsdelete(int goodsno) throws Exception {
		String sql = "delete shop_goods_details where shop_goods_no = ?";
		// values(1상품넘버, 2점포넘버, shop_goods_no_seq.nextval,
		// 3상세정보, 4재고, 5원산지, 6가격, 7무게, 8상품상세명)
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, goodsno);
		ps.executeUpdate();
		ps.close();
	}

	// GOODS_NO SHOP_NO SHOP_GOODS_NO SHOP_GOODS_INFORMATION SHOP_GOODS_STOK
	// SHOP_GOODS SHOP_GOODS_PRICE SHOP_GOODS_WEIGHT SHOP_GOODS_NAME
	public void goodsinsert(int goodsno, int shopno, String infor, int stok, String origin, int price, String Weight,
			String goodsname) throws Exception {
		String sql = "insert into shop_goods_details "
				+ "values(?, ?, shop_goods_no_seq.nextval, ?, ?, ?, ?, ?, ?, null)";
		// values(1상품넘버, 2점포넘버, shop_goods_no_seq.nextval,
		// 3상세정보, 4재고, 5원산지, 6가격, 7무게, 8상품상세명)
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, goodsno);
		ps.setInt(2, shopno);
		ps.setString(3, infor);
		ps.setInt(4, stok);
		ps.setString(5, origin);
		ps.setInt(6, price);
		ps.setString(7, Weight);
		ps.setString(8, goodsname);
		ps.executeUpdate();
		ps.close();
	}



	public ArrayList selectMiddleCategory() throws SQLException {
		ArrayList middle_category = new ArrayList<>();
		String sql = "select middle_category_name from middle_category";
		// returnScheduled

		// PreparedStatement prsd = con.prepareStatement(sql);
		// prsd.setInt(1, hostnum);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			middle_category.add(rs.getString("middle_category_name"));
		}
		System.out.print(middle_category);
		rs.close();
		stmt.close();
		return middle_category;
	}

	public ArrayList recentList(int hostnum) throws SQLException {
		ArrayList list = new ArrayList();
		String sql = "select shop_goods_no, shop_goods_name , shop_goods_stok ,shop_goods_origin ,shop_goods_weight , shop_goods_price, SHOP_GOODS_INFORMATION  from shop s, shop_goods_details d where s.shop_no = d.shop_no and s.shop_no = "
				+ hostnum;
		// returnScheduled

		// PreparedStatement prsd = con.prepareStatement(sql);
		// prsd.setInt(1, hostnum);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getString("shop_goods_no"));
			temp.add(rs.getString("shop_goods_name"));
			temp.add(rs.getString("shop_goods_stok"));
			temp.add(rs.getString("shop_goods_origin"));
			temp.add(rs.getString("shop_goods_weight"));
			temp.add(rs.getString("shop_goods_price"));
			temp.add(rs.getString("SHOP_GOODS_INFORMATION"));
			list.add(temp);
		}
//System.out.print(list);
		rs.close();
		stmt.close();
		return list;
	}

	// model
	public String ImgUrl(int num) throws SQLException {
		String sql = "select goods_img from shop_goods_details where shop_goods_no = " + num;
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

	public ArrayList rightrecentList(Object object) throws SQLException {
		// 오른쪽 table용
		ArrayList list2 = new ArrayList();
		String sql = "select GOODS_NO, GOODS_NAME from goods g, middle_category mc where g.middle_category_no = mc.middle_category_no and middle_category_name = '"
				+ object + "'";
		// returnScheduled
		// 오프젝트에

		// PreparedStatement prsd = con.prepareStatement(sql);
		// prsd.setInt(1, hostnum);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getString("GOODS_NO"));
			temp.add(rs.getString("GOODS_NAME"));
			list2.add(temp);
		}
//System.out.print(list);
		rs.close();
		stmt.close();
		return list2;
	}
}