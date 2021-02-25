package customer;

import java.awt.BorderLayout;
//import java.applet.*;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ShopView.Shop;
import categoryView.category_dae;
import customer.cust_VO;
import customer.cust_model;
import customer.custInfo;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import java.awt.TextComponent;
import java.awt.TextField;

public class cust_view extends JFrame implements ActionListener {

   cust_model model = null;
   // Shop shop = new Shop();
   private TextField tfIDinput, tfPWinput;
   private JButton btnLoginButton, btnInsertButton, btnIDconfirm, btnPHONEconfirm, btnINSERT;
   private JPanel contentPane;
   private JRadioButton cusRadioBtn, shopRadioBtn, mgrRadioBtn;
   public String shop_no, cus_name;
   JLabel lb_title;
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               cust_view frame = new cust_view();
               frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   /**
    * Create the frame.
    */
   public cust_view() {
	   setTitle("�α���");
   	getContentPane().setBackground(new Color(255, 255, 255));

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(720, 380, 470, 320);
      getContentPane().setLayout(null);

      btnLoginButton = new JButton("�α���");
      btnLoginButton.setBackground(new Color(255, 255, 255));
      btnLoginButton.setFont(new Font("�ϵ����ü Regular", Font.PLAIN, 16));
      btnLoginButton.setBounds(120, 194, 97, 23);
      getContentPane().add(btnLoginButton);

      tfIDinput = new TextField("happy0105");
      tfIDinput.setFont(new Font("�ϵ����ü Regular", Font.PLAIN, 12));
      tfIDinput.setBounds(189, 103, 137, 21);
      getContentPane().add(tfIDinput);
      tfIDinput.setColumns(10);

      tfPWinput = new TextField("imhappy!!");
      tfPWinput.setBounds(189, 134, 137, 21);
      getContentPane().add(tfPWinput);
      tfPWinput.setEchoChar('*');
      tfPWinput.setColumns(10);
      tfPWinput.addKeyListener(new KeyListener() {
  		public void keyReleased(KeyEvent e) {
  			if(e.getKeyCode() == KeyEvent.VK_ENTER)
  			{
  				System.out.println("Ŭ��");
  				btnLoginButton.doClick();
  			}}
		public void keyTyped(KeyEvent e) {}
		public void keyPressed(KeyEvent e) {}
		});

      JLabel lblNewLabel_id = new JLabel("���̵�");
      lblNewLabel_id.setFont(new Font("�ϵ����ü Regular", Font.PLAIN, 17));
      lblNewLabel_id.setBounds(122, 106, 57, 15);
      getContentPane().add(lblNewLabel_id);

      JLabel lblNewLabel_pw = new JLabel("��й�ȣ");
      lblNewLabel_pw.setFont(new Font("�ϵ����ü Regular", Font.PLAIN, 17));
      lblNewLabel_pw.setBounds(114, 136, 71, 15);
      getContentPane().add(lblNewLabel_pw);

      btnInsertButton = new JButton("ȸ������");
      btnInsertButton.setBackground(new Color(255, 255, 255));
      btnInsertButton.setFont(new Font("�ϵ����ü Regular", Font.PLAIN, 16));
      btnInsertButton.setBounds(229, 194, 97, 23);
      getContentPane().add(btnInsertButton);

      cusRadioBtn = new JRadioButton("�� �α���");
      cusRadioBtn.setFont(new Font("�ϵ����ü Regular", Font.PLAIN, 15));
      cusRadioBtn.setBackground(new Color(255, 255, 255));
      cusRadioBtn.setBounds(103, 165, 97, 23);
      cusRadioBtn.setSelected(true);
      getContentPane().add(cusRadioBtn);

      shopRadioBtn = new JRadioButton("���� �α���");
      shopRadioBtn.setFont(new Font("�ϵ����ü Regular", Font.PLAIN, 15));
      shopRadioBtn.setBackground(new Color(255, 255, 255));
      shopRadioBtn.setBounds(199, 165, 97, 23);
      getContentPane().add(shopRadioBtn);

      mgrRadioBtn = new JRadioButton("M");
      mgrRadioBtn.setBackground(new Color(255, 255, 255));
      mgrRadioBtn.setFont(new Font("�ϵ����ü Regular", Font.PLAIN, 17));
      mgrRadioBtn.setBounds(294, 165, 48, 23);
      getContentPane().add(mgrRadioBtn);
      
      ButtonGroup group = new ButtonGroup();
      group.add(cusRadioBtn);
      group.add(shopRadioBtn);
      group.add(mgrRadioBtn);
      
      
      JLabel lblNewLabel = new JLabel("\uBC30\uC2DC\uC2DC");
      lblNewLabel.setFont(new Font("�ϵ����ü Bold", Font.BOLD, 31));
      lblNewLabel.setBounds(185, 32, 102, 44);
      getContentPane().add(lblNewLabel);
      
      lb_title = new JLabel("");
      lb_title.setBounds(-12, 0, 513, 281);
      getContentPane().add(lb_title);
      
//      JLabel lblNewLabel = new JLabel("New label");
//      lblNewLabel.setFont(new Font("����", Font.PLAIN, 17));
//      lblNewLabel.setBounds(29, 10, 57, 15);
//      getContentPane().add(lblNewLabel);

      try {
         model = new cust_model();
         System.out.println("�� DB ���� ����");
      } catch (Exception e) {
         JOptionPane.showConfirmDialog(null, "DB���� ���� : " + e.getMessage());
      }
      btnInsertButton.addActionListener(this);
      btnLoginButton.addActionListener(this);
		try {
			URL url;
			Image image;
			Image chimg;
			url = new URL(model.ShowTitle("�α���"));
			image = ImageIO.read(url);
			chimg = image.getScaledInstance(470, 320, Image.SCALE_SMOOTH);
			lb_title.setIcon(new ImageIcon(chimg));
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub
      Object o = e.getSource();
      if (o == btnInsertButton) {
         new insertPanel();

         // ��ȭ��ȣ����
      } else if (o == btnLoginButton) {
         try {
            String id = tfIDinput.getText();
            String pw = tfPWinput.getText();
            if (cusRadioBtn.isSelected()) {
               cus_name = model.cuslogin(id, pw);
               if (cus_name.equals("")) {
                  JOptionPane.showMessageDialog(null, "���̵� �Ǵ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
                  tfIDinput.setText("");
                  tfPWinput.setText("");
               } else {
                  JOptionPane.showMessageDialog(null, cus_name + "�� �ݰ����ϴ�. ");
                  //custInfo cv = new custInfo(id);
                  new category_dae(id);
                  dispose();
                  
               }
            } else if (shopRadioBtn.isSelected()) {

               shop_no = model.shoplogin(id, pw);
               if (shop_no.equals("")) {
                  JOptionPane.showMessageDialog(null, "���̵� �Ǵ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
                  tfIDinput.setText("");
                  tfPWinput.setText("");
               } else {
                  JOptionPane.showMessageDialog(null, "�������� �α��� �Ǿ����ϴ�.");
                   new Shop(id);
                   dispose();
               }
            }else if(mgrRadioBtn.isSelected()) {
                String mgr_name = model.mgrlogin(id,pw);
                if(mgr_name.equals("")) {
                   JOptionPane.showMessageDialog(null, "���̵� �Ǵ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
                   tfIDinput.setText("");
                   tfPWinput.setText("");
                }else {
                   JOptionPane.showMessageDialog(null, "������ "+mgr_name+" �α��� �Ǿ����ϴ�.");
                   mgr m = new mgr(id);
                   dispose();
                }
             }

         } catch (Exception e3) {
            JOptionPane.showConfirmDialog(null, "�α��� ���� : " + e3.getMessage());
            e3.printStackTrace();
         }
      }
      
   }
}