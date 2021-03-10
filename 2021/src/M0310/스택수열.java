package M0310;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 스택수열 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		int value = 0;
		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < N; i++) {
			int check = Integer.parseInt(br.readLine());

			if (value < check) {
				for (int j = value + 1; j <= check; j++) {
					stack.add(j);
					sb.append("+").append("\n");
				}
				value = check;
			} else if (stack.peek() != check) {
				System.out.println("NO");
				System.exit(0);
			}
			stack.pop();
			sb.append("-").append("\n");
		}
		System.out.println(sb);
	}
}
