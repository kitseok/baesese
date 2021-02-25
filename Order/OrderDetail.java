package Order;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import categoryVO.categoryVO;
import customer.CardVO;
import customer.custInfo_model;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JDesktopPane;
import java.awt.SystemColor;

public class OrderDetail extends JFrame {

	int price = 0;
	int cnt = 0;
	int price2 = 0;
	int price3 = 0;
	int price4 = 0;
	int point = 0;
	int point2 = 0;
	int mcprice = 0;
	int cbindex = 0;

	int delivery = 0;
	int poco = 0;
	int coupon = 0;
	int total = 0;
	int sum = 0;
	String serial = "";
	String addname = "";
	String cardnum = "";
	private JPanel contentPane;

	JRadioButton rdbtnNewRadioButton;
	JRadioButton rdbtnNewRadioButton_1;
	JRadioButton rdbtnNewRadioButton_2;
	JRadioButton rdbtnNewRadioButton_3;

	ButtonGroup bg = new ButtonGroup();
	ButtonGroup bg1 = new ButtonGroup();

	JButton orderbtn;

	JComboBox comboBox;

	JLabel lblNewLabel;
	JLabel lblNewLabel_1;
	JLabel lblNewLabel_2;
	JLabel la;
	JLabel lblNewLabel_3;
	JLabel lblNewLabel_4;
	JLabel lblNewLabel_5;
	OrderModel model;
	custInfo_model custmodel;
	ArrayList<categoryVO> al;
	ArrayList address;
	ArrayList<CardVO> card;
	JLabel lbPoint;
	private JCheckBox chckbxNewCheckBox;
	int cusno = 0;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;

	JComboBox comboBox_1;
	JComboBox comboBox_2;
	private JDesktopPane desktopPane_1;

	public OrderDetail(int cusno) {
		this.cusno = cusno;

		try {

			model = new OrderModel();
			custmodel = new custInfo_model();
			addlayout();
			eventProc();

			AddItem();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void AddItem() throws SQLException {
		al = model.SelectCoupon(cusno);
		address = model.SelectAdd(cusno);
		card = model.SelectCard(cusno);
		// Iterator<String> it = al.iterator();
		comboBox.addItem("¾øÀ½");
		for (int i = 0; i < al.size(); i++) {
			comboBox.addItem(al.get(i).getMc_name() + ", " + al.get(i).getMc_price());
		}

		for (int i = 0; i < address.size(); i++) {
			comboBox_1.addItem(address.get(i));
		}
		for (int i = 0; i < card.size(); i++) {
			comboBox_2.addItem(card.get(i).getCardname() + ", " + card.get(i).getCardalias());
		}

	}

	public void addlayout() throws SQLException, IOException {
		setTitle("¹è½Ã½Ã");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 344, 432);

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		rdbtnNewRadioButton = new JRadioButton("\uACC4\uC88C\uC774\uCCB4"); // 1102
		rdbtnNewRadioButton.setBackground(Color.WHITE);
		rdbtnNewRadioButton.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		rdbtnNewRadioButton.setBounds(25, 23, 85, 23);
		contentPane.add(rdbtnNewRadioButton);
		bg.add(rdbtnNewRadioButton);

		rdbtnNewRadioButton_1 = new JRadioButton("\uCE74\uB4DC\uACB0\uC81C"); // 1101
		rdbtnNewRadioButton_1.setBackground(Color.WHITE);
		rdbtnNewRadioButton_1.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		rdbtnNewRadioButton_1.setBounds(118, 23, 86, 23);
		contentPane.add(rdbtnNewRadioButton_1);
		bg.add(rdbtnNewRadioButton_1);

		rdbtnNewRadioButton_2 = new JRadioButton("\uBC30\uB2EC"); // 201 DELIVERY_N_VISIT_PRICE
		rdbtnNewRadioButton_2.setBackground(Color.WHITE);
		rdbtnNewRadioButton_2.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		rdbtnNewRadioButton_2.setBounds(25, 82, 73, 23);
		contentPane.add(rdbtnNewRadioButton_2);
		bg1.add(rdbtnNewRadioButton_2);

		rdbtnNewRadioButton_3 = new JRadioButton("\uBC29\uBB38"); // 202 DELIVERY_N_VISIT_PRICE
		rdbtnNewRadioButton_3.setBackground(Color.WHITE);
		rdbtnNewRadioButton_3.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		rdbtnNewRadioButton_3.setBounds(118, 82, 86, 23);
		contentPane.add(rdbtnNewRadioButton_3);
		bg1.add(rdbtnNewRadioButton_3);

		orderbtn = new JButton("ÁÖ¹®ÇÏ±â");
		orderbtn.setBackground(Color.WHITE);
		orderbtn.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 17));
		orderbtn.setBounds(109, 340, 107, 36);
		contentPane.add(orderbtn);

