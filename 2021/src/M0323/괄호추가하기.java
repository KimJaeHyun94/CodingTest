package M0323;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 괄호추가하기 {
	static int N;
	static ArrayList<Character> ops;
	static ArrayList<Integer> nums;
	static int ans = Integer.MIN_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		ops = new ArrayList<>();
		nums = new ArrayList<>();

		String line = br.readLine();
		for (int i = 0; i < N; i++) {
			char ch = line.charAt(i);

			if (ch == '*' || ch == '+' || ch == '-') {
				ops.add(ch);
			} else {
				nums.add(ch - '0');
			}
		}

		DFS(nums.get(0), 0);
		System.out.println(ans);
	}

	private static void DFS(int num, int cnt) {
		if (cnt >= ops.size()) {
			ans = Math.max(ans, num);
			return;
		}

		// 괄호 안치기
		int s1 = calc(ops.get(cnt), num, nums.get(cnt + 1));
		DFS(s1, cnt + 1);

		if (cnt + 1 < ops.size()) {
			int s2 = calc(ops.get(cnt + 1), nums.get(cnt + 1), nums.get(cnt + 2));

			int res = calc(ops.get(cnt), num, s2);
			DFS(res, cnt + 2);

		}
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
