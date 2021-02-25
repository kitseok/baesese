package categoryView;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

import categoryModel.UserViewModel;


public class UserreviewView extends JFrame {
	private JPanel contentPane;
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
	UserViewModel model;
	ReviewList rl;
	

	/**
	 * Launch the application.
	 */
	public UserreviewView(String shopGoodsNo) throws Exception {
		getContentPane().setLayout(null);
		model = new UserViewModel();
		addlay();
		addValues(shopGoodsNo);
		eventProc();
	}

	private void addValues(String shopGoodsNo) throws SQLException {
		rl.data = model.reviewList(shopGoodsNo);

		tfShopNo.setText(shopGoodsNo);
		tfShopName.setText(model.selectShopName(shopGoodsNo));
		tfShopHost.setText(model.selectHostName(shopGoodsNo));
		
	}

	void selectTable() {
		try {

			String shopno = tfShopNo.getText();

			ArrayList list = model.reviewList(shopno);
			if(list !=null)
			{				
				rl.data = list;
			}

			System.out.println(list);
			rl.fireTableDataChanged();

		} catch (Exception ex) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "µ¥ÀÌºí Ãß·Â ½ÇÆÐ : " + ex.getMessage());

		}
	}

	public void addlay() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 773, 429);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		rl = new ReviewList();
		contentPane.setLayout(null);
		tbReview = new JTable(rl);
		tbReview.getColumn("No").setWidth(0);
		tbReview.getColumn("No").setMinWidth(0);
		tbReview.getColumn("No").setMaxWidth(0);
		// Å©±â ¹× Á¤·Ä
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel columnModel = tbReview.getColumnModel();
		//columnModel.getColumn(0).setPreferredWidth(0);
		//columnModel.getColumn(0).setCellRenderer(dtcr);
		columnModel.getColumn(1).setPreferredWidth(30);
		columnModel.getColumn(1).setCellRenderer(dtcr);
		columnModel.getColumn(2).setPreferredWidth(40);
		columnModel.getColumn(2).setCellRenderer(dtcr);
		columnModel.getColumn(3).setPreferredWidth(10);
		columnModel.getColumn(3).setCellRenderer(dtcr);
		columnModel.getColumn(4).setPreferredWidth(20);
		columnModel.getColumn(4).setCellRenderer(dtcr);
		columnModel.getColumn(5).setPreferredWidth(50);
		columnModel.getColumn(5).setCellRenderer(dtcr);
		columnModel.getColumn(6).setPreferredWidth(50);
		columnModel.getColumn(6).setCellRenderer(dtcr);
		columnModel.getColumn(7).setPreferredWidth(50);
		columnModel.getColumn(7).setCellRenderer(dtcr);
		columnModel.getColumn(8).setPreferredWidth(50);
		columnModel.getColumn(8).setCellRenderer(dtcr);
		// ÄÃ·³ ÆùÆ®
		tbReview.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		// Å×ÀÌºí ÄÃ·³ ÆùÆ®
		tbReview.getTableHeader().setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		JScrollPane scro = new JScrollPane(tbReview);
		scro.setBounds(21, 82, 550, 300);
		contentPane.add(scro);

		tfReviewNo = new JTextField();
		tfReviewNo.setEditable(false);
		tfReviewNo.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		tfReviewNo.setBounds(454, 18, 31, 21);
		contentPane.add(tfReviewNo);
		
		tfReviewNo.setColumns(10);
		contentPane.add(tfReviewNo);
		
		JLabel lblNewLabel_1 = new JLabel("No.");
		lblNewLabel_1.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(431, 21, 57, 15);
		contentPane.add(lblNewLabel_1);

		tfShopNo = new JTextField("");
		tfShopNo.setEditable(false);
		tfShopNo.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		tfShopNo.setBounds(48, 16, 73, 21);
		tfShopNo.setColumns(10);
		contentPane.add(tfShopNo);
		
		JLabel lblNewLabel_2 = new JLabel("No.");
		lblNewLabel_2.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(21, 19, 57, 15);
		contentPane.add(lblNewLabel_2);

		tfShopHost = new JTextField();
		tfShopHost.setEditable(false);
		tfShopHost.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		tfShopHost.setBounds(21, 45, 100, 21);
		tfShopHost.setText((String) null);
		tfShopHost.setColumns(10);
		contentPane.add(tfShopHost);

		tfShopName = new JTextField();
		tfShopName.setHorizontalAlignment(SwingConstants.CENTER);
		tfShopName.setBounds(134, 16, 291, 52);
		tfShopName.setText((String) null);
		tfShopName.setForeground(Color.BLACK);
		tfShopName.setFont(new Font("³ª´®¼Õ±Û¾¾ Ææ", Font.BOLD, 40));
		tfShopName.setEditable(false);
		tfShopName.setColumns(10);
		tfShopName.setBackground(Color.ORANGE);
		contentPane.add(tfShopName);

		tfGoodsName = new JTextField();
		tfGoodsName.setEditable(false);
		tfGoodsName.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		tfGoodsName.setBounds(487, 45, 149, 21);
		tfGoodsName.setColumns(10);
		getContentPane().add(tfGoodsName);

		JLabel lblNewLabel_1_1 = new JLabel("\uC0C1\uD488\uBA85 :");
		lblNewLabel_1_1.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(434, 48, 57, 15);
		contentPane.add(lblNewLabel_1_1);

		TfStar = new JTextField();
		TfStar.setEditable(false);
		TfStar.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		TfStar.setBounds(693, 45, 44, 21);
		TfStar.setColumns(10);
		contentPane.add(TfStar);

		JLabel lblNewLabel_1_2 = new JLabel("\uBCC4\uC810 :");
		lblNewLabel_1_2.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(648, 48, 57, 15);
		contentPane.add(lblNewLabel_1_2);

		tfReviewDate = new JTextField();
		tfReviewDate.setEditable(false);
		tfReviewDate.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		tfReviewDate.setBounds(528, 19, 147, 21);
		tfReviewDate.setColumns(10);
		contentPane.add(tfReviewDate);

		JLabel lblNewLabel_1_2_1 = new JLabel("\uC77C\uC2DC :");
		lblNewLabel_1_2_1.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		lblNewLabel_1_2_1.setBounds(489, 22, 57, 15);
		contentPane.add(lblNewLabel_1_2_1);

		JLabel lblNewLabel_4 = new JLabel("\uB9AC \uBDF0");
		lblNewLabel_4.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(599, 76, 38, 15);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("\uB2F5 \uBCC0");
		lblNewLabel_5.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(601, 224, 37, 15);
		contentPane.add(lblNewLabel_5);

		tfComent = new JTextArea();
		tfComent.setEditable(false);
		tfComent.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		tfComent.setBounds(601, 95, 138, 119);
		tfComent.setLineWrap(true);
		contentPane.add(tfComent);

		tfArea = new JTextArea();
		tfArea.setEditable(false);
		tfArea.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		tfArea.setBounds(599, 249, 138, 133);
		tfArea.setLineWrap(true);
		contentPane.add(tfArea);

		tfCusName = new JTextField();
		tfCusName.setEditable(false);
		tfCusName.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		tfCusName.setBounds(687, 19, 50, 21);
		tfCusName.setColumns(10);
		contentPane.add(tfCusName);
		
		 setVisible(true);
	}

	public void eventProc() {
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

		String[] title = { "No", "»óÇ°¹øÈ£", "»óÇ°¸í", "º°Á¡", "°í°´¸í", "³»¿ë", "µî·ÏÀÏ", "´äº¯", "´äº¯ÀÏ" };

		public int getColumnCount() {// ÄÃ·³ ±æÀÌ °¡Á®¿È
			return title.length;
		}

		public int getRowCount() {// Çà ±æÀÌ °¡Á®¿È
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

}
