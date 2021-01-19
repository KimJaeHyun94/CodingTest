package M1210;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 마법사상어와파이어볼 {
	static int N, M, K;
	static List<Fireball> map[][];
	static int[][] dirs = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new List[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new ArrayList<>();
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			map[r - 1][c - 1].add(new Fireball(m, s, d));
		}
		for (int i = 0; i < K; i++) {
			Solution();
		}
		

		int ans = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int t = 0; t < map[i][j].size(); t++) {
					ans += map[i][j].get(t).m;
				}
			}
		}
		System.out.println(ans);
	}

	private static void Solution() {
		List<Fireball> temp[][] = new List[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				temp[i][j] = new ArrayList<>();
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] == null)
					continue;

				for (int k = 0; k < map[r][c].size(); k++) {
					Fireball fire = map[r][c].get(k);

					int dr = r + dirs[fire.d][0] * fire.s % N;
					int dc = c + dirs[fire.d][1] * fire.s % N;

					if (dr >= N)
						dr -= N;
					else if (dr < 0) {
						dr += N;
					}

					if (dc >= N)
						dc -= N;
					else if (dc < 0) {
						dc += N;
					}
					temp[dr][dc].add(new Fireball(fire.m, fire.s, fire.d));
				}
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (temp[r][c].size() > 1) { // 2개 이상일 경우 겹친다
					int m = 0;
					int s = 0;

					boolean odd = false, even = false;
					for (Fireball fire : temp[r][c]) {
						m += fire.m;
						s += fire.s;

						if (fire.d % 2 == 0) {
							even = true;
						} else {
							odd = true;
						}
					}

					int rm = m / 5;
					int rs = s / temp[r][c].size();
					temp[r][c] = new ArrayList<>();
					if (rm > 0) {
						if (even && odd) {
							for (int dir = 1; dir <= 7; dir += 2) { // 1.3.5.7
								temp[r][c].add(new Fireball(rm, rs, dir));
							}
						} else {
							for (int dir = 0; dir <= 6; dir += 2) { // 0,2,4,6
								temp[r][c].add(new Fireball(rm, rs, dir));
							}
						}
					}
				}
			}
		}
		map = temp;
	}

	static class Fireball {
		int m;
		int s;
		int d;

		public Fireball(int m, int s, int d) {
			this.m = m;
			this.s = s;
			this.d = d;
		}

	}

}
