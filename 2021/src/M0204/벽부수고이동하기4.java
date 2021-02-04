package M0204;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 벽부수고이동하기4 {

	static int R, C;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static int[][] map;
	static List<Pair> blank;
	static int[][] check;
	static HashMap<Integer, Integer> hm;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new int[R][C];
		blank = new ArrayList<>();
		check = new int[R][C];
		hm = new HashMap<>();
		for (int r = 0; r < R; r++) {
			String line = br.readLine();
			for (int c = 0; c < C; c++) {
				char ch = line.charAt(c);
				if (ch == '0') { // Blank들을 다 넣어준다.
					blank.add(new Pair(r, c));
				} else {
					map[r][c] = 1;
				}
			}
		}

		int idx = 1;  //그룹 번호

		for (int i = 0; i < blank.size(); i++) {
			Pair pair = blank.get(i);
			int r = pair.r;
			int c = pair.c;

			if (check[r][c] == 0) {
				hm.put(idx, MadeBlank(r, c, idx++));    //같은 그룹끼리 묶어준다. 
			} else
				continue;
		}

		StringBuilder sb = new StringBuilder();
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] == 0) {
					sb.append(0);
				} else {
					sb.append(Solution(r,c));
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static Integer MadeBlank(int r, int c, int idx) {
		int cnt = 1;
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(r, c));
		check[r][c] = idx;

		while (!q.isEmpty()) {
			Pair temp = q.poll();
			int tr = temp.r;
			int tc = temp.c;

			for (int d = 0; d < 4; d++) {
				int dr = tr + dirs[d][0];
				int dc = tc + dirs[d][1];

				if (isOK(dr, dc) && map[dr][dc] == 0 && check[dr][dc] == 0) {  //이동할 수 있고 이미 그룹이 안되있는 것들에 대해서
					q.add(new Pair(dr, dc));
					check[dr][dc] = idx;  //같은 그룹을 만들어준다. 
					cnt++;
				}
			}
		}
		return cnt;
	}

	private static int Solution(int r, int c) {
		int sum = 1;
		HashSet<Integer> set = new HashSet<>();

		for (int d = 0; d < dirs.length; d++) {
			int dr = r + dirs[d][0];
			int dc = c + dirs[d][1];

			if (isOK(dr, dc) && map[dr][dc] == 0 && check[dr][dc] != 0) {  
				set.add(check[dr][dc]);
			}
		}

		for (Integer child : set) {   //이동 가능한 모든 그룹 수를 더해준다. 
			sum += hm.get(child);
		}
		return sum % 10;   //더한것을 10으로 나머지 한 것을 보낸다. 
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < R && dc >= 0 && dc < C;

	}

	static class Pair {
		int r;
		int c;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}