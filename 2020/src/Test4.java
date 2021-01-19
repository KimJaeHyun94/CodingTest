import java.io.FileInputStream;

import java.util.Scanner;

public class Test4 {
	// 1부터 시작
	static int[][] dir = { { 0, 0 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 },
			{ -1, -1 } };
	static int N, Si, Sj, P, D;
	static int Answer;

	public static boolean isInArr(int nx, int ny) {
		if (nx >= 0 && nx < N && ny >= 0 && ny < N)
			return true;
		return false;
	}

	public static boolean isTrap(int x, int y, int[] pi, int[] pj) {
		for (int i = 0; i < pi.length; i++) {
			if (x == pi[i] && y == pi[i])
				return true;
		}
		return false;
	}

	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {

			Answer = 0;

			N = sc.nextInt();
			Si = sc.nextInt() - 1; // 출발점의 i좌표
			Sj = sc.nextInt() - 1; // 출발점의 j좌표
			P = sc.nextInt();

			int Pi[] = new int[P]; // 함정의 i좌표의 모음
			int Pj[] = new int[P]; // 함정의 j좌표의 모음
			for (int k = 0; k < P; k++) { // 함정 좌표 읽어서 저장
				Pi[k] = sc.nextInt() - 1;
				Pj[k] = sc.nextInt() - 1;
			}

			D = sc.nextInt();
			int Dd[] = new int[D]; // 방향 모음
			int Da[] = new int[D]; // 이동 칸수 모음
			for (int k = 0; k < D; k++) { // 방향, 이동칸수 읽어서 저장
				Dd[k] = sc.nextInt();
				Da[k] = sc.nextInt();
			}

			int nx = Si;
			int ny = Sj;
			boolean flag = false;

			for (int i = 0; i < D; i++) {
				if (flag)
					break;
				for (int j = 0; j < Da[i]; j++) {
					nx += dir[Dd[i]][0];
					ny += dir[Dd[i]][1];
					if (isInArr(nx, ny)) {
						if (!isTrap(nx, ny, Pi, Pj)) {
							Answer++;
						}
						else {
							flag = true;
							break;
						}
					} else {
						flag = true;
						break;
					}
				}
			}
			System.out.println("#" + test_case + " " + Answer);
		}
	}
}