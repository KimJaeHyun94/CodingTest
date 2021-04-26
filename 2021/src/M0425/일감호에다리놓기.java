package M0425;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class 일감호에다리놓기 {
	static int N, M;
	static long K;
	static int stone[];
	static ArrayList<Node> list;
	static final int INF = 987654321;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		list = new ArrayList<>();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Long.parseLong(st.nextToken());

		if (M <= 1) {
			System.out.println("YES");
			System.exit(0);
		}
		stone = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			stone[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (a > b && a != N) {
				int temp = a;
				a = b;
				b = temp;
			}
			list.add(new Node(a - 1, b - 1));
		}
		Collections.sort(list);
		int last = 0;

		long sum = 0;
		for (int i = 0; i < list.size(); i++) {
			int min = INF;
			int left = list.get(i).s;
			int right = list.get(i).e;

			for (int j = last; j <= left; j++) {
				min = Math.min(min, stone[j]);
			}
			if (last == 0 && list.get(M - 1).e != 0) {
				int end = list.get(M - 1).e;
				for (int j = N - 1; j >= end; j--) {
					min = Math.min(min, stone[j]);
				}
			}
			sum += min;
			last = right;
		}

		if (sum <= K) {
			System.out.println("YES");
		} else
			System.out.println("NO");
	}

	static class Node implements Comparable<Node> {
		int s;
		int e;

		public Node(int s, int e) {
			this.s = s;
			this.e = e;
		}

		@Override
		public int compareTo(Node o) {
			return this.s - o.s;
		}

	}
}
