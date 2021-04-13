package M0413;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class 산업스파이의편지 {
	static boolean visited[];
	static HashSet<Integer> set;
	static String origin;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int tc = 0; tc < T; tc++) {
			origin = br.readLine();
			visited = new boolean[origin.length()];
			set = new HashSet<>();
			DFS(0, "");

			sb.append(set.size()).append("\n");
		}
		System.out.println(sb);
	}

	private static void DFS(int i, String str) {
		if (str.length() >= 1) {
			int num = Integer.parseInt(str);

			if (isPrime(num)) {
				set.add(num);
			}
		}

		for (int j = 0; j < origin.length(); j++) {
			if (!visited[j]) {
				visited[j] = true;
				DFS(i + 1, str + origin.charAt(j));
				visited[j] = false;
			}
		}
	}

	private static boolean isPrime(int s) {
		if (s <= 1)
			return false;
		for (int i = 2; i * i <= s; i++) {
			if (s % i == 0)
				return false;
		}
		return true;
	}
}
