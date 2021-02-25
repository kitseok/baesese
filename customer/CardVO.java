package customer;

public class CardVO {

	String cardname;
	String cardalias;
	
	public CardVO(String cardname, String cardalias){
		this.cardname = cardname;
		this.cardalias = cardalias;
	}
	
	public String getCardname() {
		return cardname;
	}
	public void setCardname(String cardname) {
		this.cardname = cardname;
	}
	public String getCardalias() {
		return cardalias;
	}
	public void setCardalias(String cardalias) {
		this.cardalias = cardalias;
	}
	
}
