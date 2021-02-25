package customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import customer.cust_VO;
import Order.*;

public class cust_model {   //DAO
   Connection con;
   
   public cust_model() throws Exception{
      connectDB();
   }
   
   void connectDB() throws Exception{
      con = ProConnectPool.getConnection();
   }
   
   //회원가입
   public void insert(cust_VO cust) throws Exception{
//      int no = cust.getCus_no();
      String id = cust.getCus_id();
      String pwd = cust.getCus_pwd();
      String birth = cust.getCus_birth();
      String name = cust.getCus_name();
      String address = cust.getCus_address();
      String phone = cust.getCus_phone();
      String phoneCon = cust.getCus_phoneconfirm();
      
      String sql = "insert into customer(cus_no, cus_id, cus_pwd, cus_birth, cus_name, cus_address, cus_phone, cus_phoneconfirm) values(customer_seq.nextval, ?,?,?,?,?,?,?)";
      
      PreparedStatement ps = con.prepareStatement(sql);
      
      ps.setString(1, id);
      ps.setString(2, pwd);
      ps.setString(3, birth);
      ps.setString(4, name);
      ps.setString(5, address);
      ps.setString(6, phone);
      ps.setString(7, phoneCon);
      
      ps.executeUpdate();
      ps.close();
      //추가하던지
      
      
   }
   
   public String idConfirm(String id) throws Exception{
      String idcon = "";
      
      String sql = "select cus_id from customer where cus_id like '"+id+"'";
      
      Statement sm = con.createStatement();
       
      ResultSet rs = sm.executeQuery(sql);
      
      if(rs.next()) {
         idcon = rs.getString("cus_id");
      }
      
      rs.close();
      sm.close();
      
      return idcon;
      
   }
   //로그인
   public String cuslogin(String id, String pw) throws Exception{
      String cus_name = "";

      
      String cussql ="select cus_name from customer where cus_id like '"+id+"' and cus_pwd like '"+pw+"'";

      Statement sm = con.createStatement();
      ResultSet rs = sm.executeQuery(cussql);
      
      if(rs.next()) {
         cus_name = rs.getString("cus_name");
      }
      
      rs.close();
      sm.close();
      
      return cus_name;
   }
   
   public String shoplogin(String id, String pw) throws Exception{
      String shop_no = "";

      

      String shopsql = "select shop_no from shop_host_mgr where shop_id like '"+id+"' and shop_pwd like '"+pw+"'";
      Statement sm = con.createStatement();
      ResultSet rs = sm.executeQuery(shopsql);
      
      if(rs.next()) {
         shop_no = rs.getString("shop_no");
         //shop_host_name = rs.getString("shop_host_name");
      }
      
      rs.close();
      sm.close();
      
      return shop_no;
   }
   public String mgrlogin(String id, String pw) throws Exception{
	      String mgr_id="";
	      String mgrsql = "select mgr_name from manager where mgr_id like '"+id+"' and mgr_pwd like '"+pw+"'";
	      Statement sm = con.createStatement();
	      ResultSet rs = sm.executeQuery(mgrsql);
	      
	      if(rs.next()) {
	         mgr_id = rs.getString("mgr_name");
	      }
	      
	      rs.close();
	      sm.close();
	      
	      return mgr_id;
	   }
   //상품별리뷰작성
   
   //상품검색
   public String ShowTitle(String str) throws SQLException {
		String al = null;
		
		String sql = "select image_url from images where image_name= '"+str+"'";
		
		Statement ps = con.createStatement();
		ResultSet rs = ps.executeQuery(sql);
		
		if(rs.next())
		{
			al = rs.getString("image_url");
		}
		return al;
	}
}