package M0310;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 균형잡힌세상 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			String line = br.readLine();
			if (line.equals(".")) {
				break;
			}
			Stack<Character> stack = new Stack<>();
			boolean flag = true;
			 for (int i = 0; i < line.length(); i++) {
				if (line.charAt(i) == '(' || line.charAt(i) == '[') {
					stack.add(line.charAt(i));
				} else if (line.charAt(i) == ')') {
					if (stack.isEmpty() || stack.pop()!='(') {
						flag = false;
						break;
					}
				}else if(line.charAt(i)==']') {
					if (stack.isEmpty() || stack.pop()!='[') {
						flag= false;
						break;
					}
				}
			}
			if (!stack.isEmpty() || !flag) {
				System.out.println("no");
			} else {
				System.out.println("yes");
			}
		}
	}
}
