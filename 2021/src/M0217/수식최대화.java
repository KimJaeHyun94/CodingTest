package M0217;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;
/**
 * 
 * @author 김재현
 * @see https://iamheesoo.github.io/blog/algo-prog67257
 */
public class 수식최대화 {

	static HashSet<Character> set = new HashSet<>();
	static char op[];
	static boolean visited[];
	static long ans;
	static HashMap<Character, Integer> hm = new HashMap<>();

	public static void main(String[] args) {
		System.out.println(solution("100-200*300-500+20"));
	}

	public static long solution(String expression) {
		for (int i = 0; i < expression.length(); i++) {
			if (expression.charAt(i) == '+' || expression.charAt(i) == '-' || expression.charAt(i) == '*') {
				set.add(expression.charAt(i));
			}
		}
		op = new char[set.size()];
		visited = new boolean[set.size()];

		int idx = 0;
		for (char c : set) {
			op[idx++] = c;
		}
		ans = 0;
		permutation(0, expression);
		return ans;
	}

	private static void permutation(int idx, String expression) {
		if (idx == op.length) {
			ArrayList<String> list = postfix(expression);
			long ret = calculate(list);
			ans = Math.max(ans, Math.abs(ret));
		}

		for (int i = 0; i < op.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				hm.put(op[i], idx);
				permutation(idx + 1, expression);
				visited[i] = false;
			}
		}
	}

	private static long calculate(ArrayList<String> list) {
		Stack<Long> st = new Stack<>(); // 숫자 저장
		for (String s : list) {
			if (s.equals("+")) {
				long b = st.pop();
				long a = st.pop();
				st.push(a + b);
			} else if (s.equals("-")) {
				long b = st.pop();
				long a = st.pop();
				st.push(a - b);
			} else if (s.equals("*")) {
				long b = st.pop();
				long a = st.pop();
				st.push(a * b);
			} else
				st.push(Long.parseLong(s));
		}
		return st.pop();
	}

	private static ArrayList<String> postfix(String expression) {
		ArrayList<String> result = new ArrayList<>();
		StringBuilder num = new StringBuilder();
		Stack<Character> st = new Stack<>();

		for (int i = 0; i < expression.length(); i++) {
			char ch = expression.charAt(i);
			if (ch == '+' || ch == '-' || ch == '*') {
				result.add(num.toString());
				num.setLength(0);

				while (!st.isEmpty() && hm.get(st.peek()) >= hm.get(ch))
					result.add(st.pop() + "");
				st.push(ch);
			} else
				num.append(ch);
		}

		if (num.length() > 0)
			result.add(num.toString());
		while (!st.isEmpty()) {
			result.add(st.pop() + "");
		}

		return result;
	}
}
