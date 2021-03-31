package M0330;

import java.util.Scanner;
/**
 * 
 * @author 김재현
 * @see https://heechan3006.github.io/problemsolving/2020/04/29/BOJ_17370.html
 * 
 */
public class 육각형우리속의개미 {
	static int N;
	static int cnt = 0;
	static int dirs[][] = { { -1, 0 }, { -1, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { -1, -1 } };
	static boolean visited[][];
	static int map[][];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		visited = new boolean[100][100];
		map = new int[100][100];

		for (int i = 0; i < 100; i++) {
			int start = 0;
			if (i % 4 == 0 || i % 4 == 3) {
				start = 1;
			}
			for (int j = start; j < 100; j+=2) {
				map[i][j] = 1;
			}
		}
		visited[50][50] = true;
		DFS(50, 50, 0, 49, 50);
		System.out.println(cnt);
	}

	private static void DFS(int r, int c, int count, int nr, int nc) {
		if (count == N) {
			if (visited[nr][nc]) {
				cnt++;
			}
			return;
		}
		if (count < N && visited[nr][nc])
			return;
		visited[nr][nc] = true;
		for (int d = 0; d < 6; d++) {
			int dr = nr + dirs[d][0];
			int dc = nc + dirs[d][1];
			
			if (dr == r && dc == c)
				continue;

			if (map[dr][dc] == 1) {
				DFS(nr, nc, count + 1, dr, dc);
			}
		}
		visited[nr][nc] = false;
	}

}
