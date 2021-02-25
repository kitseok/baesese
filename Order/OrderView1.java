package Order;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.ScrollPane;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import java.awt.SystemColor;

public class OrderView1 extends JFrame {

	private JPanel contentPane;
	private JTable ordertable;
	JButton btnNewButton;
	JButton btn_goods_delete;
	JLabel lblNewLabel;
	public static orderTablelModel tmorder;
	OrderModel model;
	int cusno = 0;
	

	
	public OrderView1(int cusno){
		getContentPane().setBackground(SystemColor.window);
		try {
			this.cusno = cusno;
			model = new OrderModel();
			
			addlay();
			eventProc();
			tmorder.data = model.Basket(cusno); //고객번호로 리스트 추가
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	public void addlay() throws SQLException
	{
		setTitle("배시시");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(720, 280, 418, 345);
		getContentPane().setLayout(null);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(717, 10, 17, 351);
		getContentPane().add(scrollBar);
		
		btnNewButton = new JButton("\uC8FC\uBB38");
		btnNewButton.setFont(new Font("완도희망체 Regular", Font.PLAIN, 17));
		btnNewButton.setBackground(SystemColor.window);
		btnNewButton.setBounds(237, 241, 106, 35);
		getContentPane().add(btnNewButton);
		
		lblNewLabel = new JLabel(Integer.toString(model.TotalPrice(cusno))); //고객넘버 받아와야함
		lblNewLabel.setBounds(493, 24, 62, 21);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\uCD1D \uAE08\uC561 :");
		lblNewLabel_1.setBounds(419, 20, 62, 28);
		getContentPane().add(lblNewLabel_1);
		// 가운데 정렬
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		tmorder = new orderTablelModel();
		ordertable = new JTable(tmorder);
		ordertable.getColumn("상품번호").setWidth(0);
		ordertable.getColumn("상품번호").setMinWidth(0);
		ordertable.getColumn("상품번호").setMaxWidth(0);
		TableColumnModel columnModel = ordertable.getColumnModel();
		columnModel.getColumn(1).setCellRenderer(dtcr);
		columnModel.getColumn(2).setCellRenderer(dtcr);
		columnModel.getColumn(3).setCellRenderer(dtcr);
		// 컬럼 폰트
		ordertable.setFont(new Font("완도희망체 Regular", Font.PLAIN, 15));
		// 테이블 컬럼 폰트
		ordertable.getTableHeader().setFont(new Font("완도희망체 Regular", Font.PLAIN, 15));
		JScrollPane scr = new JScrollPane(ordertable);
		scr.setBounds(12, 10, 378, 207);
		getContentPane().add(scr);

		btn_goods_delete = new JButton("\uC0C1\uD488 \uCDE8\uC18C");
		btn_goods_delete.setFont(new Font("완도희망체 Regular", Font.PLAIN, 17));
		btn_goods_delete.setBackground(Color.WHITE);
		btn_goods_delete.setBounds(58, 241, 97, 35);
		getContentPane().add(btn_goods_delete);
		//contentPane.add(ordertable);
		//ordertable = new JTable(tmorder);
		//add(new JScrollPane(ordertable),BorderLayout.NORTH);
		//contentPane.add(ordertable);
		setVisible(true);
	}
	
	public void eventProc(){ //이벤트 붙이기 
		BtnEvent evt = new BtnEvent();
		btnNewButton.addActionListener(evt);
		btn_goods_delete.addActionListener(evt);

	}

	class BtnEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == btnNewButton)
			{
				
				OrderDetail odd =  new OrderDetail(cusno);
				//odd.OrderDetail();
			} else if (e.getSource() == btn_goods_delete) //선택 아이템 바스켓에서 삭제시키기
			{
				if (ordertable.getRowSelectionAllowed()) {
					int row = ordertable.getSelectedRow();
					int col = 0;
					int i = (int) ordertable.getValueAt(row, col);
					//값 삭제하고 장바구니 다시 최신화
					model.deleteGoods(cusno, i);
					try {
						tmorder.data = model.Basket(cusno);
						tmorder.fireTableDataChanged();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					JOptionPane.showMessageDialog(null, "선택한 상품 삭제 완료입니다.");
				} else {
					JOptionPane.showMessageDialog(null, "삭제한 상품을 선택하세요");
				}
			}
			
			
		}
	}

	public void orderTable(int cust_num) {
		try {
			
			//String tel = tfRentTel.getText();
			// ArrayList list =  
			tmorder.data = model.Basket(cust_num);
			//tableRecentList.setModel(tmRent);
			tmorder.fireTableDataChanged();

		} catch (Exception e) {
			//JOptionPane.showMessageDialog(null, "검색 실패 : " + e.getMessage());
			e.printStackTrace();
		}
		

	}
	
	class orderTablelModel extends AbstractTableModel 
    //AbstractTableModel 추상클래스 여서 오버라이딩 해야함 오버라이딩은 이클립스의 힘으로 
    {
       ArrayList data = new ArrayList();
      String [] title={"상품번호","상품명","가격", "수량"};
      
      public int getColumnCount() {
         
         return title.length;
      }
      
      public int getRowCount() {
         
         return data.size();
      }
   
      public Object getValueAt(int row, int col) {
         //값 하나씩 얻어노는 메소드
         ArrayList temp = (ArrayList)data.get(row);
         //get은 오브젝트형으로 변환해줘서 강제 형변화 필요
         
         return temp.get(col);
      
      }
      public String getColumnName(int col){
         return title[col];
      }

      
    }
}
