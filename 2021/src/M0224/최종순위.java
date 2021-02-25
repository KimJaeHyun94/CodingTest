package M0224;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 최종순위 {
	static boolean[][] graph;
	static int degree[];
	static ArrayList<Integer> rank;
	static String ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int tc = 0; tc < T; tc++) {

			int n = Integer.parseInt(br.readLine());
			graph = new boolean[n + 1][n + 1];
			degree = new int[n + 1];
			rank = new ArrayList<>();

			String line[] = br.readLine().split(" ");

			for (int i = 0; i < n; i++) {
				int start = Integer.parseInt(line[i]);
				for (int j = i + 1; j < n; j++) {
					int end = Integer.parseInt(line[j]);

					graph[start][end] = true;
					degree[end]++;
				}
			}

			int m = Integer.parseInt(br.readLine());

			for (int i = 0; i < m; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				if (graph[a][b]) {
					graph[a][b] = false;
					graph[b][a] = true;
					degree[a]++;
					degree[b]--;
				} else {
					graph[a][b] = true;
					graph[b][a] = false;
					degree[b]++;
					degree[a]--;
				}
			}

			ans = null;
			BFS(n);

			if (ans == null) {
				for (int ans : rank) {
					sb.append(ans).append(" ");
				}
			} else {
				sb.append(ans);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void BFS(int n) {
		Queue<Integer> q = new LinkedList<>();

		for (int i = 1; i <= n; ++i) {
			if (degree[i] == 0)
				q.offer(i);
		}

		for (int i = 1; i <= n; i++) {
			int size = q.size();

			// 정확한 순위가 나오려면 큐에는 한개씩 들어가 있어야 함
			if (q.size() > 1) {
				ans = "?";
				return;
			}

			if (q.size() == 0) {
				ans = "IMPOSSIBLE";
				return;
			}

			int temp = q.poll();
			rank.add(temp);
			for (int j = 1; j <= n; ++j) {
				if (graph[temp][j]) {
					degree[j]--;

					if (degree[j] == 0) {
						q.offer(j);
					}
				}
			}
		}
	}
}

