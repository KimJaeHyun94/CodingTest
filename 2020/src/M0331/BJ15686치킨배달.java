package M0331;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ15686치킨배달 {
	static int N, M;
	static int map[][];
	static boolean visited[];
	static int min = Integer.MAX_VALUE;
	static ArrayList<Node> chicken = new ArrayList<>();
	static ArrayList<Node> home = new ArrayList<>();;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					chicken.add(new Node(i, j));
				} else if (map[i][j] == 1) {
					home.add(new Node(i, j));
				}
			}
		}
		visited = new boolean[chicken.size()];
		permutation(0, 0);
		System.out.println(min);
	}

	private static void permutation(int start, int r) {
		if (r == M) {
			min = Math.min(solution(), min);
			return;
		} else {
			for (int i = start; i < chicken.size(); i++) {
				if (!visited[i]) { // 치킨집 조합
					visited[i] = true;
					permutation(i + 1, r + 1);
					visited[i] = false;
				}
			}
		}
	}

	private static int solution() {
		int len = 0;
		int sum = 0;
	
		for (Node house : home) {
			int minlen = Integer.MAX_VALUE;
			for (int i = 0; i < chicken.size(); i++) {
				if (visited[i]) { // 체크한 치킨집에서 거리 계산
					len = Math.abs(house.y - chicken.get(i).y) + Math.abs(house.x - chicken.get(i).x);
					minlen = Math.min(minlen, len); // 계속 최소값을 찾음
				}
			}
			sum += minlen;
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
