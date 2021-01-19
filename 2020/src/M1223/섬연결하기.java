package M1223;

import java.util.Arrays;
import java.util.Comparator;

public class 섬연결하기 {
	static int[] parents;
	static int[] rank;

	public static void main(String[] args) {
		System.out.println(solution(4, new int[][] { { 0, 1, 1 }, { 0, 2, 2 }, { 2, 3, 1 } }));
	}

	public static int solution(int n, int[][] costs) {
		int answer = 0;
		Arrays.sort(costs, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// o1[2] o2[2]
				return Integer.compare(o1[2], o2[2]);
			}
		});

		parents = new int[n];
		rank = new int[n];
		for (int i = 0; i < n; i++) {
			makeSet(i);
		}
		int cnt = 0;
		for (int i = 0; i < costs.length; i++) {
			int a = findSet(costs[i][0]);
			int b = findSet(costs[i][1]);
			if (a == b)
				continue;
			union(a, b);
			answer += costs[i][2];
			cnt++;
			if (cnt == n - 1)
				break;
		}

		return answer;
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

	static int findSet(int x) {
		if (x == parents[x]) {
			return x;
		} else {
			parents[x] = findSet(parents[x]);
			return parents[x];
		}
	}

	static void makeSet(int x) {
		parents[x] = x;
	}

}
