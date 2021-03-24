package M0322;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class 새로운게임2_2 {
	static int color[][];
	static int N, K;
	static int dirs[][] = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };
	static Node[] horse;
	static LinkedList<Integer>[][] list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		color = new int[N][N];
		horse = new Node[K];
		list = new LinkedList[N][N];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				color[r][c] = Integer.parseInt(st.nextToken());
				list[r][c] = new LinkedList<>();
			}
		}

		for (int i = 0; i < K; i++) { // 1번말부터 K번 까지 말
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) - 1;

			horse[i] = new Node(r, c, d);
			list[r][c].add(i); // 말 위치
		}
		int time = 0;

		while (time++ <= 1000) {
			for (int i = 0; i < K; i++) {

				Node cur = horse[i];
				int r = cur.r;
				int c = cur.c;

				int dr = r + dirs[cur.d][0];
				int dc = c + dirs[cur.d][1];

				if (!isOK(dr, dc) || color[dr][dc] == 2) {
					cur.d = turnDir(cur.d); // 방향을 바꾸고
					dr = r + dirs[cur.d][0];
					dc = c + dirs[cur.d][1]; // 바뀐 방향으로
				}

				if (!isOK(dr, dc) || color[dr][dc] == 2) { // 바꿧는데도 또 파랑색이거나 벗어나면 가만히 있기
					continue;
				}
				int num = -1;
				for (int j = 0; j < list[r][c].size(); j++) {
					if (list[r][c].get(j) == i) {
						num = j;
					}
				}
				
				while (list[r][c].size() > num) {
					int tmp = -1;
					if(color[dr][dc]==0) {
						tmp = list[r][c].remove(num);
					}else
						tmp = list[r][c].removeLast();
					
					horse[tmp].r = dr;
					horse[tmp].c = dc; // 위치
					list[dr][dc].add(tmp);
					
					if (list[dr][dc].size() >= 4) {
						System.out.println(time);
						System.exit(0);
					}
				}
			}
		}
		System.out.println(-1);

	}

	private static int turnDir(int dir) {
		if (dir == 0) {
			return 1;
		} else if (dir == 1) {
			return 0;
		} else if (dir == 2) {
			return 3;
		} else if (dir == 3) {
			return 2;
		} else {
			return -1;
		}
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < N && dc >= 0 && dc < N;
	}

	static class Node {
		int r;
		int c;
		int d;

		public Node(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}

	}
}
