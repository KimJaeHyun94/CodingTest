package M1224;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class 단어수학 {
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String[] str = new String[N];
		for (int i = 0; i < N; i++) {
			str[i] = br.readLine();
		}
		Integer[] alphabet = new Integer[26];
		Arrays.fill(alphabet, 0);
		
		for (int i = 0; i < N; i++) {
			String t = str[i];
			for (int j = 0; j < t.length(); j++) {
				alphabet[(t.charAt(j) - 65)] += (int) Math.pow(10, (t.length() - j - 1));
			}
		}
		
		int sum = 0;
		Arrays.sort(alphabet, Collections.reverseOrder());
		int idx = 9;
		for (int i = 0; i < alphabet.length; i++) {
			if (alphabet[i] != 0) {
				sum += alphabet[i] * idx--;
			}
			if (alphabet[i] == 0)
				break;
		}

		System.out.println(sum);

	}
}
