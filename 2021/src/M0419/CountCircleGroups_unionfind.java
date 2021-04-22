package M0419;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CountCircleGroups_unionfind {
	static int T;
	static int N;
	static int unit[][];
	static boolean visited[];
	static int parents[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine());

			unit = new int[N][3];
			visited = new boolean[N];
			parents = new int[N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				unit[i][0] = Integer.parseInt(st.nextToken());
				unit[i][1] = Integer.parseInt(st.nextToken());
				unit[i][2] = Integer.parseInt(st.nextToken());
				parents[i] = i;
			}
			int cnt = N;
			for (int i = 0; i < N - 1; i++) {
				for (int j = i + 1; j < N; j++) {
					double len = Math.sqrt(Math.pow(unit[i][0] - unit[j][0], 2) + Math.pow(unit[i][1] - unit[j][1], 2));
					if (len <= unit[i][2] + unit[j][2]) {
						int si = findSet(i);
						int sj = findSet(j);
						if (si != sj) {
							union(si, sj);
							cnt--;
						}
					}
				}
			}
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
	}

	private static void union(int i, int j) {
		int si = findSet(i);
		int sj = findSet(j);

		if (si != sj) {
			if (si < sj) {
				parents[sj] = si;
			} else
				parents[si] = sj;
		}

	}

	private static int findSet(int x) {
		if (x == parents[x])
			return x;
		else
			return parents[x] = findSet(parents[x]);
	}

	private static void DFS(int idx) {
		visited[idx] = true;

		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				double len = Math.sqrt(Math.pow(unit[idx][0] - unit[i][0], 2) + Math.pow(unit[idx][1] - unit[i][1], 2));
				if (len <= unit[idx][2] + unit[i][2]) {
					DFS(i);
				}
			}
		}
	}
}
