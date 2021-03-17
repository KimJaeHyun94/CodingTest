package M0316;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 맥주플로이드 {
	static int N;
	static ArrayList<Node> list;
	static int D[][];
	static final int INF = 987654321;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine());

			list = new ArrayList<>();

			for (int i = 0; i < N + 2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				list.add(new Node(r, c));

			}
			D = new int[N + 2][N + 2];
			for (int i = 0; i < N + 2; i++) {
				for (int j = 0; j < N + 2; j++) {
					if (i == j)
						continue;
					D[i][j] = INF;
				}
			}

			for (int i = 0; i < N + 1; i++) {
				for (int j = i + 1; j < N + 2; j++) {
					int distance = Math.abs(list.get(i).r - list.get(j).r) + Math.abs(list.get(i).c - list.get(j).c);
					if (distance <= 1000) {
						D[i][j] = distance;
						D[j][i] = distance;
					}
				}
			}
			FloydWarshall();
			
			if(D[0][N+1]>0 && D[0][N+1]<INF) {
				sb.append("happy");
			}else {
				sb.append("sad");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void FloydWarshall() {
		for (int k = 0; k < D.length; k++) {
			for (int i = 0; i < D.length; i++) {
				for (int j = 0; j < D.length; j++) {
					D[i][j] = Math.min(D[i][k] + D[k][j], D[i][j]);
				}
			}
		}
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
