package M0325;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 미친로봇 {
	static int dirs[][] = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
	static double percent[];
	static boolean visited[][] = new boolean[31][31];
	static int N;
	static double ans = 0.0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		percent = new double[4];
		for (int i = 0; i < 4; i++) {
			percent[i] = Double.parseDouble(st.nextToken()) / 100;
		}
		visited[14][14] = true;
		DFS(14, 14, 1.0, 0);
		System.out.println(ans);
	}

	private static void DFS(int r, int c, double tmp, int cnt) {
		if (cnt == N) {
			ans += tmp;
			return;
		}

		for (int d = 0; d < dirs.length; d++) {
			int dr = r + dirs[d][0];
			int dc = c + dirs[d][1];
			if (!visited[dr][dc]) {
				visited[dr][dc] = true;
				DFS(dr, dc, tmp * percent[d], cnt + 1);
				visited[dr][dc] = false;
			}
		}

	}

}
