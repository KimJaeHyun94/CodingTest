package M0318;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 청소년상어 {

	static int dirs[][] = { { -1, 0 }, { -1, -1 }, { 0, -1 }, { 1, -1 }, { 1, 0 }, { 1, 1 }, { 0, 1 }, { -1, 1 } };
	static int MAX = 0;

	/**
	 * @author 김재현
	 * @see https://data-make.tistory.com/529
	 * 
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int map[][] = new int[4][4];
		Fish[] fish = new Fish[17];
		for (int r = 0; r < 4; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < 4; c++) {
				int a = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken()) - 1;

				map[r][c] = a;
				fish[a] = new Fish(r, c, d);
			}
		}
		int temp = map[0][0];

		Fish shark = new Fish(0, 0, fish[map[0][0]].d);
		fish[map[0][0]] = null;
		map[0][0] = -1; // 처음에 먹고 시작함

		DFS(map, shark, fish, temp);
		System.out.println(MAX);
	}

	private static void DFS(int[][] map, Fish shark, Fish[] fish, int sum) {

	
		for (int i = 1; i <= 16; i++) { // 번호 작은 물고기부터 이동 0번은 이미 죽었음
			Fish cur = fish[i];

			if (cur == null)
				continue;

			int origin = cur.d;

			while (true) {
				int dr = cur.r + dirs[cur.d][0];
				int dc = cur.c + dirs[cur.d][1];

				if (isOK(dr, dc) && map[dr][dc] != -1) {

					if (fish[map[dr][dc]] == null) {
						fish[i] = new Fish(dr, dc, cur.d);
					} else {
						fish[i] = new Fish(dr, dc, cur.d);
						fish[map[dr][dc]] = new Fish(cur.r, cur.c, fish[map[dr][dc]].d); // 둘이 바꾼다.
					}

					int temp = map[dr][dc];
					map[dr][dc] = i;
					map[cur.r][cur.c] = temp;
					
					break;
				} else {
					cur.d = (cur.d + 1) % 8; // 반시계 방향으로 회전
				}

				if (cur.d == origin) // 한 바퀴 돌면 나오기(다 돌았을때 없으면 끝내게)
					break;
			}
		}


		for (int j = 1; j <= 3; j++) { // 상어는 모든 방향으로 움직인다.
			
			
			int dr = shark.r + dirs[shark.d][0] * j;
			int dc = shark.c + dirs[shark.d][1] * j;

			if (isOK(dr, dc) && map[dr][dc] > 0) {
				
				int[][] copymap = Copy(map);
				Fish[] copyfish = Copy(fish);
				Fish newShark = new Fish(shark.r, shark.c, shark.d);
				
				int temp = copymap[dr][dc]; // 원래 있는 숫자
				Fish f = copyfish[copymap[dr][dc]];

				copyfish[copymap[dr][dc]] = null;
				newShark = new Fish(f.r, f.c, f.d);
				copymap[dr][dc] = -1; // 먹었으니까 값을 없앤다.
				copymap[shark.r][shark.c] = 0; // 원래 상어 위치는 비워두기

				DFS(copymap, newShark, copyfish, sum + temp);

			}
		}
		MAX = Math.max(MAX, sum);
	}

	private static Fish[] Copy(Fish[] fish) {
		Fish copy[] = new Fish[17];
		for (int i = 0; i < 17; i++) {
			if (fish[i] == null)
				continue;
			copy[i] = new Fish(fish[i].r, fish[i].c, fish[i].d);
		}
		return copy;
	}

	private static int[][] Copy(int[][] map) {
		int temp[][] = new int[4][4];
		for (int r = 0; r < 4; r++) {
			for (int c = 0; c < 4; c++) {
				temp[r][c] = map[r][c];
			}
		}
		return temp;
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < 4 && dc >= 0 && dc < 4;
	}

	static class Fish {
		int r;
		int c;
		int d;

		public Fish(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}

	}
}
