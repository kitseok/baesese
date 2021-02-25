package customer;

	import java.awt.Font;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.awt.event.MouseAdapter;
	import java.awt.event.MouseEvent;
	import java.sql.SQLException;
	import java.util.ArrayList;

	import javax.swing.JButton;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	import javax.swing.JPanel;
	import javax.swing.JScrollPane;
	import javax.swing.JTable;
	import javax.swing.table.AbstractTableModel;
	import javax.swing.table.DefaultTableModel;
	import javax.swing.table.TableColumnModel;


	public class mgr_order_view extends JPanel {
	   private JLabel showmgr;
	   private JButton btnY, btnN, btnL, btnAll;
	   private JTable excTable;
	   public JScrollPane scroExc;
	   private TableColumnModel exccolumnModel;

	   mgr_order_model model;

	   public GoodsList goodsl;

	   public mgr_order_view() {

	      try {
	         model = new mgr_order_model();
	         
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	      }

	      lay();
	      selectTable();

	   }

	   public void lay() {
	      setLayout(null);

	      showmgr = new JLabel("주문 내역 관리");
	      showmgr.setBounds(279, 30, 60, 15);
	      showmgr.setFont(new Font("G마켓 산스 TTF Bold", Font.BOLD, 22));
	      showmgr.setSize(200, 30);
	      add(showmgr);

//	      exccolumnModel = excTable.getColumnModel();
//	      exccolumnModel.getColumn(0).setPreferredWidth(10);
//	      exccolumnModel.getColumn(1).setPreferredWidth(15);
//	      exccolumnModel.getColumn(2).setPreferredWidth(10);
//	      exccolumnModel.getColumn(4).setPreferredWidth(10);
//	      exccolumnModel.getColumn(6).setPreferredWidth(30);
//	      exccolumnModel.getColumn(8).setPreferredWidth(10);

	      goodsl = new GoodsList();
	      // ArrayList list =

	      try {
	    	  goodsl.GoodsData = model.recentList();
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }

	      excTable = new JTable(goodsl);
	      scroExc = new JScrollPane(excTable);
	      scroExc.setBounds(25, 87, 701, 300);
	      // scroExc.setVisible(true);
	      add(scroExc);
	   }

	   class GoodsList extends AbstractTableModel
	   {
	      ArrayList GoodsData = new ArrayList();
	      String [] title={"주문번호","주문상세번호","상품상세명","구매수량","배송상태여부","주문날짜","배송완료일"};
	      public int getColumnCount() {//而щ읆 湲몄씠 媛??졇?샂
	         return title.length;
	      }
	      public int getRowCount() {//?뻾 湲몄씠 媛??졇?샂
	         return GoodsData.size();
	      }
	   
	      public Object getValueAt(int row, int col) {
	         // TODO Auto-generated method stub
	         ArrayList temp = (ArrayList)GoodsData.get(row);
	         return temp.get(col);
	      }
	      public String getColumnName(int col) {
	         return title[col];
	      }
	      
	   }

	   public void selectTable() {
	      try {
	         ArrayList list = model.recentList();

	         goodsl.GoodsData = list;
	         goodsl.fireTableDataChanged();
	      } catch (Exception ex) {
	         JOptionPane.showMessageDialog(null, "테이블 출력 실패 : " + ex.getMessage());
	      }
	   }


	   class myListener implements ActionListener {

	      @Override
	      public void actionPerformed(ActionEvent e) {


	   }
	}
}
