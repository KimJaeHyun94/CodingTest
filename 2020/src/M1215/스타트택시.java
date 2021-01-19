package M1215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;



public class 스타트택시 {
	static int N, M, F, start_r, start_c, fuel;
	static int map[][];
	static int[][] dirs = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
	static boolean visited[][];
	static Passenger[] passengers;
	static List<Node> people;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		F = Integer.parseInt(st.nextToken());

		map = new int[N + 1][N + 1];
		passengers = new Passenger[M + 1];

		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 1)
					map[r][c] = -1;
			}
		}
		st = new StringTokenizer(br.readLine());
		start_r = Integer.parseInt(st.nextToken());
		start_c = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int st_r = Integer.parseInt(st.nextToken());
			int st_c = Integer.parseInt(st.nextToken());
			int dest_r = Integer.parseInt(st.nextToken());
			int dest_c = Integer.parseInt(st.nextToken());

			map[st_r][st_c] = i;
			passengers[i] = new Passenger(st_r, st_c, dest_r, dest_c);
		}
		sol();

	}

	private static void sol() {
		int i = 0;
		while (true) {

			find(start_r, start_c);
			if (people.size() == 0) {
				System.out.println(-1);
				return;
			} else {
				Collections.sort(people, new Comparator<Node>() {
					@Override
					public int compare(Node o1, Node o2) {
						if(o1.r != o2.r) {
							return Integer.compare(o1.r, o2.r);
						}else {
							return Integer.compare(o1.c, o2.c);
						}
					}
				});
			}

			int check = map[people.get(0).r][people.get(0).c];
			map[people.get(0).r][people.get(0).c] = 0;
			start_r = people.get(0).r;
			start_c = people.get(0).c;

			fuel = 0;
			// System.out.println(start_r + " " + start_c + " " + check + " " + F);
			if (!dest(passengers[check].ex, passengers[check].ey)) {
				System.out.println(-1);
				break;
			}

			i++;
			if (i == M) {
				System.out.println(F);
				break;
			}
		}
	}

	public static boolean dest(int ex, int ey) {

		Queue<Node> q = new LinkedList();
		visited = new boolean[N + 1][N + 1];

		q.offer(new Node(start_r, start_c));
		visited[start_r][start_c] = true;

		while (!q.isEmpty()) {
			int len = q.size();
			F--;
			fuel++;
			for (int l = 0; l < len; l++) {
				Node p = q.poll();

				for (int i = 0; i < 4; i++) {
					int nx = p.r + dirs[i][0];
					int ny = p.c + dirs[i][1];

					if (isOK(nx, ny) && !visited[nx][ny] && map[nx][ny] != -1) {
						if (nx == ex && ny == ey) { // 현재 태운 승객의 목적지 칸
							start_r = nx;
							start_c = ny;
							F += fuel * 2;
							return true;
						} else {
							q.offer(new Node(nx, ny));
							visited[nx][ny] = true;
						}
					}
				}
			}
			if (F == 0)
				return false;
		}

		return false;
	}

	public static void find(int x, int y) {

		people = new ArrayList();

		if (map[x][y] >= 1) {
			people.add(new Node(x, y));
			return;
		}

		Queue<Node> q = new LinkedList();
		visited = new boolean[N + 1][N + 1];

		q.offer(new Node(x, y));
		visited[x][y] = true;

		while (!q.isEmpty()) {
			int len = q.size();
			F--;
			boolean flag = false;

			for (int l = 0; l < len; l++) {
				Node p = q.poll();
				for (int i = 0; i < 4; i++) {
					int nx = p.r + dirs[i][0];
					int ny = p.c + dirs[i][1];

					if (isOK(nx, ny) && !visited[nx][ny] && map[nx][ny] != -1) {
						if (map[nx][ny] == 0) { // 빈칸
							q.offer(new Node(nx, ny));
							visited[nx][ny] = true;
						} else if (map[nx][ny] >= 1) { // 승객이 있는 칸
							visited[nx][ny] = true;
							people.add(new Node(nx, ny));
							flag = true;
						}
					}
				}
			}

			if (flag)
				return;

			if (F == 0) {
				people = new ArrayList<>();
				return;
			}
		}
		people = new ArrayList<>();
		return;
	}

	private static boolean isOK(int nr, int nc) {

		return nr >= 1 && nr <= N && nc >= 1 && nc <= N;
	}

	static class Node {
		int r;
		int c;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static class Passenger {
		int sx;
		int sy;
		int ex;
		int ey;

		public Passenger(int sx, int sy, int ex, int ey) {
			this.sx = sx;
			this.sy = sy;
			this.ex = ex;
			this.ey = ey;
		}
	}

}
