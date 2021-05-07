package M0504;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 사촌 {
	static int N, K;
	static int parents[], arr[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			arr = new int[N + 1];
			parents = new int[N + 1];

			if (N == 0 && K == 0)
				break;

			int idx = 0;
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				if (arr[i] == K)
					idx = i;
			}
			if (idx == 1) {
				sb.append(0).append("\n");
				continue;
			}

			int cnt = 0; 
			parents[1] = -1;

			for (int i = 2; i <= N; i++) {
				if (arr[i] != arr[i - 1] + 1) {
					cnt++;
				}
				parents[i] = cnt;
				System.out.println(arr[i]+" "+parents[i]);
			}

			int ans = 0;
			for (int i = 2; i <= N; i++) {
				System.out.println(parents[i] +" "+ parents[idx] +" "+ parents[parents[i]] +" "+ parents[parents[idx]]);
				if (parents[i] != parents[idx] && parents[parents[i]] == parents[parents[idx]]) // 부모는 다르고 부모의 부모가 같은
					ans++;
			}
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}

}
