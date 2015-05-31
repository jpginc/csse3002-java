package sampleData;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CreateDataSet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PrintWriter pw = null;
		try {
			pw = new PrintWriter("/Users/ToanHo/Desktop/newTest.crvf");
			pw.println("1,0,0,0,0");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(int i = 0; i < 200; i++) {
			int flex1 = (int) (Math.random() * 1000) % 1023;
			int flex2 = (int) (Math.random() * 1000) % 1023;
			int x = (int) (Math.random() * 1000) % 1023;
			int y = (int) (Math.random() * 1000) % 1023;
			int z = (int) (Math.random() * 1000) % 1023;
			String str = "" + flex1 + "," + flex2 + "," + x + "," + y + "," + z;
			try {
				pw.println(str);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		pw.flush();
		pw.close();
	}

}
