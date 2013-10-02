package gatech_Ziyi;


public class ParsingResult {
	private String title;
	private String price;
	private String vendor;
	private int index;

	
	public ParsingResult(String title, String price,String vendor, int index){
		this.title = title;
		this.price = price;
		this.vendor = vendor;
		this.index = index;
	}
	public String getTitle() {
		return title;
	}
	public String getPrice() {
		return price;
	}
	public String getVendor() {
		return vendor;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public void setVentors(String vendor) {
		this.vendor = vendor;
	}
	
	public String toString(){
		return "Item " + index + ":\ntitle: " + title + "\nprice: "
				+ price + "\nvendor: " + vendor + "\n" + 
				"---------------------------------------\n";  
	}
	
}
