package profyou2;

public class ParkInfo {
	private String date;
	private String day_Or_Night;
	private int ssn;
	private int amount;
	private int ticketprice;
	private int treat;
	
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDay_Or_Night() {
		return day_Or_Night;
	}
	public void setDay_Or_Night(String day_Or_Night) {
		if (day_Or_Night == "1") {
			this.day_Or_Night = "주간권";
		} else if (day_Or_Night == "2") {
		this.day_Or_Night = "야간권";
		}
	}
	public int getAge() {
		return ssn;
	}
	public void setAge(int ssn) {
		this.ssn = ssn;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getPrice() {
		return ticketprice;
	}
	public void setPrice(int ticketprice) {
		this.ticketprice = ticketprice;
	}
	public int getTreat() {
		return treat;
	}
	public void setTreat(int treat) {
		this.treat = treat;
	}
}