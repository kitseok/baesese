package Order;


import java.awt.*;
import javax.swing.*;
import Order.OrderView1;;


public class OrderMain extends JFrame 
{
	OrderView1 view;

	public OrderMain()
	{
		//view = new OrderView1();
		
		//JPanel pane = new JPanel();
		JTabbedPane pane1 = new JTabbedPane();
		pane1.addTab("¡÷πÆ", view);
		//pane1.addTab("§∑§∑",view);
		pane1.setSelectedIndex(0);
		
		
		getContentPane().add("Center", pane1 );
		
		setSize(700,400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//add(view);
	}
	
	public static void main(String[] args) {
			new OrderMain();
	}

}
