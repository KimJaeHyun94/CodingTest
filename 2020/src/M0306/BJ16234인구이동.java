package M0306;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ16234인구이동 {
	static int N, L, R;
	static int map[][];
	static int cnt;
	static boolean visited[][];
	static List<Node> list;
	static int dir[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		while (true) {
			visited = new boolean[N][N];
			boolean flag = false;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j]) {
						list = new LinkedList<>();
						list.add(new Node(i, j));
						DFS(i, j);
						
						if(list.size()>1) {
							flag=true;
							int sum=0;
							for (int j2 = 0; j2 < list.size(); j2++) {
								sum+=map[list.get(j2).y][list.get(j2).x];
							}
							int pung=sum/list.size();
							for (int k = 0; k < list.size(); k++) {
								map[list.get(k).y][list.get(k).x]=pung;
							}
						}
					}
				}
			}
			if(flag) {
				cnt++;
			}
			else {
				break;
			}
		}
		System.out.println(cnt);
	}

	private static void DFS(int y, int x) {
		visited[y][x] = true;
		for (int i = 0; i < dir.length; i++) {
			int dy = y + dir[i][0];
			int dx = x + dir[i][1];

			if (isOK(dy, dx, y, x)) {
				list.add(new Node(dy,dx));
				DFS(dy,dx);
			}
		}

	}

	private static boolean isOK(int dy, int dx, int y, int x) {
		if(dy>=0&&dy<N&&dx>=0&&dx<N) {
			int num=Math.abs(map[dy][dx]-map[y][x]);
			if(!visited[dy][dx] && num>=L && num<=R) {
				return true;
			}
		}
		return false;
	}

	static class Node {
		int y;
		int x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
