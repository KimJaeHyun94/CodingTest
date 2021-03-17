package M0316;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 드래곤커브 {
	static int[][] dirs = { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } };
	static int map[][] = new int[101][101];
	static int cnt=0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());

			DragonCurve(x, y, d, g);
		}
		FindSquare();
		System.out.println(cnt);
	}

	private static void FindSquare() {
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if(map[i][j]==1 && map[i][j+1]==1 && map[i+1][j]==1 && map[i+1][j+1]==1) {
					cnt++;
				}
			}
		}
		
	}

	private static void DragonCurve(int x, int y, int d, int g) {
		map[y][x] = 1;

		ArrayList<Integer> dirlist = new ArrayList<>();
		dirlist.add(d);

		for (int i = 1; i <= g; i++) {
			int size = dirlist.size();
			for (int j = size-1; j >= 0; j--) {
				int nd = (dirlist.get(j) + 1) % 4;
				dirlist.add(nd);
			}
		}

		for (Integer dir : dirlist) {
			y += dirs[dir][0];
			x += dirs[dir][1];
			map[y][x] = 1;
		}
	}
}
