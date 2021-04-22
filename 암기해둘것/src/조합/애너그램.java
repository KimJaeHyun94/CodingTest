package 조합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 애너그램 {
	static StringBuilder sb = new StringBuilder();
	static int len;
	static char[] words;
	static boolean visited[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			words = br.readLine().toCharArray();
			Arrays.sort(words);
			do {
				for (Character ch : words) {
					sb.append(ch);
				}
				sb.append("\n");
			} while (nextPermutation());
		}
		System.out.println(sb);
	}

	private static boolean nextPermutation() {
		// step 1 : i찾기
		int i;
		for (i = words.length - 2; i >= 0; i--) {
			if (words[i] < words[i + 1]) {
				break;
			}
		}
		// 맨 마지막 경우는 위에 만족하는 i가 없다!
		if (i < 0)
			return false;
		// step 2 : j찾기
		int j;
		for (j = words.length - 1; j >= 0; j--) {
			if (words[i] < words[j])
				break;
		}
		// step 3 : swap
		swap(i, j);

		// step 4 : reverse
		for (int a = i + 1, b = words.length - 1; a < b; a++, b--) {
			swap(a, b);
		}
		return true;
	}

	private static void swap(int a, int b) {
		char temp = words[a];
		words[a] = words[b];
		words[b] = temp;

	}
}
