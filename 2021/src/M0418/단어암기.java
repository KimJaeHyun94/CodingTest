package M0418;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 단어암기 {
	static int words[];
	static int N, M;
	static int alphabet = (1 << 27) - 1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		words = new int[N];
		for (int i = 0; i < N; i++) {
			String word = br.readLine();
			for (char ch : word.toCharArray()) {
				words[i] |= 1 << (ch - 'a');
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			char ch = st.nextToken().charAt(0);

			if (n == 1) {
				alphabet &= ~(1 << (ch - 'a'));

			} else {
				alphabet |= (1 << (ch - 'a'));
			}

			int cnt = 0;
			for (int j = 0; j < N; j++) {
				if ((alphabet & words[j]) >= words[j]) {
					cnt++;
				}
			}
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);

	}
}
