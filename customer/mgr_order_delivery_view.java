package customer;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import customer.mgr_exc_view.myListener;

import javax.swing.JButton;

public class mgr_order_delivery_view extends JPanel {
	private JLabel showmgr;
	private JButton btnBefore, btnDone, btnGO, btnComplete;
	private JTable orderT, delT;
	private JScrollPane scroll_list, scroll_d;

	orderList olist;
	delList dlist;
	mgr_order_delivery_model model;
	String order, del, state,str;

	/**
	 * Create the panel.
	 */
	public mgr_order_delivery_view() {

		try {
			model = new mgr_order_delivery_model();
		} catch (Exception e) {
			e.printStackTrace();
		}
		setLayout(null);

		showmgr = new JLabel("\uBC30\uB2EC\uC6D0 \uAD00\uB9AC");
		showmgr.setBounds(253, 19, 121, 32);
		showmgr.setFont(new Font("Dialog", Font.BOLD, 22));
		add(showmgr);

		olist = new orderList();
		dlist = new delList();

		try {
			olist.orderdata = model.orderListBefore();
			dlist.deldata = model.dList();
			olist.fireTableDataChanged();
			dlist.fireTableDataChanged();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		orderT = new JTable(olist);
//		orderT.getColumn("배달원").setWidth(0);
//		orderT.getColumn("배달원").setMinWidth(0);
//		orderT.getColumn("배달원").setMaxWidth(0);
		scroll_list = new JScrollPane(orderT);
		scroll_list.setBounds(26, 67, 536, 302);
		add(scroll_list);

		delT = new JTable(dlist);

		scroll_d = new JScrollPane(delT);
		scroll_d.setBounds(574, 67, 167, 302);
		add(scroll_d);

		btnBefore = new JButton("배송전 건");
		btnBefore.setBounds(63, 380, 97, 23);
		add(btnBefore);

		btnDone = new JButton("배송완료 건");
		btnDone.setBounds(177, 380, 97, 23);
		add(btnDone);

		btnGO = new JButton("배송완료처리");
		btnGO.setBounds(570, 379, 110, 23);
		add(btnGO);

		btnComplete = new JButton("배송출발하기");
		btnComplete.setBounds(448, 380, 110, 23);
		add(btnComplete);

		eventProc();
	}

	class orderList extends AbstractTableModel {

		ArrayList orderdata = new ArrayList();
		String[] title = { "주문번호", "고객명", "고객주소", "결제수단", "주문총액", "주문시간", "배송상태", "배달원" };
		// DefaultTableModel dmodel = new DefaultTableModel();

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return orderdata.size();
		}

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return title.length;
		}

		@Override
		public Object getValueAt(int row, int col) {
			// TODO Auto-generated method stub
			ArrayList tmp = (ArrayList) orderdata.get(row);
			// dmodel.addRow(title);
			return tmp.get(col);
		}

		public String getColumnName(int col) {
			return title[col];
		}

	}

	class delList extends AbstractTableModel {

		ArrayList deldata = new ArrayList();
		String[] title = { "배달원번호", "배달원이름", "전화번호", "배달가능여부" };
		// DefaultTableModel dmodel = new DefaultTableModel();

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return deldata.size();
		}

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return title.length;
		}

		@Override
		public Object getValueAt(int row, int col) {
			// TODO Auto-generated method stub
			ArrayList tmp = (ArrayList) deldata.get(row);
			// dmodel.addRow(title);
			return tmp.get(col);
		}

		public String getColumnName(int col) {
			return title[col];
		}

	}

	public void eventProc() {
		myListener et = new myListener();
		btnBefore.addActionListener(et);
		btnDone.addActionListener(et);
		btnGO.addActionListener(et);
		btnComplete.addActionListener(et);

		orderT.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = orderT.getSelectedRow();
				System.out.println(row);
				try {
					order = String.valueOf(orderT.getValueAt(row, 0));
					state = String.valueOf(orderT.getValueAt(row, 6));
					str = String.valueOf(orderT.getValueAt(row, 7));
					if (state.equals("배송중")) {
						btnGO.setEnabled(true);

					} else {
						btnGO.setEnabled(false);
					}

					// excTable.setToolTipText(mexc_num);

				} catch (Exception e3) {
					e3.printStackTrace();
				}

			}
		});

		delT.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = delT.getSelectedRow();
				System.out.println(row);
				try {
					btnComplete.setEnabled(true);
					del = String.valueOf(delT.getValueAt(row, 0));
					

					// excTable.setToolTipText(mexc_num);

				} catch (Exception e3) {
					e3.printStackTrace();
				}

			}
		});
	}

	class myListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == btnBefore) {
				try {
					olist.orderdata = model.orderListBefore();
					olist.fireTableDataChanged();
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "테이블 출력 실패: " + e1.getMessage());
				}
			} else if (e.getSource() == btnDone) {
				try {

					olist.orderdata = model.orderListDone();
					olist.fireTableDataChanged();

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "테이블 출력 실패: " + e1.getMessage());
				}
			} else if (e.getSource() == btnComplete) {
				try {
					if (model.chkGo(str) == true) {
						model.delGO(order, del);
						olist.orderdata = model.orderListBefore();
						dlist.deldata = model.dList();
						olist.fireTableDataChanged();
						dlist.fireTableDataChanged();
						btnComplete.setEnabled(false);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "이미 배달중");
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "테이블 출력 실패: " + e1.getMessage());
				}
			} else if (e.getSource() == btnGO) {
				try {
					model.completeDel(order);
					olist.orderdata = model.orderListBefore();
					dlist.deldata = model.dList();
					olist.fireTableDataChanged();
					dlist.fireTableDataChanged();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "테이블 출력 실패: " + e1.getMessage());
				}
			}
		}

	}

}
