import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	private static int[] map;
	private static int N;
	private static int cnt;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T=sc.nextInt();
		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();
			cnt=0;
			map = new int[N];

			DFSBackTrack(0);
			System.out.println("#"+t+" "+cnt);
		}
		
	}

	public static void DFSBackTrack(int row) {
		if (row == N) {
			cnt++;
			return;
		} else {
			for (int i = 0; i < N; i++) {
				if (isPromisingBackTrack(row, i)) {
					map[row] = i;
					DFSBackTrack(row + 1);
					map[row] = 0;
				}
			}
		}
	}

	static boolean isPromisingBackTrack(int r, int c) {
		int lc = c - 1;
		int rc = c + 1;
		for (int i = r - 1; i >= 0; i--) {
			if (map[i] == c)
				return false;
			if (lc >= 0 && map[i] == lc--)
				return false;
			if (rc < N && map[i] == rc++)
				return false;
		}
		return true;
	}
}
