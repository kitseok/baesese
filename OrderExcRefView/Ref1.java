package OrderExcRefView;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import OrderExcRefModel.OrderListModel;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import java.awt.SystemColor;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextArea;
import java.awt.Color;

public class Ref1 extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JButton btnNewButton;
	private JTextField jubun;
	private JTextField seller;
	private JTextField goodsprice;
	private JTextField goodsname;
	private JTextField totamount;
	JSpinner count12;
	OrderListModel orderlist;
	JLabel lb_ref_money;
	JTextArea ref_text;
	private JComboBox comboBox_1;

	String num;
	String name;
	private JTextField tf_ref_money;
	private JLabel lblNewLabel_7;
	private JTextField order_quantity;
	private JLabel lblNewLabel_8;
	private JTextField seller_tel;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public Ref1(String num, String name) throws SQLException {
		this.num = num;
		this.name = name;
		addlay();
		orderlist = new OrderListModel();
		eventProc();
		

	}

	private void eventProc() {
		try {
			ArrayList list;
			int i;
			list = orderlist.recentList(num, name);
			jubun.setText(list.get(0).toString());
			goodsname.setText(list.get(1).toString());
			seller.setText(list.get(2).toString());
			goodsprice.setText(list.get(3).toString());
			order_quantity.setText(list.get(4).toString());
			totamount.setText(list.get(5).toString());
			seller_tel.setText(list.get(6).toString());
			
			i = Integer.parseInt(list.get(4).toString());
			count12.setModel(new SpinnerNumberModel(1, 0, i, 1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		count12.addChangeListener(new ChangeListener() {
//			@Override
//			public void stateChanged(ChangeEvent e) {
//				if (comboBox.getSelectedItem().equals("´Ü¼øº¯½É"))
//				{
//					int x = Integer.parseInt(goodsprice.getText());
//					int y = (int)count12.getValue();
//					tf_ref_money.setText(Integer.toString((x*y)-2000));
//				} else
//				{
//					int x = Integer.parseInt(goodsprice.getText());
//					int y = (int)count12.getValue();
//					tf_ref_money.setText(Integer.toString((x*y)));
//				}
//			}
//		});
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox j = (JComboBox) e.getSource();
				System.out.print(j.getSelectedItem());
				if (j.getSelectedItem().equals("´Ü¼øº¯½É"))
				{
					btnNewButton.setEnabled(true);
					System.out.print("1");
					int x = Integer.parseInt(goodsprice.getText());
					int y = (int)count12.getValue();
					tf_ref_money.setText(Integer.toString((x*y)-2000));
				} else if (j.getSelectedItem().equals("»çÀ¯¸¦ ¼±ÅÃÇÏ¼¼¿ä."))
				{
					btnNewButton.setEnabled(false);
				}
				else
				{
					btnNewButton.setEnabled(true);
					int x = Integer.parseInt(goodsprice.getText());
					int y = (int)count12.getValue();
					tf_ref_money.setText(Integer.toString((x*y)));
				}
				ref_text.setText("");
			}
		});

	}

	public void addlay() {
		setTitle("¹è½Ã½Ã");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(600, 300, 650, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tf_ref_money = new JTextField();
		tf_ref_money.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		tf_ref_money.setBounds(529, 405, 93, 21);
		contentPane.add(tf_ref_money);
		tf_ref_money.setColumns(10);

		comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
	
		
		count12 = new JSpinner();
		count12.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		
		count12.setBounds(554, 315, 54, 22);
		contentPane.add(count12);

		JLabel lblNewLabel_6 = new JLabel("\uAD50\uD658\uC218\uB7C9");
		lblNewLabel_6.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(533, 290, 89, 15);
		contentPane.add(lblNewLabel_6);

		lb_ref_money = new JLabel("\uBC18\uD488\uBC30\uC1A1\uBE44");
		lb_ref_money.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		lb_ref_money.setHorizontalAlignment(SwingConstants.CENTER);
		lb_ref_money.setBounds(533, 380, 89, 15);
		
		contentPane.add(lb_ref_money);
		
		comboBox_1.setToolTipText("´­·¯¿ä.");
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] { "´Ü¼øº¯½É","º¯Áú/Áþ¹«¸§/³¿»õ", "¾ó¾úÀ½/³ì¾ÒÀ½ (¿Âµµ¹®Á¦)", "À¯Åë±âÇÑ ºÒ¸¸",
	            "¿ë·®/ »çÀÌÁî ºÒ¸¸", "»óÇ° ÀÏºÎ°¡ ¹è¼Û ´©¶ôµÊ", "»óÇ°ÀÌÆÄ¼ÕµÊ", "»óÇ°ÀÌ ¼³¸í°ú ´Ù¸§", "´Ù¸¥ »óÇ°ÀÌ ¹è¼ÛµÊ", "±× ¿Ü ¹®Á¦", "»çÀ¯¸¦ ¼±ÅÃÇÏ¼¼¿ä."}));
		comboBox_1.setSelectedIndex(10);
		comboBox_1.setBounds(34, 257, 148, 23);
		contentPane.add(comboBox_1);
		
		lblNewLabel = new JLabel("\uAD50\uD658 \uC2E0\uCCAD");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblNewLabel.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.BOLD, 20));
		lblNewLabel.setBounds(218, 10, 212, 41);
		contentPane.add(lblNewLabel);

		btnNewButton = new JButton("\uAD50\uD658");
		btnNewButton.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//if (e.getSource() == btnNewButton) {
					System.out.println(ref_text.getText());
					orderlist.insertEx_ref(jubun.getText(), count12.getValue().toString(), comboBox_1.getSelectedItem().toString(), ref_text.getText(), tf_ref_money.getText());
					dispose();
				//}
				JOptionPane.showMessageDialog(null, "±³È¯ ½ÅÃ»ÀÌ ¿Ï·áµÇ¾ú½À´Ï´Ù.");

			}
		});
		btnNewButton.setBounds(487, 527, 97, 23);
		
		contentPane.add(btnNewButton);
		
		btnNewButton.setEnabled(false);

		jubun = new JTextField();
		jubun.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		jubun.setColumns(10);
		jubun.setBounds(175, 61, 163, 21);
		contentPane.add(jubun);

		JLabel lblNewLabel_1 = new JLabel("\uC8FC\uBB38\uBC88\uD638");
		lblNewLabel_1.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(42, 64, 89, 15);
		contentPane.add(lblNewLabel_1);

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(SystemColor.controlHighlight);
		desktopPane.setBounds(34, 48, 574, 179);
		contentPane.add(desktopPane);

		goodsprice = new JTextField();
		goodsprice.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		goodsprice.setBounds(281, 114, 89, 22);
		desktopPane.add(goodsprice);
		goodsprice.setHorizontalAlignment(SwingConstants.CENTER);
		goodsprice.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("\uACB0\uC81C\uAE08\uC561");
		lblNewLabel_4.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(281, 89, 89, 15);
		desktopPane.add(lblNewLabel_4);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);

		totamount = new JTextField();
		totamount.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		totamount.setHorizontalAlignment(SwingConstants.CENTER);
		totamount.setColumns(10);
		totamount.setBounds(473, 114, 89, 22);
		desktopPane.add(totamount);

		JLabel lblNewLabel_3_1 = new JLabel("\uAD6C\uB9E4\uC218\uB7C9");
		lblNewLabel_3_1.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setBounds(473, 89, 89, 15);
		desktopPane.add(lblNewLabel_3_1);

		JLabel lblNewLabel_2 = new JLabel("\uC0C1\uD488\uBA85");
		lblNewLabel_2.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(36, 89, 89, 15);
		desktopPane.add(lblNewLabel_2);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);

		seller = new JTextField();
		seller.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		seller.setBounds(137, 114, 132, 22);
		desktopPane.add(seller);
		seller.setHorizontalAlignment(SwingConstants.CENTER);
		seller.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("\uD310\uB9E4\uC790");
		lblNewLabel_3.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(162, 89, 89, 15);
		desktopPane.add(lblNewLabel_3);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);

		lblNewLabel_7 = new JLabel("\uAD6C\uB9E4\uC218\uB7C9");
		lblNewLabel_7.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setBounds(372, 89, 89, 15);
		desktopPane.add(lblNewLabel_7);

		order_quantity = new JTextField();
		order_quantity.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		order_quantity.setHorizontalAlignment(SwingConstants.CENTER);
		order_quantity.setColumns(10);
		order_quantity.setBounds(382, 114, 79, 21);
		desktopPane.add(order_quantity);

		goodsname = new JTextField();
		goodsname.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		goodsname.setBounds(22, 114, 103, 21);
		desktopPane.add(goodsname);
		goodsname.setHorizontalAlignment(SwingConstants.CENTER);
		goodsname.setColumns(10);

		lblNewLabel_8 = new JLabel("=");
		lblNewLabel_8.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		lblNewLabel_8.setBounds(462, 89, 57, 15);
		desktopPane.add(lblNewLabel_8);
		
		seller_tel = new JTextField();
		seller_tel.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		seller_tel.setHorizontalAlignment(SwingConstants.CENTER);
		seller_tel.setBounds(137, 136, 132, 21);
		desktopPane.add(seller_tel);
		seller_tel.setColumns(10);
		
		ref_text = new JTextArea();
		ref_text.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 15));
		ref_text.setBackground(Color.LIGHT_GRAY);
		ref_text.setBounds(30, 297, 466, 206);
		contentPane.add(ref_text);

		setVisible(true);

	}
}
