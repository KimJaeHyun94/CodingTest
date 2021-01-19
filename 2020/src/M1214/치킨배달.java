package M1214;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 치킨배달 {
	static int N, M;
	static int map[][];
	static boolean visited[];
	static int ans = Integer.MAX_VALUE;
	static ArrayList<Node> chicken = new ArrayList<>();
	static ArrayList<Node> home = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 1) {
					home.add(new Node(r, c));
				} else if (map[r][c] == 2) {
					chicken.add(new Node(r, c));
				}
			}
		}

		visited = new boolean[chicken.size()];
		permutation(0, 0);
		System.out.println(ans);
	}

	private static void permutation(int start, int r) {
		if (r == M) {
			ans = Math.min(solution(), ans);
			return;
		}
		for (int i = start; i < chicken.size(); i++) {
			if (!visited[i]) {
				visited[i] = true;
				permutation(i + 1, r + 1);
				visited[i] = false;
			}
		}
	}

	private static int solution() {
		int sum = 0;
		
		for (Node node : home) {
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < visited.length; i++) {
				if (visited[i]) {
					int dis = Math.abs(node.x - chicken.get(i).x) + Math.abs(node.y - chicken.get(i).y);
					min = Math.min(min,dis);
				}
			}
			sum+=min;
		}
		return sum;
	}

	static class Node {
		int y;
		int x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
