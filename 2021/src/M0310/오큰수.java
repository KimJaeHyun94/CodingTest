package M0310;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class 오큰수 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		Stack<Integer> stack = new Stack<>();
		int arr[] = new int[N];
		int result[] = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = N - 1; i >= 0; i--) {
			while (!stack.isEmpty()) {
				int num = stack.peek(); // 맨 위에 수
				if (num <= arr[i]) {
					stack.pop();
				} else
					break;
			}
			if (stack.isEmpty()) { 
				result[i] = -1;
			} else {
				result[i] = stack.peek();
			}
			stack.add(arr[i]);
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append(result[i]+" ");
		}
		System.out.println(sb);
	}
}
