package M0310;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 후위표기식 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Stack<Character> stack = new Stack<>();

		String line = br.readLine();
		for (int i = 0; i < line.length(); i++) {
			if (line.charAt(i) == '(') {
				stack.add(line.charAt(i));
			} else if (line.charAt(i) == ')') {
				while (true) {
					if (stack.isEmpty()) {
						break;
					}
					char ch = stack.pop();
					if (ch == '(') {
						break;
					}
					sb.append(ch);
				}
			} else if ('A' <= line.charAt(i) && line.charAt(i) <= 'Z') {
				sb.append(line.charAt(i));
			} else {
				int level = Find(line.charAt(i));
				while (!stack.isEmpty() && Find(stack.peek()) >= level) {
					sb.append(stack.pop());
				}
				stack.push(line.charAt(i));
			}
		}
		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		System.out.println(sb);
	}

	private static int Find(char c) {
		switch (c) {
		case '(':
		case ')':
			return 0;
		case '+':
		case '-':
			return 1;
		default:
			return 2;
		}
	}

}
