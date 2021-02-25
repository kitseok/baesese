package categoryView;

import java.awt.EventQueue;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

//import model.ConnectionPool;
import Order.*;
import categoryModel.categorySoModel;
import customer.custInfo_view;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class category_dae extends JFrame {

	private JPanel contentPane;
	private JButton meat;
	private JButton side_dish;
	private JButton snack;
	private JButton plant;
	private JButton sea_food;
	private JButton food;
	private JLabel label;
	private JButton rice_house;
	private JButton dried_fish;
	private JButton clothes;
	static Connection con = null;
	private JButton house_goods;
	private JButton etc;
	private category_so so = null;
	private String cus = null; // ���� no �ƴϸ� ���̵�
	private String id = "";
	private JLabel lblNewLabel;
	private JButton btnNewButton;

	categorySoModel model = null;
	private JButton btnNewButton_1;
	JLabel lb_meat; // ���
//	JLabel lb_plant; //��깰
//	JLabel lb_sea_food; //�ػ깰
//	JLabel lb_side_dish; //����
//	JLabel lb_snack; //�԰Ÿ�
//	JLabel lb_house_goods; //����ǰ
//	JLabel lb_dried_fish; //�Ǿ
//	JLabel lb_clothes; //�Ƿ�
//	JLabel lb_rice_house; //��Ѱ�
//	JLabel lb_food; //��ǰ
//	JLabel lb_etc; //��Ÿ

	public static void main(String[] args) {
		try {
			con = ProConnectPool.getConnection();

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// category_dae frame = new category_dae();
					// frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public category_dae(String id) throws IOException {
		this.id = id;
		model = new categorySoModel();
		try {

			showLayout();
			ShowImage();
			setVisible(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		eventProc();
	}

	private void ShowImage() {
		// �̹��� ��������
		try {
			String al = model.showImage("ī�װ�����");
			URL url;
			Image image;
			Image chimg;
			try {
				url = new URL(al);
				image = ImageIO.read(url);
				chimg = image.getScaledInstance(350, 350, Image.SCALE_SMOOTH);
				lb_meat.setIcon(new ImageIcon(chimg));

			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// URL url = new URL();
			// Image image;
			// image = ImageIO.read(url);
			// lb_image.setIcon(new ImageIcon(image));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void eventProc() {
		MyListener evt = new MyListener();
		meat.addActionListener(evt);
		side_dish.addActionListener(evt);
		snack.addActionListener(evt);
		plant.addActionListener(evt);
		sea_food.addActionListener(evt);
		food.addActionListener(evt);
		rice_house.addActionListener(evt);
		dried_fish.addActionListener(evt);
		clothes.addActionListener(evt);
		house_goods.addActionListener(evt);
		etc.addActionListener(evt);
		btnNewButton.addActionListener(evt);
		btnNewButton_1.addActionListener(evt);

	}

	private void showLayout() throws Exception {
		setTitle("��ý�");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(630, 280, 678, 525);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);

		String str = model.name_to_id(id);
		contentPane.setLayout(null);
		lblNewLabel = new JLabel(str);
		lblNewLabel.setBounds(0, 0, 0, 0);
		contentPane.add(lblNewLabel);

		btnNewButton = new JButton("\uB0B4 \uC815\uBCF4");
		btnNewButton.setFont(new Font("�ϵ����ü Regular", Font.PLAIN, 15));
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setBounds(553, 23, 87, 30);
		contentPane.add(btnNewButton);

		btnNewButton_1 = new JButton("\uC7A5\uBC14\uAD6C\uB2C8");
		btnNewButton_1.setFont(new Font("�ϵ����ü Regular", Font.PLAIN, 15));
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.setBounds(461, 23, 87, 30);
		contentPane.add(btnNewButton_1);

		label = new JLabel("\uBC30\uC2DC\uC2DC");
		label.setFont(new Font("�ϵ����ü Bold", Font.BOLD, 31));
		label.setBounds(26, 10, 102, 44);
		contentPane.add(label);

		// URL url = new URL();
		// Image image;
		// image = ImageIO.read(url);
		// lb_image.setIcon(new ImageIcon(image));
		meat = new JButton("\uCD95  \uC0B0");
		meat.setBackground(new Color(255, 255, 255));
		meat.setForeground(new Color(0, 0, 0));
		meat.setBounds(26, 95, 102, 38);
		meat.setFont(new Font("�ϵ����ü Regular", Font.PLAIN, 22));
		contentPane.add(meat);

		side_dish = new JButton("\uBC18  \uCC2C");
		side_dish.setBackground(new Color(255, 255, 255));
		side_dish.setBounds(26, 284, 102, 38);
		side_dish.setFont(new Font("�ϵ����ü Regular", Font.PLAIN, 22));
		contentPane.add(side_dish);

		snack = new JButton("�԰Ÿ�");
		snack.setBackground(new Color(255, 255, 255));
		snack.setBounds(26, 346, 102, 38);
		snack.setFont(new Font("�ϵ����ü Regular", Font.PLAIN, 22));
		contentPane.add(snack);

		plant = new JButton("��깰");
		plant.setBackground(new Color(255, 255, 255));
		plant.setBounds(26, 158, 102, 38);
		plant.setFont(new Font("�ϵ����ü Regular", Font.PLAIN, 22));
		contentPane.add(plant);

		sea_food = new JButton("���깰");
		sea_food.setBackground(new Color(255, 255, 255));
		sea_food.setBounds(26, 221, 102, 38);
		sea_food.setFont(new Font("�ϵ����ü Regular", Font.PLAIN, 22));
		contentPane.add(sea_food);

		food = new JButton("\uC2DD  \uD488");
		food.setBackground(new Color(255, 255, 255));
		food.setBounds(534, 346, 102, 38);
		food.setFont(new Font("�ϵ����ü Regular", Font.PLAIN, 22));
		contentPane.add(food);

		rice_house = new JButton("��Ѱ�");
		rice_house.setBackground(new Color(255, 255, 255));
		rice_house.setBounds(534, 284, 102, 38);
		rice_house.setFont(new Font("�ϵ����ü Regular", Font.PLAIN, 22));
		contentPane.add(rice_house);

		dried_fish = new JButton("�Ǿ");
		dried_fish.setBackground(new Color(255, 255, 255));
		dried_fish.setBounds(534, 158, 102, 38);
		dried_fish.setFont(new Font("�ϵ����ü Regular", Font.PLAIN, 22));
		contentPane.add(dried_fish);

		clothes = new JButton("\uC758  \uB958");
		clothes.setBackground(new Color(255, 255, 255));
		clothes.setBounds(534, 221, 102, 38);
		clothes.setFont(new Font("�ϵ����ü Regular", Font.PLAIN, 22));
		contentPane.add(clothes);

		house_goods = new JButton("\uC0DD\uD544\uD488");
		house_goods.setBackground(new Color(255, 255, 255));
		house_goods.setBounds(534, 95, 102, 38);
		house_goods.setFont(new Font("�ϵ����ü Regular", Font.PLAIN, 22));
		contentPane.add(house_goods);

		etc = new JButton("\uAE30  \uD0C0");
		etc.setBackground(new Color(255, 255, 255));
		etc.setBounds(534, 409, 102, 38);
		etc.setFont(new Font("�ϵ����ü Regular", Font.PLAIN, 22));
		contentPane.add(etc);

		lb_meat = new JLabel("");
		lb_meat.setBounds(156, 92, 350, 350);
		contentPane.add(lb_meat);

	}

	class MyListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == side_dish) {
				// btRe.setText("��ư�� ���������ϴ�.");
				try {
					so = new category_so("�԰Ÿ�", id);

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (e.getSource() == snack) {
				// btRe.setText("��ư�� ���������ϴ�.");
				try {
					so = new category_so("�԰Ÿ�", id);

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (e.getSource() == plant) {
				// btRe.setText("��ư�� ���������ϴ�.");
				try {
					so = new category_so("��깰", id);

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (e.getSource() == sea_food) {
				// btRe.setText("��ư�� ���������ϴ�.");
				try {
					so = new category_so("���깰", id);

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (e.getSource() == food) {
				// btRe.setText("��ư�� ���������ϴ�.");
				try {
					so = new category_so("��ǰ", id);

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (e.getSource() == meat) {
				// btRe.setText("��ư�� ���������ϴ�.");
				try {
					so = new category_so("���", id);

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (e.getSource() == rice_house) {
				// btRe.setText("��ư�� ���������ϴ�.");
				try {
					so = new category_so("��Ѱ�", id);

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (e.getSource() == dried_fish) {
				// btRe.setText("��ư�� ���������ϴ�.");
				try {
					so = new category_so("�Ǿ", id);

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (e.getSource() == clothes) {
				// btRe.setText("��ư�� ���������ϴ�.");
				try {
					so = new category_so("�Ƿ�", id);

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (e.getSource() == house_goods) {
				// btRe.setText("��ư�� ���������ϴ�.");
				try {
					so = new category_so("��Ȱ��ǰ", id);

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (e.getSource() == etc) {
				// btRe.setText("��ư�� ���������ϴ�.");
				try {
					so = new category_so("��Ÿ", id);

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (e.getSource() == btnNewButton) {
				new custInfo_view(id);
			} else if (e.getSource() == btnNewButton_1) {
				try {
					int num;
					num = model.CustnoFind(id);
					new OrderView1(num);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}
	}
}
