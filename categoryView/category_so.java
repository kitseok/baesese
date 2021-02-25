package categoryView;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

//import model.ConnectionPool;
import categoryModel.categorySoModel;
import categoryModel.tacegory_detailModel;
import customer.custInfo_view;
import Order.OrderView1;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.JTabbedPane;
import java.awt.Color;
import java.awt.Font;

public class category_so extends JFrame {

	private JPanel contentPane;
	private JButton btnNewButton_6;
	private JComboBox comboBox_so;
	private JComboBox comboBox_dae;
	private JLabel lblNewLabel;
	GoodsList gl = null;
	categorySoModel model = null;
	private JTable table;
	JScrollPane scro;
	private JTextField tfShopGoods;
	private JButton btn_ch;
	private JLabel lblNewLabel_1;
	private String id = ""; // 유저 no 아니면 아이디
	tacegory_detailModel detailmodel;
	private JLabel lblNewLabel_2;
	private JButton btnNewButton;

	public category_so(String choice, String id) throws Exception {

		this.id = id;

		model = new categorySoModel();
		gl = new GoodsList();
		detailmodel = new tacegory_detailModel();
		showLayout(choice);// 글자를 받아 그 글자 기준으로 소분류 값 가져옴
		eventProc();
		// 처음 불러오기위한거
		ArrayList al = model.selectTable(comboBox_so.getItemAt(comboBox_so.getSelectedIndex()).toString());
		gl.data = al;
		gl.fireTableDataChanged();
		//
	}

