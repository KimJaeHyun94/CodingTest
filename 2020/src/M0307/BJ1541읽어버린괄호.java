package M0307;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1541읽어버린괄호 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int result = 0;
		String temp[]=str.split("-");
		String tmp[]=temp[0].split("\\+");
		
		for (String string : tmp) {
			result+=Integer.parseInt(string);
		}
		
		int sum=0;
		for (int i = 1; i < temp.length; i++) {
			String []tmp2=temp[i].split("\\+");
			for (String string : tmp2) {
				sum+=Integer.parseInt(string);
			}
		}
		System.out.println(result-sum);
	}
	

}
