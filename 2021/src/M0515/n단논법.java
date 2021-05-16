package M0515;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n단논법 {
	static int N, M;
	static int dist[][];
	static int INF = 987654321;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dist = new int[26][26];
		for (int i = 0; i < 26; i++) {
			for (int j = 0; j < 26; j++) {
				if (i == j)
					continue;
				dist[i][j] = INF;
			}
		}
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = st.nextToken().charAt(0) - 'a';
			String is = st.nextToken();
			int b = st.nextToken().charAt(0) - 'a';

			dist[a][b] = 1;
		}

		FloydWarshall();
		M = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = st.nextToken().charAt(0) - 'a';
			String is = st.nextToken();
			int b = st.nextToken().charAt(0) - 'a';

			if (dist[a][b] == INF)
				sb.append("F").append("\n");
			else
				sb.append("T").append("\n");
		}
		System.out.println(sb);

	}

	private static void FloydWarshall() {
		for (int k = 0; k < 26; k++) {
			for (int i = 0; i < 26; i++) {
				for (int j = 0; j < 26; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
	}
}
