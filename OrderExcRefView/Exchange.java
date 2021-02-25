package OrderExcRefView;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import OrderExcRefModel.ExchangeModel;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import java.awt.SystemColor;
import javax.swing.JTextArea;
import java.awt.Color;

public class Exchange extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField tf_exmoney;
	ExchangeModel model;
	JSpinner spinner12;
	private String num;
	private String name;
	JComboBox comboBox;
	JButton exc_req; // ÍµêÌôò?ã†Ï≤?
	JTextArea exc_text;
	private JTextField seller_tel;

	public Exchange(String num, String name) throws SQLException {
		setBackground(Color.WHITE);
		showLay();
		model = new ExchangeModel();
		this.num = num;
		this.name = name;
		eventProc();

	}

	private void eventProc() throws SQLException {
		ArrayList list1;
		int a;
		list1 = model.recentList1(num, name);
		textField_1.setText(list1.get(0).toString());
		textField_2.setText(list1.get(1).toString());
		textField_5.setText(list1.get(2).toString());
		textField_3.setText(list1.get(3).toString());
		textField_6.setText(list1.get(4).toString());
		textField_4.setText(list1.get(5).toString());
		seller_tel.setText(list1.get(6).toString());

		a = Integer.parseInt(list1.get(4).toString());
		spinner12.setModel(new SpinnerNumberModel(1, 0, a, 1));

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox j = (JComboBox) e.getSource();
				System.out.print(j.getSelectedItem());
				if (j.getSelectedItem().equals("¥‹º¯∫ØΩ…")) {
					exc_req.setEnabled(true);
					tf_exmoney.setText(Integer.toString(5000));
				} else if (j.getSelectedItem().equals("ªÁ¿Ø∏¶ º±≈√«œººø‰.")) {
					exc_req.setEnabled(false);
				} else {
					exc_req.setEnabled(true);

					tf_exmoney.setText(Integer.toString((0)));
				}
				exc_text.setText("");
			}
		});
	}

	private void showLay() {
		setTitle("πËΩ√Ω√");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(600, 300, 650, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		comboBox = new JComboBox();
		comboBox.setFont(new Font("øœµµ»Ò∏¡√º Regular", Font.PLAIN, 15));

		comboBox.setToolTipText("¥≠∑Ø∫∏ººøÎ.");
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "¥‹º¯∫ØΩ…","∫Ø¡˙/¡˛π´∏ß/≥øªı", "æÛæ˙¿Ω/≥Ïæ“¿Ω (ø¬µµπÆ¡¶)", "¿Ø≈Î±‚«— ∫“∏∏",
	            "øÎ∑Æ/ ªÁ¿Ã¡Ó ∫“∏∏", "ªÛ«∞ ¿œ∫Œ∞° πËº€ ¥©∂Ùµ ", "ªÛ«∞¿Ã∆ƒº’µ ", "ªÛ«∞¿Ã º≥∏Ì∞˙ ¥Ÿ∏ß", "¥Ÿ∏• ªÛ«∞¿Ã πËº€µ ", "±◊ ø‹ πÆ¡¶", "ªÁ¿Ø∏¶ º±≈√«œººø‰."}));
		comboBox.setSelectedIndex(10);
		comboBox.setBounds(30, 257, 144, 23);
		contentPane.add(comboBox);

		JLabel lblNewLabel = new JLabel("\uD658\uBD88 \uC2E0\uCCAD");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("øœµµ»Ò∏¡√º Regular", Font.BOLD, 20));
		lblNewLabel.setBounds(214, 10, 212, 41);
		contentPane.add(lblNewLabel);

		exc_req = new JButton("\uC2E0\uCCAD");
		exc_req.setFont(new Font("øœµµ»Ò∏¡√º Regular", Font.PLAIN, 15));
		exc_req.setBackground(Color.WHITE);
		exc_req.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == exc_req) {
					model.insertEx_ref(textField_1.getText(), spinner12.getValue().toString(),comboBox.getSelectedItem().toString(), exc_text.getText(), tf_exmoney.getText());
					dispose();

				}
				JOptionPane.showMessageDialog(null, "»Ø∫“ Ω≈√ª¿Ã øœ∑·µ«æ˙Ω¿¥œ¥Ÿ.");

			}
		});
		exc_req.setBounds(507, 520, 97, 30);
		contentPane.add(exc_req);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("øœµµ»Ò∏¡√º Regular", Font.PLAIN, 15));
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setColumns(10);
		textField_2.setBounds(39, 162, 103, 21);
		contentPane.add(textField_2);

		JLabel lblNewLabel_1 = new JLabel("\uC8FC\uBB38\uBC88\uD638");
		lblNewLabel_1.setFont(new Font("øœµµ»Ò∏¡√º Regular", Font.PLAIN, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(38, 64, 89, 15);
		contentPane.add(lblNewLabel_1);

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(SystemColor.controlHighlight);
		desktopPane.setBounds(30, 48, 574, 179);
		contentPane.add(desktopPane);

		textField_3 = new JTextField();
		textField_3.setFont(new Font("øœµµ»Ò∏¡√º Regular", Font.PLAIN, 15));
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setColumns(10);
		textField_3.setBounds(276, 114, 89, 22);
		desktopPane.add(textField_3);

		JLabel lblNewLabel_4 = new JLabel("\uACB0\uC81C\uAE08\uC561");
		lblNewLabel_4.setFont(new Font("øœµµ»Ò∏¡√º Regular", Font.PLAIN, 15));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(278, 89, 89, 15);
		desktopPane.add(lblNewLabel_4);

		textField_4 = new JTextField();
		textField_4.setFont(new Font("øœµµ»Ò∏¡√º Regular", Font.PLAIN, 15));
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setColumns(10);
		textField_4.setBounds(473, 114, 89, 22);
		desktopPane.add(textField_4);

		JLabel lblNewLabel_3_1 = new JLabel("\uAD6C\uB9E4\uCD1D\uC561");
		lblNewLabel_3_1.setFont(new Font("øœµµ»Ò∏¡√º Regular", Font.PLAIN, 15));
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setBounds(473, 89, 89, 15);
		desktopPane.add(lblNewLabel_3_1);

		JLabel lblNewLabel_2 = new JLabel("\uC0C1\uD488\uBA85");
		lblNewLabel_2.setFont(new Font("øœµµ»Ò∏¡√º Regular", Font.PLAIN, 15));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(12, 89, 89, 15);
		desktopPane.add(lblNewLabel_2);

		textField_5 = new JTextField();
		textField_5.setFont(new Font("øœµµ»Ò∏¡√º Regular", Font.PLAIN, 15));
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setColumns(10);
		textField_5.setBounds(124, 114, 142, 22);
		desktopPane.add(textField_5);

		JLabel lblNewLabel_3 = new JLabel("\uD310\uB9E4\uC790");
		lblNewLabel_3.setFont(new Font("øœµµ»Ò∏¡√º Regular", Font.PLAIN, 15));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(151, 89, 89, 15);
		desktopPane.add(lblNewLabel_3);

		textField_6 = new JTextField();
		textField_6.setFont(new Font("øœµµ»Ò∏¡√º Regular", Font.PLAIN, 15));
		textField_6.setHorizontalAlignment(SwingConstants.CENTER);
		textField_6.setColumns(10);
		textField_6.setBounds(377, 114, 77, 22);
		desktopPane.add(textField_6);

		JLabel lblNewLabel_3_1_1 = new JLabel("\uAD6C\uB9E4\uC218\uB7C9");
		lblNewLabel_3_1_1.setFont(new Font("øœµµ»Ò∏¡√º Regular", Font.PLAIN, 15));
		lblNewLabel_3_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1_1.setBounds(370, 89, 89, 15);
		desktopPane.add(lblNewLabel_3_1_1);

		JLabel lblNewLabel_3_1_1_1 = new JLabel("=");
		lblNewLabel_3_1_1_1.setFont(new Font("øœµµ»Ò∏¡√º Regular", Font.PLAIN, 15));
		lblNewLabel_3_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1_1_1.setBounds(418, 89, 89, 15);
		desktopPane.add(lblNewLabel_3_1_1_1);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("øœµµ»Ò∏¡√º Regular", Font.PLAIN, 15));
		textField_1.setBounds(97, 10, 163, 21);
		desktopPane.add(textField_1);
		textField_1.setColumns(10);

		seller_tel = new JTextField();
		seller_tel.setFont(new Font("øœµµ»Ò∏¡√º Regular", Font.PLAIN, 15));
		seller_tel.setHorizontalAlignment(SwingConstants.CENTER);
		seller_tel.setBounds(124, 136, 142, 21);
		desktopPane.add(seller_tel);
		seller_tel.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("\uD658\uBD88 \uC2E0\uCCAD\uC0AC\uC720");
		lblNewLabel_6.setFont(new Font("øœµµ»Ò∏¡√º Regular", Font.PLAIN, 15));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(39, 237, 89, 15);
		contentPane.add(lblNewLabel_6);

		spinner12 = new JSpinner();
		spinner12.setFont(new Font("øœµµ»Ò∏¡√º Regular", Font.PLAIN, 15));
		spinner12.setBounds(550, 315, 54, 22);
		contentPane.add(spinner12);

		JLabel lblNewLabel_5 = new JLabel("\uD658\uBD88\uC218\uB7C9");
		lblNewLabel_5.setFont(new Font("øœµµ»Ò∏¡√º Regular", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(533, 290, 89, 15);
		contentPane.add(lblNewLabel_5);
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblNewLabel_3_1_2 = new JLabel("\uD658\uBD88\uC608\uC815\uAE08\uC561");
		lblNewLabel_3_1_2.setFont(new Font("øœµµ»Ò∏¡√º Regular", Font.PLAIN, 15));
		lblNewLabel_3_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1_2.setBounds(515, 369, 107, 15);
		contentPane.add(lblNewLabel_3_1_2);

		tf_exmoney = new JTextField();
		tf_exmoney.setFont(new Font("øœµµ»Ò∏¡√º Regular", Font.PLAIN, 15));
		tf_exmoney.setHorizontalAlignment(SwingConstants.CENTER);
		tf_exmoney.setColumns(10);
		tf_exmoney.setBounds(515, 394, 89, 22);
		contentPane.add(tf_exmoney);

		exc_text = new JTextArea();
		exc_text.setBackground(Color.LIGHT_GRAY);
		exc_text.setBounds(30, 297, 466, 206);
		contentPane.add(exc_text);
		exc_req.setEnabled(false);
		setVisible(true);
	}
}
