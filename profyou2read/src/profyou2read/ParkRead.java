package profyou2read;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class ParkRead {
	
	public List<List<String>> Report() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(ParkConVal.address));
			List<List<String>> csvlist = new ArrayList<List<String>>();
			List<String> csvlistoneline = new ArrayList<String>();
			String line = "";
			while ((line = reader.readLine()) != null) {
				String [] arrayofline = line.split(",");
				csvlistoneline = Arrays.asList(arrayofline);
				csvlist.add(csvlistoneline);
			}
			return csvlist;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
