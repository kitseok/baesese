package customer;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class mgr_exc_view extends JFrame{

   private JLabel showNum, showName, showAmount, showReason, showDate, ltable, Num, Name, Amount, Date;
   private JTextField tfReason;
   private JButton btnY, btnN;
   private JPanel contentPane;
String id = null;
   ArrayList list;
   int exc;
   mgr_exc_model model;

   public mgr_exc_view(String mexc_num, String id) {
	   this.id = id;
      try {
         model = new mgr_exc_model();
      } catch (Exception e) {
         e.printStackTrace();
      }

      lay_(mexc_num);
      eventProc();

   }

   public void lay_(String mexc_num) {
      // setLayout(null);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setBounds(100, 100, 400, 400);
      contentPane = new JPanel();
      // contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
      setContentPane(contentPane);

      try {
         list = model.showgoods(mexc_num);
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      contentPane.setLayout(null);

      showNum = new JLabel("교환/환불 신청번호 | ");
      showNum.setLocation(46, 35);
      // showNum.setBounds(50, 50, 60, 30);
      showNum.setFont(new Font("G마켓 산스 TTF Medium", Font.PLAIN, 15));
      showNum.setSize(136, 18);
      contentPane.add(showNum);

      Num = new JLabel();
      Num.setLocation(184, 35);
      Num.setText(list.get(0).toString());
      // Num.setBounds(180, 50, 60, 30);
      Num.setFont(new Font("G마켓 산스 TTF Bold", Font.PLAIN, 15));
      Num.setSize(91, 18);
      contentPane.add(Num);

      showName = new JLabel("상품명 | ");
      showName.setLocation(46, 63);
      // showName.setBounds(50, 90, 60, 30);
      showName.setFont(new Font("G마켓 산스 TTF Medium", Font.PLAIN, 15));
      showName.setSize(54, 18);
      contentPane.add(showName);

      Name = new JLabel();
      Name.setLocation(100, 63);
      Name.setText(list.get(1).toString());
      // Name.setBounds(110, 90, 60, 30);
      Name.setFont(new Font("G마켓 산스 TTF Bold", Font.PLAIN, 15));
      Name.setSize(175, 18);
      contentPane.add(Name);

      showAmount = new JLabel("금액 | ");
      showAmount.setLocation(46, 91);
      // showAmount.setBounds(50, 130, 60, 30);
      showAmount.setFont(new Font("G마켓 산스 TTF Medium", Font.PLAIN, 15));
      showAmount.setSize(40, 18);
      contentPane.add(showAmount);

      Amount = new JLabel();
      Amount.setLocation(86, 91);
      Amount.setText(list.get(2).toString());
      // Amount.setBounds(95, 130, 60, 30);
      Amount.setFont(new Font("G마켓 산스 TTF Bold", Font.PLAIN, 15));
      Amount.setSize(136, 18);
      contentPane.add(Amount);

      showDate = new JLabel("요청일 | ");
      showDate.setLocation(46, 119);
      // showDate.setBounds(50, 170, 60, 30);
      showDate.setFont(new Font("G마켓 산스 TTF Medium", Font.PLAIN, 15));
      showDate.setSize(54, 18);
      contentPane.add(showDate);

      Date = new JLabel();
      Date.setLocation(100, 119);
      Date.setText(list.get(4).toString());
      // Date.setBounds(105, 170, 60, 30);
      Date.setFont(new Font("G마켓 산스 TTF Bold", Font.PLAIN, 14));
      Date.setSize(206, 18);
      contentPane.add(Date);

      showReason = new JLabel("사유");
      showReason.setLocation(46, 147);
      // showReason.setBounds(50, 210, 60, 30);
      showReason.setFont(new Font("G마켓 산스 TTF Medium", Font.PLAIN, 15));
      showReason.setSize(28, 18);
      contentPane.add(showReason);

      tfReason = new JTextField();
      tfReason.setBounds(46, 175, 291, 88);
      tfReason.setText(list.get(3).toString());
      // tfReason.setBounds(50, 240, 364, 150);
      tfReason.setFont(new Font("G마켓 산스 TTF Bold", Font.PLAIN, 11));
      contentPane.add(tfReason);

      btnY = new JButton("승인");
      btnY.setLocation(121, 295);
      // btnY.setBounds(140, 405, 90, 40);
      btnY.setSize(63, 37);
      btnY.setFont(new Font("G마켓 산스 TTF Bold", Font.PLAIN, 15));
      contentPane.add(btnY);

      btnN = new JButton("반려");
      btnN.setLocation(196, 295);
      // btnN.setBounds(260, 405, 90, 40);
      btnN.setSize(63, 37);
      btnN.setFont(new Font("G마켓 산스 TTF Bold", Font.PLAIN, 15));
      contentPane.add(btnN);

      String ok = "Y";
      String nok = "N";
      exc = Integer.parseInt(Num.getText());

      try {
         if (ok.equals(model.confirmOK(exc))) {
            btnY.setEnabled(false);
            btnN.setEnabled(false);
            JOptionPane.showMessageDialog(null, "이미 완료된 건입니다.");
         } else if (nok.equals(model.confirmOK(exc))) {
            btnN.setEnabled(false);
            btnY.setEnabled(false);
            JOptionPane.showMessageDialog(null, "이미 완료된 건입니다.");
         }
         
      } catch (HeadlessException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      setVisible(true);
   }

   public void eventProc() {
      myListener et = new myListener();
      btnY.addActionListener(et);
      btnN.addActionListener(et);
   }

   class myListener implements ActionListener {

      @SuppressWarnings("deprecation")
      @Override
      public void actionPerformed(ActionEvent e) {
         // TODO Auto-generated method stub

         // int exc = Integer.parseInt(Num.getText());
         // System.out.println(exc);

         if (e.getSource() == btnY) {
            try {
               model.click_Y(exc, id);
               JOptionPane.showMessageDialog(null, "교환/환불 승인되었습니다.");
               mgr_view.excl.excdata = model.recentList();
               mgr_view.excl.fireTableDataChanged();
            } catch (Exception e1) {
               JOptionPane.showMessageDialog(null, "처리 승인 실패: " + e1.getMessage());
               e1.printStackTrace();
            }
         }

         else if (e.getSource() == btnN) {
            try {
               model.click_N(exc, id);
               JOptionPane.showMessageDialog(null, "교환/환불 반려되었습니다.");
               mgr_view.excl.excdata = model.recentList();
               mgr_view.excl.fireTableDataChanged();
            } catch (Exception e2) {
               JOptionPane.showMessageDialog(null, "처리 승인 실패: " + e2.getMessage());
            }
         }

         dispose();
         //mgr_view.excl.fireTableDataChanged();
      }
   }

}