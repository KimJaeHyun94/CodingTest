package 비트마스킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 가르침 {
	static int N, K;
	static int flag=0;
	static String words[];
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		if (K == 26) {
			System.out.println(N);
		} else if (K < 5) {
			System.out.println(0);
		} else {
			K -= 5;

			words = new String[N];
			for (int i = 0; i < N; i++) {
				words[i] = br.readLine().replaceAll("anta|tica", "");
			}
			flag |= 1 << ('a'-'a');
			flag |= 1 << ('n'-'a');
			flag |= 1 << ('t'-'a');
			flag |= 1 << ('i'-'a');
			flag |= 1 << ('c'-'a');
			DFS(0, 0, flag);
			System.out.println(max);
		}

	}

	private static void DFS(int start, int k, int flag) {
		if (k == K) {
			int cnt = 0;
			for (String word : words) {
				boolean check = true;
				for (int i = 0; i < word.length(); i++) {
					if ((flag & 1 << (word.charAt(i) - 'a')) == 0) {
						check = false;
						break;
					}
				}
				if (check)
					cnt++;
			}
			max = Math.max(max, cnt);
			return;
		}
		for (int i = start; i < 26; i++) {
			if ((flag & 1 << i) != 0)
				continue;
			DFS(i, k + 1, flag | 1 << i);

		}
	}

}