		lblNewLabel = new JLabel("\uCD1D \uAE08\uC561 :");
		lblNewLabel.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		lblNewLabel.setBounds(161, 225, 57, 15);
		contentPane.add(lblNewLabel);

		price = model.TotalPrice(cusno);
		price2 = price;
		price3 = price;
		// price4 = price;

		lblNewLabel_1 = new JLabel(); // °í°´ ³Ñ¹ö °¡Á®¿Í¾ßÇÔ
		lblNewLabel_1.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		lblNewLabel_1.setText(Integer.toString(price));
		lblNewLabel_1.setBounds(241, 225, 57, 15);
		contentPane.add(lblNewLabel_1);

		// URL url = new URL(model.ImgUrl());
		// Image image = ImageIO.read(url);

		// lblNewLabel_2 = new JLabel(new ImageIcon(image));
		// lblNewLabel_2.setBounds(35, 34, 267, 143);
		// contentPane.add(lblNewLabel_2);

		chckbxNewCheckBox = new JCheckBox("\uD3EC\uC778\uD2B8\uC0AC\uC6A9");
		chckbxNewCheckBox.setBackground(Color.WHITE);
		chckbxNewCheckBox.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		chckbxNewCheckBox.setBounds(35, 107, 152, 23);
		contentPane.add(chckbxNewCheckBox);

		comboBox = new JComboBox();
		comboBox.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		comboBox.setBounds(25, 174, 111, 23);
		contentPane.add(comboBox);
		// °í°´ÀÇ ÀÜ¿© Æ÷ÀÎÆ®
		sum = model.sum(cusno);
		lbPoint = new JLabel("ÇöÀçÆ÷ÀÎÆ® : " + sum);
		lbPoint.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		lbPoint.setBounds(25, 143, 160, 21);
		contentPane.add(lbPoint);

		lblNewLabel_4 = new JLabel("0");
		lblNewLabel_4.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		lblNewLabel_4.setText(Integer.toString(price));
		lblNewLabel_4.setBounds(241, 27, 57, 15);
		contentPane.add(lblNewLabel_4);

		lblNewLabel_5 = new JLabel("0");
		lblNewLabel_5.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(241, 86, 57, 15);
		contentPane.add(lblNewLabel_5);

		lblNewLabel_6 = new JLabel("0");
		lblNewLabel_6.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		lblNewLabel_6.setBounds(241, 111, 57, 15);
		contentPane.add(lblNewLabel_6);

		lblNewLabel_7 = new JLabel("0");
		lblNewLabel_7.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		lblNewLabel_7.setBounds(241, 178, 57, 15);
		contentPane.add(lblNewLabel_7);

		comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		comboBox_1.setToolTipText("\uC8FC\uC18C\uC9C0\uB97C \uC120\uD0DD\uD574\uC8FC\uC2ED\uC2DC\uC624");
		comboBox_1.setBounds(25, 249, 273, 23);

		contentPane.add(comboBox_1);

		comboBox_2 = new JComboBox();
		comboBox_2.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		comboBox_2.setToolTipText("\uCE74\uB4DC\uB97C \uC120\uD0DD\uD574\uC8FC\uC2ED\uC2DC\uC624");
		comboBox_2.setBounds(25, 282, 273, 23);
		contentPane.add(comboBox_2);

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(SystemColor.activeCaption);
		desktopPane.setBounds(12, 10, 304, 320);
		contentPane.add(desktopPane);

		desktopPane_1 = new JDesktopPane();
		desktopPane_1.setBackground(SystemColor.window);
		desktopPane_1.setBounds(3, 3, 298, 314);
		desktopPane.add(desktopPane_1);

