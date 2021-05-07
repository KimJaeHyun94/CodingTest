package M0430;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 오민식의고민 {
	static int N, S, E, M;
	static ArrayList<Node> list;
	static long max[];
	static long dist[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		list = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken())*-1;

			list.add(new Node(a, b, c));
		}

		max = new long[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			max[i] = Long.parseLong(st.nextToken());
		}

		BellmanFord();

		if (dist[E] == Long.MIN_VALUE)
			System.out.println("gg");
		else if (dist[E] == Long.MAX_VALUE)
			System.out.println("Gee");
		else
			System.out.println(dist[E]);

	}

	private static void BellmanFord() {
		dist = new long[N];
		Arrays.fill(dist, Long.MIN_VALUE);
		
		dist[S] = max[S];
		for (int i = 1; i <= 2*N; i++) {
			for (Node child : list) {
				
				if (dist[child.s] == Long.MIN_VALUE)
					continue;
				else if (dist[child.s] == Long.MAX_VALUE)
					dist[child.e] = Long.MAX_VALUE;
				else if (dist[child.e] < dist[child.s] + max[child.e] + child.w) {
				
					dist[child.e] = dist[child.s] + max[child.e] + child.w;

					if (i >= N)
						dist[child.e] = Long.MAX_VALUE;
				}
			}
		}
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
