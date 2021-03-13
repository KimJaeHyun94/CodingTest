package M0313;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 열쇠 {
	static int dirs[][] = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
	static char map[][];
	static HashSet<Character> keyset;
	static int H, W;
	static int ans;
	static List<Node> wait[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 0; t < TC; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			map = new char[H + 2][W + 2];
			ans = 0;
			keyset = new HashSet<>();
			wait = new List[26];

			for (int i = 0; i < 26; i++) {
				wait[i] = new ArrayList<>();
			}

			for (int i = 0; i < H + 2; i++) { // 바깥 테투리 설정
				for (int j = 0; j < W + 2; j++) {
					map[i][j] = '.';
				}
			}

			for (int h = 1; h <= H; h++) { // 값 입력받기
				String str = br.readLine();
				for (int w = 1; w <= W; w++) {
					map[h][w] = str.charAt(w-1);
				}
			}
			String keys = br.readLine();
			for (int i = 0; i < keys.length(); i++) {
				char key = Character.toUpperCase(keys.charAt(i));
				keyset.add(key);
			}
			BFS();
			sb.append(ans).append("\n");
		}
		System.out.println(sb);

	}

	private static void BFS() {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0, 0));
		boolean visited[][] = new boolean[H + 2][W + 2];
		visited[0][0] = true;

		while (!q.isEmpty()) {
			Node cur = q.poll();
	
			for (int d = 0; d < dirs.length; d++) {
				int dr = cur.r + dirs[d][0];
				int dc = cur.c + dirs[d][1];

				if (isOK(dr, dc) && !visited[dr][dc] && map[dr][dc] != '*') {
					if (map[dr][dc] == '$') {
						ans++;
						visited[dr][dc] = true;
						q.add(new Node(dr, dc));
					} else if (map[dr][dc] >= 'A' && map[dr][dc] <= 'Z') { // 문을 만난다면
						if (keyset.contains(map[dr][dc])) {
							map[dr][dc] = '.'; // 초기화
							q.add(new Node(dr, dc));
							visited[dr][dc] = true;
						} else {
							int idx = map[dr][dc] - 'A';
							wait[idx].add(new Node(dr, dc)); // 대기 번호 추가
						}
					} else if (map[dr][dc] >= 'a' && map[dr][dc] <= 'z') { // 키를 만난다면
						char key = Character.toUpperCase(map[dr][dc]);
						keyset.add(key); // 새로운 키 추가
						visited[dr][dc] = true;
						q.add(new Node(dr, dc));

						int idx = key - 'A';
						if (!wait[idx].isEmpty()) { // 이 키가 없어서 기다리고 있는 문들이 있다면 문을 열어준다.
							for (int i = 0; i < wait[idx].size(); i++) {
								int sr = wait[idx].get(i).r;
								int sc = wait[idx].get(i).c;

								map[sr][sc] = '.';
								visited[sr][sc] = true;
								q.add(new Node(sr, sc));
							}
						}

					} else {
						visited[dr][dc] = true;
						q.add(new Node(dr, dc));
					}
				}
			}

		}

	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < H + 2 && dc >= 0 && dc < W + 2;
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
