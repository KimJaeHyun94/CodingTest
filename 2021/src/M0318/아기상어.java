package M0318;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 아기상어 {
	static int N;
	static int map[][];
	static boolean visited[][];
	static int[][] dirs = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int sr, sc;
	static int size = 2;
	static ArrayList<Shark> list;
	static int cnt = 0; // 물고기 먹은 갯수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					sr = i;
					sc = j;
					map[i][j] = 0;
				}
			}
		}
		int time = 0;
		while (true) {
			visited = new boolean[N][N];
			BFS();
			if (list.size() == 0) {
				break;
			} else if (list.size() == 1) {
				sr = list.get(0).r;
				sc = list.get(0).c;
				map[sr][sc] = 0;
				cnt++;
				time += list.get(0).d;
			} else {
				Collections.sort(list, new Comparator<Shark>() {
					@Override
					public int compare(Shark o1, Shark o2) {
						if (o1.d == o2.d) {
							if (o1.r == o2.r) {
								return o1.c - o2.c;
							} else {
								return o1.r - o2.r;
							}
						} else {
							return o1.d - o2.d;
						}
					}
				});

				sr = list.get(0).r;
				sc = list.get(0).c;
				map[sr][sc] = 0;
				time += list.get(0).d;
				cnt++;
			}

			if (cnt == size) {
				size++;
				cnt = 0;
			}
		}
		System.out.println(time);
	}

	private static void BFS() {
		Queue<Shark> q = new LinkedList<>();
		list = new ArrayList<>();

		q.add(new Shark(sr, sc, 0));
		visited[sr][sc] = true;

		while (!q.isEmpty()) {
			Shark cur = q.poll();
			
			int r = cur.r;
			int c = cur.c;
			int dist = cur.d;

			for (int d = 0; d < dirs.length; d++) {
				int dr = r + dirs[d][0];
				int dc = c + dirs[d][1];

				if (isOK(dr, dc) && !visited[dr][dc] && map[dr][dc] <= size) {
					if (map[dr][dc] != 0 && map[dr][dc]<size) {
						list.add(new Shark(dr, dc, dist + 1));
					}
					visited[dr][dc] = true;
					q.add(new Shark(dr, dc, dist + 1));
				}
			}
		}
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < N && dc >= 0 && dc < N;
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
