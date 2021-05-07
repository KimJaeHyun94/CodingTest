package M0430;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class 골목길_2 {
	static int N, M;
	static List<Node> list[];
	static int dist[];
	static int path[];
	static boolean visited[];
	static int INF = 987654321;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new List[N + 1];
		path = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			list[a].add(new Node(b, c * -1));
		}

		BellmanFord();
		
		if (dist[N] == Integer.MIN_VALUE || dist[N] == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			Stack<Integer> stack = new Stack<>();
			int idx = N;
			while (true) {
				stack.add(idx);
				if (idx == 1)
					break;
				else
					idx = path[idx];
			}
			while (!stack.isEmpty()) {
				System.out.print(stack.pop() + " ");
			}
		}
	}

	private static void BellmanFord() {
		dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[1] = 0;

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				for (Node child : list[j]) {
					if (dist[j] == Integer.MIN_VALUE)
						dist[child.e] = Integer.MIN_VALUE;
					else if (dist[j] == Integer.MAX_VALUE)
						continue;
					else if (dist[child.e] > dist[j] + child.w) {
						dist[child.e] = dist[j] + child.w;
						path[child.e] = j;
						if (i == N) {
							dist[child.e] = Integer.MIN_VALUE;
						}
					}
				}
			}
		}
		return;
	}

	static class Node {
		int e;
		int w;

		public Node(int e, int w) {
			this.e = e;
			this.w = w;
		}
	}
}
