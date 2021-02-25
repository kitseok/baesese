package customer;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import customer.cust_VO;
import customer.cust_model;

class insertPanel extends JFrame implements ActionListener {
   private JTextField tfIDinsert, tfPWinsert, tfNAMEinsert, tfBIRTHinsert, tfADDinsert, tfPHONEinsert,
         tfPHONECONinsert;

   static String randomcode;
   private JButton btnIDconfirm, btnPHONEconfirm, btnINSERT;
   private JPanel contentPane;

   cust_model model;

   insertPanel() {
      setTitle("ȸ������");

      JFrame frame = new JFrame();
      frame.setSize(500, 500);
      frame.setLocationRelativeTo(null);
//      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setLayout(null);

      frame.setResizable(false);
      frame.setVisible(true);

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 450, 490);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);

      JLabel isInsertLabel = new JLabel(" -- ȸ������ -- ");
      isInsertLabel.setBounds(162, 11, 93, 15); // ȸ������Label
      frame.getContentPane().add(isInsertLabel);

      JLabel idInsertLabel = new JLabel("���̵�");
      idInsertLabel.setBounds(42, 39, 42, 15); // ���̵�Label
      frame.getContentPane().add(idInsertLabel);

      tfIDinsert = new JTextField();
      tfIDinsert.setBounds(139, 36, 116, 21); // ���̵�tf
      frame.getContentPane().add(tfIDinsert);
      tfIDinsert.setColumns(10);

      btnIDconfirm = new JButton("�ߺ�Ȯ��");
      btnIDconfirm.setFont(new Font("����", Font.PLAIN, 11));
      btnIDconfirm.setBounds(281, 36, 78, 23);// ���̵��ߺ�Ȯ�ι�ư
      frame.getContentPane().add(btnIDconfirm);

