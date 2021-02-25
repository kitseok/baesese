package customer;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import customer.custInfo_view.cardList;
import customer.mgr_order_delivery_view.myListener;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class custInfo_cardInsert extends JFrame {
	private JTextField tfCardNumber;
	private JTextField tfCardAlias;
	private JLabel INSERTlabel,cardNumberLabel,cardAliasLabel,cardCompanyLabel;
	private JComboBox cardComboBox;
	private JButton btnCardInsert;
	private JPanel contentPane;

	//cardList clist;

	int cus_no = 0;
	cardList cardl;
	custInfo_model model;
	/**
	 * Create the panel.
	 * @throws Exception 
	 */
	
	private void addValues() throws Exception {
		try {
			System.out.println("나오라!ㅏ!");
			ArrayList<String> cl = model.selectCardCompany();
			for(String str : cl) {
				cardComboBox.addItem(str);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public custInfo_cardInsert(int cusno) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBackground(Color.LIGHT_GRAY);
		getContentPane().setLayout(null);
		setSize(355,322);
		
		
		
		//clist = new cardList();
		this.cus_no = cusno;
		contentPane = new JPanel();
		setContentPane(contentPane);
		try {
			model = new custInfo_model();
		}catch(Exception e) {
			System.out.println("카드추가 패널 확인 안됨"+e.getMessage());
		}
		contentPane.setLayout(null);
		
		INSERTlabel = new JLabel("카드 추가");
		INSERTlabel.setFont(new Font("완도희망체 Regular", Font.PLAIN, 18));
		INSERTlabel.setLocation(138,30);
		INSERTlabel.setBounds(138, 30, 77, 21);
		contentPane.add(INSERTlabel);
		
		tfCardNumber = new JTextField();
		tfCardNumber.setSize(0, 0);
		tfCardNumber.setLocation(115,81);
		tfCardNumber.setBounds(115, 81, 182, 21);
		contentPane.add(tfCardNumber);
		tfCardNumber.setColumns(10);
		
		tfCardAlias = new JTextField();
		tfCardAlias.setBounds(115, 126, 182, 21);
		contentPane.add(tfCardAlias);
		tfCardAlias.setColumns(10);
		
		cardNumberLabel = new JLabel("카드번호");
		cardNumberLabel.setFont(new Font("완도희망체 Regular", Font.PLAIN, 14));
		cardNumberLabel.setBounds(33, 84, 57, 15);
		contentPane.add(cardNumberLabel);
		
		cardAliasLabel = new JLabel("카드별칭");
		cardAliasLabel.setFont(new Font("완도희망체 Regular", Font.PLAIN, 14));
		cardAliasLabel.setBounds(33, 129, 57, 15);
		contentPane.add(cardAliasLabel);
		
		cardComboBox = new JComboBox();
		cardComboBox.setBounds(138, 175, 134, 23);
		contentPane.add(cardComboBox);
		
		cardCompanyLabel = new JLabel("카드종류");
		cardCompanyLabel.setFont(new Font("완도희망체 Regular", Font.PLAIN, 14));
		cardCompanyLabel.setBounds(33, 179, 57, 15);
		contentPane.add(cardCompanyLabel);
		
		btnCardInsert = new JButton("카드 추가하기");
		btnCardInsert.setBackground(Color.WHITE);
		btnCardInsert.setFont(new Font("완도희망체 Regular", Font.PLAIN, 15));
		btnCardInsert.setBounds(103, 237, 128, 23);
		contentPane.add(btnCardInsert);
		
		setVisible(true);
		
		try {
			addValues();
			eventProc();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	
	public void eventProc() {
		myListener et = new myListener();
		btnCardInsert.addActionListener(et);
		cardComboBox.addActionListener(et);
		
	}
	
	class myListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == btnCardInsert) {
				try {
					String cardNum = tfCardNumber.getText();
					String cardAli = tfCardAlias.getText();
					int cardCom = model.cardComNo(cardComboBox.getSelectedItem().toString());
					System.out.println(cardNum);
					System.out.println(cardAli);
					System.out.println(cardCom);
					model.insertCusCard(cardNum,cardAli,cardCom,cus_no);
					JOptionPane.showMessageDialog(null, "카드가 추가되었습니다.");
					custInfo_view.cardl.carddata = model.cardlist(cus_no);
					custInfo_view.cardl.fireTableDataChanged();
					dispose();
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "카드 추가 실패"+e1.getMessage());
				}
			}
		}
		
	}
}
