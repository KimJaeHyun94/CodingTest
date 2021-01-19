import java.util.Scanner;

public class SWEA_1861_정사각형방_DFS {
	private static int dir[][] = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
	private static int arr[][];
	private static int max = 0;
	private static int maxvalue;
	static int N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			max = 0;
			N = sc.nextInt();
			arr = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					arr[i][j] = sc.nextInt();
				}
			}
			int cnt = 1;
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr.length; j++) {
					DFS(arr[i][j], i, j, cnt);
				}
			}
			System.out.println("#" + tc + " " + maxvalue + " " + max);
		}
	}

	public static void DFS(int start, int y, int x, int cnt) {
		if (max <= cnt) {
			if (max == cnt) {
				if (maxvalue > start)
					maxvalue = start;
			} else {
				max = cnt;
				maxvalue = start;
			}
		}
		for (int i = 0; i < dir.length; i++) {
			int dy = y + dir[i][0];
			int dx = x + dir[i][1];

			if (isOk(dy, dx, y, x)) {
				cnt++;
				DFS(start, dy, dx, cnt);
			}
		}
	}

	private static boolean isOk(int dy, int dx, int y, int x) {
		if (dy >= 0 && dy < N && dx >= 0 && dx < N) {
			if (arr[y][x] + 1 == arr[dy][dx])
				return true;
		}
		return false;
	}
}
