package M1202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 잃어버린괄호 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		
		String arr[] = line.split("-");
		int ans = 0;
		String first[] = arr[0].split("\\+");
		
		for (String string : first) {
			ans+=Integer.parseInt(string);
		}
		
		int minus=0;
		for (int i = 1; i < arr.length; i++) {
			String[] tmp = arr[i].split("\\+");
			for (String string : tmp) {
				minus+=Integer.parseInt(string);
			}
		}
		System.out.println(ans-minus);
	}

}
