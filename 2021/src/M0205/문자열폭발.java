package M0205;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 문자열폭발 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		String explore = br.readLine();
		char last = explore.charAt(explore.length() - 1);
		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < line.length(); i++) {
			char ch = line.charAt(i);
			stack.push(ch); // 스택에 하나씩 넣는다.

			if (stack.size() >= explore.length()) {
				if (stack.peek() == last) { // 만약에 스택의 마지막 글자가 폭발할 넘의 마지막 글자와 같다면
					boolean bomb = true;
					int idx = 0;
					for (int j = explore.length() - 1; j >= 0; j--, idx++) {
						if (explore.charAt(j) != stack.get(stack.size() - 1 - idx)) { // 거꾸로 돌리면서 비교한다(stack의 맨 위랑 터트릴 폭탄의 맨 뒤)
							bomb = false;
							break;
						}
					}
					if (bomb) // 터트릴 수 있다면 위에서부터 터트린다.
						for (int j = 0; j < explore.length(); j++) {
							stack.pop();
						}
				}
			}
		}
		if (stack.size() == 0) {
			System.out.println("FRULA");
		} else {
			StringBuilder sb = new StringBuilder();
			for (Character child : stack) {
				sb.append(child);
			}
			System.out.println(sb);
		}
	}

}
