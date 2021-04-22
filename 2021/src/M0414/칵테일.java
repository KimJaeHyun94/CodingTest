package M0414;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 칵테일 {
	static List<Node> graph[];
	static long arr[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		graph = new List[N];
		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
		}
		arr = new long[N];
		long mx = 1;

		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());

			graph[a].add(new Node(b, p, q));
			graph[b].add(new Node(a, q, p));
			
			mx = mx * getLCM(p,q,getGCD(p,q));
		}
		arr[0] = mx;
		DFS(0, -1);
		long g = arr[0];
		for (int i = 1; i < N; i++) {
			if (arr[i] == 0)
				continue;
			g = getGCD(g, arr[i]);
		}

		for (int i = 0; i < N; i++) {
			System.out.print(arr[i] / g + " ");
		}
	}

	private static void DFS(int x, int p) {
		for (Node child : graph[x]) {
			if (child.e == p)
				continue;
			arr[child.e] = (arr[x] * child.q / child.p);
			DFS(child.e, x);
		}

	}

	private static long getLCM(long a, long b, long gcd) {
		return a * b / gcd;
	}

	private static long getGCD(long a, long b) {
		if (b == 0)
			return a;
		return getGCD(b, a % b);
	}

	static class Node {
		int e;
		int p;
		int q;

		public Node(int e, int p, int q) {
			this.e = e;
			this.p = p;
			this.q = q;
		}

	}
}
