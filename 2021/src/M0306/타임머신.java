package M0306;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 타임머신 {

	static int N, M;
	static int INF = 987654321;
	static ArrayList<Node> list;
	static long dist[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			list.add(new Node(a, b, c));
		}

		if (BellmanFord()) {
			for (int i = 2; i <= N; i++) {
				if(dist[i]==INF) {
					System.out.println(-1);
				}else {
					System.out.println(dist[i]);
				}
			}
		}else {
			System.out.println(-1);
		}

	}

	private static boolean BellmanFord() {
		boolean flag = false;
		dist = new long[N + 1];
		Arrays.fill(dist, INF);
		dist[1] = 0;

		for (int i = 0; i <= N; i++) {
			for (Node child : list) {
				if (dist[child.s] != INF && dist[child.e] > dist[child.s] + child.w) {
					dist[child.e] = dist[child.s] + child.w;
				}
			}
		}

		for (Node child : list) {
			if (dist[child.s] != INF && dist[child.e] > dist[child.s] + child.w)
				return false;
		}

		return true;
	}

	static class Node {
		int s;
		int e;
		int w;

		public Node(int s, int e, int w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}
	}

}
