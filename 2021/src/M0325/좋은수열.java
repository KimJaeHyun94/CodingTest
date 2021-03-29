package M0325;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 좋은수열 {
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		DFS(0, "");
	}

	private static void DFS(int cnt, String str) {
		if (cnt == N) {
			if(isOK(str)) {
			System.out.println(str);
			System.exit(0);
			}
		}

		for (int i = 1; i <= 3; i++) {
			if (isOK(str+i)) {
				DFS(cnt + 1, str+i);
			}
		}
	}

	private static boolean isOK(String str) {
		int len = str.length();
		for (int i = 1; i <= len / 2; i++) {
			String front = str.substring(len - 2 * i, len - i);
			String back = str.substring(len - i, len);
			if (front.equals(back))
				return false;
		}
		return true;
	}
}