//      JLabel idConfirmMessage = new JLabel("��� ������ ���̵��Դϴ�.");
//      idConfirmMessage.setEnabled(false);
//      idConfirmMessage.setForeground(Color.BLUE);
//      idConfirmMessage.setFont(new Font("����", Font.PLAIN, 10));
//      idConfirmMessage.setBounds(139, 62, 126, 15);// �ߺ�Ȯ�� �� label
//      frame.getContentPane().add(idConfirmMessage);

      JLabel pwInsertLabel = new JLabel("��й�ȣ");
      pwInsertLabel.setBounds(42, 90, 57, 15);// ��й�ȣlabel
      frame.getContentPane().add(pwInsertLabel);

      tfPWinsert = new JTextField();
      tfPWinsert.setBounds(139, 87, 116, 21);// ��й�ȣ tf
      frame.getContentPane().add(tfPWinsert);
      tfPWinsert.setColumns(10);

      JLabel nameInsertLabel = new JLabel("�̸�");
      nameInsertLabel.setBounds(42, 143, 57, 15);// �̸�label
      frame.getContentPane().add(nameInsertLabel);

      tfNAMEinsert = new JTextField();
      tfNAMEinsert.setBounds(139, 140, 116, 21);// �̸�tf
      frame.getContentPane().add(tfNAMEinsert);
      tfNAMEinsert.setColumns(10);

      JLabel birthInsertLabel = new JLabel("�������");
      birthInsertLabel.setBounds(42, 192, 63, 15);// ������� label
      frame.getContentPane().add(birthInsertLabel);

      tfBIRTHinsert = new JTextField();
      tfBIRTHinsert.setBounds(139, 189, 116, 21); // ������� tf
      frame.getContentPane().add(tfBIRTHinsert);
      tfBIRTHinsert.setColumns(10);

      JLabel addInsertLabel = new JLabel("�ּ�");
      addInsertLabel.setBounds(42, 240, 57, 15); // �ּ�label
      frame.getContentPane().add(addInsertLabel);

      tfADDinsert = new JTextField();
      tfADDinsert.setBounds(139, 237, 268, 21); // �ּ�tf
      frame.getContentPane().add(tfADDinsert);
      tfADDinsert.setColumns(10);

      JLabel phoneInsertLabel = new JLabel("��ȭ��ȣ");
      phoneInsertLabel.setBounds(42, 290, 57, 15); // ��ȭ��ȣ label
      frame.getContentPane().add(phoneInsertLabel);

      tfPHONEinsert = new JTextField();
      tfPHONEinsert.setBounds(139, 287, 138, 21); // ��ȭ��ȣtf
      frame.getContentPane().add(tfPHONEinsert);
      tfPHONEinsert.setColumns(10);
      
      btnPHONEconfirm = new JButton("�ڵ�ޱ�");
      btnPHONEconfirm.setBounds(298, 287, 138, 21);
      btnPHONEconfirm.setSize(79,22);
      btnPHONEconfirm.setFont(new Font("����", Font.PLAIN, 11));
      frame.getContentPane().add(btnPHONEconfirm);
      

      JLabel phoneConfirmInsertLabel = new JLabel("��ȭ��ȣ����");
      phoneConfirmInsertLabel.setBounds(42, 338, 78, 15);// ��ȭ��ȣ����label
      frame.getContentPane().add(phoneConfirmInsertLabel);

      tfPHONECONinsert = new JTextField();
      tfPHONECONinsert.setBounds(139, 335, 87, 21);// ��ȭ��ȣ����tf
      frame.getContentPane().add(tfPHONECONinsert);
      tfPHONECONinsert.setColumns(10);

      btnINSERT = new JButton("ȸ������");
      btnINSERT.setBounds(162, 389, 97, 23); // ȸ�����Թ�ư
      frame.getContentPane().add(btnINSERT);

      JLabel birthEx = new JLabel("ex) 950101");
      birthEx.setFont(new Font("����", Font.PLAIN, 10));
      birthEx.setBounds(42, 205, 57, 15); // ������Ͽ��� label
      frame.getContentPane().add(birthEx);
      
      try {
         model = new cust_model();
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      btnINSERT.addActionListener(this);
      btnIDconfirm.addActionListener(this);
      btnPHONEconfirm.addActionListener(this);
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub
      Object o = e.getSource();

      if (o == btnIDconfirm) {
         
         try {
            String idcon = null;
            idcon = model.idConfirm(tfIDinsert.getText());
            if (idcon.equals("")) {
               System.out.println(idcon);
               JOptionPane.showMessageDialog(null, "��� ������ ���̵��Դϴ�.");
            } else {
               JOptionPane.showMessageDialog(null, "�ߺ��� ���̵��Դϴ�.");
               tfIDinsert.setText("");
               
            }
         } catch (Exception e2) {
            // TODO Auto-generated catch block
            JOptionPane.showConfirmDialog(null, "���̵� �ߺ�Ȯ�� ����: " + e2.getMessage());
         }
      } else if (o == btnINSERT) {
         String id = tfIDinsert.getText();
         String pw = tfPWinsert.getText();
         String name = tfNAMEinsert.getText();
         String birth = tfBIRTHinsert.getText();
         String address = tfADDinsert.getText();
         String phone = tfPHONEinsert.getText();
         String phoneconfirm = null;
         if((tfPHONECONinsert.getText()).equals(randomcode)) {
            phoneconfirm = "Y";
         }else phoneconfirm = "N";
            
         

         cust_VO vo = new cust_VO(id, pw, name, birth, address, phone, phoneconfirm);
         
         try {
            model.insert(vo);
            JOptionPane.showMessageDialog(null, "ȸ�������� �Ϸ�Ǿ����ϴ�.");
            clear();
            dispose();
         } catch (Exception e1) {
            JOptionPane.showConfirmDialog(null, "ȸ������ ����: " + e1.getMessage());
         }
      }else if(o==btnPHONEconfirm) {
         
            randomcode = Integer.toString((int) (Math.random() * 10000));
            System.out.print(randomcode);
         
      }
   }

   void clear() {
      tfIDinsert.setText("");
      tfPWinsert.setText("");
      tfNAMEinsert.setText("");
      tfBIRTHinsert.setText("");
      tfADDinsert.setText("");
      tfPHONEinsert.setText("");
      tfPHONECONinsert.setText("");
   }
   
}