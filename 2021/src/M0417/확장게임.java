package M0417;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 확장게임 {
	static int N, M, P;
	static int distance[];
	static int map[][];
	static int player[];
	static Queue<Node> q[];
	static int dirs[][] = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());

		distance = new int[P + 1];
		player = new int[P + 1];
		map = new int[N][M];
		q = new LinkedList[P + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= P; i++) {
			distance[i] = Integer.parseInt(st.nextToken());
			q[i] = new LinkedList<>();
		}

		for (int r = 0; r < N; r++) {
			String line = br.readLine();
			for (int c = 0; c < M; c++) {
				char ch = line.charAt(c);
				if (ch == '.') {
					map[r][c] = 0;
				} else if (ch == '#') {
					map[r][c] = -1;
				} else {
					map[r][c] = ch - '0';
					player[map[r][c]]++;
					q[map[r][c]].add(new Node(r, c));
				}
			}
		}

		Game();

	}

	private static void Game() {
		while (true) {
			int build = 0;
			for (int i = 1; i <= P; i++) {
				Queue<Node> eq = q[i];
				build += BFS(eq, i);
				
			}
			if (build == 0)
				break;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= P; i++) {
			sb.append(player[i] + " ");
		}
		System.out.println(sb);
	}

	private static int BFS(Queue<Node> q, int now) {
		int dist = distance[now]; // 최대 거리
		int turn = 0;
		int cnt = 0;

		while (!q.isEmpty()) {
			int size = q.size();
			while (size-- > 0) {
				Node cur = q.poll();

				for (int d = 0; d < dirs.length; d++) {
					int dr = cur.r + dirs[d][0];
					int dc = cur.c + dirs[d][1];

					if (isOK(dr, dc) && map[dr][dc] == 0) {
						q.add(new Node(dr, dc));
						map[dr][dc] = now;
						cnt++;
					}
				}

			}
			turn++;
			if (turn == dist)
				break;
		}
		player[now]+=cnt;
		return cnt;
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < N && dc >= 0 && dc < M;
	}

	static class Node {
		int r;
		int c;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
