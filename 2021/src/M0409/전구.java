package M0409;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class 전구 {
	static ArrayList<Integer> stack;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int idx[] = new int[N + 1];
		int rev[] = new int[N + 1];
		int con[] = new int[N + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			idx[tmp] = i;
			rev[i] = tmp;
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			con[i] = idx[Integer.parseInt(st.nextToken())];
		}

		stack = new ArrayList<>();
		ArrayList<Node> trace = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			if (stack.isEmpty() || stack.get(stack.size() - 1) < con[i]) {
				stack.add(con[i]);
				trace.add(new Node(stack.size(), con[i]));
			} else {
				int low = lowerbound(0, stack.size() - 1, con[i]);
				stack.set(low, con[i]);
				trace.add(new Node(low+1, con[i]));
			}
		}

		ArrayList<Integer> ans = new ArrayList<>();
		int len = stack.size();

		for (int i = trace.size() - 1; i >= 0; i--) {
			if (trace.get(i).idx != len)
				continue;
			ans.add(rev[trace.get(i).sw]);
			len--;
		}
		Collections.sort(ans);
		System.out.println(ans.size());
		for (Integer integer : ans) {
			System.out.print(integer + " ");
		}
	}

	private static int lowerbound(int left, int right, int target) {
		while (left < right) {
			int mid = (left + right) >> 1;
			if (stack.get(mid) < target)
				left = mid + 1;
			else
				right = mid;
		}
		return right;
	}

	static class Node {
		int idx;
		int sw;

		public Node(int idx, int sw) {
			this.idx = idx;
			this.sw = sw;
		}

	}
}