package M1210;

import java.io.*;
import java.util.*;

import M1210.마법사상어와파이어볼.Fireball;

//문제를 처음에 잘못 접근했다....
public class dddd {
	static int[][] dirs = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } };
	static List<Ball>[][] map;
	static int N, M, K;

	static class Ball {
		int m, d, s;

		public Ball(int m, int d, int s) {
			super();
			this.m = m;
			this.d = d;
			this.s = s;
		}

		@Override
		public String toString() {
			return "Ball [m=" + m + ", d=" + d + ", s=" + s + "]";
		}
	}

	// 파이어볼 이동
	private static void move() {
		for (int k = 0; k < K; k++) {// K번 반복
			// 다음 map 상태 저장하는 배열 초기화
			List<Ball>[][] next_map = new List[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					next_map[i][j] = new ArrayList<>();
				}
			}

			// 이동
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == null)
						continue;

					for (int l = 0; l < map[i][j].size(); l++) {
						Ball ball = map[i][j].get(l);
						int m = ball.m;
						int d = ball.d;
						int s = ball.s;
						int distance = s % N;

						int r = i + dirs[d][0] * distance;
						int c = j + dirs[d][1] * distance;
						System.out.println(r + " " + c);
						if (r >= N)
							r -= N;
						else if (r < 0)
							r += N;

						if (c >= N)
							c -= N;
						else if (c < 0)
							c += N;

						next_map[r][c].add(new Ball(m, d, s));

					}
				}
			}
			// 정리
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (next_map[i][j].size() > 1) {

						int total_m = 0;
						int total_s = 0;
						int size = next_map[i][j].size();

						boolean odd = false, even = false;
						for (int l = 0; l < next_map[i][j].size(); l++) {
							Ball ball = next_map[i][j].get(l);
							total_m += ball.m;
							total_s += ball.s;

							if (ball.d % 2 == 0) {
								even = true;
							} else {
								odd = true;
							}
						}

						int next_m = total_m / 5;
						int next_s = total_s / size;

						next_map[i][j] = new ArrayList<>();
						if (next_m > 0) {
							if (even && odd) {
								for (int dir = 1; dir <= 7; dir += 2) { // 1.3.5.7
									next_map[i][j].add(new Ball(next_m, dir, next_s));
								}
							} else {
								for (int dir = 0; dir <= 6; dir += 2) { // 0,2,4,6
									next_map[i][j].add(new Ball(next_m, dir, next_s));
								}
							}
						}
					}
				}
			}
			map = next_map;
		}
	}

	private static boolean isInMap(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}

	public static void main(String[] args) throws Exception {
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
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			map[r][c].add(new Ball(m, d, s));
		}

		move();

		int answer = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < map[i][j].size(); k++) {
					answer += map[i][j].get(k).m;
				}
			}
		}
		System.out.println(answer);
	}
}
