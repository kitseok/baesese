package customer;

public class cust_VO {

	   //private int cus_no;
	   private String cus_name, cus_address, cus_birth, cus_id, cus_pwd, cus_phone, cus_phoneconfirm;
	//   public int getCus_no() {
//	      return cus_no;
	//   }
	//   public void setCus_no(int cus_no) {
//	      this.cus_no = cus_no;
	//   }
	   
	   public cust_VO(String cus_id, String cus_pwd,String  cus_name,String  cus_birth,String  cus_address,String  cus_phone,String  cus_phoneconfirm) {
	      this.cus_id=cus_id;
	      this.cus_pwd=cus_pwd;
	      this.cus_name=cus_name;
	      this.cus_birth=cus_birth;
	      this.cus_address=cus_address;
	      this.cus_phone=cus_phone;
	      this.cus_phoneconfirm = cus_phoneconfirm;
	   };
	   public String getCus_name() {
	      return cus_name;
	   }
	   public void setCus_name(String cus_name) {
	      this.cus_name = cus_name;
	   }
	   public String getCus_address() {
	      return cus_address;
	   }
	   public void setCus_address(String cus_address) {
	      this.cus_address = cus_address;
	   }
	   public String getCus_birth() {
	      return cus_birth;
	   }
	   public void setCus_birth(String cus_birth) {
	      this.cus_birth = cus_birth;
	   }
	   public String getCus_id() {
	      return cus_id;
	   }
	   public void setCus_id(String cus_id) {
	      this.cus_id = cus_id;
	   }
	   public String getCus_pwd() {
	      return cus_pwd;
	   }
	   public void setCus_pwd(String cus_pwd) {
	      this.cus_pwd = cus_pwd;
	   }
	   public String getCus_phone() {
	      return cus_phone;
	   }
	   public void setCus_phone(String cus_phone) {
	      this.cus_phone = cus_phone;
	   }
	   public String getCus_phoneconfirm() {
	      return cus_phoneconfirm;
	   }
	   public void setCus_phoneconfirm(String cus_phoneconfirm) {
	      this.cus_phoneconfirm = cus_phoneconfirm;
	   }
	   
	}