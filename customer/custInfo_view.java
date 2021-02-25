package customer;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import OrderExcRefView.OrderListF;
import customer.*;
import java.awt.Color;

public class custInfo_view extends JFrame {

	private JLabel showname, showpoint;
	private JButton btnMyBuy, btnMyPoint, btnMyCoupon, btnMyCard, btnMyAdd;
	private JButton btnCardInsert, btnCardUpdate, btnCardDelete, btnAddInsert, btnAddUpdate, btnAddDelete;
//	private JButton addInsert, addUpdate, addDelete;
	private JTable pointtable, coupontable, cardtable, addresstable;
	private JScrollPane scrop, scroc, scrocd, scroad;
	private int custpoint = 0;

	custInfo_model model;

	pointList pointl;
	couponList coul;
	public static cardList cardl;
	public static addressList addl;

	String id = "";
	String name = "";
	String cardNum = "";
	String addNum = "";
	int cus_no = 0;

	public custInfo_view(String id) {
		getContentPane().setBackground(Color.WHITE);

		try {
			model = new custInfo_model();
			layout(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		eventProc();
		btnMyPoint.doClick();
	}

	public void layout(String id) throws Exception {
		setTitle("배시시");

		getContentPane().setLayout(null);

		setBounds(100, 100, 727, 524);
		this.id = id;
		name = model.name_to_id(id);
		showname = new JLabel(name + "님 반갑습니다!");
		showname.setBounds(32, 30, 57, 15);
		showname.setFont(new Font("완도희망체 Regular", Font.PLAIN, 22));
		showname.setSize(200, 20);
		getContentPane().add(showname);

		try {
			cus_no = model.no_to_name(id);
//         cus_no = model.no_to_name(cust_view.cus_name);
			custpoint = model.sum(cus_no);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		showpoint = new JLabel("현재 포인트 : " + custpoint);
		showpoint.setBounds(32, 55, 57, 15);
		showpoint.setFont(new Font("완도희망체 Regular", Font.PLAIN, 15));
		showpoint.setSize(200, 20);
		getContentPane().add(showpoint);

		pointl = new pointList();
		coul = new couponList();
		cardl = new cardList();
		addl = new addressList();

		pointtable = new JTable(pointl);
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel colPoint = pointtable.getColumnModel();
		colPoint.getColumn(0).setCellRenderer(dtcr);
		colPoint.getColumn(1).setCellRenderer(dtcr);
		colPoint.getColumn(2).setCellRenderer(dtcr);
		// 컬럼 폰트
		pointtable.setFont(new Font("완도희망체 Regular", Font.PLAIN, 15));
		// 테이블 컬럼 폰트
		pointtable.getTableHeader().setFont(new Font("완도희망체 Regular", Font.PLAIN, 15));
		coupontable = new JTable(coul);
		TableColumnModel colcoupon = coupontable.getColumnModel();
		colcoupon.getColumn(0).setCellRenderer(dtcr);
		colcoupon.getColumn(0).setPreferredWidth(100); // 행은 개별적
		colcoupon.getColumn(1).setCellRenderer(dtcr);
		colcoupon.getColumn(1).setPreferredWidth(50); // 행은 개별적
		colcoupon.getColumn(2).setCellRenderer(dtcr);
		colcoupon.getColumn(2).setPreferredWidth(150);
		colcoupon.getColumn(3).setCellRenderer(dtcr);
		colcoupon.getColumn(3).setPreferredWidth(150);
		// 컬럼 폰트
		coupontable.setFont(new Font("완도희망체 Regular", Font.PLAIN, 15));
		// 테이블 컬럼 폰트
		coupontable.getTableHeader().setFont(new Font("완도희망체 Regular", Font.PLAIN, 15));
		cardtable = new JTable(cardl);
		TableColumnModel colcard = cardtable.getColumnModel();
		colcard.getColumn(0).setCellRenderer(dtcr);
		colcard.getColumn(1).setCellRenderer(dtcr);
		colcard.getColumn(2).setCellRenderer(dtcr);
		// 컬럼 폰트
		cardtable.setFont(new Font("완도희망체 Regular", Font.PLAIN, 15));
		// 테이블 컬럼 폰트
		cardtable.getTableHeader().setFont(new Font("완도희망체 Regular", Font.PLAIN, 15));
		addresstable = new JTable(addl);
		addresstable.getColumn("no").setWidth(0);
		addresstable.getColumn("no").setMinWidth(0);
		addresstable.getColumn("no").setMaxWidth(0);
		TableColumnModel columnModel = addresstable.getColumnModel();
		columnModel.getColumn(0).setCellRenderer(dtcr);
		columnModel.getColumn(0).setPreferredWidth(0); // 행은 개별적
		columnModel.getColumn(1).setCellRenderer(dtcr);
		columnModel.getColumn(1).setPreferredWidth(300);
		columnModel.getColumn(2).setCellRenderer(dtcr);
		columnModel.getColumn(2).setPreferredWidth(30);
		// 컬럼 폰트
		addresstable.setFont(new Font("완도희망체 Regular", Font.PLAIN, 15));
		// 테이블 컬럼 폰트
		addresstable.getTableHeader().setFont(new Font("완도희망체 Regular", Font.PLAIN, 15));
		scrop = new JScrollPane(pointtable);
		scrop.setBounds(32, 87, 500, 300);
		scrop.setVisible(false);
		getContentPane().add(scrop);

		scroc = new JScrollPane(coupontable);
		scroc.setBounds(32, 87, 500, 300);
		scroc.setVisible(false);
		getContentPane().add(scroc);

		scrocd = new JScrollPane(cardtable);
		scrocd.setBounds(32, 87, 500, 300);
		scrocd.setVisible(false);
		getContentPane().add(scrocd);

		scroad = new JScrollPane(addresstable);
		scroad.setBounds(32, 87, 500, 300);
		scroad.setVisible(false);
		getContentPane().add(scroad);

		btnMyBuy = new JButton("나의 주문목록");
		btnMyBuy.setFont(new Font("완도희망체 Regular", Font.PLAIN, 15));
		btnMyBuy.setBackground(Color.WHITE);
		btnMyBuy.setSize(150, 40);
		btnMyBuy.setBounds(550, 87, 150, 40);
		getContentPane().add(btnMyBuy);

		btnMyPoint = new JButton("포인트");
		btnMyPoint.setFont(new Font("완도희망체 Regular", Font.PLAIN, 15));
		btnMyPoint.setBackground(Color.WHITE);
		btnMyPoint.setSize(150, 40);
		btnMyPoint.setBounds(550, 150, 150, 40);
		getContentPane().add(btnMyPoint);

		btnMyCoupon = new JButton("쿠폰");
		btnMyCoupon.setBackground(Color.WHITE);
		btnMyCoupon.setFont(new Font("완도희망체 Regular", Font.PLAIN, 15));
		btnMyCoupon.setSize(150, 40);
		btnMyCoupon.setBounds(550, 213, 150, 40);
		getContentPane().add(btnMyCoupon);

		btnMyCard = new JButton("등록한 카드");
		btnMyCard.setFont(new Font("완도희망체 Regular", Font.PLAIN, 15));
		btnMyCard.setBackground(Color.WHITE);
		btnMyCard.setSize(150, 40);
		btnMyCard.setBounds(550, 276, 150, 40);
		getContentPane().add(btnMyCard);

		btnMyAdd = new JButton("등록한 배송지");
		btnMyAdd.setBackground(Color.WHITE);
		btnMyAdd.setFont(new Font("완도희망체 Regular", Font.PLAIN, 15));
		btnMyAdd.setSize(150, 40);
		btnMyAdd.setBounds(550, 339, 150, 40);
		getContentPane().add(btnMyAdd);

		btnCardInsert = new JButton("추가");
		btnCardInsert.setBackground(Color.WHITE);
		btnCardInsert.setFont(new Font("완도희망체 Regular", Font.PLAIN, 14));
		btnCardInsert.setBounds(116, 418, 96, 33);
		btnCardInsert.setVisible(false);
		getContentPane().add(btnCardInsert);

		btnCardUpdate = new JButton("수정");
		btnCardUpdate.setBackground(Color.WHITE);
		btnCardUpdate.setFont(new Font("완도희망체 Regular", Font.PLAIN, 14));
		btnCardUpdate.setBounds(235, 418, 96, 33);
		btnCardUpdate.setVisible(false);
		getContentPane().add(btnCardUpdate);

		btnCardDelete = new JButton("삭제");
		btnCardDelete.setBackground(Color.WHITE);
		btnCardDelete.setFont(new Font("완도희망체 Regular", Font.PLAIN, 13));
		btnCardDelete.setBounds(354, 418, 96, 33);
		btnCardDelete.setVisible(false);
		getContentPane().add(btnCardDelete);

//		btnAddInsert = new JButton("배송지추가");
//		btnAddInsert.setBackground(Color.WHITE);
//		btnAddInsert.setFont(new Font("완도희망체 Regular", Font.PLAIN, 14));
//		btnAddInsert.setBounds(333, 397, 101, 33);
//		btnAddInsert.setVisible(false);
//		getContentPane().add(btnAddInsert);
//
//		btnAddUpdate = new JButton("배송지수정");
//		btnAddUpdate.setFont(new Font("완도희망체 Regular", Font.PLAIN, 14));
//		btnAddUpdate.setBackground(Color.WHITE);
//		btnAddUpdate.setBounds(446, 397, 101, 33);
//		btnAddUpdate.setVisible(false);
//		getContentPane().add(btnAddUpdate);
//
//		btnAddDelete = new JButton("배송지삭제");
//		btnAddDelete.setFont(new Font("완도희망체 Regular", Font.PLAIN, 14));
//		btnAddDelete.setBackground(Color.WHITE);
//		btnAddDelete.setBounds(560, 397, 101, 33);
//		btnAddDelete.setVisible(false);
//		getContentPane().add(btnAddDelete);

		setVisible(true);

	}

	class pointList extends AbstractTableModel {
		ArrayList pointdata = new ArrayList();
		String[] title = { "+/-", "내역", "날짜" };

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return pointdata.size();
		}

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return title.length;
		}

		@Override
		public Object getValueAt(int row, int col) {
			// TODO Auto-generated method stub
			ArrayList tmp = (ArrayList) pointdata.get(row);
			return tmp.get(col);
		}

		public String getColumnName(int col) {
			return title[col];
		}
	}

	class couponList extends AbstractTableModel {
		ArrayList coupondata = new ArrayList();
		String[] title = { "쿠폰번호", "금액", "발급날짜", "사용종료일" };

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return coupondata.size();
		}

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return title.length;
		}

