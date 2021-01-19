package M1228;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class 도시분할계획 {
	static int[] parents;
	static int[] rank;
	static int[][] arr;
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		parents = new int[N];
		rank = new int[N];

		int[][] edges = new int[M][3];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			edges[i][0] = Integer.parseInt(st.nextToken()) - 1;
			edges[i][1] = Integer.parseInt(st.nextToken()) - 1;
			edges[i][2] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(edges, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[2], o2[2]);
			}
		});
		
		for (int i = 0; i < N; i++) {
			makeSet(i);
		}
		int result = 0;
		int cnt = 0;
		for (int i = 0; i < edges.length; i++) {
			int a = findSet(edges[i][0]);
			int b = findSet(edges[i][1]);
			if (a == b)
				continue;
			union(a, b);
			result += edges[i][2];
			max = Math.max(max, edges[i][2]);
			cnt++;
			if (cnt == N - 1)
				break;
		}
		System.out.println(result-max);
	}

	static void makeSet(int x) {
		parents[x] = x;
	}

	static int findSet(int x) {
		if (x == parents[x]) {
			return x;
		} else {
			parents[x] = findSet(parents[x]);
			return parents[x];
		}
	}

	static void union(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);
		if (rank[px] > rank[py]) {
			parents[py] = px;
		} else {
			parents[px] = py;
			if (rank[px] == rank[py]) {
				rank[py]++;
			}
		}
	}
}
