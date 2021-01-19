package M0415;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2933미네랄 {
	static int R, C;
	static char map[][];
	static int N;
	static int arr[];
	static int dirs[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static boolean visited[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		visited = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Break();
		BFS();
	}

	private static void Break() {
		for (int i = 0; i < N; i++) {
			if (i % 2 == 0) {
				for (int j = 0; j < C; j++) {
					if(map[arr[i]][j]=='x') {
						map[arr[i]][j]='.';
						break;
					}
				}
			}
			else {
				for (int j = C-1; j >= 0; j++) {
					if(map[arr[i]][j]=='x') {
						map[arr[i]][j]='.';
						break;
					}
				}
			}
		}

	}

	private static void BFS() {

	}

	static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
