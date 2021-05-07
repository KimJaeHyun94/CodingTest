package M0506;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 소가길을건너간이유6 {
	static int N, K, R;
	static List<Node> graph[][];
	static boolean visited[][];
	static int dirs[][] = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		graph = new List[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				graph[i][j] = new ArrayList<>();
			}
		}
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int r1 = Integer.parseInt(st.nextToken());
			int c1 = Integer.parseInt(st.nextToken());

			graph[r][c].add(new Node(r1, c1));
			graph[r1][c1].add(new Node(r, c));
		}

	
		ArrayList<Node> list = new ArrayList<>();

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			list.add(new Node(r, c));
		}
		
		int cnt = 0;
		
		for (int i = 0; i < K; i++) {
			visited = new boolean[N + 1][N + 1];
			Node cur = list.get(i);
			int r = cur.r;
			int c = cur.c;

			dfs(r, c);

			for (int j = i + 1; j < K; j++) {
				Node next = list.get(j);
				int dr = next.r;
				int dc = next.c;

				if (!visited[dr][dc]) {
					cnt++;
				}
			}
		}
		System.out.println(cnt);

	}

	private static void dfs(int r, int c) {
		
		visited[r][c] = true;
		
		
		for (int d = 0; d < dirs.length; d++) {
			int dr = r + dirs[d][0];
			int dc = c + dirs[d][1];

			if (!isOK(dr, dc))
				continue;
			if (visited[dr][dc])
				continue;
			if (graph[r][c].contains(new Node(dr, dc)))
				continue;
			dfs(dr, dc);

		}

	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 1 && dr <= N && dc >= 1 && dc <= N;
	}

	static class Node {
		int r;
		int c;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + c;
			result = prime * result + r;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (c != other.c)
				return false;
			if (r != other.r)
				return false;
			return true;
		}

	}
}
