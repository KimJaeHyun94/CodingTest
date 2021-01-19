package M0522;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class hwalgo0522_서울_13반_김재현 {

	public static int[] weight = null;
	public static int N;
	public static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#" + tc + " ");

			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			weight = new int[N];
			result = 0;
			for (int i = 0; i < N; i++) {
				weight[i] = Integer.parseInt(st.nextToken());
			}
			DFS(0, 0, 0);
			sb.append(result).append("\n");
		}
		System.out.println(sb);
	}

	private static void DFS(int step, int left, int right) {
		if (step == N) {
			result++;
		} else {
			for (int i = step; i < N; i++) {
				swap(step, i);
				DFS(step + 1, left + weight[step], right); // 왼쪽
				if (left >= right + weight[step]) {
					DFS(step + 1, left, right + weight[step]);
				}
				swap(step, i);
			}
		}
	}

	private static void swap(int step, int i) {
		int temp = weight[step];
		weight[step] = weight[i];
		weight[i] = temp;
	}
}
