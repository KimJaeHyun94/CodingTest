
import java.util.Arrays;
import java.util.Scanner;

public class IMTest_여행 {
	public static boolean isIn(int row, int col, int N) {
		return row > 0 && col > 0 && row <= N && col <= N;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();
			int[][] map = new int[N + 1][N + 1];
			int startR = sc.nextInt();
			int startC = sc.nextInt();
			int trapn = sc.nextInt();
			int[][] trap = new int[trapn][2];

			for (int t = 0; t < trapn; t++) {
				trap[t][0] = sc.nextInt();
				trap[t][1] = sc.nextInt();
			}

			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					for (int t = 0; t < trapn; t++) {
						int ti = trap[t][0];
						int tj = trap[t][1];
						map[ti][tj] = 1;
					}
				}
			}

			int gonum = sc.nextInt();

			int cnt = 0;
				for (int go = 0; go < gonum; go++) {
					int dir = sc.nextInt();
					int move = sc.nextInt();
					switch (dir) {
					case 1: // 상
						for (int m = 0; m <= move; m++) {
							if (!isIn(startR - m, startC, N) || map[startR - m][startC] == 1) {
								break;
							} 
							startR = startR--;
							cnt++;
						}
						break;
					case 2: // 상우
						for (int m = 0; m <= move; m++) {
							if (!isIn(startR - m, startC + m, N) || map[startR - m][startC + m] == 1) {
								break;
							}
								startR = startR --;
								startC = startC ++;
								cnt++;
						}
						break;
					case 3: // 우
						for (int m = 0; m <= move; m++) {
							if (!isIn(startR, startC + m, N) || map[startR][startC + m] == 1) {
								break;
							} 
								startC = startC ++;
								cnt++;
						}
						break;
					case 4: // 하우
						for (int m = 0; m <= move; m++) {
							if (!isIn(startR + m, startC + m, N) || map[startR + m][startC + m] == 1) {
								break;
							} 
								startR = startR ++;
								startC = startC ++;
								cnt++;
						}
						break;
					case 5: // 하
						for (int m = 0; m <= move; m++) {
							if (!isIn(startR + m, startC, N) || map[startR + m][startC] == 1) {
								cnt--;
								break;
							}
								startR = startR ++;
								cnt++;
						}
						break;
					case 6: // 하좌
						for (int m = 0; m <= move; m++) {
							if (!isIn(startR + m, startC - m, N) || map[startR + m][startC - m] == 1) {
								cnt--;
								break;
							} 
								startR = startR ++;
								startC = startC --;
								cnt++;	
						}
						break;
					case 7: // 좌
						for (int m = 0; m <= move; m++) {
							if (!isIn(startR, startC - m, N) && map[startR][startC - m] == 1) {
								cnt--;
								break;
								
							} 
							startC = startC --;
							cnt++;
						}
						break;
					case 8: // 상좌
						for (int m = 0; m <= move; m++) {
							if (isIn(startR - m, startC - m, N) && map[startR - m][startC - m] == 0) {
								cnt--;
								break;
							} 
								startR = startR --;
								startC = startC --;
								cnt++;
						}
						break;
					}
				}
			System.out.println("#" + test_case + " " + cnt);
		}
	}
}