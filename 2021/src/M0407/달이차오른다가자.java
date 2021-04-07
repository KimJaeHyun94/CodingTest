package M0407;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 달이차오른다가자 {
	static int N, M;
	static char map[][];
	static int dirs[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static boolean[][][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		Data start = null;
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == '0') {
					start = new Data(i, j, 0, 0);
				}
			}
		}
		int result = bfs(start);
		System.out.println(result);

	}

	private static int bfs(Data start) {
		visited = new boolean[N][M][1 << 6];
		Queue<Data> q = new LinkedList<>();
		q.add(start);
		visited[start.r][start.c][0] = true;

		while (!q.isEmpty()) {
			Data cur = q.poll();
			
			if (map[cur.r][cur.c] == '1') {
				return cur.cnt;
			}
			
			for (int d = 0; d < dirs.length; d++) {
				int dr = cur.r + dirs[d][0];
				int dc = cur.c + dirs[d][1];
				int key = cur.key;
				
				if (!isOK(dr, dc) || map[dr][dc] == '#' || visited[dr][dc][key])
					continue;

				if ('a' <= map[dr][dc] && map[dr][dc] <= 'f') { // 키가 있다면
					key |= (1 << (map[dr][dc] - 'a'));
				}

				if ('A' <= map[dr][dc] && map[dr][dc] <= 'F') { // 문
					if ((key & (1 << (map[dr][dc] - 'A'))) == 0) { // 키가 없다면 문 무시
						continue;
					}
				}

				visited[dr][dc][key] = true;
				q.add(new Data(dr, dc, key, cur.cnt + 1));
			}
		}
		return -1;
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < N && dc >= 0 && dc < M;
	}

	static class Data {
		int r, c;
		int key;
		int cnt;

		public Data(int r, int c, int key, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.key = key;
			this.cnt = cnt;
		}

	}
}
