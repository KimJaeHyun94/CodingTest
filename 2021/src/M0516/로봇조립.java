package M0516;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 로봇조립 {
	static int N;
	static int parents[], cnt[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		parents = new int[1000001];
		cnt = new int[1000001];

		for (int i = 0; i < 1000001; i++) {
			parents[i] = i;
			cnt[i] = 1;
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char ch = st.nextToken().charAt(0);
			if (ch == 'I') {
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int ps = findSet(s);
				int pe = findSet(e);
				if (ps != pe) {
					parents[ps] = pe;
					cnt[pe] += cnt[ps];
					cnt[ps] = 0;
				}
			} else {
				int s = Integer.parseInt(st.nextToken());
				sb.append(cnt[findSet(s)]).append("\n");
			}
		}
		System.out.println(sb);
	}

	private static int findSet(int p) {
		if (p == parents[p])
			return p;
		return parents[p] = findSet(parents[p]);
	}
}