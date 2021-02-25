package customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Order.*;

public class custInfo_model {

   Connection con;

   public custInfo_model() throws Exception {
      connectDB();
   }

   void connectDB() throws Exception {
      con = ProConnectPool.getConnection();
   }

   public int no_to_name(String cus_id) throws Exception {
      int gocusno = 0;
      String sql = "select cus_no from customer where cus_id like '" + cus_id + "'";

      Statement sm = con.createStatement();
      ResultSet rs = sm.executeQuery(sql);

      if (rs.next()) {
         gocusno = rs.getInt("cus_no");
      }
      return gocusno;
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

   public ArrayList pointlist(int cus_no) throws Exception {
      ArrayList list = new ArrayList();
      String sql = "select point_io_amount, point_io_comment, point_io_date from point_inout where cus_no =" + cus_no +" order by point_io_date desc";

      Statement sm = con.createStatement();
      ResultSet rs = sm.executeQuery(sql);

      while (rs.next()) {
         ArrayList tmp = new ArrayList();
         tmp.add(rs.getInt("point_io_amount"));
         tmp.add(rs.getString("point_io_comment"));
         tmp.add(rs.getString("point_io_date"));
         list.add(tmp);
      }

      rs.close();
      sm.close();
      return list;
   }

   public ArrayList couponlist(int cus_no) throws SQLException {
      ArrayList list = new ArrayList();
      String sql = "select ccl.coupon_serial, c.coupon_amount, ccl.cus_coupon_givedate, c.coupon_enddate from coupon c, customer_couponList ccl"
            + " where c.coupon_serial = ccl.coupon_serial and ccl.cus_no =" + cus_no;

      Statement sm = con.createStatement();
      ResultSet rs = sm.executeQuery(sql);

      while (rs.next()) {
         ArrayList tmp = new ArrayList();
         tmp.add(rs.getString("coupon_serial"));
         tmp.add(rs.getString("coupon_amount"));
         tmp.add(rs.getString("cus_coupon_givedate"));
         tmp.add(rs.getString("coupon_enddate"));
         list.add(tmp);
      }

      rs.close();
      sm.close();
      return list;
   }

   public ArrayList cardlist(int cus_no) throws Exception {
      ArrayList list = new ArrayList();

      String sql = "select fc.friend_com_name, cc.cus_cardnumber, cc.cus_cardalias from friend_company fc, customer_cardlist cc "
            + "where fc.friend_com_no = cc.friend_com_no and cc.cus_no = " + cus_no;

      Statement sm = con.createStatement();
      ResultSet rs = sm.executeQuery(sql);

      while (rs.next()) {
         ArrayList tmp = new ArrayList();
         tmp.add(rs.getString("friend_com_name"));
         tmp.add(rs.getString("cus_cardnumber"));
         tmp.add(rs.getString("cus_cardalias"));
         list.add(tmp);
      }

      rs.close();
      sm.close();
      return list;
   }

   public ArrayList addlist(int cus_no) throws SQLException {
      ArrayList list = new ArrayList();

      String sql = "select addList_no, addlist_address, addlist_name from addresslist where cus_no = " + cus_no;

      Statement sm = con.createStatement();
      ResultSet rs = sm.executeQuery(sql);

      while (rs.next()) {
         ArrayList tmp = new ArrayList();
         tmp.add(rs.getInt("addList_no"));
         tmp.add(rs.getString("addlist_address"));
         tmp.add(rs.getString("addlist_name"));
         list.add(tmp);
      }

      rs.close();
      sm.close();
      return list;
   }
   
   public int sum(int cus_no) throws SQLException{
      int result =0;
      
      String sql="select sum(point_io_amount) from point_inout where cus_no = "+cus_no;
      
      Statement sm = con.createStatement();
      ResultSet rs = sm.executeQuery(sql);
      
      if(rs.next()) {
         result = rs.getInt("sum(point_io_amount)");
      }
      rs.close();
      sm.close();
      return result;
   }
   public int goid(String id) throws Exception{
	      int cus_no = 0;
	      
	      String sql = "select cus_no from customer where cus_id like '"+id+"'";
	      Statement sm = con.createStatement();
	      ResultSet rs = sm.executeQuery(sql);
	      
	      if(rs.next()) {
	         cus_no = rs.getInt("cus_no");
	         
	      }
	      rs.close();
	      sm.close();
	      
	      return cus_no;
	   }
   
   public ArrayList selectCardCompany() throws SQLException{
	   ArrayList<String> company = new ArrayList<>();
	   String sql = "select friend_com_name from friend_company";
	   Statement sm = con.createStatement();
	   ResultSet rs = sm.executeQuery(sql);
	   
	   while(rs.next()) {
//		   String tmp = new ArrayList();
//		   tmp.add(rs.getString("friend_com_name"));
		   company.add(rs.getString("friend_com_name"));
	   }
	   
	   System.out.println(company);
	   rs.close();
	   sm.close();
	   
	   return company;
   }
   
   public int cardComNo(String cardComName) throws Exception{
	   int comNo=0;
	   String sql = "select friend_com_no from friend_company where friend_com_name like '"+cardComName+"'";
	   Statement sm = con.createStatement();
	   ResultSet rs = sm.executeQuery(sql);
	   
	   if(rs.next()) {
		   comNo = rs.getInt("friend_com_no");
	   }
	   rs.close();
	   sm.close();
	   
	   return comNo; 
   }
   public void insertCusCard(String num, String ali, int com, int cusno) throws SQLException{
	   String sql = "insert into customer_cardList(cus_cardnumber, cus_cardalias,"
	   		+ " cus_no, cus_cardno, friend_com_no) values(?,?,?, customerCard_seq.nextval,?)";
	   
	   PreparedStatement ps = con.prepareStatement(sql);
	   ps.setString(1, num);
	   ps.setString(2, ali);
	   ps.setInt(3, cusno);
	   ps.setInt(4, com);
	   ps.executeUpdate();
	   ps.close();
	   
   }

   public void updateCusCard(String cardNum, String reAlias) throws Exception{
	   String sql = "update customer_cardList set cus_cardalias = '"+reAlias+"'"
			   +" where cus_cardnumber like '"+cardNum+"'";
	   
	   PreparedStatement ps = con.prepareStatement(sql);
	   ps.executeUpdate();
	   
	   ps.close();
	   
	   
   }
   
   public void deleteCusCard(String cardNum) throws Exception{
	   String sql = "delete customer_cardList where cus_cardnumber like '"+cardNum+"'";
	   
	   PreparedStatement ps = con.prepareStatement(sql);
	   ps.executeUpdate();
	   
	   ps.close();
   }
   
   public String HIcardInfo(String cardno) throws Exception{
	   String comname = "";
	   
	   String sql = "select f.friend_com_name from friend_company f, "
	   		+ "customer_cardList c where f.friend_com_no = c.friend_com_no "
	   		+ "and c.cus_cardNumber like '"+cardno+"'";
	   
	   Statement sm = con.createStatement();
	   ResultSet rs = sm.executeQuery(sql);
	   
	   if(rs.next()) {
		   comname = rs.getString("friend_com_name");
	   }
	   
	   rs.close();
	   sm.close();
	   
	   return comname;
   }
   
   public void CusAddInsert(int cusno, String add, String name, String phone) throws Exception {
	   String sql = "insert into addressList(addList_no, cus_no, addList_address,"
	   		+ " addList_name, addList_phone) values(cus_addresslist_seq.nextval,"
	   		+ " ?,?,?,?)";
	   
	   PreparedStatement ps = con.prepareStatement(sql);
	   ps.setInt(1, cusno);
	   ps.setString(2,add);
	   ps.setString(3,name);
	   ps.setString(4,phone);
	   ps.executeQuery();
	   ps.close();
   }
   
   public void CusAddUpdate(int add_num, String add_add, String add_name, String add_phone) throws Exception{
	   String sql = "update addressList set addList_address = ?, addList_name = ?, addList_phone = ? where"
			   +" addList_no = "+add_num;
	   PreparedStatement ps = con.prepareStatement(sql);
	   ps.setString(1, add_add);
	   ps.setString(2,add_name);
	   ps.setString(3,add_phone);

	   ps.executeQuery();
	   ps.close();
	   
   }
   
   public void deleteCusAdd(String add_num) throws Exception{
	   int an = Integer.parseInt(add_num);
	   String sql = "delete addressList where addList_no = "+an;
	   PreparedStatement ps = con.prepareStatement(sql);
	   ps.executeQuery();
	   ps.close();
   }
}