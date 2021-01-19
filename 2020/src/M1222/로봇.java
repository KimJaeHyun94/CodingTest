package M1222;

import java.util.*;
import java.io.*;

public class 로봇 {
	static int[][] dirs = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
	static int[][] map;
	static int R, C;
	static Robot person;
	static int ansr, ansc, ansd;
	static int ans;
	static boolean visited[][][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		visited = new boolean[R][C][5];
		for (int i = 0; i < map.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		person = new Robot(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1,
				Integer.parseInt(st.nextToken()), 0);

		st = new StringTokenizer(br.readLine());
		ansr = Integer.parseInt(st.nextToken())-1;
		ansc = Integer.parseInt(st.nextToken())-1;
		ansd = Integer.parseInt(st.nextToken());

		BFS();
		System.out.println(ans);
	}

	private static void BFS() {
		Queue<Robot> q = new LinkedList<>();
		q.add(person);
		visited[person.r][person.c][person.dir] = true;

		while (!q.isEmpty()) {
			Robot temp = q.poll();
			int r = temp.r;
			int c = temp.c;
			int dir = temp.dir;
			int dep = temp.dep;

			if (r == ansr && c == ansc && dir == ansd) {
				ans = dep;
				return;
			}

			for (int i = 1; i <= 3; i++) {
				int dr = r+ dirs[dir - 1][0] * i;
				int dc = c+ dirs[dir - 1][1] * i;
	
				if (isOK(dr, dc) && map[dr][dc]==0) {
					if (!visited[dr][dc][dir]) {
						visited[dr][dc][dir] = true;
						q.add(new Robot(dr, dc, dir, dep + 1));
					}
				}else 
					break;
			}

			for (int i = 1; i <= 4; i++) {
				if (dir != i && !visited[r][c][i]) {
					int cnt = 1;
					if(dir==1) {
						if(i==2)
							cnt++;
					}
					else if(dir==2) {
						if(i==1) {
							cnt++;
						}
					}else if(dir==3) {
						if(i==4) {
							cnt++;
						}
					}else {
						if(i==3) {
							cnt++;
						}
					}
					visited[r][c][i] = true;
					q.add(new Robot(r,c,i,dep+cnt));
				}
			}
		}
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < R && dc >= 0 && dc < C;
	}

	static class Robot {
		int r;
		int c;
		int dir;
		int dep;

		public Robot(int r, int c, int dir, int dep) {
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.dep = dep;
		}

	}
}
