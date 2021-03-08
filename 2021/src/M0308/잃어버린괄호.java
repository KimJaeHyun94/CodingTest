package M0308;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 잃어버린괄호 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		String temp[] = str.split("-");

		int plus = 0;
		int minus = 0;
		for (int i = 0; i < temp.length; i++) {
			String tmp[] = temp[i].split("\\+");
			for (int j = 0; j < tmp.length; j++) {
				if (i == 0) {
					plus += Integer.parseInt(tmp[j]);
				} else {
					minus += Integer.parseInt(tmp[j]);
				}
			}
		}
		System.out.println(plus-minus);
	}
}
