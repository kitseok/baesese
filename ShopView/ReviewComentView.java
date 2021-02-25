package ShopView;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import ShopModel.ReviewComentModel;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;

import javax.swing.JButton;
import javax.swing.JTextArea;

public class ReviewComentView extends JPanel {
	private JTable tbReview;
	private JTextField tfReviewNo;
	private JTextField tfShopNo;
	private JTextField tfShopHost;
	private JTextField tfShopName;
	private JTextField tfGoodsName;
	private JTextField tfCusName;
	private JTextField TfStar;
	private JTextField tfReviewDate;
	private JTextArea tfArea;
	private JTextArea tfComent;
	ReviewComentModel model;
	ReviewList rl;
	JButton btInsert;

	/**
	 * Launch the application.
	 */
	public ReviewComentView(String shopNo) throws Exception {
		setLayout(null);
		model = new ReviewComentModel();
		addlay(shopNo);
		addValues(shopNo);
		eventProc();
	}

	private void addValues(String shopNo) {
		try {

			ArrayList ee = model.reviewList(shopNo);
			rl.data = ee;
			tfShopNo.setText(shopNo);
			tfShopName.setText(model.selectShopName(shopNo));
			tfShopHost.setText(model.selectHostName(shopNo));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	void selectTable() {
		try {

			String shopno = tfShopNo.getText();
			
			ArrayList list = model.reviewList(shopno);
			rl.data = list;

			//System.out.println(list);
			//System.out.println(list2);
			// tableRecentList.setModel(tmRent);
			rl.fireTableDataChanged();

		} catch (Exception ex) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "데이블 추력 실패 : " + ex.getMessage());

		}
	}

	public void addlay(String shopNo) {

		rl = new ReviewList();
		tbReview = new JTable(rl);
		JScrollPane scro = new JScrollPane(tbReview);
		scro.setBounds(21, 82, 568, 300);
		add(scro);
		

		tfReviewNo = new JTextField();
		tfReviewNo.setEditable(false);
		tfReviewNo.setBounds(547, 19, 24, 21);
		add(tfReviewNo);
		tfReviewNo.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("No.");
		lblNewLabel_1.setBounds(518, 23, 57, 15);
		add(lblNewLabel_1);

		tfShopNo = new JTextField("");
		tfShopNo.setEditable(false);
		tfShopNo.setHorizontalAlignment(SwingConstants.CENTER);
		tfShopNo.setColumns(10);
		tfShopNo.setBounds(48, 16, 73, 21);
		add(tfShopNo);

		JLabel lblNewLabel_2 = new JLabel("No.");
		lblNewLabel_2.setBounds(21, 19, 57, 15);
		add(lblNewLabel_2);

		tfShopHost = new JTextField();
		tfShopHost.setText((String) null);
		tfShopHost.setHorizontalAlignment(SwingConstants.LEFT);
		tfShopHost.setEditable(false);
		tfShopHost.setColumns(10);
		tfShopHost.setBounds(21, 45, 100, 21);
		add(tfShopHost);

		tfShopName = new JTextField();
		tfShopName.setText((String) null);
		tfShopName.setHorizontalAlignment(SwingConstants.CENTER);
		tfShopName.setForeground(Color.BLACK);
		tfShopName.setFont(new Font("나눔손글씨 펜", Font.BOLD, 40));
		tfShopName.setEditable(false);
		tfShopName.setColumns(10);
		tfShopName.setBackground(Color.ORANGE);
		tfShopName.setBounds(134, 16, 372, 52);
		add(tfShopName);

		tfGoodsName = new JTextField();
		tfGoodsName.setEditable(false);
		tfGoodsName.setColumns(10);
		tfGoodsName.setBounds(570, 45, 73, 21);
		add(tfGoodsName);

		JLabel lblNewLabel_1_1 = new JLabel("\uC0C1\uD488\uBA85 :");
		lblNewLabel_1_1.setBounds(515, 48, 57, 15);
		add(lblNewLabel_1_1);

		TfStar = new JTextField();
		TfStar.setEditable(false);
		TfStar.setColumns(10);
		TfStar.setBounds(693, 45, 44, 21);
		add(TfStar);

		JLabel lblNewLabel_1_2 = new JLabel("\uBCC4\uC810 :");
		lblNewLabel_1_2.setBounds(657, 50, 57, 15);
		add(lblNewLabel_1_2);

		tfReviewDate = new JTextField();
		tfReviewDate.setEditable(false);
		tfReviewDate.setColumns(10);
		tfReviewDate.setBounds(614, 19, 67, 21);
		add(tfReviewDate);

		JLabel lblNewLabel_1_2_1 = new JLabel("\uC77C\uC2DC :");
		lblNewLabel_1_2_1.setBounds(577, 22, 57, 15);
		add(lblNewLabel_1_2_1);

		JLabel lblNewLabel_4 = new JLabel("\uB9AC \uBDF0");
		lblNewLabel_4.setBounds(599, 76, 38, 15);
		add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("\uB2F5 \uBCC0");
		lblNewLabel_5.setBounds(599, 216, 37, 15);
		add(lblNewLabel_5);

		btInsert = new JButton("\uB4F1\uB85D / \uC218\uC815");
		btInsert.setBounds(599, 361, 140, 21);
		add(btInsert);

		tfComent = new JTextArea();
		tfComent.setEditable(false);
		tfComent.setBounds(601, 95, 138, 114);
		tfComent.setLineWrap(true);
		add(tfComent);

		tfArea = new JTextArea();
		tfArea.setBounds(599, 241, 138, 110);
		tfArea.setLineWrap(true);
		add(tfArea);

		tfCusName = new JTextField();
		tfCusName.setEditable(false);
		tfCusName.setColumns(10);
		tfCusName.setBounds(686, 19, 51, 21);
		add(tfCusName);
	}

	public void eventProc() {
		MyListener evt = new MyListener();
		btInsert.addActionListener(evt);
		// btupdate.addActionListener(evt);

		tbReview.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = tbReview.getSelectedRow();

				tfReviewNo.setText(String.valueOf(tbReview.getValueAt(row, 0)));
				tfGoodsName.setText(String.valueOf(tbReview.getValueAt(row, 2)));
				TfStar.setText(String.valueOf(tbReview.getValueAt(row, 3)));
				tfCusName.setText(String.valueOf(tbReview.getValueAt(row, 4)));
				tfReviewDate.setText(String.valueOf(tbReview.getValueAt(row, 6)));

				tfComent.setText(String.valueOf(tbReview.getValueAt(row, 5)));
				tfArea.setText(String.valueOf(tbReview.getValueAt(row, 7)));

				// int col = 0;
				// int col2 = 1;
				// String goodsno = String.valueOf(tbReview.getValueAt(row, col));
				// String goodsname = String.valueOf(tbReview.getValueAt(row, col2));
				// tbReview.setToolTipText(goodsno);
				// tfGoodsNo.setText(goodsno);
				// tfGoodsName.setText(goodsname);

			}
		});
	}

	class ReviewList extends AbstractTableModel {
		ArrayList data = new ArrayList();

		String[] title = { "No", "상품No", "상풍명", "별점", "고객명", "내용", "등록일", "답변", "답변일" };
		
		
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

	class MyListener implements ActionListener {
		public void clear() {

			tfReviewNo.setText(null);
			tfGoodsName.setText(null);
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == btInsert) {
				// btRe.setText("버튼이 눌러졌습니다.");
				int shopno = Integer.parseInt(tfReviewNo.getText());
				String area = tfArea.getText();
				
				try {
					model.areainsert(shopno, area);
					selectTable();
					
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				

			}
		}
	}
}
