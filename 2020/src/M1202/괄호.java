package M1202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class 괄호 {
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			Stack<String> stack = new Stack<>();
			String line = br.readLine();
			boolean flag = true;
			for (int j = 0; j < line.length(); j++) {
				if (line.charAt(j) == '(')
					stack.push("(");
				else if (stack.isEmpty() && line.charAt(j) == ')')
					flag = false;
				else if(line.charAt(j)==')')
					stack.pop();
			}

			if (stack.isEmpty() && flag) {
				sb.append("YES" + "\n");
			} else {
				sb.append("NO" + "\n");
			}
		}
		System.out.println(sb);
	}

}
