package M0202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
/*
 * @Author : AKKabiyo
 * @See https://steady-coding.tistory.com/109
 * 
 * 
 */
public class 여행가자 {
	static int N, M;
	static List Graph[];
	static int Route[];
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		List<Integer>[] graph = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		Route = new int[M];
		parents = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			makeSet(i);
		}
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				int check = Integer.parseInt(st.nextToken());
				if (check == 1) {
					union(i, j);
				}
			}
		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			Route[i] = Integer.parseInt(st.nextToken());
		}

		boolean flag = true;
		for (int i = 1; i < M; i++) {
			if (findSet(Route[0]) != findSet(Route[i])) {
				flag = false;
				break;
			}
		}

		if (flag) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}

	static void makeSet(int x) {
		parents[x] = x;
	}

	static int findSet(int x) {
		if (x == parents[x]) {
			return x;
		} else {
			parents[x] = findSet(parents[x]);
			return parents[x];
		}
	}

	static void union(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);
		if (px != py) {
			if (px < py)
				parents[py] = px;
		} else {
			parents[px] = py;
		}
	}
}
