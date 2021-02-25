package Order;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Order.ProConnectPool;
import categoryVO.categoryVO;
import customer.CardVO;


public class OrderModel {

	Connection con;
	
	ArrayList list = null;
	ArrayList<OrderVO> listor;
	ArrayList<categoryVO> listcvo;
	ArrayList<CardVO> cardlist;
	//유저번호
	private int custnum;
	public OrderModel() throws Exception 
	{
		connectDB();
	}


	void connectDB() throws Exception 
	{
		con = ProConnectPool.getConnection();
	}
	
	
	//기능
	//장바구니에 넣기 (메인 UI완성되면 이미클릭하고 장바구니 담기 버튼 클릭시)
	
	//장바구니에 있는 데이터 띄우기
	public ArrayList Basket(int custnum) throws SQLException
	{
		this.custnum = custnum;
		//listor = new ArrayList<OrderVO>();
		list = new ArrayList();
		String sql = "select b.goodsnum, shop_goods_name, b.price,b.qty from basket b,shop_goods_details s "
				+ "where b.goodsnum = s.shop_goods_no and custnum = " + custnum;
		int goodsnum = 0;
		String goodsname = "";
		int price = 0;
		int qty = 0;
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		while (rs.next()) {
			ArrayList temp = new ArrayList();
			
			//temp.add(rs.getString("custnum"));
			goodsnum = rs.getInt("goodsnum");
			goodsname = rs.getString("shop_goods_name");
			price = rs.getInt("price");
			qty = rs.getInt("qty");
			temp.add(goodsnum);
			temp.add(goodsname);
			temp.add(price);
			temp.add(qty);
			list.add(temp);
			//OrderVO vo = new OrderVO(goodsnum, goodsname, price);
			//listor.add(vo);
			
			//listor.add(list);
			
		}
		rs.close();
		st.close();
		
		return list;
	}
	
	public int SelectPoint(int custnum) throws SQLException
	{
		int point = 0;
		String sql = "select point_io_amount from point_inout where cus_no = " +custnum;
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		while(rs.next())
		{
			point = rs.getInt("point_io_amount");
		}
		rs.close();
		st.close();
		
		return point;
	}
	
	//총 금액 라벨에 띄우기
	public int TotalPrice(int custnum) throws SQLException
	{
		int price = 0;
		int qty = 0;
		//int count = 0;
		int total = 0;
		String sql = "select price,qty from basket where custnum = " + custnum;
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		while(rs.next())
		{
			price = rs.getInt("price");
			qty = rs.getInt("qty");
			total += price * qty;
			
		}
		rs.close();
		st.close();
		
		
		return total;
	}
	
	public String SerialFind()
	{
		return "";
	}
	
	
	//주문 넣기
	public void OrderStart(int cusno, int pay, int del, int price,int point2,String serial,String add,String card) throws SQLException
	{
		String sql = "{call pro_Order(?,?,?,?,?,?,?,?)}";
	      CallableStatement st = con.prepareCall(sql);
	      st.setInt(1, cusno);
	      st.setInt(2, pay);
	      st.setInt(3, del);
	      st.setInt(4, price);
	      st.setInt(5, point2);
	      st.setString(6, serial);
	      st.setString(7, add);
	      st.setString(8, card);
	      st.execute();
	      
	      st.close();

	}
	
	public ArrayList SelectAdd(int custno) throws SQLException 
	{
		ArrayList list = new ArrayList();
		
		String sql = "select addlist_address from addresslist al, customer ct where ct.cus_no = al.cus_no and ct.cus_no = "
				+ custno;
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			list.add(rs.getString("addlist_address"));

		}
		rs.close();
		st.close();

