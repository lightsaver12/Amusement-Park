package profyou2read;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class ParkOut {

	public static void FileRead() throws IOException {

		BufferedReader reader;
		List<List<String>> csvlist = new ArrayList<List<String>>();

		try {
			String line = "";
			reader = new BufferedReader(new FileReader(ParkConVal.address));

			while ((line = reader.readLine()) != null) {

				List<String> csvlistoneline = new ArrayList<String>();

				String[] arrayofline = line.split(",");

				csvlistoneline = Arrays.asList(arrayofline);
				csvlist.add(csvlistoneline);

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("------------------------report.csv------------------------");

		int sumd = 0, sumn = 0;
		int babyd = 0, childd = 0, teenagerd = 0, adultd = 0, eldersd = 0;
		int babyn = 0, childn = 0, teenagern = 0, adultn = 0, eldersn = 0;
		int totald = 0, totaln = 0;

		for (int i = 0; i < csvlist.size(); i++) {
			for (int j = 0; j < csvlist.get(i).size(); j++) {
				System.out.print(csvlist.get(i).get(j) + "   ");

			}
			System.out.println();
		}

		System.out.println("----------------------------------------------------------\n");
		System.out.println("=================== 권종 별 판매 현황 =====================");
		
		String temp1 = "";
		for (int i = 0; i < csvlist.size(); i++) {
			if (csvlist.get(i).get(1).contains("주간권")) {
				totald += Integer.parseInt(csvlist.get(i).get(3));
			}
		}
		System.out.println("주간권 총 " + totald + "매");

		for (int i = 0; i < csvlist.size(); i++) {
			if (csvlist.get(i).get(1).contains("주간권")) {
				if (csvlist.get(i).get(2).contains("노인")) {
					eldersd += Integer.parseInt(csvlist.get(i).get(3));
				} else if (csvlist.get(i).get(2).contains("어른")) {
					adultd += Integer.parseInt(csvlist.get(i).get(3));
				} else if (csvlist.get(i).get(2).contains("청소년")) {
					teenagerd += Integer.parseInt(csvlist.get(i).get(3));
				} else if (csvlist.get(i).get(2).contains("아이")) {
					childd += Integer.parseInt(csvlist.get(i).get(3));
				} else if (csvlist.get(i).get(2).contains("유아")) {
					babyd += Integer.parseInt(csvlist.get(i).get(3));
				}
			}
		}
		System.out.print("유아 " + babyd + "매, ");
		System.out.print("아이 " + childd + "매, ");
		System.out.print("청소년 " + teenagerd + "매, ");
		System.out.print("어른 " + adultd + "매, ");
		System.out.print("노인 " + eldersd + "매\n");

		for (int i = 0; i < csvlist.size(); i++) {
			if (csvlist.get(i).get(1).contains("주간권")) {
				sumd += Integer.parseInt(csvlist.get(i).get(4));
			}
		}
		System.out.println("주간권 매출 : " + sumd + "원\n");

		for (int i = 0; i < csvlist.size(); i++) {
			if (csvlist.get(i).get(1).contains("야간권")) {
				totaln += Integer.parseInt(csvlist.get(i).get(3));
			}
		}
		System.out.println("야간권 총 " + totaln + "매");

		for (int i = 0; i < csvlist.size(); i++) {
			if (csvlist.get(i).get(1).contains("야간권")) {
				if (csvlist.get(i).get(2).contains("노인")) {
					eldersn += Integer.parseInt(csvlist.get(i).get(3));
				} else if (csvlist.get(i).get(2).contains("어른")) {
					adultn += Integer.parseInt(csvlist.get(i).get(3));
				} else if (csvlist.get(i).get(2).contains("청소년")) {
					teenagern += Integer.parseInt(csvlist.get(i).get(3));
				} else if (csvlist.get(i).get(2).contains("아이")) {
					childn += Integer.parseInt(csvlist.get(i).get(3));
				} else if (csvlist.get(i).get(2).contains("유아")) {
					babyn += Integer.parseInt(csvlist.get(i).get(3));
				}
			}
		}
		System.out.print("유아 " + babyn + "매, ");
		System.out.print("아이 " + childn + "매, ");
		System.out.print("청소년 " + teenagern + "매, ");
		System.out.print("어른 " + adultn + "매, ");
		System.out.print("노인 " + eldersn + "매\n");
		

		for (int i = 0; i < csvlist.size(); i++) {
			if (csvlist.get(i).get(1).contains("야간권")) {
				sumn += Integer.parseInt(csvlist.get(i).get(4));
			}
		}
		
		
		System.out.println("야간권 매출 : " + sumn + "원");
		System.out.println("-----------------------------------------------------------\n");
		System.out.println("================= 일자별 매출 현황 ==================");

		temp1 += "유아 ," + babyd + "," + babyn +"\n";
		temp1 += "아이 ," + childd + "," + childn +"\n";
		temp1 += "청소년 ," + teenagerd + "," + teenagern +"\n";
		temp1 += "어른 ," + adultd + "," + adultn +"\n";
		temp1 += "노인 ," + eldersd + "," + eldersn +"\n";
		temp1 += "합계 ," + totald + "," + totaln + "\n";
		temp1 += "매출 ," + sumd + "," + sumn;
				
		HashSet<String> hash = new HashSet<String>();
		// 날짜의 중복값을 없애기 위해 hashset 사용 hash라는 객체 생성
		for (int i = 1; i < csvlist.size(); i++) { // 모든 날짜 수만큼 돌려라
			hash.add(csvlist.get(i).get(0)); // hash에 날짜 저장
		}

		ArrayList<String> date = new ArrayList<String>(hash);
		// ArrayList 선언 하고 안에 hash의 내용을 넣는다.
		Collections.sort(date);
		// date를 오름차순으로 정렬
		

		int[] saledate = new int[date.size()];
		// int 배열 안에 사이즈를 date.size 만큼의 사이즈로 설정해줍니다.
		
		String temp = "";
		for (int i = 0; i < date.size(); i++) { // date.size만큼 for문을 돌려줍니다.
			
			for (int j = 0; j < csvlist.size(); j++) { // 모든 날짜 수만큼 사이즈를 돌려줍니다.
				if (csvlist.get(j).get(0).equals(date.get(i))) {
					// 만약 csvlist의 날짜 목록중에 Arraylist의 날짜 목록과 같은게 있다면
					saledate[i] += Integer.parseInt(csvlist.get(j).get(4));
					//temp += saledate[i];
					// 인트 배열안에 arraylist의 해당하는 날짜의 가격들을 차례로 계속 더해줍니다.
				}
			}
			temp += date.get(i) + " ," + saledate[i] + "\n";
			System.out.println(date.get(i) + " : 총 매출 " + saledate[i] + "원");
		}

		System.out.println("---------------------------------------------\n");
		System.out.println("======= 우대권 판매 현황 =======");

		int totaltic = 0, totalno = 0, totaldis = 0, totalmer = 0, totalmany = 0, totalpreg = 0;

		for (int i = 1; i < csvlist.size(); i++) {
			totaltic += Integer.parseInt(csvlist.get(i).get(3));
		}
		System.out.printf("%s%8s%7d매\n", "총 판매 티켓수", ":", totaltic);

		for (int i = 1; i < csvlist.size(); i++) {
			if (csvlist.get(i).get(5).equals("없음")) {
				totalno += Integer.parseInt(csvlist.get(i).get(3));
			} else if (csvlist.get(i).get(5).contains("장애인")) {
				totaldis += Integer.parseInt(csvlist.get(i).get(3));
			} else if (csvlist.get(i).get(5).contains("국가유공자")) {
				totalmer += Integer.parseInt(csvlist.get(i).get(3));
			} else if (csvlist.get(i).get(5).contains("다자녀")) {
				totalmany += Integer.parseInt(csvlist.get(i).get(3));
			} else if (csvlist.get(i).get(5).contains("임산부")) {
				totalpreg += Integer.parseInt(csvlist.get(i).get(3));
			}
		}
		System.out.printf("%s%13s%7d매\n", "우대 없음", ":", totalno);
		System.out.printf("%s%16s%7d매\n", "장애인", ":", totaldis);
		System.out.printf("%s%12s%7d매\n", "국가유공자", ":", totalmer);
		System.out.printf("%s%16s%7d매\n", "다자녀", ":", totalmany);
		System.out.printf("%s%16s%7d매\n", "임산부", ":", totalpreg);
		System.out.println("--------------------------------");
		
		BufferedWriter bw;
		BufferedWriter bw2;
		String data1 = "일자 ,총 매출\n";
		bw = new BufferedWriter(new OutputStreamWriter
				(new FileOutputStream(ParkConVal.firstfile)));
		bw.write(data1);
		bw.write(temp);
		bw.close();
	
		String data2 = "구분 ,주간권, 야간권\n";
		bw2 = new BufferedWriter(new OutputStreamWriter
				(new FileOutputStream(ParkConVal.secondfile)));
		bw2.write(data2);
		bw2.write(temp1);
		bw2.close();
	}
}
