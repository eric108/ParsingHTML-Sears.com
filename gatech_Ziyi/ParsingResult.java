package gatech_Ziyi;

/**
 * The ParsingResult class stores the parsing data retrieved from Sears.java.
 *
 * It takes four properties of one item as title, price, vendor and index shown
 * in a specific page.
 *
 * @author Ziyi Jiang
 * @version 1.0 09/15/2013
 */
public class ParsingResult {
	private String title;
	private String price;
	private String vendor;
	private int index;

	/**
	 * Constructor used to input four properties together
	 *
	 * @param title title of item 
	 * @param price price of item
	 * @param vendor vendor of item
	 * @param index index of item shown in a specific page
	 */
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
	/**
	 * Customized toString() method to organize the four properties 
	 *
	 * @return the nice-looking customized String 
	 */
	public String toString(){
		return "Item " + index + ":\ntitle: " + title + "\nprice: "
				+ price + "\nvendor: " + vendor + "\n" + 
				"---------------------------------------\n";  
	}
	
}
