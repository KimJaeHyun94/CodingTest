import java.util.Scanner;

public class Solution1861_정사각형방_김재현_수정본 {
	private static int dir[][] = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int arr[][] = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					arr[i][j] = sc.nextInt();
				}
				
			}

			int iindex[] = new int[N * N];
			int jindex[] = new int[N * N];

			int k = 0;
			int t = 0;

			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr.length; j++) {
					for (int j2 = 0; j2 < dir.length; j2++) {
						int ik = i + dir[j2][0];
						int jk = j + dir[j2][1];

						if (ik < 0 || jk < 0 || ik >= N || jk >= N)
							continue;
						else if (arr[i][j] + 1 == arr[ik][jk]) {
							iindex[k++] = i;
							jindex[t++] = j;
							break;
						}
					}
				}
			}

			boolean flag = true;
			int max = 0;
			int cnt = 1;
			int ch1 = 0;
			int ch2 = 0;
			for (int i = 0; i < jindex.length; i++) {
				cnt = 1;
				int y = iindex[i];
				int x = jindex[i];
				while (true) {
					flag = false;
					for (int j = 0; j < dir.length; j++) {
						int ik = y + dir[j][0];
						int jk = x + dir[j][1];
						if (ik < 0 || jk < 0 || ik >= N || jk >= N)
							continue;
						else if (arr[y][x] + 1 == arr[ik][jk]) {
							y = ik;
							x = jk;
							cnt++;
							flag = true;
						}
					}
					if (!flag)
						break;
				}
				if (max < cnt) {
					max = cnt;
					ch1 = iindex[i];
					ch2 = jindex[i];
				} else if (max == cnt) {
					if (arr[ch1][ch2] > arr[iindex[i]][jindex[i]]) {
						ch1 = iindex[i];
						ch2 = jindex[i];
					}
				}
			}
			System.out.println("#" + tc + " " + arr[ch1][ch2] + " " + max);
		}
	}
}
