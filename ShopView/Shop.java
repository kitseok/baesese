package ShopView;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ShopModel.ShopModel;
import customer.cust_view;

public class Shop extends JFrame {

	ShopView main;
	ShopGoodsView goods;
	ShopHostView host;
	ShopModel model = null;
	ReviewComentView review;

	public Shop(String id) {

		try {
			model = new ShopModel();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String shopNo = id; // ���⿡ �α��� �� �� �ѹ��� ����
		try {
			shopNo = model.hookShopNo(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		main = new ShopView(shopNo);
		goods = new ShopGoodsView(shopNo);

		try {
			host = new ShopHostView(shopNo);
			review = new ReviewComentView(shopNo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setTitle("��ý�");
		JTabbedPane pane = new JTabbedPane();
		pane.addTab("����������", main);

		pane.addTab("��ǰ����", goods);
		pane.addTab("�������", host);
		pane.addTab("�������", review);
		getContentPane().add("Center", pane);
		setBounds(600, 300, 780, 500);
		ChangeListener changeListener = new ChangeListener() {
			public void stateChanged(ChangeEvent changeEvent) {
				if (pane.getSelectedIndex() == 0) // Index starts at 0, so Index 2 = Tab3
				{
					System.out.println("���� ���ΰ�ħ");
					main.selectTable();
				}
			}
		};
		pane.addChangeListener(changeListener);
		setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}