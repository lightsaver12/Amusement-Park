package profyou2;

import java.io.IOException;
import java.sql.SQLException;

public class ParkMain {

	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		
		int pass = 0;
		int ssn = 0;
		int amount = 0;
		int preftreat = 0;
		int ticketprice = 0;
		int whether = 0;
		int whether2 = 0;
		String temp1 = "";
		String Final_temp1 = "";
		int sum = 0;
		String date = "";
		String temp13 = "";
		String temp133 = "";
		String [] database;
		ParkInput parkinput = new ParkInput();
		ParkRun run = new ParkRun();
		ParkOutput ot = new ParkOutput();
		ParkWrite write = new ParkWrite();
		ParkDatabase pdb = new ParkDatabase();
		while (true) {
			pass = parkinput.Pass(); // 주야간 결정
			ssn = parkinput.Ssn(); // 생일 결정
			amount = parkinput.Amount(); // 수량
			preftreat = parkinput.Preftreat(); // 우대사항
			ticketprice = run.get_Price(pass, ssn, amount, preftreat);
			// 티켓 가격 결정
			ot.price(ticketprice); // 티켓 가격 출력

			temp1 = run.get_Name(pass, ssn, amount, preftreat, ticketprice);
			Final_temp1 += temp1 + "\n";
			sum += ticketprice;
			whether = parkinput.Whether(); // 다시 발권 혹은 나갈지 여부
			temp13 = run.Names(date, pass, ssn, amount, preftreat, ticketprice);
			temp133 += temp13 + "\n";
			if (whether == 2) {
				ot.Last();
				System.out.println(Final_temp1);
				ot.totalprice2(sum);
				whether2 = parkinput.Whether2();
				if (whether2 == 2) {
					break;
				}
			}
		}
		database = run.ParkData(pass, ssn, amount, preftreat, ticketprice);
		pdb.Agetypeprice();
		pdb.Dateprice();
		pdb.TicketCount();
		write.report(temp133);	
	}
}
