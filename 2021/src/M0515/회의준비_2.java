package M0515;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class 회의준비_2 {
	static int N, M;
	static int dist[][], max[];
	static int INF = 987654321;
	static boolean visited[] = new boolean[N + 1];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		dist = new int[N + 1][N + 1];
		max = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j)
					continue;
				dist[i][j] = INF;
			}
		}

		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			dist[a][b] = 1;
			dist[b][a] = 1;
		}

		FloydWarshall();

		ArrayList<Integer> list = new ArrayList<>();
		visited = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				list.add(sol(i));
			}
		}
		Collections.sort(list);
		StringBuilder sb = new StringBuilder();
		sb.append(list.size()).append("\n");
		for (Integer integer : list) {
			sb.append(integer).append("\n");
		}
		System.out.println(sb);
	}

	private static int sol(int start) {
		int ret = 0, check = INF;
		for (int i = 1; i <= N; i++) {
			if (dist[start][i] != INF) {
				visited[i] = true;
				int tmp = 0;
				for (int j = 1; j <= N; j++) {
					if (dist[i][j] != INF && tmp < dist[i][j])	
						tmp = dist[i][j];
				}
				if (check > tmp) {
					check = tmp;
					ret = i;
				}
			}
		}
		return ret;
	}

	private static void FloydWarshall() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
	}
}
