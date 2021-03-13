package M0312;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 매직스타 {
	static int[][][] Route = { 
			{ { 0, 4 }, { 1, 3 }, { 2, 2 }, { 3, 1 } }, 
			{ { 3, 1 }, { 3, 3 }, { 3, 5 }, { 3, 7 } },
			{ { 0, 4 }, { 1, 5 }, { 2, 6 }, { 3, 7 } },
			{ { 1, 1 }, { 1, 3 }, { 1, 5 }, { 1, 7 } },
			{ { 1, 1 }, { 2, 2 }, { 3, 3 }, { 4, 4 } }, 
			{ { 4, 4 }, { 3, 5 }, { 2, 6 }, { 1, 7 } } 
			};
	static char map[][];
	static boolean visited[];
	static ArrayList<Node> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[5][9];
		visited = new boolean[13]; // 알파벳
		list = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			String str = br.readLine();
			for (int j = 0; j < 9; j++) {
				map[i][j] = str.charAt(j);

				if (map[i][j] == '.') {
					continue;
				} else if (map[i][j] == 'x') {
					list.add(new Node(i, j));
				} else {
					visited[map[i][j] - 'A' + 1] = true;
				}
			}
		}

		DFS(0);
		
	}

	private static void DFS(int cnt) {
		if (cnt == list.size()) {
			if (Sol()) {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < 9; j++) {
						sb.append(map[i][j]);
					}
					sb.append("\n");
				}
				System.out.println(sb);
				System.exit(0);
			}
			return;
		}
		for (int i = 1; i < 13; i++) {
			if (!visited[i]) { // 알파벳 하나씩 넣기
				Node cur = list.get(cnt);
				visited[i] = true;
				map[cur.r][cur.c] = (char) (i + 64);
				DFS(cnt + 1);
				visited[i]=false;
				map[cur.r][cur.c] = 'x';
			}
		}
	}

	private static boolean Sol() {
		int sum = 0;
		for (int i = 0; i < 6; i++) {
			sum=0;
			for (int j = 0; j < 4; j++) {
				int r = Route[i][j][0];
				int c = Route[i][j][1];

				sum += map[r][c] - 'A' + 1;
			}
			if (sum != 26) {
				return false;
			}
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
