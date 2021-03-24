package M0323;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 괄호추가하기3 {
	static int N;
	static int max[][];
	static int min[][];
	static char s[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		max = new int[N][N];
		min = new int[N][N];
		s = new char[N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(max[i], Integer.MIN_VALUE);
			Arrays.fill(min[i], Integer.MAX_VALUE);
		}

		String line = br.readLine();
		for (int i = 0; i < N; i++) {
			char ch = line.charAt(i);
			s[i] = ch;
			if (Character.isDigit(ch)) {
				max[i][i] = ch - '0';
				min[i][i] = ch - '0';
			}
		}

		for (int j = 2; j < N; j += 2) {
			for (int i = 0; i < N - j; i += 2) {
				for (int k = 2; k <= j; k += 2) {
					int[] num = new int[4];
					int op = i + k - 1;
					num[0] = calc(s[op],max[i][i + k - 2], max[i + k][i + j]);
					num[1] = calc(s[op],max[i][i + k - 2], min[i + k][i + j]);
					num[2] = calc(s[op],min[i][i + k - 2], max[i + k][i + j]);
					num[3] = calc(s[op],min[i][i + k - 2], min[i + k][i + j]);
					Arrays.sort(num);
					max[i][i + j] = Math.max(max[i][i + j], num[3]);
					min[i][i + j] = Math.min(min[i][i + j], num[0]);
				}
			}
		}
		System.out.println(max[0][N - 1]);
	}

	public static int calc(char op, int n1, int n2) {
		switch (op) {
		case '+':
			return n1 + n2;
		case '-':
			return n1 - n2;
		case '*':
			return n1 * n2;
		}
		return -1;
	}

}
