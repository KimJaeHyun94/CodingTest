package M0423;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 영만들기 {
	static int N;
	static StringBuilder sb;
	static char[] arr = { ' ', '+', '-' };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		for (int t = 0; t < TC; t++) {
			N = Integer.parseInt(br.readLine());
			DFS(1, "1");
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void DFS(int num, String str) {
		if (num == N) {
			if (zero(str)) {
				sb.append(str).append("\n");
			}
			return;
		}

		for (int i = 0; i < arr.length; i++) {
			DFS(num + 1, str + arr[i] + (num + 1));
		}
	}

	private static boolean zero(String s) {
		String exp = s.replaceAll(" ", "");
		int sum = 0;
		String left = "";
		char op = '0';
		
		int idx = exp.length();
		for (int i = 0; i < exp.length(); i++) {
			char ch = exp.charAt(i);
			if (isOp(ch)) {
				idx = i + 1;
				op = ch;
				break;
			} else {
				left += ch;
			}
		}
		sum = Integer.parseInt(left);

		String right = "";
		for (int i = idx; i < exp.length(); i++) {
			char ch = exp.charAt(i);
			if (isOp(ch)) {
				// 계산
				sum = calc(sum, Integer.parseInt(right), op);
				op = ch;
				right = "";
			} else {
				right += ch;
				if (i == exp.length() - 1) {
					sum = calc(sum, Integer.parseInt(right), op);
				}
			}
		}
		return sum == 0;
	}

	private static int calc(int a, int b, char op) {
		if (op == '+') {
			return a + b;
		} else {
			return a - b;
		}
	}

	private static boolean isOp(char ch) {
		if (ch == '+' || ch == '-')
			return true;
		return false;
	}
}
