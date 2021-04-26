package 플로이드와샬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class 플로이드2 {
	static int N, M;
	static final int INF = 987654321;
	static int distance[][];
	static int Route[][];
	static Stack<Integer> stack = new Stack<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		distance = new int[N + 1][N + 1];
		Route = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				Route[i][j] = -1;
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

			distance[start][end] = Math.min(distance[start][end], w); // 똑같은 start/end로 입력되는 경우가 있어서
			Route[start][end] = start; // 한방에 가는 경우를 저장해둔다
		}
		floydWarshall();
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (distance[i][j] == INF) {
					sb.append(0).append(" ");
				} else {
					sb.append(distance[i][j]).append(" ");
				}
			}
			sb.append("\n");
		}
		System.out.print(sb); // 맵 출력하기

		sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (Route[i][j] == -1) {
					sb.append(0);
				} else {
					int end = j;
					stack.push(j);
					while (true) {
						if (i==Route[i][end]) {
							break;
						} else {
							end = Route[i][end];
							stack.push(end);
						}
					}
					stack.push(i);
					sb.append(stack.size()).append(" ");
					while(!stack.isEmpty()){
						sb.append(stack.pop()).append(" ");
					}
				}
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}

	public static void floydWarshall() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (distance[i][j] > distance[i][k] + distance[k][j]) {
						distance[i][j] = distance[i][k] + distance[k][j];
						Route[i][j] = Route[k][j]; // k를 거쳐간다는 것을 저장해둔다.
					}
				}
			}
		}
	}
}