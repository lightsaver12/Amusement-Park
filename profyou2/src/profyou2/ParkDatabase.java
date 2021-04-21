package profyou2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ParkDatabase {
	private Connection conn;
	private Statement stmt;
	
	public ParkDatabase() {
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testdb1", 
													"root",
													"cookie15");
		 stmt = conn.createStatement();
		 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ParkDatabase (String[] raw) {
		
	}
	
	public void report2 (String[] array) throws IOException, ClassNotFoundException, SQLException {
		try {
			String content = String.format("INSERT INTO `report1` (`Date`, `Type`, `Agegroup`, `Amount`, `Price`, `Advantage`) "
					+ "VALUES ('%s','%s','%s','%s','%s','%s')", array[0], array[1], array[2], array[3], array[4], array[5]);
			stmt.execute(content);
			ResultSet rset = stmt.executeQuery("SELECT * FROM `report1`");
			System.out.println("\n===================== report.csv =====================");
			System.out.println("날짜  권종  연령구분  수량  가격  우대사항");
			while(rset.next()) {
			System.out.println(rset.getString(1) + "  " + rset.getString(2) + "  " + rset.getString(3) 
			+ "  " + rset.getInt(4) + "  " + rset.getInt(5) + "  " + rset.getString(6));
			}
			System.out.println("------------------------------------------------------\n");
			} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Agetypeprice () {
		try {
			System.out.println("==================== 권종 별 판매현황 ======================");
			ResultSet rset = stmt.executeQuery("SELECT SUM(Amount) FROM `report1` WHERE `Type` LIKE \"주간권\"");
			while(rset.next()) {
				System.out.print("주간권 총 " + rset.getInt(1) + "매\n");	
			}
			
			rset = stmt.executeQuery("SELECT SUM(Amount) FROM `report1` WHERE `Type` LIKE '주간권' AND `Agegroup` LIKE '유아'");
			while(rset.next()) {
				System.out.print("유아 " + rset.getInt(1) + "매, ");
			}
			
			rset = stmt.executeQuery("SELECT SUM(Amount) FROM `report1` WHERE `Type` LIKE '주간권' AND `Agegroup` LIKE '아이'");
			while(rset.next()) {
				System.out.print("아이 " + rset.getInt(1) + "매, ");
			}
			
			rset = stmt.executeQuery("SELECT SUM(Amount) FROM `report1` WHERE `Type` LIKE '주간권' AND `Agegroup` LIKE '청소년'");
			while(rset.next()) {
				System.out.print("청소년 " + rset.getInt(1) + "매, ");
			}
			
			rset = stmt.executeQuery("SELECT SUM(Amount) FROM `report1` WHERE `Type` LIKE '주간권' AND `Agegroup` LIKE '어른'");
			while(rset.next()) {
				System.out.print("어른 " + rset.getInt(1) + "매, ");
			}
			
			rset = stmt.executeQuery("SELECT SUM(Amount) FROM `report1` WHERE `Type` LIKE '주간권' AND `Agegroup` LIKE '노인'");
			while(rset.next()) {
				System.out.print("노인 " + rset.getInt(1) + "매\n");
			}
			
			rset = stmt.executeQuery("SELECT SUM(price) FROM `report1` WHERE `Type` LIKE '주간권'");
			while(rset.next()) {
				System.out.print("주간권 매출 : " + rset.getInt(1) + "원\n\n");
			}
			
			rset = stmt.executeQuery("SELECT SUM(Amount) FROM `report1` WHERE `Type` LIKE \"야간권\"");
			while(rset.next()) {
				System.out.print("야간권 총 " + rset.getInt(1) + "매\n");	
			}
			
			rset = stmt.executeQuery("SELECT SUM(Amount) FROM `report1` WHERE `Type` LIKE '야간권' AND `Agegroup` LIKE '유아'");
			while(rset.next()) {
				System.out.print("유아 " + rset.getInt(1) + "매, ");
			}
			
			rset = stmt.executeQuery("SELECT SUM(Amount) FROM `report1` WHERE `Type` LIKE '야간권' AND `Agegroup` LIKE '아이'");
			while(rset.next()) {
				System.out.print("아이 " + rset.getInt(1) + "매, ");
			}
			
			rset = stmt.executeQuery("SELECT SUM(Amount) FROM `report1` WHERE `Type` LIKE '야간권' AND `Agegroup` LIKE '청소년'");
			while(rset.next()) {
				System.out.print("청소년 " + rset.getInt(1) + "매, ");
			}
			
			rset = stmt.executeQuery("SELECT SUM(Amount) FROM `report1` WHERE `Type` LIKE '야간권' AND `Agegroup` LIKE '어른'");
			while(rset.next()) {
				System.out.print("어른 " + rset.getInt(1) + "매, ");
			}
			
			rset = stmt.executeQuery("SELECT SUM(Amount) FROM `report1` WHERE `Type` LIKE '야간권' AND `Agegroup` LIKE '노인'");
			while(rset.next()) {
				System.out.print("노인 " + rset.getInt(1) + "매\n");
			}
			
			rset = stmt.executeQuery("SELECT SUM(price) FROM `report1` WHERE `Type` LIKE '야간권'");
			while(rset.next()) {
				System.out.print("야간권 매출 : " + rset.getInt(1) + "원\n");
			}
			System.out.println("------------------------------------------------------------\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Dateprice () {
		try {
			System.out.println("=========== 일자별 매출 현황 ============");
			ResultSet rset = stmt.executeQuery("select `Date`, sum(price) from `report1` group by `Date`");
			while(rset.next()) {
				rset.getInt(2);
				System.out.print(rset.getString(1) + " : 총 매출\t" + rset.getInt(2) + "원\n");
			}
			System.out.println("-----------------------------------------\n");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void TicketCount () {
		try {
			System.out.println("======= 우대권 판매 현황 =======");
			ResultSet rset = stmt.executeQuery("SELECT SUM(Amount) FROM `report1`");
			while(rset.next()) {
				System.out.printf("%s%8s%7s매\n", "총 판매 티켓 수", ":", rset.getInt(1));
			}
			
			rset = stmt.executeQuery("SELECT SUM(Amount) FROM `report1` WHERE `Advantage` LIKE '없음'");
			while(rset.next()) {
				System.out.printf("%s%14s%7s매\n", "우대 없음", ":", rset.getInt(1));
			}
			
			rset = stmt.executeQuery("SELECT SUM(Amount) FROM `report1` WHERE `Advantage` LIKE '장애인'");
			while(rset.next()) {
				System.out.printf("%s%17s%7s매\n", "장애인", ":", rset.getInt(1));
			}
			
			rset = stmt.executeQuery("SELECT SUM(Amount) FROM `report1` WHERE `Advantage` LIKE '국가유공자'");
			while(rset.next()) {
				System.out.printf("%s%13s%7s매\n", "국가유공자", ":", rset.getInt(1));
			}
			
			rset = stmt.executeQuery("SELECT SUM(Amount) FROM `report1` WHERE `Advantage` LIKE '다자녀'");
			while(rset.next()) {
				System.out.printf("%s%17s%7s매\n", "다자녀", ":", rset.getInt(1));
			}
			
			rset = stmt.executeQuery("SELECT SUM(Amount) FROM `report1` WHERE `Advantage` LIKE '임산부'");
			while(rset.next()) {
				System.out.printf("%s%17s%7s매\n", "임산부", ":", rset.getInt(1));
			}
			
			System.out.println("--------------------------------\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
