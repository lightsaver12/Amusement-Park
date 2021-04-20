package profyou2;

import java.util.Scanner;

public class ParkInput {
	private Scanner sc = null;
	
	public ParkInput() {
		sc = new Scanner(System.in);
	}
	
	public int Pass() {
		int pass;
		while(true) {
		System.out.println("권종을 선택하세요\n1. 주간권\n2. 야간권");
		pass = sc.nextInt();
			if (pass != 1 && pass != 2) {
				System.out.println("다시\n");
			} else {
				break;
			}
		}
		return pass;
	}
	
	public int Ssn() {
		int ssn = 0;
		System.out.println("주민번호를 입력하세요");
		String temp = sc.next();
		temp = temp.substring(0, 2);
		ssn = Integer.parseInt(temp);
		return ssn;
	}
	
	public int Amount() {
		int amount;
		while (true) {
		System.out.println("몇개를 주문하시겠습니까? (최대 10개)");
		amount = sc.nextInt();
			if (amount > 10) {
				System.out.println("다시");
			} else {
				break;
			}
		}
		return amount;
	}
	
	public int Preftreat() {
		int preftreat;
		System.out.println("우대사항을 선택하세요.\n1. 없음 (나이 우대는 자동처리)\n2. 장애인\n3. 국가유공자\n4. 다자녀\n5. 임산부");
		preftreat = sc.nextInt();
		return preftreat;
	}
	
	public int Whether() {
		int whether;
		System.out.println("계속 발권 하시겠습니까?\n1. 티켓 발권\n2. 종료");
		whether = sc.nextInt();
		return whether;
	}
	
	public int Whether2() {
		int whether2;
		System.out.print("계속 진행(1: 새로운 주문, 2: 프로그램 종료) : ");
		whether2 = sc.nextInt();
		return whether2;
	}
}
