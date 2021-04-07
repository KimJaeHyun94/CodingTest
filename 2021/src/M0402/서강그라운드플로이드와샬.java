package M0402;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 서강그라운드플로이드와샬 {
	static int arr[][];
	static int INF = 987654321;
	static int N, K, M;
	static int item[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		item = new int[N + 1];
		arr = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j)
					continue;
				arr[i][j] = INF;
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			item[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			arr[a][b] = v;
			arr[b][a] = v;
		}
		FloydWarshall();
		int max = 0;
		for (int i = 1; i <= N; i++) {
			int cnt = item[i];
			for (int j = 1; j <= N; j++) {
				if (i == j || arr[i][j] > K)
					continue;
				cnt += item[j];
			}
			max = Math.max(cnt, max);
		}
		System.out.println(max);
	}

	private static void FloydWarshall() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
				}
			}
		}
	}
}
