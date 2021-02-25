package OrderExcRefView;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import OrderExcRefModel.OrderDetailModel;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JDesktopPane;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Color;

public class OrderList extends JFrame {

	private JPanel contentPane;
	private JTextField jubun01;
	private JTextField delivery_state1;
	private JTextField order_time_end1;
	private JTextField shop_goods_information1;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private OrderDetailModel model = null;
	private JLabel url_emg;
	private JLabel lblNewLabel_6;
	
	private String order_no;
	private String ordergoodsname;
	private String id;
	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 * @throws IOException
	 */
	public OrderList(String goodsno, String id) throws SQLException, IOException {
		model = new OrderDetailModel();
		this.id = id;
		try {
			Layout();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		URL url = new URL(model.ImgUrl(goodsno));
		Image image = ImageIO.read(url);
		Image chimg = image.getScaledInstance(216, 153, Image.SCALE_SMOOTH);
		url_emg.setIcon(new ImageIcon(chimg));

		ArrayList list = model.Orderlistview(goodsno);

		jubun01.setText(list.get(0).toString());
		shop_goods_information1.setText(list.get(1).toString());
		delivery_state1.setText(list.get(2).toString());
		order_time_end1.setText(list.get(3).toString());
		
		order_no = list.get(0).toString();
		ordergoodsname = list.get(1).toString();
		eventProc();
		addValues();

	}

	private void eventProc() {
	}

	private void addValues() {
		model = new OrderDetailModel();

	}

	private void Layout() throws SQLException, IOException {
		setTitle("πËΩ√Ω√");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(630, 330, 650, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(SystemColor.controlHighlight);
		desktopPane.setBounds(41, 52, 542, 378);
		contentPane.add(desktopPane);
		
		JButton btnNewButton = new JButton("±≥»ØΩ≈√ª");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setFont(new Font("øœµµ»Ò∏¡√º Regular", Font.PLAIN, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new Ref1(order_no,ordergoodsname);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//ref.run();
			}
		});
		
		btnNewButton.setBounds(215, 340, 97, 30);
		desktopPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("»Ø∫“Ω≈√ª");
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setFont(new Font("øœµµ»Ò∏¡√º Regular", Font.PLAIN, 15));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println(order_no);
					new Exchange(order_no, ordergoodsname);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		});
		btnNewButton_1.setBounds(324, 340, 97, 30);
		desktopPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("±∏∏≈»ƒ±‚");
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.setFont(new Font("øœµµ»Ò∏¡√º Regular", Font.PLAIN, 15));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String str = jubun01.getText();
					
					new Review(str, id);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			

			}
		});
		btnNewButton_2.setBounds(433, 340, 97, 30);
		desktopPane.add(btnNewButton_2);

		jubun01 = new JTextField();
		jubun01.setFont(new Font("øœµµ»Ò∏¡√º Regular", Font.PLAIN, 15));
		jubun01.setBounds(31, 65, 116, 21);
		desktopPane.add(jubun01);
		jubun01.setColumns(10);

		delivery_state1 = new JTextField();
		delivery_state1.setFont(new Font("øœµµ»Ò∏¡√º Regular", Font.PLAIN, 15));
		delivery_state1.setColumns(10);
		delivery_state1.setBounds(287, 218, 116, 21);
		desktopPane.add(delivery_state1);

		order_time_end1 = new JTextField();
		order_time_end1.setFont(new Font("øœµµ»Ò∏¡√º Regular", Font.PLAIN, 15));
		order_time_end1.setColumns(10);
		order_time_end1.setBounds(287, 271, 134, 21);
		desktopPane.add(order_time_end1);

		shop_goods_information1 = new JTextField();
		shop_goods_information1.setFont(new Font("øœµµ»Ò∏¡√º Regular", Font.PLAIN, 15));
		shop_goods_information1.setColumns(10);
		shop_goods_information1.setBounds(287, 164, 243, 21);
		desktopPane.add(shop_goods_information1);

		lblNewLabel = new JLabel("\uC8FC\uBB38\uBC88\uD638");
		lblNewLabel.setFont(new Font("øœµµ»Ò∏¡√º Regular", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(51, 40, 74, 15);
		desktopPane.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("\uC81C\uD488\uAC00\uACA9");
		lblNewLabel_1.setFont(new Font("øœµµ»Ò∏¡√º Regular", Font.PLAIN, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(271, 195, 97, 15);
		desktopPane.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("\uBC30\uC1A1\uC644\uB8CC\uC77C");
		lblNewLabel_2.setFont(new Font("øœµµ»Ò∏¡√º Regular", Font.PLAIN, 15));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(287, 250, 74, 15);
		desktopPane.add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("\uC0C1\uD488\uC0C1\uC138\uBA85");
		lblNewLabel_3.setFont(new Font("øœµµ»Ò∏¡√º Regular", Font.PLAIN, 15));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(287, 139, 74, 15);
		desktopPane.add(lblNewLabel_3);

		url_emg = new JLabel("");
		// lblNewLabel_5.setIcon(new
		// ImageIcon("C:\\Users\\user\\Desktop\\img\\simple.jpg"));
		url_emg.setBounds(31, 139, 216, 153);
		desktopPane.add(url_emg);
		
		URL url = new URL("https://i.esdrop.com/d/wJDcz58ikF.png");
		Image image1 = ImageIO.read(url);
		Image chimg1 = image1.getScaledInstance(259, 119, Image.SCALE_SMOOTH);

		lblNewLabel_6 = new JLabel(new ImageIcon(chimg1));
		// Image chimg = image.getScaledInstance(216, 153, Image.SCALE_SMOOTH);

		lblNewLabel_6.setBounds(271, 10, 259, 119);
		desktopPane.add(lblNewLabel_6);

		// URL url = new URL(model.ImgUrl());
		// Image image = ImageIO.read(url);

		// lblNewLabel_2 = new JLabel(new ImageIcon(image));
		// lblNewLabel_2.setBounds(35, 34, 267, 143);
		// contentPane.add(lblNewLabel_2); url?óê?Ñú ?ù¥ÎØ∏Ï? Î∂àÎü¨?ò§Í∏?

		lblNewLabel_4 = new JLabel("πËº€øœ∑·¿œ");
		lblNewLabel_4.setFont(new Font("øœµµ»Ò∏¡√º Regular", Font.BOLD, 20));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(258, 10, 91, 32);
		contentPane.add(lblNewLabel_4);

		setVisible(true);
	}

}
