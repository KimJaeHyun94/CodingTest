package M0318;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 어른상어 {
	static int N, M, K;
	static Info map[][];
	static Shark shark[];
	static int priority[][][];
	static int dirs[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static List<Shark> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new Info[N][N];
		shark = new Shark[M + 1];
		priority = new int[M + 1][4][4];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				int num = Integer.parseInt(st.nextToken());
				if (num > 0) {
					shark[num] = new Shark(r, c, -1);
					map[r][c] = new Info(num, K);
				} else {
					map[r][c] = new Info(0, 0);
				}
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {
			shark[i].d = Integer.parseInt(st.nextToken()) - 1; // 각각의 상어의 방향 넣어주기
		}

		for (int i = 1; i <= M; i++) {
			for (int j = 0; j < 4; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < 4; k++) {
					priority[i][j][k] = Integer.parseInt(st.nextToken()) - 1;
				}
			}
		}
		int time = 0;
		int death = 0;
		while (time <= 1000) {
			if (death >= M - 1) {
				break;
			}
			for (int i = 1; i <= M; i++) { // 모든 상어의 이동
				if (shark[i] == null)
					continue;

				int r = shark[i].r;
				int c = shark[i].c;

				boolean find = false;
				outer: for (int d = 0; d < 4; d++) {
					int dr = r + dirs[priority[i][shark[i].d][d]][0]; // 현재 상어의 이름 / 현재 방향 / 우선순위 순서
					int dc = c + dirs[priority[i][shark[i].d][d]][1];

					if (isOK(dr, dc)) {
						if (map[dr][dc].smell == 0) {
							find = true;
							shark[i].r = dr;
							shark[i].c = dc;
							shark[i].d = priority[i][shark[i].d][d]; // 방향 바꾸기
							break outer;
						}
					}
				}
				outer2: if (!find) { // 한바퀴 돌앗는데 못찾았다면
					for (int d = 0; d < 4; d++) {
						int dr = r + dirs[priority[i][shark[i].d][d]][0]; // 현재 상어의 이름 / 현재 방향 / 우선순위 순서
						int dc = c + dirs[priority[i][shark[i].d][d]][1];

						if (isOK(dr, dc)) {

							if (map[dr][dc].n == i) {
								shark[i].r = dr;
								shark[i].c = dc;
								shark[i].d = priority[i][shark[i].d][d]; // 방향 바꾸기
								find = true;
								break outer2;
							}
						}
					}
				}
			} // 상어 이동

			for (int j = 0; j < N; j++) { // 냄새 빼기
				for (int k = 0; k < N; k++) {
					if (map[j][k].smell > 1) {
						map[j][k].smell--;
					} else {
						map[j][k].smell = 0;
						map[j][k].n = 0;
					}
				}
			}

			for (int j = M; j >= 1; j--) {
				if (shark[j] == null)
					continue;

				int r = shark[j].r;
				int c = shark[j].c;
				boolean flag = false;
				if (j > 1) {
					for (int k = j - 1; k >= 1; k--) {
						if (shark[k] == null)
							continue;
						if (shark[k].r == r && shark[k].c == c) { // 둘이 겹친다면
							shark[j] = null; // 없애버린다.
							Shark cur = shark[k];
							map[cur.r][cur.c].smell = K; // 새로 뿌린다.
							map[cur.r][cur.c].n = k;
							death++;
							flag = true;
							break;
						}
					}
					if (!flag) {
						map[r][c].smell = K; // 새로 뿌린다.
						map[r][c].n = j;
					}

				} else {
					map[r][c].smell = K; // 새로 뿌린다.
					map[r][c].n = 1;
				}
			}
			time++;
		}
		if (time > 1000) {
			System.out.println(-1);
		} else {
			System.out.println(time);
		}
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < N && dc >= 0 && dc < N;
	}

	static class Info {
		int n; // 상어 이름
		int smell; // 상어 냄새

		public Info(int n, int smell) {
			this.n = n;
			this.smell = smell;
		}

	}

	static class Shark {
		int r;
		int c;
		int d;

		public Shark(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}

	}
}
