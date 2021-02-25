package Order;

import java.util.ArrayList;

public class OrderVO {
	int goodsno;
	String goodsname;
	int goodsprice;
	int goodsquantity;
	ArrayList<OrderVO> list = new ArrayList<OrderVO>();
	public OrderVO(OrderVO vo)
	{
		
	}
	
	public OrderVO(int goodsno, String goodsname, int goodsprice, int goodsquantity)
	{
		this.goodsno = goodsno;
		this.goodsname = goodsname;
		this.goodsprice = goodsprice;
		this.goodsquantity = goodsquantity;
	}
	public int getGoodsno() {
		return goodsno;
	}
	public void setGoodsno(int goodsno) {
		this.goodsno = goodsno;
	}
	public String getGoodsname() {
		return goodsname;
	}
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
	public int getGoodsprice() {
		return goodsprice;
	}
	public void setGoodsprice(int goodsprice) {
		this.goodsprice = goodsprice;
	}
	public int getGoodsquantity() {
		return goodsquantity;
	}
	public void setGoodsquantity(int goodsquantity) {
		this.goodsquantity = goodsquantity;
	}
	
	

}
