package M0419;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CountCircleGroups {
	static int T;
	static int N;
	static int unit[][];
	static boolean visited[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine());

			unit = new int[N][3];
			visited = new boolean[N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				unit[i][0] = Integer.parseInt(st.nextToken());
				;
				unit[i][1] = Integer.parseInt(st.nextToken());
				;
				unit[i][2] = Integer.parseInt(st.nextToken());
				;
			}
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				if (!visited[i]) {
					DFS(i);
					cnt++;
				}
			}
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
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
