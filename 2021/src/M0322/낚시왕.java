package M0322;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class 낚시왕 {

	static int R, C, M;
	static ArrayList<Shark> list = new ArrayList<>();
	static int dirs[][] = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()) - 1;
			int z = Integer.parseInt(st.nextToken());

			list.add(new Shark(r, c, s, d, z));
		}
		int sum = 0;
		for (int i = 0; i < C; i++) { // 낚시왕의 위치
			Collections.sort(list);

			int size = list.size();

			for (int j = 0; j < size; j++) { // 낚시
				Shark shark = list.get(j);
				if (shark.c == i) { // 낚시 할 수 있다면
					sum += shark.z; // 상어 크기를 저장.
					list.remove(j);
					break;
				}
			}
			Info newshark[][] = new Info[R][C];
			// 상어 이동
			for (Shark shark : list) {
				int r = shark.r;
				int c = shark.c;
				int d = shark.d;
				int s = shark.s;
				int z = shark.z;
				if (d ==0 || d==1)
					s = s % (2 * (R-1));	//나머지 계산으로 (8방향으로 돌면 제자리에 오기 때문에)
				else
					s = s % (2 * (C-1));
				
				for (int j = 0; j < s; j++) {
					if (d == 0 && r == 0) d = 1;
					else if (d == 1 && r == R-1) d = 0;
					else if (d == 2 && c == C-1) d = 3;
					else if (d == 3 && c == 0) d = 2;
					
					r+=dirs[d][0];
					c+=dirs[d][1];
				}
				// 방향 틀어서 다 도착하면
				if (newshark[r][c] == null) {
					newshark[r][c] = new Info(s, d, z);
				} else {
					if (newshark[r][c].z < z) { // 저장 되어 있는 놈보다 크다면
						newshark[r][c] = new Info(s, d, z); // 잡아 먹기
					} else { // 아니면 잡아 먹힘
						continue;
					}
				}
			}

			list.clear(); // 리스트를 비워둔다.
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (newshark[r][c] != null) {
						int s = newshark[r][c].s;
						int d = newshark[r][c].d;
						int z = newshark[r][c].z;
						list.add(new Shark(r, c, s, d, z));
					}
				}
			}
		}
		System.out.println(sum);
	}



	static class Info {
		int s;
		int d;
		int z;

		public Info(int s, int d, int z) {
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}

	static class Shark implements Comparable<Shark> {
		int r, c, s, d, z;

		public Shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}

		@Override
		public int compareTo(Shark s) {
			if (this.c == s.c) {
				if (this.r == s.r) {
					return this.z - s.z;
				} else
					return this.r - s.r;
			} else
				return this.c - s.c;
		}

	}
}
