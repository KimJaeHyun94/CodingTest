package M0401;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 램프 {
	static int N, M, K;
	static int lamp[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		lamp = new int[N][M];
		String temp[] = new String[N];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			temp[i] = line;
			for (int j = 0; j < M; j++) {
				lamp[i][j] = line.charAt(j) - '0';
			}
		}

		K = Integer.parseInt(br.readLine());
		boolean check[] = new boolean[N];
		int max = 0;

		for (int i = 0; i < N; i++) {
			int cnt = 0;
			for (int j = 0; j < M; j++) {
				if (lamp[i][j] == 0) {
					cnt++;
				}
			}
			if ((cnt % 2 == K % 2) && cnt <= K) { // k번 조작해서 조건에 맞는 행이 나오는지
				check[i] = true;
			}
		}

		for (int i = 0; i < N; i++) {
			if (check[i]) {
				int cnt = 0;
				for (int j = 0; j < N; j++) {
					if(temp[i].equals(temp[j])) {
						cnt++;
					}
				}
				max = Math.max(max, cnt);
			}
		}
		System.out.println(max);
	}
}
