package customer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import customer.custInfo_cardInsert.myListener;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class custInfo_addInsert extends JFrame {

	private JPanel contentPane;
	private JLabel showADDINFO, addressLabel, addressNameLabel, addressPhoneLabel;
	private JButton btnAddInsert;
	int cusno = 0;
	custInfo_model model;
	private JTextField tfAddress;
	private JTextField tfName;
	private JTextField tfPhone;
	
	public custInfo_addInsert(int cusno) {
		this.cusno=cusno;
		try {
			model = new custInfo_model();
		}catch(Exception e) {
			System.out.println("ī���߰� �г� Ȯ�� �ȵ�"+e.getMessage());
		}
		llay();
		eventProc();
	}
	
	public void llay() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setSize(384,335);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		showADDINFO = new JLabel("����� �߰�");
		showADDINFO.setFont(new Font("�ϵ����ü Bold", Font.PLAIN, 18));
		showADDINFO.setBounds(141, 18, 91, 29);
		contentPane.add(showADDINFO);
		
		addressLabel = new JLabel("�����");
		addressLabel.setFont(new Font("�ϵ����ü Bold", Font.PLAIN, 14));
		addressLabel.setBounds(39, 68, 42, 15);
		contentPane.add(addressLabel);
		
		addressNameLabel = new JLabel("������");
		addressNameLabel.setFont(new Font("�ϵ����ü Bold", Font.PLAIN, 14));
		addressNameLabel.setBounds(39, 116, 42, 15);
		contentPane.add(addressNameLabel);
		
		addressPhoneLabel = new JLabel("������ ��ȭ��ȣ");
		addressPhoneLabel.setFont(new Font("�ϵ����ü Bold", Font.PLAIN, 14));
		addressPhoneLabel.setBounds(12, 170, 91, 15);
		contentPane.add(addressPhoneLabel);
		
		tfAddress = new JTextField();
		tfAddress.setFont(new Font("�ϵ����ü Regular", Font.PLAIN, 13));
		tfAddress.setBounds(108, 65, 234, 21);
		contentPane.add(tfAddress);
		tfAddress.setColumns(10);
		
		tfName = new JTextField();
		tfName.setFont(new Font("�ϵ����ü Regular", Font.PLAIN, 13));
		tfName.setBounds(108, 113, 129, 21);
		contentPane.add(tfName);
		tfName.setColumns(10);
		
		tfPhone = new JTextField();
		tfPhone.setFont(new Font("�ϵ����ü Regular", Font.PLAIN, 13));
		tfPhone.setBounds(108, 167, 129, 21);
		contentPane.add(tfPhone);
		tfPhone.setColumns(10);
		
		btnAddInsert = new JButton("\uCD94\uAC00");
		btnAddInsert.setFont(new Font("�ϵ����ü Bold", Font.PLAIN, 16));
		btnAddInsert.setBounds(141, 241, 79, 29);
		contentPane.add(btnAddInsert);
		
		setVisible(true);
		
		
	}
	
	public void eventProc() {
		myListener et = new myListener();
		btnAddInsert.addActionListener(et);
	}
	
	class myListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == btnAddInsert) {
				try {
					String address = tfAddress.getText();
					String name = tfName.getText();
					String phone = tfPhone.getText();
					model.CusAddInsert(cusno, address, name, phone);
					JOptionPane.showMessageDialog(null, "������� �߰��Ǿ����ϴ�.");
					custInfo_view.addl.addressdata = model.addlist(cusno);
					custInfo_view.addl.fireTableDataChanged();
					dispose();
				}catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, "����� �߰� ����: "+e1.getMessage());
				}
			}
		}
		
	}
}
