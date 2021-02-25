package categoryView;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import categoryModel.tacegory_detailModel;


import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JScrollBar;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JSplitPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.JTabbedPane;
import javax.swing.JSlider;
import java.awt.Font;

public class tacegory_detail extends JFrame {

	private JPanel contentPane;
	private final JLabel lb_name = new JLabel("\uC0C1\uD488\uBA85_\uC791\uC131");
	private JLabel lb_origin;
	private JLabel lb_shop_name;
	private JLabel lb_price;
	private JLabel lb_weight;
	private JLabel lb_stok;
	private JLabel lb_imformation;
	JLabel lb_image;
	JComboBox cb_stok;
	String id = "";
	String shopGoodsNo ="";
	JButton btnNewButton_1;
	JSlider slider;
	JButton btn_rating;
	private tacegory_detailModel model = null;
	private JTextField tf_reiview;
	JLabel lb_rating;
	JButton btn_review;
	public void run() {
		try {
			setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public tacegory_detail(String shopGoodsNo,String id) {
		this.id = id;
		this.shopGoodsNo = shopGoodsNo;
		model = new tacegory_detailModel();
		showLayout();
		showValues(shopGoodsNo);
		eventProc();
	}
	private void showValues(String shopGoodsNo) {
		ArrayList al = model.choiceData(shopGoodsNo);
		lb_name.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 25));
		lb_name.setBounds(31, 14, 197, 45);
		
		lb_name.setText(al.get(1).toString());
		lb_origin.setText(al.get(3).toString());
		lb_shop_name.setText(al.get(0).toString());
		lb_price.setText(al.get(6).toString());
		lb_weight.setText(al.get(5).toString());
		lb_stok.setText(al.get(2).toString());
		lb_imformation.setText(al.get(4).toString());
		URL url;
		try {
			url = new URL(al.get(7).toString());
			Image image = ImageIO.read(url);
			Image chimg = image.getScaledInstance(500, 500, Image.SCALE_SMOOTH);
			lb_image.setIcon(new ImageIcon(chimg));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e1)
		{
			e1.printStackTrace();
		}
		for (int i = 0; i < Integer.parseInt(lb_stok.getText()); i++) {
			cb_stok.addItem(i+1);
		}
	}

