package M0506;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 고냥이 {
	static int N;
	static String str;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		str = br.readLine();

		if (str.length() <= N) {
			System.out.println(str.length());
			return;
		}

		int left = 0;
		int right = 0;
		int max = 1;
		int alpha[] = new int[26];

		int cur = 0;
		while (right < str.length()) {
			if (right == str.length())
				break;
			if (alpha[str.charAt(right) - 'a']++ == 0) {
				cur++;
			}
			if (cur <= N) {
				max = Math.max(max, right - left + 1);
			}

			while (cur > N && left < right) {
				if (alpha[str.charAt(left) - 'a']-- == 1) {
					cur--;
				}
				left++;
			}
			right++;
		}
		System.out.println(max);

	}
}
