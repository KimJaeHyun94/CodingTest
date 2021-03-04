package M0304;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 옥상정원꾸미기 {
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		Stack<Long> stack = new Stack<>();
		long sum = 0;
		for (int i = 0; i < N; i++) {
			long build = Long.parseLong(br.readLine());
			while (!stack.isEmpty()) {
				if (stack.peek() <= build) {
					stack.pop();
				} else {
					break;
				}
			}
			sum += stack.size();
			stack.add(build);
		}
		System.out.println(sum);
	}

}
