package customer;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import customer.custInfo_addInsert.myListener;

public class custInfo_addUpdate extends JFrame {

	private JPanel contentPane;
	private JLabel showADDINFO, addressLabel, addressNameLabel, addressPhoneLabel;
	private JButton btnAddUpdate;
	int addNum = 0;
	int customerNo = 0;
	custInfo_model model;
	private JTextField tfAddress;
	private JTextField tfName;
	private JTextField tfPhone;
	
	public custInfo_addUpdate(int add_cusno, String add_num) {
		this.addNum=Integer.parseInt(add_num);
		this.customerNo = add_cusno;
		try {
			model = new custInfo_model();
		}catch(Exception e) {
			System.out.println("ī���߰� �г� Ȯ�� �ȵ�"+e.getMessage());
		}
		lllay();
		eventProc();
	}
	
	public void lllay() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setSize(384,335);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		showADDINFO = new JLabel("����� ����");
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
		
		btnAddUpdate = new JButton("����");
		btnAddUpdate.setFont(new Font("�ϵ����ü Bold", Font.PLAIN, 16));
		btnAddUpdate.setBounds(141, 241, 79, 29);
		contentPane.add(btnAddUpdate);
		
		setVisible(true);
		
		
	}
	
	public void eventProc() {
		myListener et = new myListener();
		btnAddUpdate.addActionListener(et);
	}
	
	class myListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == btnAddUpdate) {
				try {
					String address = tfAddress.getText();
					String name = tfName.getText();
					String phone = tfPhone.getText();
					model.CusAddUpdate(addNum, address, name, phone);
					JOptionPane.showMessageDialog(null, "������� �����Ǿ����ϴ�.");
					custInfo_view.addl.addressdata = model.addlist(customerNo);
					custInfo_view.addl.fireTableDataChanged();
					dispose();
				}catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, "����� ���� ����: "+e1.getMessage());
				}
			}
		}
		
	}
}
