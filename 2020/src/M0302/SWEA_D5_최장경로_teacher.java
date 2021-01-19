package M0302;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_D5_최장경로_teacher {
	static int N;
	static int min, CX, CY, HX, HY;
	static int[][] customers;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			customers = new int[N][2];
			min = Integer.MAX_VALUE;

			st = new StringTokenizer(br.readLine());
			CX = Integer.parseInt(st.nextToken());
			CY = Integer.parseInt(st.nextToken());
			HX = Integer.parseInt(st.nextToken());
			HY = Integer.parseInt(st.nextToken());

			// 고객집 좌표
			for (int i = 0; i <N ; i++) {
				customers[i][0] = Integer.parseInt(st.nextToken());
				customers[i][1] = Integer.parseInt(st.nextToken());
			}
			go(0, 0, CX, CY, 0);
			System.out.println("#" + tc + " " + min);
		}
	}

	private static void go(int count, int visited, int bx, int by, int result) {
		if (result >= min)   //가지치기: 기존까지 순열로 처리중인 고잭집들까지 이동했던 거리가 기존 min값과 비교
			return;
		if (count == N) { // 기저조건
			result += Math.abs(bx - HX) + Math.abs(by - HY);
			min = Math.min(min, result);
			return;
		}
		for (int i = 0; i < N; i++) {
			if ((visited & 1 << i) == 0) { // i 고객집이 기존 순열에 처리되었는지 확인
											// 0 -> 처리 안됨, 0 아님 -> 처리되었음
				go(count + 1, visited | (1 << i), customers[i][0], customers[i][1],
						result + Math.abs(bx - customers[i][0]) + Math.abs(by - customers[i][1]));
			}
		}
	}
}
