package OrderExcRefView;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import OrderExcRefModel.ReviewModel;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;

public class Review extends JFrame {

	private JPanel contentPane;
	private JTextField tfshopNo;
	private JTextField tfcusNo;
	JButton btInsert;
	JButton btn_add;
	JLabel lb_rating;
	JLabel lb_ratingName;
	private JLabel lblNewLabel_3;
	private JTextField tfshop_name;
	ReviewModel rmodel;
	private String jubun;
	private String id;
	ArrayList list;
	JSlider slider;
	JTextArea textArea;
	public Review(String jubun, String id) throws SQLException {
		setTitle("πËΩ√Ω√");
		rmodel = new ReviewModel();
		list = new ArrayList();
		this.jubun = jubun;
		this.id = id;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 399, 484);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tfshopNo = new JTextField();
		tfshopNo.setBounds(123, 39, 234, 21);
		contentPane.add(tfshopNo);
		tfshopNo.setColumns(10);
		tfshopNo.setEditable(false);
		tfcusNo = new JTextField(id);
		tfcusNo.setColumns(10);
		tfcusNo.setBounds(123, 116, 234, 21);
		contentPane.add(tfcusNo);
		tfcusNo.setEditable(false);
		JLabel lblNewLabel = new JLabel("\uD3C9\uC810\uC8FC\uAE30 :");
		lblNewLabel.setFont(new Font("øœµµ»Ò∏¡√º Regular", Font.PLAIN, 15));
		lblNewLabel.setBounds(52, 158, 67, 15);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\uC0AC\uC6A9\uC790 \uB9AC\uBDF0");
		lblNewLabel_1.setFont(new Font("øœµµ»Ò∏¡√º Regular", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(28, 189, 108, 18);
		contentPane.add(lblNewLabel_1);

		JLabel lblShopno = new JLabel("\uC810\uD3EC\uBC88\uD638 :");
		lblShopno.setFont(new Font("øœµµ»Ò∏¡√º Regular", Font.PLAIN, 15));
		lblShopno.setBounds(54, 37, 63, 24);
		contentPane.add(lblShopno);

		JLabel lblCusno = new JLabel("\uC544\uC774\uB514 :");
		lblCusno.setFont(new Font("øœµµ»Ò∏¡√º Regular", Font.PLAIN, 15));
		lblCusno.setBounds(64, 119, 57, 15);
		contentPane.add(lblCusno);

		btn_add = new JButton("\uD6C4\uAE30 \uB0A8\uAE30\uAE30");
		btn_add.setFont(new Font("øœµµ»Ò∏¡√º Regular", Font.PLAIN, 15));
		btn_add.setBackground(Color.WHITE);
		btn_add.setBounds(245, 394, 112, 30);
		contentPane.add(btn_add);

		slider = new JSlider();
		slider.setBackground(Color.WHITE);
		slider.setMinimum(1);
		slider.setMaximum(5);
		slider.setBounds(132, 155, 173, 26);
		slider.addChangeListener(new ChangeListener() { // ?ù¥Î≤§Ìä∏ ?Ñ§?†ï
			public void stateChanged(ChangeEvent e) { // ?ÉÅ?Éú Î≥??ôî ?ù¥Î≤§Ìä∏
				lb_rating.setText(Integer.toString(slider.getValue()));
				if (lb_rating.getText().equals("5")) {
					lb_ratingName.setText("5");
				} else if (lb_rating.getText().equals("4")) {
					lb_ratingName.setText("4");
				} else if (lb_rating.getText().equals("3")) {
					lb_ratingName.setText("3");
				} else if (lb_rating.getText().equals("2")) {
					lb_ratingName.setText("2");
				} else {
					lb_ratingName.setText("1");
				}
			}
		});

		contentPane.add(slider);

		lb_rating = new JLabel("5");
		lb_rating.setFont(new Font("øœµµ»Ò∏¡√º Regular", Font.PLAIN, 15));
		lb_rating.setBounds(316, 160, 26, 15);
		contentPane.add(lb_rating);

		lb_ratingName = new JLabel("\uD3C9\uC810");
		lb_ratingName.setFont(new Font("øœµµ»Ò∏¡√º Regular", Font.PLAIN, 15));
		lb_ratingName.setBounds(334, 159, 34, 16);
		contentPane.add(lb_ratingName);

		lblNewLabel_3 = new JLabel("\uC0C1\uD488\uBA85 :");
		lblNewLabel_3.setFont(new Font("øœµµ»Ò∏¡√º Regular", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(64, 77, 65, 21);
		contentPane.add(lblNewLabel_3);

		tfshop_name = new JTextField();
		tfshop_name.setColumns(10);
		tfshop_name.setBounds(123, 75, 234, 21);
		contentPane.add(tfshop_name);
		tfshop_name.setEditable(false);
		textArea = new JTextArea();
		textArea.setFont(new Font("øœµµ»Ò∏¡√º Regular", Font.PLAIN, 15));
		textArea.setBackground(Color.LIGHT_GRAY);
		textArea.setBounds(25, 221, 332, 152);
		contentPane.add(textArea);

		setVisible(true);

		list = rmodel.recentList2(jubun, "1");

		tfshopNo.setText(list.get(0).toString());
		tfshop_name.setText(list.get(1).toString());

		eventProc();
	}

	public void eventProc() throws SQLException {
		MyListener evt = new MyListener();
		btn_add.addActionListener(evt);
		// ArrayList list2;
		// int a;
		// list2 = rmodel.recentList2(num, name);
		// tfshopNo.setText(list2.get(0).toString());
		// tfshop_name.setText(list2.get(1).toString());
		// tfcusNo.setText(list2.get(2).toString());

	}

	class MyListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
//			if (e.getSource().equals(btInsert)) {
//				try {
//					String shopno = tfshopNo.getText();
//				} catch (Exception e1) {
//					e1.printStackTrace();
//				}
//			} else 
			if (e.getSource().equals(btn_add)) {
				rmodel.insertEx_ref(lb_rating.getText(), textArea.getText(), tfshopNo.getText(), id, jubun);
				JOptionPane.showMessageDialog(null, "∏Æ∫‰ øœ∑·.");
				dispose();
			}
		}
	}
}