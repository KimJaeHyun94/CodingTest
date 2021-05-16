package M0516;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 인간_컴퓨터상호작용 {
	static int sum[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();

		sum = new int[str.length()][26];
		for (int i = 0; i < str.length(); i++) {
			int n = str.charAt(i) - 'a';

			if (i == 0) {
				sum[0][n] = 1;
			} else {
				for (int j = 0; j < 26; j++) {
					sum[i][j] = sum[i - 1][j];
				}
				sum[i][n]++;
			}

		}

		int q = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < q; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int ch = st.nextToken().charAt(0) - 'a';
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			int cnt = 0;
			if (s == 0) {
				cnt = sum[e][ch];
			} else
				cnt = sum[e][ch] - sum[s - 1][ch];

			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
	}

}
