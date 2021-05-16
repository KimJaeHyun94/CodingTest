package 누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 행성탐사 {
	static int M, N, K;
	static char map[][];
	static int js[][], is[][], os[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());

		map = new char[M + 1][N + 1];
		js = new int[M + 1][N + 1];
		is = new int[M + 1][N + 1];
		os = new int[M + 1][N + 1];

		for (int i = 1; i <= M; i++) {
			String line = br.readLine();
			for (int j = 1; j <= N; j++) {
				map[i][j] = line.charAt(j-1);
			}
		}

		for (int i = 1; i <= M; i++) {
			for (int j = 1; j <= N; j++) {
				js[i][j] = (map[i][j] == 'J' ? 1 : 0) + js[i - 1][j] + js[i][j - 1] - js[i - 1][j - 1];
				is[i][j] = (map[i][j] == 'I' ? 1 : 0) + is[i - 1][j] + is[i][j - 1] - is[i - 1][j - 1];
				os[i][j] = (map[i][j] == 'O' ? 1 : 0) + os[i - 1][j] + os[i][j - 1] - os[i - 1][j - 1];
			}
		}
		StringBuilder stb = new StringBuilder();
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			int sa = js[c][d] - js[a - 1][d] - js[c][b - 1] + js[a - 1][b - 1];
			int sb = os[c][d] - os[a - 1][d] - os[c][b - 1] + os[a - 1][b - 1];
			int sc = is[c][d] - is[a - 1][d] - is[c][b - 1] + is[a - 1][b - 1];
			stb.append(sa).append(" ").append(sb).append(" ").append(sc).append("\n");
		}
		System.out.println(stb);
	}
}
