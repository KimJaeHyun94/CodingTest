package M0401;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 역사 {
	static int N, K, S;
	static int arr[][];
	static int reverse[][];
	static int INF = 987654321;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		arr = new int[N + 1][N + 1];
		reverse = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j)
					continue;
				arr[i][j] = INF;
				reverse[i][j] = INF;
			}
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			arr[s][e] = 1;
			reverse[e][s] = 1;
		}

		FloydWarshall();

		S = Integer.parseInt(br.readLine());

		for (int i = 0; i < S; i++) {
			st = new StringTokenizer(br.readLine());
			int f = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if (arr[f][b] != INF) {
				System.out.println(-1);
			} else if (reverse[f][b] != INF) {
				System.out.println(1);
			} else {
				System.out.println(0);
			}
		}
	}

	private static void FloydWarshall() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
					reverse[i][j] = Math.min(reverse[i][j], reverse[i][k] + reverse[k][j]);
				}
			}
		}
	}
}
