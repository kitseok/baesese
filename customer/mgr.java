package customer;


import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class mgr extends JFrame{

   mgr_view mv;
   mgr_order_view mov;
   mgr_order_delivery_view modv;
   //String str = "dd";
   
   public mgr(String id) {
      mv = new mgr_view(id);
      mov = new mgr_order_view();
      modv = new mgr_order_delivery_view();
      
      
      JTabbedPane pane = new JTabbedPane();
      pane.addTab("硅崔 包府", modv);
      pane.addTab("惑前 惑技郴开 包府", mov);
      //pane.getTabComponentAt(1).setName(str);
      pane.addTab("背券/券阂惑技包府", mv);
      getContentPane().add("Center",pane);
      setBounds(600, 300, 780,500);
      ChangeListener changeListener = new ChangeListener() {
    	  public void stateChanged(ChangeEvent changeEvent) {
    		  if(pane.getSelectedIndex()==1) //Index starts at 0, so Index 2 = Tab3
              {
    			  System.out.println(changeEvent.getSource());
    			  mov.selectTable();
              }
    	  }
    	};
    	pane.addChangeListener(changeListener);
      setVisible(true);
      
      setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
   }
//   public static void main(String[] args) {
//      // TODO Auto-generated method stub
//      new mgr();
//   }

}