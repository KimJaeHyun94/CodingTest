package M0419;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ14890경사로 {
	static int N, L;
	static int map[][];
	static int arr[][];
	static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				arr[j][i] = map[i][j];
			}
		}
		for (int i = 0; i < N; i++) {
			Solution(i, map);
			Solution(i, arr);
		}
		System.out.println(cnt);
	}

	private static void Solution(int i, int map[][]) {
		boolean visited[] = new boolean[N];
		for (int j = 0; j < N - 1; j++) {
			if (map[i][j] != map[i][j + 1]) {
				int minus = map[i][j] - map[i][j + 1];
				if(minus != -1 && minus != 1) return;
				
				if (minus == 1) { // 차이가 1일 때 경사로 가능
					for (int l = 1; l <= L; l++) {
						if (j + l >= N || visited[j + l]) { // 범위 벗어나거나 방문한대면 false
							return;
						}
						if (map[i][j] - 1 == map[i][j + l]) { // 같다면 true
							visited[j + l] = true;
						} else { // 하나라도 false면 경사길 못만듬
							return;
						}
					}
				} else  { // 차이가 -1일 때 경사로 가능
					for (int l = 0; l < L; l++) {
						if (j - l < 0 || visited[j - l]) { // 범위 벗어나거나 방문한대면 false
							return;
						}
						if (map[i][j] == map[i][j - l]) { // 같다면 true
							visited[j - l] = true;
						} else {
							return;
						}
					}
				}
			}
		}
		cnt++;
	}
}
