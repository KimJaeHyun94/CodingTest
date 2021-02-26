package M0226;

import java.util.Scanner;
import java.util.Stack;

public class 앨리스1 {
	public static void main(String args[]) {
		String line;
		Stack<Character> stack = new Stack();
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		line = sc.next();
		for (int i = 0; i < line.length(); i++) {
			if (line.charAt(i) == '(') {
				stack.push('(');
			} else {
				if (stack.isEmpty()) {
					flag = false;
					break;
				} else {
					stack.pop();
				}
			}
		}
		
		if (stack.isEmpty() || !flag) {
			System.out.println("YES");
		} else
			System.out.println("NO");
	}
}
