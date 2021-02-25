package customer;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;


public class mgr_view extends JPanel {

   private JLabel showmgr;
   private JButton btnY, btnN, btnL, btnAll;
   private JTable excTable;
   public JScrollPane scroExc;
   private TableColumnModel exccolumnModel;
   String id = null;
   mgr_model model;

   public static excList excl;

   public mgr_view(String id) {
this.id = id;
      try {
         model = new mgr_model();

      } catch (Exception e) {
         e.printStackTrace();
      }

      lay();
      eventProc();

      // addValues();
      // selectTable();

   }

   private void addValues() {
      // try {
      // excl.exctdata = model.recentList();
      // }catch(SQLException e) {
      // e.printStackTrace();
      // }
   }

   public void lay() {
      setLayout(null);

      showmgr = new JLabel("교환/환불 상세 관리");
      showmgr.setBounds(279, 30, 60, 15);
      showmgr.setFont(new Font("G마켓 산스 TTF Bold", Font.BOLD, 22));
      showmgr.setSize(200, 30);
      add(showmgr);

//      exccolumnModel = excTable.getColumnModel();
//      exccolumnModel.getColumn(0).setPreferredWidth(10);
//      exccolumnModel.getColumn(1).setPreferredWidth(15);
//      exccolumnModel.getColumn(2).setPreferredWidth(10);
//      exccolumnModel.getColumn(4).setPreferredWidth(10);
//      exccolumnModel.getColumn(6).setPreferredWidth(30);
//      exccolumnModel.getColumn(8).setPreferredWidth(10);

      excl = new excList();
      // ArrayList list =

      try {
         excl.excdata = model.recentList();
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      excTable = new JTable(excl);
      scroExc = new JScrollPane(excTable);
      scroExc.setBounds(25, 87, 700, 300);
      // scroExc.setVisible(true);
      add(scroExc);

      btnY = new JButton("승인된 건");
      btnY.setBounds(95, 395, 100, 30);
      add(btnY);

      btnL = new JButton("처리중인 건");
      btnL.setBounds(239, 395, 100, 30);
      add(btnL);

      btnN = new JButton("반려된 건");
      btnN.setBounds(390, 395, 100, 30);
      add(btnN);
      
      btnAll = new JButton("모두보기");
      btnAll.setBounds(544, 395, 100, 30);
      add(btnAll);
   }

   class excList extends AbstractTableModel {

      ArrayList excdata = new ArrayList();
      String[] title = { "no", "교환/환불", "번호", "상품", "사유", "수량", "요청일", "금액", "승인날짜", "승인여부" };
      // DefaultTableModel dmodel = new DefaultTableModel();

      @Override
      public int getRowCount() {
         // TODO Auto-generated method stub
         return excdata.size();
      }

      @Override
      public int getColumnCount() {
         // TODO Auto-generated method stub
         return title.length;
      }

      @Override
      public Object getValueAt(int row, int col) {
         // TODO Auto-generated method stub
         ArrayList tmp = (ArrayList) excdata.get(row);
         // dmodel.addRow(title);
         return tmp.get(col);
      }

      public String getColumnName(int col) {
         return title[col];
      }

   }

   public void selectTable() {
      try {
         ArrayList list = model.recentList();

         excl.excdata = list;
         excl.fireTableDataChanged();
      } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, "테이블 출력 실패 : " + ex.getMessage());
      }
   }

   public void eventProc() {
      myListener et = new myListener();
      btnY.addActionListener(et);
      btnN.addActionListener(et);
      btnL.addActionListener(et);
      btnAll.addActionListener(et);

      excTable.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            int row = excTable.getSelectedRow();
            System.out.println(row);
            try {
               String mexc_num = String.valueOf(excTable.getValueAt(row, 2));

               // excTable.setToolTipText(mexc_num);
               new mgr_exc_view(mexc_num, id);
               
            } catch (Exception e3) {
               e3.printStackTrace();
            }

         }
      });

   }

   class myListener implements ActionListener {

      @Override
      public void actionPerformed(ActionEvent e) {

         // TODO Auto-generated method stub

         if (e.getSource() == btnY) {
            try {
               excl.excdata = model.clickY();
               excl.fireTableDataChanged();
               //selectTable();
            } catch (Exception e1) {
               JOptionPane.showMessageDialog(null, "테이블 출력 실패:  " + e1.getMessage());
            }
         }else if(e.getSource() == btnL) {
            try {
               excl.excdata = model.clickL();
               
               excl.fireTableDataChanged();
               //selectTable();
            } catch (Exception e1) {
               JOptionPane.showMessageDialog(null, "테이블 출력 실패:  " + e1.getMessage());
            }
         }else if(e.getSource() == btnN) {
            try {
               excl.excdata = model.clickN();
               
               excl.fireTableDataChanged();
               //selectTable();
            } catch (Exception e1) {
               JOptionPane.showMessageDialog(null, "테이블 출력 실패:  " + e1.getMessage());
            }
         }else if(e.getSource() == btnAll) {
            try {
               excl.excdata = model.recentList();
               //selectTable();
               excl.fireTableDataChanged();
               
               //selectTable();
            } catch (Exception e1) {
               JOptionPane.showMessageDialog(null, "테이블 출력 실패:  " + e1.getMessage());
            }
         }

      }

   }
}