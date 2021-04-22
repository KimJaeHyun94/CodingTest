package M0419;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class 친구비 {
	static int N, M, C;
	static int wants[];
	static int parents[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		parents = new int[N + 1];
		wants = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			wants[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			union(s, e);
		}
		boolean visited[] = new boolean[N + 1];
		int cost = 0;
		int cnt = 0;

		for (int i = 1; i <= N; i++) {
			int fi = findSet(i);
			if (visited[fi]) {
				cnt++;
				continue;
			}

			if (wants[fi] <= C) {
				visited[fi] = true;
				C -= wants[i];
				cost += wants[i];
				cnt++;
			}

		}

		if (cnt == N) {
			System.out.println(cost);
		} else {
			System.out.println("Oh no");
		}
	}

	private static void union(int i, int j) {
		int si = findSet(i);
		int sj = findSet(j);

		if (si > sj) {
			parents[si] = sj;
		} else
			parents[sj] = si;

		if (wants[si] > wants[sj]) {
			wants[si] = wants[sj];
		} else {
			wants[sj] = wants[si];
		}
	}

	private static int findSet(int x) {
		if (x == parents[x])
			return x;
		else
			return parents[x] = findSet(parents[x]);
	}

}
