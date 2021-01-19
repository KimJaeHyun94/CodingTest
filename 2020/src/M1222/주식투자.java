package M1222;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 주식투자 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int tc = 0; tc < T; tc++) {
			int C = Integer.parseInt(br.readLine());
			int ans = 0;
			for (int i = 0; i < C; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int cnt = 0;
				for (int j = 0; j < 3; j++) {
					cnt = Math.max(cnt, Integer.parseInt(st.nextToken()));
				}
				ans+=cnt;
			}
			sb.append(ans).append("\n");
		}
		System.out.println(sb.toString());
	}

}
