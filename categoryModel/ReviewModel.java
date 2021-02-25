package categoryModel;

import java.sql.*;
import Order.*;
public class ReviewModel {
   Connection con;
   int hostNo = 0;

   // constructor
   public ReviewModel() throws Exception {
      connectDB();
   }

   void connectDB() throws Exception {
      con = ProConnectPool.getConnection();
   }
   
   public void review(String rating, String coment, String picture, int shopno, int cusno) throws Exception {
      String sql = "insert into reiew (review_seq.nextval, ?, ?, ?,sysdate,  ?)";
      // insert into shop_review ( review_seq.nextval, rating, coment, picture,
      // sysdate, 손님넘버, 상품넘버);
      //
      PreparedStatement ps = con.prepareStatement(sql);
      ps.setString(1, rating);
      ps.setString(2, coment);
      ps.setString(3, picture);
      ps.setInt(4, shopno);
      ps.setInt(5, cusno);
      ps.executeUpdate();
      ps.close();
   }

}