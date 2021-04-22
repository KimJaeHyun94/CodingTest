package M0422;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

public class 애너그램DFS {
	static StringBuilder sb = new StringBuilder();
	static int len;
	static char[] words;
	static int alphabet[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			words = br.readLine().toCharArray();
			Arrays.sort(words);
			len = words.length;
			alphabet = new int[26];
			for (int j = 0; j < len; j++) {
				alphabet[words[j] - 'a']++;
			}
			DFS(0, "");
		}
		System.out.println(sb);
	}

	private static void DFS(int idx, String s) {
		if (idx == len) {
			sb.append(s).append("\n");
			return;
		}

		for (int i = 0; i < 26; i++) {
			if (alphabet[i] > 0) {
				alphabet[i]--;
				char ch = (char) ('a' + i);
				DFS(idx + 1, s + ch);
				alphabet[i]++;
			}
		}
	}
}