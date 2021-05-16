package M0512;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 문자열교환 {
	static int bcnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();

		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == 'b')
				bcnt++;
		}
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < str.length(); i++) {
			min = Math.min(min, sol(str, i));
		}

		System.out.println(min);
	}

	private static int sol(String str, int idx) {

		int result = 0;
		for (int i = idx; i < idx + bcnt; i++) {
			if (str.charAt((i + 1) % str.length()) == 'a') {
				result++;
			}
		}
		return result;
	}

}