		return list;
	}
	
	public ArrayList<CardVO> SelectCard(int custno) throws SQLException 
	{
		cardlist = new ArrayList<CardVO>();
		
		String sql = "select cus_cardnumber,cus_cardalias from customer_cardlist cc, customer ct where ct.cus_no = cc.cus_no and ct.cus_no = "
				+ custno;
		String cardnum = "";
		String cardal = "";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			
			cardnum = rs.getString("cus_cardnumber");
			cardal = rs.getString("cus_cardalias");
			
			CardVO cavo = new CardVO(cardnum, cardal);
			cardlist.add(cavo);

		}
		rs.close();
		st.close();

		return cardlist;
	}
	
	
	public ArrayList<categoryVO> SelectCoupon(int custno) throws SQLException
	{
		ArrayList list = new ArrayList<>();
		listcvo = new ArrayList<categoryVO>();
		String sql = "select cp.main_category,mc.main_category_name,cp.coupon_amount,cp.coupon_serial from main_category mc, customer_couponlist cl, coupon cp "
				+ "where cl.coupon_serial = cp.coupon_serial and cp.main_category = mc.main_category and cl.cus_no = "+custno;
		//String str = "없음";
		String maincategory ="";
		String mainname = "";
		String amount = "";
		String serial = "";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		//list.add(str);
		while(rs.next()) {
		//	ArrayList temp = new ArrayList();
			//temp.add(str);
			maincategory = rs.getString("main_category");
			mainname = rs.getString("main_category_name");
			amount = rs.getString("coupon_amount");
			serial = rs.getString("coupon_serial");
			
			categoryVO cat = new categoryVO(maincategory, mainname, amount,serial);
			listcvo.add(cat);
			
		}
		rs.close();
		st.close();
		
		return listcvo;
		
	}
	
	public boolean ChkCoupon(int mc_no,int cusno) throws SQLException
	{
		String sql = "select bk.goodsnum, ma.main_category from main_category ma, "
				+ "middle_category mi, goods gs, shop_goods_details sgd, basket bk "
				+ "where bk.goodsnum = sgd.shop_goods_no "
				+ "and sgd.goods_no = gs.goods_no "
				+ "and gs.middle_category_no = mi.middle_category_no "
				+ "and mi.main_category = ma.main_category and bk.custnum = "+cusno;
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		while (rs.next()) {
			int cate_num = rs.getInt("main_category");
			if(cate_num == mc_no)
			{
				rs.close();
				st.close();
				return true;
			}
			
		}

		rs.close();
		st.close();
		return false;
	}
	
	public boolean GoodsPrice(int mc_no,int cusno,int mcprice) throws SQLException
	{
		String sql = "select bk.price,bk.qty, ma.main_category from main_category ma, "
				+ "middle_category mi, goods gs, shop_goods_details sgd, basket bk "
				+ "where bk.goodsnum = sgd.shop_goods_no "
				+ "and sgd.goods_no = gs.goods_no "
				+ "and gs.middle_category_no = mi.middle_category_no "
				+ "and mi.main_category = ma.main_category and bk.custnum = "+cusno;
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		int num = 0;
		
		while (rs.next()) {
			int cate_num = rs.getInt("main_category");
			int price = rs.getInt("price");
			int qty = rs.getInt("qty");
			num += price*qty;
			if(cate_num == mc_no)
			{
				if(mcprice < num)
				{
					return true;
				}
			}
			
		}
		
		return false;
		
	}
	
	

	// 장바구니에 있는 상품 번호 이름 가격 가져오기(현재 접속된 id로 고객 넘버 찾기)
	public void GoodsInfo(int custno) throws SQLException {

		listor = new ArrayList<OrderVO>();
		// list = new ArrayList();
		String sql = "select b.goodsnum, shop_goods_name, b.price,b.qty from basket b,shop_goods_details s "
				+ "where b.goodsnum = s.shop_goods_no and custnum = " + custno;
		int goodsnum = 0;
		String goodsname = "";
		int price = 0;
		int qty = 0;
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		while (rs.next()) {
			ArrayList temp = new ArrayList();
			
			//temp.add(rs.getString("custnum"));
			goodsnum = rs.getInt("goodsnum");
			goodsname = rs.getString("shop_goods_name");
			price = rs.getInt("price");
			qty = rs.getInt("qty");
			//temp.add(goodsname);
			//temp.add(price);
			//temp.add(qty);
			//list.add(temp);
			OrderVO vo = new OrderVO(goodsnum, goodsname, price,qty);
			listor.add(vo);
			
			//listor.add(list);
			
		}
		rs.close();
		st.close();

	}
	