		@Override
		public Object getValueAt(int row, int col) {
			// TODO Auto-generated method stub
			ArrayList tmp = (ArrayList) coupondata.get(row);
			return tmp.get(col);
		}

		public String getColumnName(int col) {
			return title[col];
		}
	}

	class cardList extends AbstractTableModel {
		ArrayList carddata = new ArrayList();
		String[] title = { "제휴사이름", "카드번호", "카드별명" };

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return carddata.size();
		}

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return title.length;
		}

		@Override
		public Object getValueAt(int row, int col) {
			// TODO Auto-generated method stub
			ArrayList tmp = (ArrayList) carddata.get(row);
			return tmp.get(col);
		}

		public String getColumnName(int col) {
			return title[col];
		}
	}

	class addressList extends AbstractTableModel {
		ArrayList addressdata = new ArrayList();
		String[] title = { "no", "주소", "배송지명" };

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return addressdata.size();
		}

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return title.length;
		}

		@Override
		public Object getValueAt(int row, int col) {
			// TODO Auto-generated method stub
			ArrayList tmp = (ArrayList) addressdata.get(row);
			return tmp.get(col);
		}

		public String getColumnName(int col) {
			return title[col];
		}
	}

	public void eventProc() {
		mmyListener et = new mmyListener();
		btnMyBuy.addActionListener(et);
		btnMyPoint.addActionListener(et);
		btnMyCoupon.addActionListener(et);
		btnMyCard.addActionListener(et);
		btnMyAdd.addActionListener(et);
		btnCardInsert.addActionListener(et);
		btnCardUpdate.addActionListener(et);
		btnCardDelete.addActionListener(et);

		cardtable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = cardtable.getSelectedRow();
				try {
					cardNum = String.valueOf(cardtable.getValueAt(row, 1));

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "일단그냥" + e1.getMessage());
				}
			}
		});

		addresstable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = addresstable.getSelectedRow();
				try {
					addNum = String.valueOf(addresstable.getValueAt(row, 0));

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "일단그냥" + e1.getMessage());
				}
			}
		});
	}

	class mmyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// System.out.println(e.getSource());
			// System.out.println(btnMyBuy);
			// TODO Auto-generated method stub
			if (e.getActionCommand().equals(btnMyBuy.getText())) {
				// 주문목록
				try {
					new OrderListF(id);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (e.getActionCommand().equals(btnMyPoint.getText())) {
				// 포인트확인
				try {
					
					scrop.setVisible(true);
					scroc.setVisible(false);
					scrocd.setVisible(false);
					scroad.setVisible(false);
					btnMyPoint.setEnabled(false);
					btnMyCoupon.setEnabled(true);
					btnMyCard.setEnabled(true);
					btnMyAdd.setEnabled(true);
					btnCardInsert.setVisible(false);
					btnCardUpdate.setVisible(false);
					btnCardDelete.setVisible(false);

					int cus_no = model.no_to_name(id);
					pointl.pointdata = model.pointlist(cus_no);
					pointl.fireTableDataChanged();

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "테이블 출력 실패: " + e2.getMessage());
				}
			} else if (e.getActionCommand().equals(btnMyCoupon.getText())) {
				// 갖고있는 쿠폰확인
				try {
					scroc.setVisible(true);
					scrop.setVisible(false);
					scrocd.setVisible(false);
					scroad.setVisible(false);
					btnMyPoint.setEnabled(true);
					btnMyCoupon.setEnabled(false);
					btnMyCard.setEnabled(true);
					btnMyAdd.setEnabled(true);
					btnCardInsert.setVisible(false);
					btnCardUpdate.setVisible(false);
					btnCardDelete.setVisible(false);

					int cus_no = model.no_to_name(id);
					coul.coupondata = model.couponlist(cus_no);
					coul.fireTableDataChanged();

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "테이블 출력 실패: " + e2.getMessage());
				}

			} else if (e.getActionCommand().equals(btnMyCard.getText())) {
				// 등록한 카드목록

				try {
					scrocd.setVisible(true);
					scrop.setVisible(false);
					scroc.setVisible(false);
					scroad.setVisible(false);
					btnMyPoint.setEnabled(true);
					btnMyCoupon.setEnabled(true);
					btnMyCard.setEnabled(false);
					btnMyAdd.setEnabled(true);
					cus_no = model.no_to_name(id);
					cardl.carddata = model.cardlist(cus_no);
					cardl.fireTableDataChanged();

					btnCardInsert.setVisible(true);
					btnCardUpdate.setVisible(true);
					btnCardDelete.setVisible(true);

//
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "테이블 출력 실패: " + e2.getMessage());
				}

			} else if (e.getActionCommand().equals(btnMyAdd.getText())) {
				// 등록한 배송지목록
				try {
					scroad.setVisible(true);
					scrop.setVisible(false);
					scroc.setVisible(false);
					scrocd.setVisible(false);
					btnMyPoint.setEnabled(true);
					btnMyCoupon.setEnabled(true);
					btnMyCard.setEnabled(true);
					btnMyAdd.setEnabled(false);
					btnCardInsert.setVisible(true);
					btnCardUpdate.setVisible(true);
					btnCardDelete.setVisible(true);

					int cus_no = model.no_to_name(id);
					addl.addressdata = model.addlist(cus_no);
					addl.fireTableDataChanged();

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "테이블 출력 실패: " + e2.getMessage());
				}
			} else if (e.getActionCommand().equals(btnCardInsert.getText())) {
				try {
					if (scrocd.isVisible()) {
						new custInfo_cardInsert(cus_no);
					} else if (scroad.isVisible()) {
						new custInfo_addInsert(cus_no);
					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} else if (e.getActionCommand().equals(btnCardUpdate.getText())) {
				try {
					if (scrocd.isVisible()) {
						new custInfo_cardUpdate(cus_no, cardNum);
						System.out.println("진짜 !!!");
					} else if (scroad.isVisible()) {
						new custInfo_addUpdate(cus_no, addNum);
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			} else if (e.getActionCommand().equals(btnCardDelete.getText())) {
				try {
					if (scrocd.isVisible()) {
						model.deleteCusCard(cardNum);
						JOptionPane.showMessageDialog(null, "카드가 삭제되었습니다.");
						cardl.carddata = model.cardlist(cus_no);
						cardl.fireTableDataChanged();
						System.out.println("나왕 !!");
					} else if (scroad.isVisible()) {
						model.deleteCusAdd(addNum);
						JOptionPane.showMessageDialog(null, "배송지가 삭제되었습니다.");
						addl.addressdata = model.addlist(cus_no);
						addl.fireTableDataChanged();
					}
				} catch (Exception e3) {
					e3.printStackTrace();
				}
			}

		}
	}
}