		setVisible(true);

	}

	public void eventProc() { // ÀÌº¥Æ® ºÙÀÌ±â
		ODDEvent evt = new ODDEvent();
		orderbtn.addActionListener(evt);
		rdbtnNewRadioButton.addActionListener(evt);
		rdbtnNewRadioButton_1.addActionListener(evt);
		rdbtnNewRadioButton_2.addActionListener(evt);
		rdbtnNewRadioButton_3.addActionListener(evt);
		chckbxNewCheckBox.addActionListener(evt);
		comboBox.addActionListener(evt);

	}

	class ODDEvent implements ActionListener { // price ¼öÁ¤ (µÎ °¡°ÝÀ» ³ªÁß¿¡ ÇÕÄ¡´Â½ÄÀ¸·Î)
		public void actionPerformed(ActionEvent e) {
			// point = Integer.parseInt(textField.getText());
			// lblNewLabel_1.setText(Integer.toString(price));

			if (e.getSource() == rdbtnNewRadioButton_2) {
				delivery = 4000;
				lblNewLabel_5.setText(Integer.toString(delivery));
				total = price + delivery + point + coupon;
				lblNewLabel_1.setText(Integer.toString(total));

			} else if (e.getSource() == rdbtnNewRadioButton_3) {
				delivery = 2000;
				lblNewLabel_5.setText(Integer.toString(delivery));
				total = price + delivery + point + coupon;
				lblNewLabel_1.setText(Integer.toString(total));

			} else if (e.getSource() == orderbtn) {
				int pay = 0;
				int del = 0;

				if (rdbtnNewRadioButton.isSelected()) {
					pay = 1102;

					// System.out.println("1");
				} else if (rdbtnNewRadioButton_1.isSelected()) {
					pay = 1101;
					// System.out.println("2");

				}

				if (rdbtnNewRadioButton_2.isSelected()) {
					del = 201;
					// System.out.println("3");
				} else if (rdbtnNewRadioButton_3.isSelected()) {
					del = 202;
					// System.out.println("4");
				}
				if (chckbxNewCheckBox.isSelected()) {
					// int point = Integer.parseInt(lblNewLabel_3.getText());

					if ((total - point) > 0) {
						total = total - point;
					} else {
						point = point - total;
						total = 0;
					}

				}
				try {
					if (comboBox.getSelectedIndex() > 0) {
						serial = al.get(comboBox.getSelectedIndex() - 1).getSerial();
					} else {
						serial = "¾øÀ½";
					}
					if (comboBox_1.getSelectedIndex() > -1) {
						if (comboBox_2.getSelectedIndex() > -1) {
							addname = address.get(comboBox_1.getSelectedIndex()).toString();
							cardnum = card.get(comboBox_2.getSelectedIndex()).getCardname();
							model.GoodsInfo(cusno);
							model.OrderStart(cusno, pay, del, total, point, serial, addname, cardnum);
							// Àå¹Ù±¸´Ï ÃÊ±âÈ­
							model.clearBasket(cusno);
							Order.OrderView1.tmorder.data = model.Basket(cusno);
							Order.OrderView1.tmorder.fireTableDataChanged();
						}

					} else {
						JOptionPane.showMessageDialog(null, "ÁÖ¼ÒÁö ¶Ç´Â Ä«µå¸¦ ¼±ÅÃÇØÁÖ½Ê½Ã¿À");

					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
				JOptionPane.showMessageDialog(null, "ÁÖ¹®ÀÌ ¿Ï·áµÇ¾ú½À´Ï´Ù.");
			} else if (e.getSource() == rdbtnNewRadioButton) {
				// model.ImgInput();
				comboBox_2.setEnabled(false);
				System.out.println("1");

			} else if (e.getSource() == rdbtnNewRadioButton_1) {
				comboBox_2.setEnabled(true);
				System.out.println("2");
			} else if (e.getSource() == chckbxNewCheckBox) {
				if (chckbxNewCheckBox.isSelected()) {
					if (total - sum >= 0) {
						point = -sum;
						lblNewLabel_6.setText(Integer.toString(point));
						total = price + delivery + point + coupon;
						lblNewLabel_1.setText(Integer.toString(total));

					} else {
						point = -total;
						lblNewLabel_6.setText(Integer.toString(point));
						total = 0;
						lblNewLabel_1.setText(Integer.toString(total));
					}
				} else {
					point = 0;
					lblNewLabel_6.setText(Integer.toString(point));
					total = price + delivery + point + coupon;
					lblNewLabel_1.setText(Integer.toString(total));
				}

				System.out.println("click point");
			} else if (e.getSource() == comboBox) {
				System.out.println("click CouPon ee");

				if (comboBox.getSelectedIndex() > 0) {
					try {
						if (model.ChkCoupon(Integer.parseInt(al.get(comboBox.getSelectedIndex() - 1).getMc_no()),
								cusno) == true) {

							coupon = -mcprice;
							mcprice = Integer.parseInt(al.get(comboBox.getSelectedIndex() - 1).getMc_price());
							if (model.GoodsPrice(Integer.parseInt(al.get(comboBox.getSelectedIndex() - 1).getMc_no()),
									cusno, mcprice) == true) {
								coupon = -mcprice;
								lblNewLabel_7.setText(Integer.toString(coupon));
								total = price + delivery + point + coupon;
								lblNewLabel_1.setText(Integer.toString(total));
							} else {
								JOptionPane.showMessageDialog(null, "ÄíÆùÀÇ °¡°ÝÀ» È®ÀÎÇØÁÖ½Ê½Ã¿À.");
								// lblNewLabel_1.setText(Integer.toString(price2));

							}

						} else {

							JOptionPane.showMessageDialog(null, "ÁÖ¹®¿¡ ¸Â´Â ÄíÆùÀÌ ¾Æ´Õ´Ï´Ù.");
							coupon = 0;
							total = price + delivery + point + coupon;
							lblNewLabel_7.setText(Integer.toString(coupon));
							lblNewLabel_1.setText(Integer.toString(total));

							comboBox.setSelectedIndex(0);
						}
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {
					coupon = 0;
					total = price + delivery + point + coupon;
					lblNewLabel_1.setText(Integer.toString(total));
				}

			}

		}
	}
}
