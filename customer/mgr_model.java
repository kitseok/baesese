package customer;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Order.ProConnectPool;

public class mgr_model {

   Connection con;
   
   public mgr_model() throws Exception{
      connectDB();
   }
   
   void connectDB() throws Exception{
      con = ProConnectPool.getConnection();
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
   
   public ArrayList clickY() throws SQLException {
      ArrayList list = new ArrayList();
      
      String sql = "select mgr.exc_ref_mgrNo, custy.exc_ref_type_types,cus.exc_ref_number, sgd.shop_goods_name, "
            + "cus.exc_ref_reason, cus.exc_ref_quantity, cus.exc_ref_request_date, cus.exc_ref_payment_amount, "
            + "mgr.exc_ref_mgrDate, mgr.exc_ref_mgrOK from exchange_refund_details cus, exchange_refund_mgr mgr ,"
            + " exchange_refund_type custy, shop_goods_details sgd, order_detail od where cus.exc_ref_number = "
            + "mgr.exc_ref_number and cus.exc_ref_type_number = custy.exc_ref_type_number and "
            + "cus.order_detail_num = od.order_detail_num and od.shop_goods_no = sgd.shop_goods_no "
            + "and mgr.exc_ref_mgrOK like 'Y'";
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
   
   public ArrayList clickL() throws SQLException {
      ArrayList list = new ArrayList();
      
      String sql = "select mgr.exc_ref_mgrNo, custy.exc_ref_type_types,cus.exc_ref_number, sgd.shop_goods_name, "
            + "cus.exc_ref_reason, cus.exc_ref_quantity, cus.exc_ref_request_date, cus.exc_ref_payment_amount, "
            + "mgr.exc_ref_mgrDate, mgr.exc_ref_mgrOK from exchange_refund_details cus, exchange_refund_mgr mgr ,"
            + " exchange_refund_type custy, shop_goods_details sgd, order_detail od where cus.exc_ref_number = "
            + "mgr.exc_ref_number and cus.exc_ref_type_number = custy.exc_ref_type_number and "
            + "cus.order_detail_num = od.order_detail_num and od.shop_goods_no = sgd.shop_goods_no "
            + "and mgr.exc_ref_mgrOK like 'L'";
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
   
   public ArrayList clickN() throws SQLException {
      ArrayList list = new ArrayList();
      
      String sql = "select mgr.exc_ref_mgrNo, custy.exc_ref_type_types,cus.exc_ref_number, sgd.shop_goods_name, "
            + "cus.exc_ref_reason, cus.exc_ref_quantity, cus.exc_ref_request_date, cus.exc_ref_payment_amount, "
            + "mgr.exc_ref_mgrDate, mgr.exc_ref_mgrOK from exchange_refund_details cus, exchange_refund_mgr mgr ,"
            + " exchange_refund_type custy, shop_goods_details sgd, order_detail od where cus.exc_ref_number = "
            + "mgr.exc_ref_number and cus.exc_ref_type_number = custy.exc_ref_type_number and "
            + "cus.order_detail_num = od.order_detail_num and od.shop_goods_no = sgd.shop_goods_no "
            + "and mgr.exc_ref_mgrOK like 'N'";
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
