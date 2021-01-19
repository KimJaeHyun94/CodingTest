package M0111;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * @see https://whereisusb.tistory.com/230
 * @author AKKabiyo
 * 
 */
public class 종이조각 {
	static int R, C, N;
	static boolean visited[][];
	static int[][] map;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		visited = new boolean[R][C];
		map = new int[R][C];

		for (int r = 0; r < R; r++) {
			String line = br.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = line.charAt(c)-'0';
			}
		}

		DFS(0, 0);
		System.out.println(ans);
	}

	private static void DFS(int r, int c) {
		if (r == R) {
			ans = Math.max(ans, sol());
			return;
		}
		if (c == C) {
			DFS(r + 1, 0);
			return;
		}
		visited[r][c] = true;
		DFS(r, c + 1);
		visited[r][c] = false;
		DFS(r, c + 1);
	}

	private static int sol() {
		int sum = 0;
		for (int r = 0; r < R; r++) {
			int gsum = 0;
			for (int c = 0; c < C; c++) {
				if (visited[r][c]) {
					gsum *= 10;
					gsum += map[r][c];
				} else {
					sum += gsum;
					gsum = 0;
				}
			}
			sum += gsum;
		}
		for (int c = 0; c < C; c++) {
			int ssum = 0;
			for (int r = 0; r < R; r++) {
				if (!visited[r][c]) {
					ssum *= 10;
					ssum += map[r][c];
				} else {
					sum += ssum;
					ssum = 0;
				}
			}
			sum += ssum;
		}
		return sum;
	}
}
