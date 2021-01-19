package M0311;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BJ2668숫자고르기 {
	static int map[];
	static boolean visited[];
	static int cnt;
	static boolean list[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		map = new int[N + 1];
		list = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			map[i] = Integer.parseInt(br.readLine());
		}

		for (int i = 1; i <= N; i++) {
			visited = new boolean[N + 1];
			DFS(i, map[i]);
		}

		for (int i = 1; i < N + 1; i++) {
			if (list[i]) {
				cnt++;
			}
		}
		System.out.println(cnt);
		for (int i = 1; i < N + 1; i++) {
			if (list[i]) {
				System.out.println(i);
			}
		}
	}

	private static boolean DFS(int start, int node) {
		visited[node] = true;

		if (start == node || DFS(start, map[node])) {
			list[node] = true;
			return true;
		}
		return false;
	}
}
