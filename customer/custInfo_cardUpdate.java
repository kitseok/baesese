package customer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import customer.custInfo_view.cardList;
import customer.mgr_exc_view.myListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Color;

public class custInfo_cardUpdate extends JFrame {

	private JPanel contentPane;
	private JTextField tfReAlias;
	private JLabel show, cardNumLabel, cardCompanyLabel, cardAlias, showcn, showCc ;
	private JButton btnUPDATE;
	
	custInfo_model model;
	
	String showCardNum ="";
	String showCardCompany="";
	
	int cus_no = 0;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					custInfo_cardUpdate frame = new custInfo_cardUpdate(cusno);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	public custInfo_cardUpdate(int cusno, String cardno) {
		try {
			model = new custInfo_model();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Ä«µå ¼öÁ¤ ÆÐ³Î È®ÀÎ ¾ÈµÊ");
		}
		this.cus_no = cusno;
		lay(cusno, cardno);
		eventProc();
		
	}

	/**
	 * Create the frame.
	 */
	public void lay(int cusno, String cardno) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 309, 298);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		try {
		showCardNum = cardno;
		showCardCompany = model.HIcardInfo(cardno);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		show = new JLabel("Ä«µå º°Äª ¼öÁ¤");
		show.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Bold", Font.PLAIN, 18));
		show.setBounds(96, 10, 109, 29);
		contentPane.add(show);
		
		System.out.println(showCardNum);
		System.out.println(showCardCompany);
		
		cardNumLabel = new JLabel("Ä«µå¹øÈ£");
		cardNumLabel.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Bold", Font.PLAIN, 15));
		cardNumLabel.setBounds(12, 63, 57, 15);
		contentPane.add(cardNumLabel);
		
		showcn = new JLabel(showCardNum);
		showcn.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 14));
		showcn.setBounds(96, 63, 159, 15);
		contentPane.add(showcn);
		
		cardCompanyLabel = new JLabel("Ä«µå»ç");
		cardCompanyLabel.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Bold", Font.PLAIN, 15));
		cardCompanyLabel.setBounds(12, 103, 57, 15);
		contentPane.add(cardCompanyLabel);
		
		showCc = new JLabel(showCardCompany);
		showCc.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 14));
		showCc.setBounds(96, 103, 159, 15);
		contentPane.add(showCc);
		
		cardAlias = new JLabel("Ä«µåº°Äª");
		cardAlias.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Bold", Font.PLAIN, 15));
		cardAlias.setBounds(12, 148, 57, 15);
		contentPane.add(cardAlias);
		
		tfReAlias = new JTextField();
		tfReAlias.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Regular", Font.PLAIN, 14));
		tfReAlias.setBounds(96, 145, 159, 21);
		contentPane.add(tfReAlias);
		tfReAlias.setColumns(10);
		
		btnUPDATE = new JButton("¼öÁ¤ÇÏ±â");
		btnUPDATE.setBackground(Color.WHITE);
		btnUPDATE.setFont(new Font("¿ÏµµÈñ¸ÁÃ¼ Bold", Font.PLAIN, 15));
		btnUPDATE.setBounds(96, 214, 97, 23);
		contentPane.add(btnUPDATE);
		
		setVisible(true);
	}
	

	
	public void eventProc() {
		 myListener et = new myListener();
		 btnUPDATE.addActionListener(et);
	}

	class myListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getActionCommand().equals(btnUPDATE.getText())) {
				try {
					System.out.println("³ª¿À´É°¡...");
					String realias = tfReAlias.getText();
					model.updateCusCard(showCardNum, realias);
					JOptionPane.showMessageDialog(null, "Ä«µå º°ÄªÀÌ ¾÷µ¥ÀÌÆ® µÇ¾ú½À´Ï´Ù.");
					custInfo_view.cardl.carddata = model.cardlist(cus_no);
					custInfo_view.cardl.fireTableDataChanged();
					dispose();
				}catch(Exception e3) {
					JOptionPane.showMessageDialog(null,"¾÷µ¥ÀÌÆ® ½ÇÆÐ"+ e3.getMessage());
				}
			}
		}
		
	}
}
