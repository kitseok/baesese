package ShopView;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import ShopModel.ShopModel;

import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
//import view.RentView.BtnEvent;

public class ShopView extends JPanel {

	// ShopGoodsModel sgm;

	private JTextField tfShopHost;
	private JTextField tfShopNo;
	private JTextField tfShopName;
	// private JTable table;
	private JTable table_1;
	GoodsList gl;
	ShopModel model;

	public ShopView(String shopNo) {

		addlay(shopNo);
		eventProc();
		try {
			model = new ShopModel();
			// gl = new GoodsList();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addValues(); //콤보박스 값 넣기
		
	}
	private void addValues() {
		try {
		//	ArrayList<String> al = model.selectMiddleCategory();
			//for(String str : al)
		tfShopName.setText(model.selectShopName(Integer.parseInt(tfShopNo.getText())));
		tfShopHost.setText(model.selectHostName(Integer.parseInt(tfShopNo.getText())));
			
			//	comboCategory.addItem(str);
			
			
			
			gl.data = model.recentList(Integer.parseInt(tfShopNo.getText()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public void addlay(String shopNo) {
		setLayout(null);
		tfShopHost = new JTextField();
		tfShopHost.setEditable(false);
		tfShopHost.setHorizontalAlignment(SwingConstants.LEFT);
		tfShopHost.setBounds(80, 41, 100, 21);
		add(tfShopHost);
		tfShopHost.setColumns(10);

		tfShopNo = new JTextField(shopNo);
		tfShopNo.setEditable(false);
		tfShopNo.setHorizontalAlignment(SwingConstants.CENTER);
		tfShopNo.setColumns(10);
		tfShopNo.setBounds(107, 10, 73, 21);
		add(tfShopNo);

		tfShopName = new JTextField();
		tfShopName.setHorizontalAlignment(SwingConstants.CENTER);
		tfShopName.setBackground(Color.ORANGE);
		tfShopName.setForeground(new Color(0, 0, 0));
		tfShopName.setFont(new Font("나눔손글씨 펜", Font.BOLD, 40));
		tfShopName.setEditable(false);
		tfShopName.setColumns(10);
		tfShopName.setBounds(192, 10, 482, 52);
		add(tfShopName);

		JLabel lblNewLabel = new JLabel("No.");
		lblNewLabel.setBounds(80, 13, 57, 15);
		add(lblNewLabel);

		gl = new GoodsList();
		table_1 = new JTable(gl);

		JScrollPane scro = new JScrollPane(table_1);
		scro.setBounds(80, 92, 594, 327);
		add(scro);
		// btRe.addActionListener(new MyListener());

		// add(JScrollPane( table_1));

	}

	public void eventProc() {

		// 이벤트 대상과 이벤트 핸들러 객체와 연결

	}

	
	class GoodsList extends AbstractTableModel {
		ArrayList data = new ArrayList();
		String[] title = {"물품명", "재고", "원산지", "사이즈", "가격"};

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

	}

	void selectTable() {
		try {

			String shopno = tfShopNo.getText();
			ArrayList list = model.recentList(Integer.parseInt(shopno));
			gl.data = list;
			
			gl.fireTableDataChanged();
		} catch (Exception ex) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "데이블 추력 실패 : " + ex.getMessage());

		}
	}

}
