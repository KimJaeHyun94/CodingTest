package 분할정복;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 종이접기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();

		for (int tc = 0; tc < T; tc++) {
			String str = br.readLine();
			int len = str.length();
			if (len % 2 == 0)
				sb.append("NO").append("\n");
			else if (check(str, 0, len - 1)) {
				sb.append("YES").append("\n");
			} else
				sb.append("NO").append("\n");
		}
		System.out.println(sb);
	}

	private static boolean check(String str, int s, int e) {
		if (s >= e)
			return true;
		int left = s;
		int right = e;
		while (left < right) {
			if (str.charAt(left) == str.charAt(right))
				return false;
			left++;
			right--;
		}
		return check(str, s, right - 1);
	}

}
