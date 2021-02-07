package M0207;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 플로이드2DFS {
	static int N, M;
	static final int INF = 987654321;
	static int distance[][];
	static int Route[][];
	static ArrayList<Integer> list;
	static int cnt;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		distance = new int[N+1][N+1];
		Route = new int[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				Route[i][j] = INF;
				if (i == j)
					continue;
				distance[i][j] = INF;
			}
		}
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			if (distance[start][end] != INF)
				distance[start][end] = Math.min(distance[start][end], w);
			else
				distance[start][end] = w; // 똑같은 start/end로 입력되는 경우가 있어서
		}
		floydWarshall();
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (distance[i][j] == INF || i == j) {
					sb.append(0).append(" ");
				} else {
					sb.append(distance[i][j]).append(" ");
				}
			}
			sb.append("\n");
		}
		System.out.print(sb); // 맵 출력하기
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (distance[i][j] == INF || i == j) {
					System.out.print(0);
				} else {
					list = new ArrayList<>();
					list.add(i);
					find_Route(i, j);
					System.out.print(list.size()+1 + " ");
					for (Integer child : list) {
						System.out.print(child+" ");
					}
					System.out.print(j);
				}
				System.out.println();
			}
		}
	}

	public static void find_Route(int i, int j) {
		if (Route[i][j] != INF) {
			find_Route(i, Route[i][j]);
			list.add(Route[i][j]);
			find_Route(Route[i][j], j);
		}
	}

	public static void floydWarshall() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (distance[i][j] > distance[i][k] + distance[k][j]) {
						distance[i][j] = distance[i][k] + distance[k][j];
						Route[i][j] = k; // k를 거쳐간다는 것을 저장해둔다.
					}
				}
			}
		}
	}
}