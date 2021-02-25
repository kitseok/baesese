package OrderExcRefView;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

import OrderExcRefModel.OrderListFModel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Color;

public class OrderListF extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	GoodsList gl = null;
	OrderListFModel model = null;
	private JTable table;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private String id = null;
	
	public OrderListF(String id) throws Exception {
		this.id = id;
		model = new OrderListFModel();
		gl = new GoodsList();
		showLayout();
		setVisible(true);
		eventProc();
		
		gl.data = model.recentList(id);
		
		
	}

	public void eventProc()
	{
		table.addMouseListener(new MouseAdapter(){
	           public void mouseClicked(MouseEvent e) {
	              int row = table.getSelectedRow();
	              int col = 2;
	              String goodsno = String.valueOf(table.getValueAt(row, col));
	              try {
					new OrderList(goodsno, id);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}    
	           }
	         });
		
	}
		 
	
	private void showLayout() {
		setTitle("��ý�");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(600, 270, 800, 624);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(0,0,0,0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
				
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(0, 0, 0, 0);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel(" ");
		lblNewLabel_2.setBounds(507, 5, 4, 15);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel = new JLabel("�� �� �� �� �� ��");
		lblNewLabel.setBounds(286, 39, 197, 33);
		lblNewLabel.setFont(new Font("�ϵ����ü Regular", Font.BOLD, 26));
		contentPane.add(lblNewLabel);
		
		lblNewLabel_3 = new JLabel(" ");
		lblNewLabel_3.setBounds(507, 77, 4, 15);
		contentPane.add(lblNewLabel_3);
		
		table = new JTable(gl);
		table.getColumn("�ֹ��󼼹�ȣ").setWidth(0);
		table.getColumn("�ֹ��󼼹�ȣ").setMinWidth(0);
		table.getColumn("�ֹ��󼼹�ȣ").setMaxWidth(0);
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(100);
		columnModel.getColumn(0).setCellRenderer(dtcr);
		columnModel.getColumn(1).setPreferredWidth(10);
		//columnModel.getColumn(1).setCellRenderer(dtcr); //�̹��� ����
		columnModel.getColumn(2).setPreferredWidth(0);
		columnModel.getColumn(2).setCellRenderer(dtcr);
		columnModel.getColumn(3).setPreferredWidth(50);
		columnModel.getColumn(3).setCellRenderer(dtcr);
		columnModel.getColumn(4).setPreferredWidth(10);
		columnModel.getColumn(4).setCellRenderer(dtcr);
		columnModel.getColumn(5).setPreferredWidth(100);
		columnModel.getColumn(5).setCellRenderer(dtcr);
		//�÷� ��Ʈ
		table.setFont(new Font("�ϵ����ü Regular", Font.PLAIN, 15));
		//���̺� �÷� ��Ʈ
		table.getTableHeader().setFont(new Font("�ϵ����ü Regular", Font.PLAIN, 15));
		JScrollPane scro = new JScrollPane(table);
		scro.setBounds(30, 97, 718, 458);
		contentPane.add(scro);
	}
	
	class GoodsList extends AbstractTableModel
	   {
	      ArrayList data = new ArrayList();
	      String [] title={"�ֹ���¥","�ֹ���ȣ","�ֹ��󼼹�ȣ","��ǰ�󼼸�","���ż���","��ۿϷ���"};
	      public int getColumnCount() {//컬럼 길이 �??��?��
	         return title.length;
	      }
	      public int getRowCount() {//?�� 길이 �??��?��
	         return data.size();
	      }
	   
	      public Object getValueAt(int row, int col) {
	         // TODO Auto-generated method stub
	         ArrayList temp = (ArrayList)data.get(row);
	         return temp.get(col);
	      }
	      public String getColumnName(int col) {
	         return title[col];
	      }
	      
	   }
}
