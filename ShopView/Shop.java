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
		String shopNo = id; // 여기에 로그인 후 샵 넘버값 들어가게
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
		setTitle("배시시");
		JTabbedPane pane = new JTabbedPane();
		pane.addTab("메인페이지", main);

		pane.addTab("상품관리", goods);
		pane.addTab("매장관리", host);
		pane.addTab("리뷰관리", review);
		getContentPane().add("Center", pane);
		setBounds(600, 300, 780, 500);
		ChangeListener changeListener = new ChangeListener() {
			public void stateChanged(ChangeEvent changeEvent) {
				if (pane.getSelectedIndex() == 0) // Index starts at 0, so Index 2 = Tab3
				{
					System.out.println("메인 새로고침");
					main.selectTable();
				}
			}
		};
		pane.addChangeListener(changeListener);
		setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}