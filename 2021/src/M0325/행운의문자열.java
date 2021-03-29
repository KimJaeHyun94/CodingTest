package M0325;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 행운의문자열 {
	static int N;
	static int[] alpha;
	static boolean visited[];
	static int ct = 0;
	static String line;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		line = br.readLine();

		alpha = new int[26];

		for (int i = 0; i < line.length(); i++) {
			alpha[line.charAt(i) - 'a']++;
		}

		DFS(0, "");
		System.out.println(ct);
	}

	private static void DFS(int idx, String str) {
		if (idx == line.length()) {
			ct++;
			return;
		}

		for (int i = 0; i < 26; i++) {
			if (alpha[i] == 0)
				continue;

			if (str != "" && str.charAt(str.length()-1) == (char) ('a' + i))
				continue;
			
			alpha[i]--;
			DFS(idx + 1, str + (char) ('a' + i));
			alpha[i]++;

		}
	}

	
}