//	public void ImgInput()
//	{
//		String insertQuery = "INSERT INTO ex_blob(file_name, img) values (?, EMPTY_BLOB())"; 
//		String selectQuery = "SELECT img FROM ex_blob WHERE file_name=? FOR UPDATE";
//		//bda.insertBlob(conn, "test.png", "c:\\temp\\test.png", insertQuery, selectQuery);
//		
//		File file = null; 
//		FileInputStream is = null; 
//		OutputStream os = null;
//
//
//		try {
//			
//			PreparedStatement pstmt = con.prepareStatement(insertQuery);
//			pstmt.setString(1, "test.png");
//			pstmt.executeUpdate();
//
//			
//			PreparedStatement pstmt2 = con.prepareStatement(selectQuery);
//			pstmt2.setString(1, "test.png");
//			
//			ResultSet rs = pstmt2.executeQuery();
//
//			if (rs.next()) {
//				Blob tmpBlob = rs.getBlob(1);
//				file = new File("C:\\Users\\user\\test.png");
//				is = new FileInputStream(file);
//				os = tmpBlob.setBinaryStream(1);
//				byte[] buffer = new byte[1024];
//				int length = -1;
//				while ((length = is.read(buffer)) != -1) {
//					os.write(buffer, 0, length);
//				}
//			}
//			os.close();
//			os = null;
//			
//			is.close();
//			
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

	
	
	//model
	public String ImgUrl() throws SQLException
	{
		String sql = "select ex_url from ex_imgsave";
		String str1 = "";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		while(rs.next())
		{
			str1 = rs.getString("ex_url");
			
		}
		rs.close();
		st.close();
		
		return str1;
		
	}
	
	
	public String ImgeOutput()
	{
		InputStream is = null;
		FileOutputStream os = null;
		String selectQuery = "SELECT img FROM ex_blob WHERE file_name=? FOR UPDATE";
		String selectDownloadQuery = "SELECT img FROM ex_blob WHERE file_name=?";
		String str = "";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(selectQuery);
			pstmt.setString(1, "test.png");
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				
				Blob blob = rs.getBlob(1);
				is = blob.getBinaryStream();
				//os = new FileOutputStream(downloadFilepath);
				//str = is;
				byte[] buf = new byte[1024];
				int len = -1;
				//while ((len = is.read(buf)) != -1) {
					//str = len;
					
					//os.write(buf, 0, len);
				}
			//}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		return "";
	}


	public void clearBasket(int cusno) throws SQLException { //바스켓지우는거
		String sql = "delete basket where custnum = ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, cusno);
		st.executeUpdate();
		
		st.close();
	}


	public void deleteGoods(int cusno, int i) {
		//선택 아이템 삭제 cusno = 유저번호, i = 선택된 아이템 no
		String sql = "delete basket where custnum = ? and goodsnum = ?";
		PreparedStatement st;
		try {
			st = con.prepareStatement(sql);
			st.setInt(1, cusno);
			st.setInt(2, i);
			st.executeUpdate();
			
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public int sum(int cusno) {
		int result = 0;
		try {
			String sql = "select sum(point_io_amount) from point_inout where cus_no = " + cusno;
			Statement sm;
			sm = con.createStatement();
			ResultSet rs = sm.executeQuery(sql);

			if (rs.next()) {
				result = rs.getInt("sum(point_io_amount)");
			}
			rs.close();
			sm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	
	
}






















