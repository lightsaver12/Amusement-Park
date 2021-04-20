package profyou2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class ParkWrite {

	public void report (String temp133) throws IOException {
		BufferedWriter bw; 
		File file = new File(ParkConstValue.address);
		
		String data1 = "날짜, 권종, 연령구분, 수량, 가격, 우대사항\n";
		
		if (file.exists() == false) {
			bw = new BufferedWriter
					(new OutputStreamWriter(new FileOutputStream(ParkConstValue.address, true)));
			bw.write(data1);
			bw.write(temp133);
		} else {
			bw = new BufferedWriter
					(new OutputStreamWriter(new FileOutputStream(ParkConstValue.address, true)));
			bw.write(temp133);
		}
		bw.close();
	}
}
