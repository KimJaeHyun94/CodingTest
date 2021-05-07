package 벨만포드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class 할로윈묘지 {
	static int W, H, G, E, X1, Y1, X2, Y2;
	static int dirs[][] = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
	static int map[][];
	static LinkedList<Edge> list;
	static long dist[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
		
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			if (W == 0 || H == 0) {
				break;
			}
			map = new int[W][H];
			list = new LinkedList<>();
			dist = new long[W][H];
			
			G = Integer.parseInt(br.readLine());
			for (int i = 0; i < G; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				map[x][y] = -1;
			}

			E = Integer.parseInt(br.readLine());
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int x1 = Integer.parseInt(st.nextToken());
				int y1 = Integer.parseInt(st.nextToken());
				int x2 = Integer.parseInt(st.nextToken());
				int y2 = Integer.parseInt(st.nextToken());
				int t = Integer.parseInt(st.nextToken());
				map[x1][y1] = 1;
				list.add(new Edge(new Point(x1, y1), new Point(x2, y2), t));
			}

			search();
			if (BellmanFord()) {
				sb.append("Never").append("\n");
			} else {
				if (dist[W - 1][H - 1] != Long.MAX_VALUE) {
					sb.append(dist[W - 1][H - 1]).append("\n");
				} else
					sb.append("Impossible").append("\n");
			}
		}

		System.out.println(sb);
	}

	private static boolean BellmanFord() {
		long INF = Long.MAX_VALUE;
		for (int i = 0; i < W; i++)
			Arrays.fill(dist[i], INF);

		dist[0][0] = 0;
		boolean flag = false;
		for (int i = 0; i < W * H; i++) {
			for (Edge edge : list) {
				Point st = edge.s;
				Point en = edge.e;

				if (dist[st.x][st.y] != INF && dist[en.x][en.y] > dist[st.x][st.y] + edge.w) {
					dist[en.x][en.y] = dist[st.x][st.y] + edge.w;
					flag = true;
				}
			}
			if (!flag) {
				return false;
			}
		}
		
		for (Edge edge : list) {
			Point st = edge.s;
			Point en = edge.e;

			if (dist[st.x][st.y] != INF && dist[en.x][en.y] > dist[st.x][st.y] + edge.w) {
				return true;
			}
		}
		return false;
	}

	private static void search() {
		for (int i = 0; i < W; i++) {
			for (int j = 0; j < H; j++) {
				if (i == W - 1 && j == H - 1)
					continue;
				if (map[i][j] != 0)
					continue;

				for (int d = 0; d < dirs.length; d++) {
					int dx = i + dirs[d][0];
					int dy = j + dirs[d][1];

					if (isOK(dx, dy) && map[dx][dy] != -1) {
						list.add(new Edge(new Point(i, j), new Point(dx, dy), 1));
					}
				}
			}
		}

	}

	private static boolean isOK(int dx, int dy) {
		return dx >= 0 && dx < W && dy >= 0 && dy < H;
	}

	static class Point {
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Edge {
		Point s, e;
		int w;

		Edge(Point s, Point e, int w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}
	}
}
