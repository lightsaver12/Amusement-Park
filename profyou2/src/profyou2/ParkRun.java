package profyou2;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ParkRun {
	private ParkOutput output = null;
	public ParkRun() {
		output = new ParkOutput();
	}
	ParkDatabase pdb = new ParkDatabase();
	ParkInput parkinput = new ParkInput();
	Calendar cal = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat("YY");
	int current = Integer.parseInt(sdf.format(cal.getTime()));
	int ticketsum = 0;
	int Price = 0;
	
	public int get_Price (int day_Or_Night, int ssn, int Amount, int preftreat) {
		int Price = 0;
		int age = 0;
		
		// 실제나이산출
		if (ssn < 21) {
			age = 21 - ssn;
		} else {
			age = 121 - ssn;
		}
		
		if (day_Or_Night == 1) { // 주간권
			if (age >=  65) {
				Price = ParkConstValue.Elders_dp;
			} else if (age >= 19) {
				Price = ParkConstValue.Adult_dp;
			} else if (age >= 13) {
				Price = ParkConstValue.Teenager_dp;
			} else if (age >= 3) {
				Price = ParkConstValue.Child_dp;
			} else {
				Price = ParkConstValue.Babies;
			}
			
		} else { // 야간권
			if (age >=  65) {
				Price = ParkConstValue.Elders_np;
			} else if (age >= 19) {
				Price = ParkConstValue.Adult_np;
			} else if (age >= 13) {
				Price = ParkConstValue.Teenager_np;
			} else if (age >= 3) {
				Price = ParkConstValue.Child_np;
			} else {
				Price = ParkConstValue.Babies;
			}
		}
				
		Price = Price * Amount;
		
		if (preftreat == ParkConstValue.Nothing) {
			Price = Price;
		} else if (preftreat == ParkConstValue.Disabled) {
			Price = (int) (Price * (1-ParkConstValue.Disabled_dis));
		} else if (preftreat == ParkConstValue.Natmerit) {
			Price = (int) (Price * (1-ParkConstValue.Natmerit_dis));
		} else if (preftreat == ParkConstValue.Mulchild) {
			Price = (int) (Price * (1-ParkConstValue.Mulchild_dis));
		} else if (preftreat == ParkConstValue.Pregnant) {
			Price = (int) (Price * (1-ParkConstValue.Pregnant_dis));
		}
		return Price;
	}
	
	public String get_Name (int day_Or_Night, int ssn, int Amount, int preftreat, int ticketprice) throws ClassNotFoundException, IOException, SQLException {
		String temp = "";

		if (day_Or_Night == 1) {
			temp += "주간권 ";
		} else if (day_Or_Night == 2) {
			temp += "야간권 ";
		}
		
		int age = 0;
		if (ssn < 21) {
			age = 21 - ssn;
		} else {
			age = 121 - ssn;
		}
	
		if (age >=  65) {
			temp += "노인 ";
		//	a =  "노인 ";
		} else if (age >= 19) {
			temp += "어른 ";
		//	a = "어른 ";
		} else if (age >= 13) {
			temp += "청소년 ";
		//	a = "청소년 ";
		} else if (age >= 3) {
			temp += "아이 ";
		//	a = "아이 ";
		} else {
			temp += "유아 ";
		//	a = "유아 ";
		}
		//array[2] = a;
		//array[3] = "" + Amount;
		//array[4] = "" + ticketprice;
		
		temp += "X    " + Amount;
		temp += "      " + ticketprice + "원";
		
		if (preftreat == ParkConstValue.Nothing) {
			temp += "  *우대적용 없음";
		//	a = "없음";
		} else if (preftreat == ParkConstValue.Disabled) {
			temp += "  *장애인 우대적용";
		//	a = "장애인";
		} else if (preftreat == ParkConstValue.Natmerit) {
			temp += "  *국가유공자 우대적용";
		//	a = "국가유공자";
		} else if (preftreat == ParkConstValue.Mulchild) {
			temp += "  *다자녀 우대적용";
		//	a = "다자녀";
		} else if (preftreat == ParkConstValue.Pregnant) {
			temp += "  *임산부 우대적용";
		//	a = "임산부";
		}
		//array[5] = a;
		
	//	ParkDatabase pdb = new ParkDatabase();
	//	pdb.report2(array);
		
	return temp;
	
	}
	
	public String Names (String date, int day_Or_Night, int ssn, int Amount, int preftreat, int ticketprice) throws IOException {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMdd");
		String temp1 = sdf.format(cal.getTime()) + "," + "";
		
		if (day_Or_Night == 1) {
			temp1 += "주간권,";
		} else if (day_Or_Night == 2) {
			temp1 += "야간권,";
		}
		
		int age = 0;
		if (ssn < 21) {
			age = 21 - ssn;
			
		} else {
			age = 121 - ssn;
		}
	
		if (age >=  65) {
			temp1 += "노인,";
		} else if (age >= 19) {
			temp1 += "어른,";
		} else if (age >= 13) {
			temp1 += "청소년,";
		} else if (age >= 3) {
			temp1 += "아이,";
		} else {
			temp1 += "유아,";
		}
		
		temp1 += Integer.toString(Amount) + ",";
		temp1 += Integer.toString(ticketprice) + ",";
		
		if (preftreat == ParkConstValue.Nothing) {
			temp1 += "없음";
		} else if (preftreat == ParkConstValue.Disabled) {
			temp1 += "장애인";
		} else if (preftreat == ParkConstValue.Natmerit) {
			temp1 += "국가유공자";
		} else if (preftreat == ParkConstValue.Mulchild) {
			temp1 += "다자녀";
		} else if (preftreat == ParkConstValue.Pregnant) {
			temp1 += "임산부";
		}
		return temp1;
	}

	
	public String[] ParkData (int day_Or_Night, int ssn, int Amount, int preftreat, int ticketprice) throws ClassNotFoundException, IOException, SQLException {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		String [] array = new String[6];
		String a = "";
		array[0] = sdf.format(cal.getTime());
		if (day_Or_Night == 1) {
			a = "주간권";
		} else if (day_Or_Night == 2) {
			a = "야간권";
		}
		array[1] = a;
		
		int age = 0;
		if (ssn < 21) {
			age = 21 - ssn;
		} else {
			age = 121 - ssn;
		}
	
		if (age >=  65) {
			a =  "노인 ";
		} else if (age >= 19) {
			a = "어른 ";
		} else if (age >= 13) {
			a = "청소년 ";
		} else if (age >= 3) {
			a = "아이 ";
		} else {
			a = "유아 ";
		}
		array[2] = a;
		array[3] = "" + Amount;
		array[4] = "" + ticketprice;
		
		if (preftreat == ParkConstValue.Nothing) {
			a = "없음";
		} else if (preftreat == ParkConstValue.Disabled) {
			a = "장애인";
		} else if (preftreat == ParkConstValue.Natmerit) {
			a = "국가유공자";
		} else if (preftreat == ParkConstValue.Mulchild) {
			a = "다자녀";
		} else if (preftreat == ParkConstValue.Pregnant) {
			a = "임산부";
		}
		array[5] = a;
		
		ParkDatabase pdb = new ParkDatabase();
		pdb.report2(array);
		return array;
	}
}

