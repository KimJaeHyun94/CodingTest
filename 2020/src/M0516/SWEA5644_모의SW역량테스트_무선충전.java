package M0516;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA5644_모의SW역량테스트_무선충전 {
	static int[][] dirs = { { 0, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };

	static int M, A;

	static int[] userA;
	static int[] userB;

	static ArrayList<AP> list;

	static int ax, ay;
	static int bx, by;
	static int ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			userA = new int[M];
			userB = new int[M];
			list = new ArrayList<>();

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				userA[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				userB[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());

				list.add(new AP(x, y, c, p));
			}
			ax = 1;
			ay = 1; // a 시작점

			bx = 10;
			by = 10; // b 시작점
			ans = sol(ax, ay, bx, by);
			for (int i = 0; i < M; i++) {
				ax += dirs[userA[i]][0]; // userA에 저장되어 있는 숫자로 움직인다
				ay += dirs[userA[i]][1];
				bx += dirs[userB[i]][0];
				by += dirs[userB[i]][1];

				ans += sol(ax, ay, bx, by);
			}
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}

	private static int sol(int ax, int ay, int bx, int by) {
		int ans = 0;
		int[] onA = new int[A];
		int[] onB = new int[A];

		for (int i = 0; i < A; i++) {
			if (Math.abs(ax - list.get(i).x) + Math.abs(ay - list.get(i).y) <= list.get(i).c)
				onA[i] = 1;
			if (Math.abs(bx - list.get(i).x) + Math.abs(by - list.get(i).y) <= list.get(i).c)
				onB[i] = 1;
		}

		for (int i = 0; i < A; i++) {
			for (int j = 0; j < A; j++) {
				int temp = 0;
				if (i == j) {
					if (onA[i] == 1 || onB[j] == 1)
						temp = list.get(i).p;
				} else {
					if (onA[i] == 1)
						temp += list.get(i).p;
					if (onB[j] == 1)
						temp += list.get(j).p;
				}
				ans = Math.max(ans, temp);
			}
		}
		return ans;

	}

	static class AP {
		int x;
		int y;
		int c;
		int p;

		public AP(int x, int y, int c, int p) {
			this.x = x;
			this.y = y;
			this.c = c;
			this.p = p;
		}
	}
}
