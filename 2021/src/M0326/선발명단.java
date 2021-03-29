package M0326;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 선발명단 {
	static int ans;
	static int map[][];
	static int position[];
	static boolean visited[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());

		for (int t = 0; t < TC; t++) {
			ans = Integer.MIN_VALUE;
			map = new int[11][11];
			position = new int[11];
			visited = new boolean[11];

			for (int r = 0; r < 11; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int c = 0; c < 11; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			DFS(0);
			System.out.println(ans);
		}
	}

	private static void DFS(int cnt) {
		if (cnt == 11) {
			int sum = 0;
			for (int i = 0; i < 11; i++) {
				sum += position[i];
			}
			ans = Math.max(ans, sum);
			return;
		}
		for (int i = 0; i < 11; i++) {
			if (!visited[i] && map[i][cnt] > 0) {
				visited[i] = true;
				position[cnt] = map[i][cnt];
				DFS(cnt + 1);
				visited[i] = false;
				position[cnt] = 0;
			}
		}
	}

}
