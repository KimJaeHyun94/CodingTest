package M0116;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
/*
 * @See https://m.blog.naver.com/PostView.nhn?blogId=miyamae&logNo=220918830855&proxyReferer=https:%2F%2Fwww.google.com%2F
 * @Author : AKKabiyo
 * 
 */
public class 웜홀 {
	static int N, M, W;
	static List<Node> list;
	static int INF = 987654321;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());
		for (int t = 1; t <= TC; t++) {
			list = new ArrayList<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());

				list.add(new Node(start, end, cost));
				list.add(new Node(end, start, cost));
			}

			for (int i = 0; i < W; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());

				list.add(new Node(start, end, -cost));	
			}

			boolean flag = bellmanFord();
			if (flag) {
				sb.append("YES").append("\n");
			} else {
				sb.append("NO").append("\n");
			}
		}
		System.out.println(sb);
	}

	private static boolean bellmanFord() {
		boolean update;
		int[] distance = new int[N + 1];

		Arrays.fill(distance, INF);
		distance[1] = 0;

		for (int iter = 0; iter <= N; ++iter) {
			update = false;

			for (Node node : list) {
				if (distance[node.end] > distance[node.start] + node.cost) {
					update = true;
					distance[node.end] = distance[node.start] + node.cost;
				}
			}

			if (!update) {
				return false;
			}
		}

		return true;
	}

	static class Node{
		int start, end, cost;

		public Node(int start, int end, int cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}
	}
}
