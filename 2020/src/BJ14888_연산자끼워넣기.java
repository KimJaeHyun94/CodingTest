import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ14888_연산자끼워넣기 {
	private static int N;
	private static int arr[];
	private static int map[] = new int[4];
	private static int max = Integer.MIN_VALUE;
	private static int min = Integer.MAX_VALUE;
	private static boolean visited[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		visited = new boolean[N - 1];
		for (int i = 0; i < 4; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		int idx = 0;

		DFS(0, arr[0]);
		System.out.println(max);
		System.out.println(min);
	}

	private static void DFS(int r, int result) {
		if (r == N - 1) {
			max = Math.max(max, result);
			min = Math.min(min, result);
		} else {
			for (int i = 0; i < 4; i++) {
				if (map[i] > 0) {
					map[i]--;
					switch (i) {
					case 0:
						DFS(r + 1, result + arr[r + 1]);
						break;
					case 1:
						DFS(r + 1, result - arr[r + 1]);
						break;
					case 2:
						DFS(r + 1, result * arr[r + 1]);
						break;
					case 3:
						DFS(r + 1, result / arr[r + 1]);
						break;
					}
					map[i]++;
				}
			}
		}
	}
}
