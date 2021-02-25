package categoryVO;

public class categoryVO {
	String serial;
	String mc_no;
	String mc_name;
	String mc_price;
	
	public categoryVO(String mc_no,String mc_name, String mc_price,String serial) {
		this.mc_no = mc_no;
		this.mc_name = mc_name;
		this.mc_price = mc_price;
		this.serial = serial;
		
	}
	
	public String getMc_no() {
		return mc_no;
	}
	public void setMc_no(String mc_no) {
		this.mc_no = mc_no;
	}
	public String getMc_name() {
		return mc_name;
	}
	public void setMc_name(String mc_name) {
		this.mc_name = mc_name;
	}
	public String getMc_price() {
		return mc_price;
	}
	public void setMc_price(String mc_price) {
		this.mc_price = mc_price;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String mc_price) {
		this.serial = serial;
	}
	
}
