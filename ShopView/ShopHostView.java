package ShopView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

import ShopModel.ShopHostModel;
import javax.swing.JDesktopPane;
import java.awt.SystemColor;

public class ShopHostView extends JPanel {
	private JTextField tfShopNo;
	private JTextField tfShopName;
	private JTextField tfphone;
	private JTextField tfShopSign;
	private JTextField tfopentime;
	private JTextField tfclosetime;
	private JTextField tfstartdate;
	private JTextField tfaccountno;
	private JTextField tfShopHost;
	private JButton bthostupdate;
	ShopHostModel model;
	private JDesktopPane desktopPane;

	public ShopHostView(String shopNo) throws Exception {
		setLayout(null);
		model = new ShopHostModel();
		addlay(shopNo);
		eventProc();
		showvalues();

		// showvalues();

	}

	private void addlay(String shopNo) {
		JLabel lblNewLabel = new JLabel("No.");
		lblNewLabel.setBounds(61, 35, 42, 15);
		add(lblNewLabel);

		tfShopNo = new JTextField(shopNo);
		tfShopNo.setEditable(false);
		tfShopNo.setBounds(99, 32, 61, 21);
		add(tfShopNo);
		tfShopNo.setColumns(10);

		tfShopName = new JTextField();
		tfShopName.setHorizontalAlignment(SwingConstants.CENTER);
		tfShopName.setFont(new Font("³ª´®¼Õ±Û¾¾ Ææ", Font.PLAIN, 45));
		tfShopName.setForeground(Color.BLACK);
		tfShopName.setBackground(Color.ORANGE);
		tfShopName.setColumns(10);
		tfShopName.setBounds(182, 32, 402, 58);
		add(tfShopName);

		JLabel lblNewLabel_1_2 = new JLabel("\uC774\uB984 :");
		lblNewLabel_1_2.setBounds(61, 66, 42, 15);
		add(lblNewLabel_1_2);

		tfShopHost = new JTextField();
		tfShopHost.setColumns(10);
		tfShopHost.setBounds(99, 62, 61, 21);
		add(tfShopHost);

		desktopPane = new JDesktopPane();
		desktopPane.setBackground(SystemColor.window);
		desktopPane.setBounds(61, 130, 650, 244);
		add(desktopPane);

		tfphone = new JTextField();
		tfphone.setBounds(151, 105, 116, 23);
		desktopPane.add(tfphone);
		tfphone.setHorizontalAlignment(SwingConstants.CENTER);
		tfphone.setColumns(10);

		tfShopSign = new JTextField();
		tfShopSign.setBounds(151, 44, 116, 21);
		desktopPane.add(tfShopSign);
		tfShopSign.setHorizontalAlignment(SwingConstants.CENTER);
		tfShopSign.setColumns(10);

		tfopentime = new JTextField();
		tfopentime.setBounds(466, 44, 77, 23);
		desktopPane.add(tfopentime);
		tfopentime.setHorizontalAlignment(SwingConstants.CENTER);
		tfopentime.setColumns(10);

		tfclosetime = new JTextField();
		tfclosetime.setBounds(466, 108, 77, 23);
		desktopPane.add(tfclosetime);
		tfclosetime.setHorizontalAlignment(SwingConstants.CENTER);
		tfclosetime.setColumns(10);

		tfstartdate = new JTextField();
		tfstartdate.setBounds(466, 171, 77, 23);
		desktopPane.add(tfstartdate);
		tfstartdate.setHorizontalAlignment(SwingConstants.CENTER);
		tfstartdate.setColumns(10);

		tfaccountno = new JTextField();
		tfaccountno.setBounds(151, 169, 116, 23);
		desktopPane.add(tfaccountno);
		tfaccountno.setHorizontalAlignment(SwingConstants.CENTER);
		tfaccountno.setColumns(10);

		JLabel lblNewLabel_1_3_1 = new JLabel("\uC704\uCE58 :");
		lblNewLabel_1_3_1.setBounds(71, 41, 77, 19);
		desktopPane.add(lblNewLabel_1_3_1);
		lblNewLabel_1_3_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));

		JLabel lblNewLabel_1_3_1_1 = new JLabel("\uC5F0\uB77D\uCC98 :");
		lblNewLabel_1_3_1_1.setBounds(71, 103, 77, 19);
		desktopPane.add(lblNewLabel_1_3_1_1);
		lblNewLabel_1_3_1_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));

		JLabel lblNewLabel_1_3_1_2 = new JLabel("\uACC4\uC88C\uBC88\uD638 :");
		lblNewLabel_1_3_1_2.setBounds(71, 169, 77, 19);
		desktopPane.add(lblNewLabel_1_3_1_2);
		lblNewLabel_1_3_1_2.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));

		JLabel lblNewLabel_1_3_1_3 = new JLabel("\uAC1C\uC5C5\uC77C :");
		lblNewLabel_1_3_1_3.setBounds(391, 171, 77, 19);
		desktopPane.add(lblNewLabel_1_3_1_3);
		lblNewLabel_1_3_1_3.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));

		JLabel lblNewLabel_1_3_1_4 = new JLabel("\uC601\uC5C5\uC2DC\uC791 :");
		lblNewLabel_1_3_1_4.setBounds(391, 44, 77, 19);
		desktopPane.add(lblNewLabel_1_3_1_4);
		lblNewLabel_1_3_1_4.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));

		JLabel lblNewLabel_1_3_1_5 = new JLabel("\uC601\uC5C5\uC885\uB8CC :");
		lblNewLabel_1_3_1_5.setBounds(391, 113, 77, 19);
		desktopPane.add(lblNewLabel_1_3_1_5);
		lblNewLabel_1_3_1_5.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));

		bthostupdate = new JButton("\uC815\uBCF4\uC218\uC815");
		bthostupdate.setFont(new Font("³ª´®¼Õ±Û¾¾ Ææ", Font.PLAIN, 26));
		bthostupdate.setBounds(606, 32, 98, 58);
		add(bthostupdate);
	}

	public void eventProc() { // ÀÌº¥Æ® ºÙÀÌ±â
		MyListener evt = new MyListener();
		bthostupdate.addActionListener(evt);
	}

	class MyListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == bthostupdate) {

				System.out.println("¹öÆ°Å¬¸¯µÊ");
				String shopsignno = tfShopSign.getText();
				String shopphone = tfphone.getText();
				String shopname = tfShopName.getText();
				String opentime = tfopentime.getText();
				String startdate = tfstartdate.getText();
				String closetime = tfclosetime.getText();
				String accountno = tfaccountno.getText();
				String hostname = tfShopHost.getText();
				int shopno = Integer.parseInt(tfShopNo.getText());

				try {
					model.hostupdate(shopsignno, shopphone, shopname, opentime, startdate, closetime, accountno,
							hostname, shopno);
					System.out.println("Á¤º¸ ¼öÁ¤ ¿Ï·á");
					JOptionPane.showMessageDialog(null, "Á¤º¸ ¼öÁ¤ ¿Ï·á");

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}
	}

	public void showvalues() throws NumberFormatException, SQLException {
		ArrayList al = model.selectHostName(Integer.parseInt(tfShopNo.getText()));
		tfShopHost.setText(al.get(0).toString());
		tfShopSign.setText(al.get(1).toString());
		tfphone.setText(al.get(2).toString());
		tfShopName.setText(al.get(3).toString());
		tfopentime.setText(al.get(4).toString());
		tfclosetime.setText(al.get(5).toString());
		String str = al.get(6).toString();
		int idx = str.indexOf(" ");
		tfstartdate.setText(str.substring(0, idx));
		tfaccountno.setText(al.get(7).toString());
	}

}
