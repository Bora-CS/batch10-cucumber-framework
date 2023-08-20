package hui_automation.pojos;

public class AmazonSearchResult {

	public int id;
	public double price;
	public String title;
	public String subtitle;

	public AmazonSearchResult(int id, double price, String title, String subtitle) {
		this.id = id;
		this.price = price;
		this.title = title;
		this.subtitle = subtitle;
	}

}
