import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONObject;


public class Logic {
	
	private int sizeOfList;
	private String uri;
	private Client client;
	private WebTarget target;
	private List<Loan> loans;
	
	public Logic(){
		this.client = ClientBuilder.newClient();
		this.uri = "https://api.zonky.cz/loans/marketplace";
		this.target = client.target(uri);
		this.sizeOfList = 0;
		this.loans = new ArrayList<Loan>();		
	}
	public Logic(String uri){
		this.client = ClientBuilder.newClient();
		this.uri = uri;
		this.target = client.target(this.uri);
		this.sizeOfList = 0;
		this.loans = new ArrayList<Loan>();
	}
	public void loansProcessing(){
		try{
			String s = target.request(MediaType.APPLICATION_JSON).get(String.class);
			JSONArray array = new JSONArray(s);
			for(int i = 0; i< array.length(); i++){
				JSONObject obj = (JSONObject) array.get(i);
				fillLoan(obj);
				}
			//Sorting List by time of publishing
			Collections.sort(loans, new LoanComparator());
			findNew();
			}
		catch(Exception e){
			e.printStackTrace();
			}
		}
	private void findNew(){
		if(sizeOfList > loans.size() && sizeOfList < 0){
			System.out.println("sizeOflist is out of bounds"
					+ "-> starting from 0 to avoid error");
			this.sizeOfList = 0;
			}
			for(int i = sizeOfList; i < loans.size(); i++){
				System.out.println(loans.get(i).toString());
			}
			this.sizeOfList = loans.size();
	}
	private boolean isLoanInArray(List<Loan> loans, Loan loan){
		for(int i =0; i< loans.size(); i++){
			if(loan.getId() == loans.get(i).getId()){
				return true;
			}
		}
		return false;
	}
	private void fillLoan(JSONObject obj){
		Loan loan = new Loan();
		loan.setId(obj.getInt("id"));
		if(!isLoanInArray(loans, loan)){
			String published = obj.getString("datePublished");
			TemporalAccessor tempAccessor = DateTimeFormatter.ISO_OFFSET_DATE_TIME.parse(published);
			Instant instant = Instant.from(tempAccessor);
			loan.setPublishedMil(instant.toEpochMilli());
			loan.setDatePublished(published);
			loan.setAmount(obj.getInt("amount"));
			loan.setRating(obj.getString("rating"));
			loan.setName(obj.getString("name"));
			loan.setInterestRate(obj.getDouble("interestRate"));
			loan.setUrl(obj.getString("url"));
			loans.add(loan);
		}
	}
}
