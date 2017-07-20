
public class Loan {
	
	private double interestRate;
	private int amount,id;
	private String name,rating, datePublished,url;
	private long  publishedMil;

	public Loan(){
		this.interestRate = -1;
		this.amount = -1;
		this.datePublished = "";
		this.rating = "";
		this.url = "";
		this.name = "";
		this.id = -1;
		this.publishedMil = -1;		
	}

	public void setPublishedMil(long publishedMil) {
		this.publishedMil = publishedMil;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return " id = " + id + "\n name = " + name + "\n amount = " + amount + "\n rating = " + rating + "\n interestRate = "
				+ interestRate + "\n datePublished = " + datePublished + "\n url = " + url + "\n";
	}

	public int getId() {
		return id;
	}

	public long getPublishedMil() {
		return publishedMil;
	}
	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getDatePublished() {
		return datePublished;
	}

	public void setDatePublished(String datePublished) {
		this.datePublished = datePublished;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


}
