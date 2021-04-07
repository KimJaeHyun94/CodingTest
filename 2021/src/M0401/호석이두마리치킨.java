package M0401;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 호석이두마리치킨 {
	static int N, M;
	static int d[][];
	static int INF = 987654321;
	static ArrayList<Node> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		d = new int[N + 1][N + 1];
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				if (r == c)
					continue;
				d[r][c] = INF;
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			d[a][b] = d[b][a] = 1;
		}
		FloydWarshall();
		list = new ArrayList<>();
		int min = INF;
		int s = N, b = 1;
		int sum = 0;
		for (int i = 1; i < N; i++) {
			for (int j = i + 1; j <= N; j++) { // 두 점을 정한다.
				sum = 0;
				for (int k = 1; k <= N; k++) {
					sum += Math.min(d[i][k], d[j][k]) * 2;
				}
				if (sum == min) {
					if (i<s) {
						s = i;
						b = j;
						min = sum;
					}else if(i==s && j < b) {
						s = i;
						b = j;
						min = sum;
					}
				} else if (sum < min) {
					s = i;
					b = j;
					min = sum;
				}
			}
		}

		System.out.println(s + " " + b + " " + min);
	}

	private static void FloydWarshall() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
				}
			}
		}
	}

	static class Node implements Comparable<Node> {

		int small;
		int big;
		int sum;

		public Node(int small, int big, int sum) {
			this.small = small;
			this.big = big;
			this.sum = sum;
		}

		@Override
		public int compareTo(Node n) {
			if (this.sum != n.sum) {
				return this.sum - n.sum;
			} else {
				if (this.small != n.small) {
					return this.small - n.small;
				} else {
					return n.big-this.big;
				}
			}
		}
	}

}