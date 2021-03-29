package M0326;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 스도쿠 {
	static int sdoku[][];
	static ArrayList<Node> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sdoku = new int[9][9];
		list = new ArrayList<>();

		for (int i = 0; i < 9; i++) {
			String line = br.readLine();
			for (int j = 0; j < 9; j++) {
				sdoku[i][j] = line.charAt(j) - '0';

				if (sdoku[i][j] == 0) {
					list.add(new Node(i, j));
				}
			}
		}

		DFS(0, 0);

	}

	private static void DFS(int idx, int n) {
		if (n == list.size()) { // 0인거 모두 다 돌았다면
			print();
			return;
		}
		
		int r = list.get(idx).r;
		int c = list.get(idx).c;
		for (int i = 1; i <= 9; i++) {
			if (Solution(r, c, i)) {
				sdoku[r][c] = i;
				DFS(idx + 1, n + 1);
				sdoku[r][c] = 0;
			}
		}

	}

	private static boolean Solution(int r, int c, int i) {
		for (int j = 0; j < 9; j++) { // 가로, 세로
			if (sdoku[r][j] == i)
				return false;
			if (sdoku[j][c] == i)
				return false;
		}

		int R = r / 3 * 3;
		int C = c / 3 * 3;

		for (int j = R; j < R + 3; j++) {
			for (int j2 = C; j2 < C + 3; j2++) {
				if (sdoku[j][j2] == i) {
					return false;
				}
			}
		}
		return true;
	}

	private static void print() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(sdoku[i][j]);
			}
			System.out.println();
		}
		System.exit(0);
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