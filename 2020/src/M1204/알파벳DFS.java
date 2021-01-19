package M1204;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 알파벳DFS {
	static int R, C;
	static char map[][];
	static int MAX = Integer.MIN_VALUE;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static boolean alpha[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		alpha = new boolean[26];

		for (int r = 0; r < R; r++) {
			String line = br.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = line.charAt(c);
			}
		}
		alpha[map[0][0]-'A'] = true;
		DFS(0, 0, 1);
		System.out.println(MAX);
	}

	private static void DFS(int r, int c, int cnt) {
		MAX = Math.max(cnt, MAX);
		if(cnt==26)
			return;
		for (int d = 0; d < dirs.length; d++) {
			int dr = r + dirs[d][0];
			int dc = c + dirs[d][1];

			if (isOK(dr, dc)) {
				alpha[map[dr][dc]-'A'] = true;
				DFS(dr,dc,cnt+1);
				alpha[map[dr][dc]-'A'] = false;
			}
		}

	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < R && dc >= 0 && dc < C && !alpha[map[dr][dc]-'A'];
	}

}