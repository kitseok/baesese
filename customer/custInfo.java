package customer;


import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import customer.custInfo_view;

public class custInfo extends JFrame{

   custInfo_view cv;
   
   public custInfo(String id) {
      cv = new custInfo_view(id);
      
      JTabbedPane pane = new JTabbedPane();
      pane.addTab("°í°´Á¤º¸", cv);
      
      getContentPane().add("Center",pane);
         setSize(866,500);
         setVisible( true );

         setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
   }
   
//   public static void main(String[] args) {
//      // TODO Auto-generated method stub
//
//      new custInfo();
//   }

}