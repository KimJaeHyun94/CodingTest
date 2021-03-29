package M0326;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SudokuVariant {
	static int sdoku[][] = new int[3][3];
	static ArrayList<Node> list;
	static int ans=0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		list = new ArrayList<>();
		for (int r = 0; r < 3; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < 3; c++) {
				sdoku[r][c] = Integer.parseInt(st.nextToken());
				if (sdoku[r][c] == 0) {
					list.add(new Node(r, c));
				}
			}
		}
		DFS(0, 0);
		System.out.println(ans);
	}

	private static void DFS(int idx, int cnt) {
		if (idx==list.size()) {
			ans++;
			return;
		}
		int r = list.get(idx).r;
		int c = list.get(idx).c;
		
		for (int i = 1; i <= 9; i++) {
			if (isOK(i, r, c)) {
				sdoku[r][c] = i;
				DFS(idx + 1, cnt + 1);
				sdoku[r][c] = 0;
			}
		}

	}

	private static boolean isOK(int i, int r, int c) {

		for (int j = 0; j < 3; j++) {
			if (sdoku[r][j] == i)
				return false;
			if (sdoku[j][c] == i)
				return false;
		}
		return true;
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