	private void eventProc() {
		this.setVisible(true);
		MyListener evt = new MyListener();
		comboBox_dae.addActionListener(evt);
		comboBox_so.addActionListener(evt);
		btn_ch.addActionListener(evt);
		btnNewButton_6.addActionListener(evt);
		btnNewButton.addActionListener(evt);

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				int col = 0;
				int col2 = 1;
				String shopGoodsNo = String.valueOf(table.getValueAt(row, col));
				// setVisible(false);
				tacegory_detail td = new tacegory_detail(shopGoodsNo, id);
				td.run();
			}
		});
	}

	private void showLayout(String choice) throws Exception {
		setTitle("배시시");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(630, 280, 678, 525);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		String str = model.name_to_id(id);
		contentPane.setLayout(null);
		lblNewLabel_2 = new JLabel(str);
		lblNewLabel_2.setBounds(0, 0, 0, 0);
		contentPane.add(lblNewLabel_2);

		btnNewButton = new JButton("\uB0B4 \uC815\uBCF4");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setFont(new Font("완도희망체 Regular", Font.PLAIN, 15));
		btnNewButton.setBounds(532, 28, 87, 30);
		contentPane.add(btnNewButton);

		lblNewLabel_1 = new JLabel("\uC0C1\uD488\uBA85 :");
		lblNewLabel_1.setBounds(47, 28, 55, 30);
		lblNewLabel_1.setFont(new Font("완도희망체 Regular", Font.PLAIN, 17));
		contentPane.add(lblNewLabel_1);

		tfShopGoods = new JTextField();
		tfShopGoods.setFont(new Font("완도희망체 Regular", Font.PLAIN, 15));
		tfShopGoods.setBounds(107, 29, 119, 30);
		contentPane.add(tfShopGoods);
		tfShopGoods.setColumns(10);
		tfShopGoods.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btn_ch.doClick();
			}
		});

		btn_ch = new JButton("\uAC80\uC0C9");
		btn_ch.setBackground(Color.WHITE);
		btn_ch.setFont(new Font("완도희망체 Regular", Font.PLAIN, 17));
		btn_ch.setBounds(225, 29, 89, 30);
		contentPane.add(btn_ch);

		btnNewButton_6 = new JButton("\uC7A5\uBC14\uAD6C\uB2C8");
		btnNewButton_6.setBackground(Color.WHITE);
		btnNewButton_6.setFont(new Font("완도희망체 Regular", Font.PLAIN, 15));
		btnNewButton_6.setBounds(433, 28, 87, 30);
		contentPane.add(btnNewButton_6);

		lblNewLabel = new JLabel("\uB300\uBAA9\uB85D :");
		lblNewLabel.setBounds(47, 75, 56, 30);
		lblNewLabel.setFont(new Font("완도희망체 Regular", Font.PLAIN, 17));
		contentPane.add(lblNewLabel);

		ArrayList<String> al = model.combobxInsert_dae(); // 콤보박스용 대분류_값
		Iterator<String> it = al.iterator();
		String[] name = new String[al.size()];

		for (int i = 0; i < al.size(); i++) {
			name[i] = it.next();
		}
		comboBox_dae = new JComboBox(name);
		comboBox_dae.setFont(new Font("완도희망체 Regular", Font.PLAIN, 15));
		comboBox_dae.setBounds(107, 75, 207, 30);
		for (int i = 0; i < al.size(); i++) {
			if (comboBox_dae.getItemAt(i).equals(choice)) {
				comboBox_dae.setSelectedIndex(i);
			}
		}
		contentPane.add(comboBox_dae);

		ArrayList<String> al2 = model.combobxInsert_so(choice); // 콤보박스용 소분류_값
		Iterator<String> it2 = al2.iterator();
		String[] name2 = new String[al2.size()];
		for (int i = 0; i < al2.size(); i++) {
			name2[i] = it2.next();
		}

		JLabel label = new JLabel("\uCE74\uD14C\uACE0\uB9AC :");
		label.setBounds(330, 75, 72, 30);
		label.setFont(new Font("완도희망체 Regular", Font.PLAIN, 17));
		contentPane.add(label);
		comboBox_so = new JComboBox(name2);
		comboBox_so.setFont(new Font("완도희망체 Regular", Font.PLAIN, 15));
		comboBox_so.setBounds(414, 75, 205, 30);
		contentPane.add(comboBox_so);

		table = new JTable(gl);
		table.setRowHeight(100); // 높이만
		table.getColumn("제품번호").setWidth(0);
		table.getColumn("제품번호").setMinWidth(0);
		table.getColumn("제품번호").setMaxWidth(0);
		//크기 및 정렬
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(50);
		columnModel.getColumn(0).setCellRenderer(dtcr);
		columnModel.getColumn(1).setPreferredWidth(100);
		//columnModel.getColumn(1).setCellRenderer(dtcr); //이미지 제외
		columnModel.getColumn(2).setPreferredWidth(100);
		columnModel.getColumn(2).setCellRenderer(dtcr);
		columnModel.getColumn(3).setPreferredWidth(100);
		columnModel.getColumn(3).setCellRenderer(dtcr);
		columnModel.getColumn(4).setPreferredWidth(30);
		columnModel.getColumn(4).setCellRenderer(dtcr);
		columnModel.getColumn(5).setPreferredWidth(50);
		columnModel.getColumn(5).setCellRenderer(dtcr);
		columnModel.getColumn(6).setPreferredWidth(50);
		columnModel.getColumn(6).setCellRenderer(dtcr);
		columnModel.getColumn(7).setPreferredWidth(50);
		columnModel.getColumn(7).setCellRenderer(dtcr);
		//컬럼 폰트
		table.setFont(new Font("완도희망체 Regular", Font.PLAIN, 15));
		//테이블 컬럼 폰트
		table.getTableHeader().setFont(new Font("완도희망체 Regular", Font.PLAIN, 15));
		scro = new JScrollPane(table);
		scro.setBounds(47, 111, 572, 331);
		contentPane.add(scro);
	}

	class MyListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			System.out.println(e.getSource());
			if (e.getSource() == comboBox_dae) { // 대 선택시 소카테고리 값 변경
				comboBox_so.removeAllItems();
				ArrayList<String> al = model.replace_so(comboBox_dae.getItemAt(comboBox_dae.getSelectedIndex()).toString());
				// 값 지우고 다시넣기

				Iterator<String> it = al.iterator();
				for (int i = 0; i < al.size(); i++) {
					comboBox_so.addItem(it.next());
				}
			} else if (e.getSource() == comboBox_so) {
				if (comboBox_so.getItemCount() > 0) {
					System.out.print(comboBox_so.getItemAt(comboBox_so.getSelectedIndex()).toString());
					ArrayList al = model.selectTable(comboBox_so.getItemAt(comboBox_so.getSelectedIndex()).toString());
					gl.data = al;
					gl.fireTableDataChanged();
				}
			} else if (e.getSource() == btnNewButton) {
				new custInfo_view(id);
				System.out.println("1");

			} else if (e.getSource() == btn_ch) {
				ArrayList al = model.like(tfShopGoods.getText());
				/*
				 * comboBox_so.removeAllItems();
				 * //System.out.print(comboBox_so.getItemAt(comboBox_so.getSelectedIndex()).
				 * toString()); //Object imsi = al.get(0); 값 초기화하기
				 * 
				 * if ((int)gl.getValueAt(0, 0) > 0) { int no = (int)gl.getValueAt(0, 0); String
				 * dae_values; for(int i = 0; i<comboBox_dae.getItemCount(); i ++) { String str
				 * = model.getMain_category(no); if(str.equals(comboBox_dae.getItemAt(i))) {
				 * comboBox_dae.requestFocus(); comboBox_dae.showPopup();
				 * comboBox_dae.setSelectedIndex(i); dae_values = str; ArrayList<String> al2 =
				 * model.combobxInsert_so(dae_values); // 콤보박스용 Iterator<String> it2 =
				 * al2.iterator(); String name2; for (int j = 0; j < al2.size(); j++) {
				 * 
				 * comboBox_so.addItem(it2.next()); } comboBox_so.requestFocus();
				 * comboBox_so.showPopup(); comboBox_so.setSelectedIndex();
				 * 
				 * } // 소분류_값
				 * 
				 * }
				 */

				gl.data = al;
				gl.fireTableDataChanged();

			} else if (e.getSource() == btnNewButton_6) {
				try {
					int num = detailmodel.CustnoFind(id);
					OrderView1 vi1 = new OrderView1(num);
					// System.out.println("1");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		}
	}

	class GoodsList extends AbstractTableModel {
		ArrayList data = new ArrayList();
		String[] title = { "제품번호", "이미지", "점포명", "물품명", "재고", "원산지", "사이즈", "가격" };
		public int getColumnCount() {// 컬럼 길이 가져옴
			return title.length;
		}

		public int getRowCount() {// 행 길이 가져옴
			return data.size();
		}

		public Object getValueAt(int row, int col) {
			// TODO Auto-generated method stub
			ArrayList temp = (ArrayList) data.get(row);
			return temp.get(col);
		}

		public String getColumnName(int col) {
			return title[col];
		}

		public Class getColumnClass(int column) {
			return getValueAt(0, column).getClass();
		}
	}
}
