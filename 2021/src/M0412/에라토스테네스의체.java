package M0412;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 에라토스테네스의체 {

	public static void main(String[] args) throws IOException {
		int cnt = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		boolean[] cache = new boolean[N + 1];
		for (int i = 2; i <= N; i++) {
			if (!cache[i]) {
				cnt++;
				if(cnt==K) {
					System.out.println(i);
					break;
				}
				for (int j = i * i; j <= N; j += i) {
					if (!cache[j]) {
						cache[j] = true;
						cnt++;
						if (cnt == K) {
							System.out.println(j);
							break;
						}
					}
				}
			}

		}
	}

}
