package ShopView;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import ShopModel.ShopGoodsModel;


public class ShopGoodsView extends JPanel {
	private JTable table_1;
	private JTable tbGoods;
	private JTextField tfGoodsName;
	private JTextField tfInfomation;
	private JTextField tfShopGoodsName;
	private JTextField tfWeigth;
	private JTextField tfPrice;
	private JTextField tfStok;
	private JTextField tfOrigin;
	JButton btInsert;
	JButton btupdate;
	JButton btdelete;
	GoodsList gl;
	GoodsList2 gl2;
	ShopGoodsModel model;
	JComboBox comboCategory;
	private JTextField tfShopNo;
	private JTextField tfGoodsNo;
	private JLabel lblNewLabel_5;
	private JTextField tfShopGoodsNo;
	private JLabel lblNewLabel_6;
	URL url;

	public ShopGoodsView(String shopNo) {

		addlay(shopNo);

		try {
			model = new ShopGoodsModel();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		addValues(); // 콤보박스 값 넣기
		eventProc();

	}

	private void addValues() {
		try {
			ArrayList<String> al = model.selectMiddleCategory();
			for (String str : al) {
				comboCategory.addItem(str);
			}

			gl2.data2 = model.rightrecentList(comboCategory.getItemAt(comboCategory.getSelectedIndex()));
			gl.data = model.recentList(Integer.parseInt(tfShopNo.getText()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void addlay(String shopNo) {
		setLayout(null);

		gl = new GoodsList();
		table_1 = new JTable(gl);
		JScrollPane scro = new JScrollPane(table_1);
		scro.setBounds(12, 10, 515, 230);
		add(scro);

		gl2 = new GoodsList2();
		tbGoods = new JTable(gl2);
		JScrollPane jpane = new JScrollPane(tbGoods);
		jpane.setBounds(539, 36, 202, 287);
		add(jpane);

		comboCategory = new JComboBox();
		comboCategory.setBounds(649, 10, 92, 23);
		add(comboCategory);

		btInsert = new JButton("\uCD94\uAC00");
		btInsert.setBounds(539, 358, 87, 35);
		add(btInsert);

		btdelete = new JButton("\uC0AD\uC81C");
		btdelete.setBounds(654, 377, 87, 16);
		add(btdelete);

		btupdate = new JButton("\uC218\uC815");
		btupdate.setBounds(654, 358, 87, 16);
		add(btupdate);

		tfGoodsName = new JTextField();
		tfGoodsName.setEditable(false);
		tfGoodsName.setBounds(665, 333, 73, 16);
		add(tfGoodsName);
		tfGoodsName.setColumns(10);

		tfInfomation = new JTextField();
		tfInfomation.setColumns(10);
		tfInfomation.setBounds(33, 333, 366, 66);
		add(tfInfomation);

		tfShopGoodsName = new JTextField();
		tfShopGoodsName.setColumns(10);
		tfShopGoodsName.setBounds(98, 276, 124, 16);
		add(tfShopGoodsName);

		tfWeigth = new JTextField();
		tfWeigth.setColumns(10);
		tfWeigth.setBounds(272, 302, 127, 16);
		add(tfWeigth);

		tfPrice = new JTextField();
		tfPrice.setColumns(10);
		tfPrice.setBounds(272, 276, 127, 16);
		add(tfPrice);

		tfStok = new JTextField();
		tfStok.setColumns(10);
		tfStok.setBounds(272, 250, 127, 16);
		add(tfStok);

		tfOrigin = new JTextField();
		tfOrigin.setColumns(10);
		tfOrigin.setBounds(98, 303, 124, 16);
		add(tfOrigin);

		JLabel lblNewLabel = new JLabel("\uC0C1\uD488\uBA85 : ");
		lblNewLabel.setBounds(614, 334, 57, 15);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\uC810\uD3EC \uC0C1\uD488\uBA85 : ");
		lblNewLabel_1.setBounds(12, 277, 92, 15);
		add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("\uC6D0\uC0B0\uC9C0 : ");
		lblNewLabel_2.setBounds(40, 302, 57, 15);
		add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("\uC7AC\uACE0 : ");
		lblNewLabel_2_1.setBounds(232, 251, 57, 15);
		add(lblNewLabel_2_1);

		JLabel lblNewLabel_1_1 = new JLabel("\uAC00\uACA9 : ");
		lblNewLabel_1_1.setBounds(232, 277, 57, 15);
		add(lblNewLabel_1_1);

		JLabel lblNewLabel_3 = new JLabel("\uBB34\uAC8C : ");
		lblNewLabel_3.setBounds(232, 302, 57, 15);
		add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("\uBE44");
		lblNewLabel_4.setBounds(12, 345, 57, 15);
		add(lblNewLabel_4);

		JLabel lblNewLabel_4_1_2 = new JLabel("\uACE0");
		lblNewLabel_4_1_2.setBounds(12, 372, 57, 15);
		add(lblNewLabel_4_1_2);

		tfShopNo = new JTextField(shopNo);
		tfShopNo.setEditable(false);
		tfShopNo.setColumns(10);
		tfShopNo.setBounds(599, 10, 38, 23);
		add(tfShopNo);

		JLabel lblNo = new JLabel("\uC810\uD3EC No.");
		lblNo.setBounds(539, 11, 48, 22);
		add(lblNo);

		tfGoodsNo = new JTextField();
		tfGoodsNo.setEditable(false);
		tfGoodsNo.setColumns(10);
		tfGoodsNo.setBounds(567, 333, 38, 16);
		add(tfGoodsNo);

		JLabel goodsNo = new JLabel("No.");
		goodsNo.setBounds(539, 334, 57, 15);
		add(goodsNo);

		lblNewLabel_5 = new JLabel("\uC0C1\uD488 No : ");
		lblNewLabel_5.setBounds(31, 251, 92, 15);
		add(lblNewLabel_5);

		tfShopGoodsNo = new JTextField();
		tfShopGoodsNo.setEditable(false);
		tfShopGoodsNo.setColumns(10);
		tfShopGoodsNo.setBounds(98, 250, 124, 16);
		add(tfShopGoodsNo);

		lblNewLabel_6 = new JLabel("New label");
		lblNewLabel_6.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\32132132131.PNG"));
		lblNewLabel_6.setBounds(411, 251, 116, 121);
		add(lblNewLabel_6);

		// URL url = new URL(model.ImgUrl(139));
		// Image image = ImageIO.read(url);

		// lblNewLabel_2 = new JLabel(new ImageIcon(image));
		// lblNewLabel_2.setBounds(35, 34, 267, 143);
		// add(lblNewLabel_2);

		JButton btphotoInsert = new JButton("\uC0AC\uC9C4\uB4F1\uB85D");
		btphotoInsert.setBounds(421, 383, 91, 16);
		add(btphotoInsert);
	}

	public void eventProc() { // 이벤트 붙이기
		MyListener evt = new MyListener();
		btInsert.addActionListener(evt);
		btupdate.addActionListener(evt);
		btdelete.addActionListener(evt);
		comboCategory.addActionListener(evt);

		tbGoods.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = tbGoods.getSelectedRow();
				System.out.println(row);
				int col = 0;
				int col2 = 1;
				String goodsno = String.valueOf(tbGoods.getValueAt(row, col));
				String goodsname = String.valueOf(tbGoods.getValueAt(row, col2));
				tbGoods.setToolTipText(goodsno);
				tfGoodsNo.setText(goodsno);
				tfGoodsName.setText(goodsname);

			}
		});
		table_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = table_1.getSelectedRow();

				String shopgoodsno = String.valueOf(table_1.getValueAt(row, 0));
				String shopgoodsname = String.valueOf(table_1.getValueAt(row, 1));
				String shopgoodsstok = String.valueOf(table_1.getValueAt(row, 2));
				String shopgoodsorigin = String.valueOf(table_1.getValueAt(row, 3));
				String shopgoodssize = String.valueOf(table_1.getValueAt(row, 4));
				String shopgoodsprice = String.valueOf(table_1.getValueAt(row, 5));
				String shopgoodsinfor = String.valueOf(table_1.getValueAt(row, 6));
				table_1.setToolTipText(shopgoodsno);
				tfShopGoodsNo.setText(shopgoodsno);
				tfShopGoodsName.setText(shopgoodsname);
				tfStok.setText(shopgoodsstok);
				tfOrigin.setText(shopgoodsorigin);
				tfWeigth.setText(shopgoodssize);
				tfPrice.setText(shopgoodsprice);
				tfInfomation.setText(shopgoodsinfor);

				try {
					url = new URL(model.ImgUrl(Integer.parseInt(shopgoodsno)));
					Image image = ImageIO.read(url);
					Image chimg = image.getScaledInstance(116, 121, Image.SCALE_SMOOTH);
					lblNewLabel_6.setIcon(new ImageIcon(chimg));
				} catch (NumberFormatException | SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		// 이벤트 대상과 이벤트 핸들러 객체와 연결
		int titleno;

	}

	class GoodsList extends AbstractTableModel {
		ArrayList data = new ArrayList();

		String[] title = { "No", "물품명", "재고", "원산지", "사이즈", "가격", "비고" };

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

	class GoodsList2 extends AbstractTableModel {
		ArrayList data2 = new ArrayList();
		String[] title = { "제품번호", "제품명" };
		public ArrayList data;

		public int getColumnCount() {// 컬럼 길이 가져옴
			return title.length;
		}

		public int getRowCount() {// 행 길이 가져옴
			return data2.size();
		}

		public Object getValueAt(int row, int col) {
			// TODO Auto-generated method stub
			ArrayList temp = (ArrayList) data2.get(row);
			return temp.get(col);
		}

		public String getColumnName(int col) {
			return title[col];
		}

	}

	void selectTable() {
		try {

			String shopno = tfShopNo.getText();
			String category = comboCategory.getSelectedItem().toString();
			ArrayList list = model.recentList(Integer.parseInt(shopno));
			ArrayList list2 = model.rightrecentList(category);
			gl.data = list;
			gl2.data = list2;
			// System.out.println(list);
			// System.out.println(list2);
			// tableRecentList.setModel(tmRent);
			gl.fireTableDataChanged();
			gl2.fireTableDataChanged();
		} catch (Exception ex) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "데이블 추력 실패 : " + ex.getMessage());

		}
	}

	class MyListener implements ActionListener {
		public void clear() {
			tfShopGoodsNo.setText(null);
			tfShopGoodsName.setText(null);
			tfStok.setText(null);
			tfOrigin.setText(null);
			tfWeigth.setText(null);
			tfPrice.setText(null);
			tfInfomation.setText(null);
			tfGoodsNo.setText(null);
			tfGoodsName.setText(null);
		}

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == comboCategory) {
				// btRe.setText("버튼이 눌러졌습니다.");

				try {
					String shopno = tfShopNo.getText();
					String category = comboCategory.getSelectedItem().toString();
					// System.out.println(shopno);
					// System.out.println(category);
					int num = Integer.parseInt(shopno);
					gl.data = model.recentList(num);
					gl2.data2 = model.rightrecentList(category);
					// tfShopHost.setText(model.selectHostName(num));
					// tfShopName.setText(model.selectShopName(num));
					selectTable();
					gl.fireTableDataChanged();
					gl2.fireTableDataChanged();
					clear();

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} else if (e.getSource() == btInsert) {
				System.out.println("추가 버튼 클릭함");
				String shopno1 = tfShopNo.getText();
				String category = comboCategory.getSelectedItem().toString();
				int num = Integer.parseInt(shopno1);
				int goodsno = Integer.parseInt(tfGoodsNo.getText());
				int shopno = Integer.parseInt(tfShopNo.getText());
				String infor = tfInfomation.getText();
				int stok = Integer.parseInt(tfStok.getText());
				String origin = tfOrigin.getText();
				int price = Integer.parseInt(tfPrice.getText());
				String weight = tfWeigth.getText();
				String tfShopGoodsName = tfGoodsName.getText();

				try {
					model.goodsinsert(goodsno, shopno, infor, stok, origin, price, weight, tfShopGoodsName);
					gl.data = model.recentList(num);
					gl2.data2 = model.rightrecentList(category);
					System.out.println("상품 추가 완료");
					JOptionPane.showMessageDialog(null, "상품 추가 완료");
					clear();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				gl.fireTableDataChanged();
				gl2.fireTableDataChanged();

			} else if (e.getSource() == btupdate) {

				System.out.println("수정 버튼 클릭함");
				// String goodsname, String origin, int stok, int price, String Weight, String
				// infor, int shopgoodsno
				String shopno1 = tfShopNo.getText();
				int num = Integer.parseInt(shopno1);
				String category = comboCategory.getSelectedItem().toString();

				String goodsname = tfShopGoodsName.getText();
				String origin = tfOrigin.getText();
				int stok = Integer.parseInt(tfStok.getText());
				int price = Integer.parseInt(tfPrice.getText());
				String weight = tfWeigth.getText();
				String infor = tfInfomation.getText();
				int shopgoodsno = Integer.parseInt(tfShopGoodsNo.getText());
				try {
					model.goodsupdate(goodsname, origin, stok, price, weight, infor, shopgoodsno);
					gl.data = model.recentList(num);
					gl2.data2 = model.rightrecentList(category);
					System.out.println("상품 수정 완료");
					JOptionPane.showMessageDialog(null, "상품 수정 완료");
					clear();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				gl.fireTableDataChanged();
				gl2.fireTableDataChanged();

			}

			else if (e.getSource() == btInsert) {
				System.out.println("추가 버튼 클릭함");
				String shopno1 = tfShopNo.getText();
				String category = comboCategory.getSelectedItem().toString();
				int num = Integer.parseInt(shopno1);
				int goodsno = Integer.parseInt(tfGoodsNo.getText());
				int shopno = Integer.parseInt(tfShopNo.getText());
				String infor = tfInfomation.getText();
				int stok = Integer.parseInt(tfStok.getText());
				String origin = tfOrigin.getText();
				int price = Integer.parseInt(tfPrice.getText());
				String weight = tfWeigth.getText();
				String tfShopGoodsName = tfGoodsName.getText();

				try {
					model.goodsinsert(goodsno, shopno, infor, stok, origin, price, weight, tfShopGoodsName);
					gl.data = model.recentList(num);
					gl2.data2 = model.rightrecentList(category);
					System.out.println("상품 추가 완료");
					JOptionPane.showMessageDialog(null, "상품 추가 완료");
					clear();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				gl.fireTableDataChanged();
				gl2.fireTableDataChanged();

			}

			else if (e.getSource() == btdelete) {
				System.out.println("삭제 버튼 클릭함");
				int shopgoodsno = Integer.parseInt(tfShopGoodsNo.getText());
				try {
					System.out.println(shopgoodsno);
					model.goodsdelete(shopgoodsno);
					selectTable();
					gl.fireTableDataChanged();
					gl2.fireTableDataChanged();
					System.out.println("삭제 삭제 완료");
					JOptionPane.showMessageDialog(null, "상품 삭제 완료");
					clear();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				clear();
			}

		}
	}
}
