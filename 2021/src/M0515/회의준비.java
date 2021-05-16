package M0515;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class 회의준비 {
	static int N, M;
	static int dist[][], max[];
	static int INF = 987654321;

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

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (dist[i][j] < INF && dist[i][j] > max[i]) {	//각각의 숫자에서 가장 높은거
					max[i] = dist[i][j];
				}
			}
		}

		boolean visited[] = new boolean[N + 1];
		ArrayList<Integer> list = new ArrayList<>();

		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				int idx = i;
				for (int j = i; j <= N; j++) {
					if (dist[i][j] < INF) { // 연결되어있으면
						visited[j] = true;
						if (max[idx] > max[j])
							idx = j;
					}
				}
				list.add(idx);		//대표가 될 수 있으면
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
