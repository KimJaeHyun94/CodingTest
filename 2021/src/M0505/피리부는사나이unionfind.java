package M0505;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class 피리부는사나이unionfind {
	static int N, M;
	static int map[][];
	static int dirs[][] = { { 0, -1 }, { 0, 1 }, { 1, 0 }, { -1, 0 } };
	static int dp[][];
	static int parents[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		dp = new int[N][M];
		parents = new int[N * M];

		for (int i = 0; i < parents.length; i++) {
			parents[i] = i;
		}
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = findDir(line.charAt(j));
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int cur = i * M + j;
				int next = (i + dirs[map[i][j]][0]) * M + j + dirs[map[i][j]][1];

				if (findSet(cur) != findSet(next)) {
					union(cur, next);
				}
			}
		}
		
		HashSet<Integer> set = new HashSet();
		for (int i = 0; i < parents.length; i++) {
			set.add(findSet(parents[i]));
		}
		System.out.println(set.size());

	}

	private static void union(int a, int b) {
		int pa = findSet(a);
		int pb = findSet(b);

		if (pa != pb) {
			if (pa > pb) {
				parents[pa] = pb;

			} else
				parents[pb] = pa;

		}
	}

	private static int findSet(int a) {
		if (parents[a] == a)
			return a;
		return findSet(parents[a]);
	}

	private static int findDir(char c) {
		switch (c) {
		case 'L':
			return 0;
		case 'R':
			return 1;
		case 'D':
			return 2;
		case 'U':
			return 3;
		}
		return -1;
	}
}
