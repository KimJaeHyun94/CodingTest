package M0407;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 배열에서이동 {
	static int N;
	static int map[][];
	static int dirs[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static HashSet<Integer> set = new HashSet<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		map = new int[N][N];

		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				set.add(map[r][c]);
			}
		}

		ArrayList<Integer> list = new ArrayList<>();
		list.addAll(set);
		Collections.sort(list);

		int left = 0;
		int right = 0;
		int ans = Integer.MAX_VALUE;
		while (left < list.size() && right<list.size()) {
			
			int min = list.get(left);
			int max = list.get(right);
			if (BFS(min, max)) {
				int gap = max-min;
				ans = Math.min(ans, gap);
				left++;
			}  else {
				right++;
			}
		}

		System.out.println(ans);
	}

	private static boolean BFS(int min, int max) {
		if(map[0][0] < min || map[0][0] > max) return false;
		boolean visited[][] = new boolean[N][N];
		visited[0][0] = true;
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0, 0));

		while (!q.isEmpty()) {
			Node cur = q.poll();

			if (cur.r == N - 1 && cur.c == N - 1) {
				return true;
			}
			for (int d = 0; d < dirs.length; d++) {
				int dr = cur.r + dirs[d][0];
				int dc = cur.c + dirs[d][1];

				if (isOK(dr, dc) && !visited[dr][dc] && map[dr][dc]>=min && map[dr][dc]<=max) {
					visited[dr][dc] = true;
					q.add(new Node(dr, dc));
				}
			}
		}
		return false;
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < N && dc >= 0 && dc < N;
	}

	static class Node {
		int r;
		int c;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}

	}
}