	private void eventProc() {
		BtnEvent evt = new BtnEvent();
		btnNewButton_1.addActionListener(evt);
		btn_rating.addActionListener(evt); // ³ª¸¸ÀÇ ÆòÁ¡ ¹öÆ° 
		btn_review.addActionListener(evt); // »óÇ°¿¡ °ü·Ã ¸®ºä º¸±â
		slider.addChangeListener(new ChangeListener() { //½½¶óÀÌ´õ¿¡ µû¸¥ º°Á¡ 1Á¡¿¡¼­ 5Á¡»çÀÌ
			@Override
			public void stateChanged(ChangeEvent e) {
				lb_rating.setText(Integer.toString(slider.getValue()));
			}
		});
		ArrayList al = model.selectCusShopReview(shopGoodsNo, id);
		lb_rating.setText(al.get(0).toString());
		tf_reiview.setText(al.get(1).toString());
		slider.setValue(Integer.parseInt(lb_rating.getText()));
	}
	private void showLayout() {
		setTitle("¹è½Ã½Ã");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(630, 280, 896, 544);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(lb_name);
		
		JLabel lblNewLabel = new JLabel("\uB098\uB9CC\uC758 \uD3C9\uC810");
		lblNewLabel.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		lblNewLabel.setBounds(240, 39, 79, 19);
		contentPane.add(lblNewLabel);
		
		slider = new JSlider();
		slider.setBackground(Color.WHITE);
		slider.setBounds(323, 39, 189, 19);
		slider.setValue(1);
		slider.setMinimum(1);
		slider.setMaximum(5);
		contentPane.add(slider);
		
		lb_rating = new JLabel("1");
		lb_rating.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		lb_rating.setBounds(524, 36, 33, 26);
		contentPane.add(lb_rating);
		
		tf_reiview = new JTextField();
		tf_reiview.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		tf_reiview.setBounds(565, 37, 181, 25);
		contentPane.add(tf_reiview);
		tf_reiview.setColumns(10);
		
		btn_rating = new JButton("\uB098\uB9CC\uC758\uD3C9\uC810");
		btn_rating.setBackground(Color.WHITE);
		btn_rating.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		btn_rating.setBounds(754, 34, 105, 30);
		contentPane.add(btn_rating);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setBounds(30, 81, 827, 374);
		splitPane.setMinimumSize(new Dimension(190, 32));
		contentPane.add(splitPane);
		
		
		
		lb_image = new JLabel("");
		lb_image.setAlignmentX(Component.CENTER_ALIGNMENT);
		lb_image.setSize(500, 500);
		splitPane.setLeftComponent(lb_image);
		lb_image.setBackground(Color.LIGHT_GRAY);
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setRightComponent(splitPane_1);
		
		JSplitPane splitPane_2 = new JSplitPane();
		splitPane_2.setEnabled(false);
		splitPane_1.setLeftComponent(splitPane_2);
		
		JLabel lblNewLabel_1 = new JLabel("   \uAC00 \uACA9 :");
		lblNewLabel_1.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		splitPane_2.setLeftComponent(lblNewLabel_1);
		
		lb_price = new JLabel("$$$$$\uC6D0");
		splitPane_2.setRightComponent(lb_price);
		
		JSplitPane splitPane_3 = new JSplitPane();
		splitPane_3.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_1.setRightComponent(splitPane_3);
		
		JSplitPane splitPane_4 = new JSplitPane();
		splitPane_4.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_3.setRightComponent(splitPane_4);
		
		JSplitPane splitPane_6 = new JSplitPane();
		splitPane_6.setEnabled(false);
		splitPane_4.setLeftComponent(splitPane_6);
		
		JLabel lblNewLabel_6 = new JLabel("\uC6D0\uC0B0\uC9C0 :");
		lblNewLabel_6.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		splitPane_6.setLeftComponent(lblNewLabel_6);
		
		lb_origin = new JLabel("\uB300\uC804");
		lb_origin.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		splitPane_6.setRightComponent(lb_origin);
		
		JSplitPane splitPane_7 = new JSplitPane();
		splitPane_7.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_4.setRightComponent(splitPane_7);
		
		JSplitPane splitPane_8 = new JSplitPane();
		splitPane_7.setLeftComponent(splitPane_8);
		
		JLabel lblNewLabel_8 = new JLabel("\uC810\uD3EC\uBA85 :");
		lblNewLabel_8.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		splitPane_8.setLeftComponent(lblNewLabel_8);
		
		lb_shop_name = new JLabel("New label");
		lb_shop_name.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		splitPane_8.setRightComponent(lb_shop_name);
		
		JSplitPane splitPane_9 = new JSplitPane();
		splitPane_9.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_7.setRightComponent(splitPane_9);
		
		JSplitPane splitPane_10 = new JSplitPane();
		splitPane_9.setLeftComponent(splitPane_10);
		
		JLabel lblNewLabel_10 = new JLabel("   \uBB34\uAC8C :");
		lblNewLabel_10.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		splitPane_10.setLeftComponent(lblNewLabel_10);
		
		 lb_weight = new JLabel("New label");
		 lb_weight.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		splitPane_10.setRightComponent(lb_weight);
		
		JSplitPane splitPane_11 = new JSplitPane();
		splitPane_11.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_9.setRightComponent(splitPane_11);
		
		JSplitPane splitPane_12 = new JSplitPane();
		splitPane_11.setLeftComponent(splitPane_12);
		
		JLabel lblNewLabel_9 = new JLabel("   \uC7AC\uACE0 :");
		lblNewLabel_9.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		splitPane_12.setLeftComponent(lblNewLabel_9);
		
		lb_stok = new JLabel("New label");
		lb_stok.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		splitPane_12.setRightComponent(lb_stok);
		
		lb_imformation = new JLabel("\uC790\uC138\uD55C \uC124\uBA85");
		lb_imformation.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		splitPane_11.setRightComponent(lb_imformation);
		
		JSplitPane splitPane_5 = new JSplitPane();
		splitPane_5.setEnabled(false);
		splitPane_3.setLeftComponent(splitPane_5);
		
		JLabel lblNewLabel_3 = new JLabel("   \uC218 \uB7C9 :");
		lblNewLabel_3.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		splitPane_5.setLeftComponent(lblNewLabel_3);
		
		cb_stok = new JComboBox();
		cb_stok.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		splitPane_5.setRightComponent(cb_stok);
		
		btn_review = new JButton("\uB9AC\uBDF0 \uBCF4\uAE30");
		btn_review.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 17));
		btn_review.setBackground(Color.WHITE);
		btn_review.setBounds(596, 466, 121, 30);
		contentPane.add(btn_review);
		
		btnNewButton_1 = new JButton("\uC7A5\uBC14\uAD6C\uB2C8\uB2F4\uAE30");
		btnNewButton_1.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 17));
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setBounds(730, 466, 129, 30);
		contentPane.add(btnNewButton_1);
	}

	class BtnEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();
			
			if(o == btnNewButton_1)
			{
				int num;
				
				try {
					num = model.CustnoFind(id);
					int price = Integer.parseInt(lb_price.getText());
					int qty = Integer.parseInt(cb_stok.getSelectedItem().toString());
					int goodsnum = Integer.parseInt(shopGoodsNo);
					model.BasketAdd(goodsnum,num,price,qty);
					JOptionPane.showMessageDialog(null,  "Àå¹Ù±¸´Ï ´ã±â ¿Ï·á.");
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			} else if ( o == btn_review) //¸®ºäº¸±â Ãß°¡
			{
				try {
					new UserreviewView(shopGoodsNo);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (o == btn_rating)	// À¯Àú°£´Ü º°Á¡ 
			{
				model.insertCusView(shopGoodsNo, id, lb_rating.getText(),tf_reiview.getText()); //±èÇØÇÇ¾¾
				JOptionPane.showMessageDialog(null,  "ÆòÁ¡ ¾÷µ¥ÀÌÆ®");
			}
		}
	}
	
}
