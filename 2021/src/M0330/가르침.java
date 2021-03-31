package M0330;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 가르침 {
	static int N, K;
	static boolean visited[];
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
			visited = new boolean[26];

			visited['a' - 'a'] = true;
			visited['n' - 'a'] = true;
			visited['t' - 'a'] = true;
			visited['i' - 'a'] = true;
			visited['c' - 'a'] = true;
			words = new String[N];
			for (int i = 0; i < N; i++) {
				words[i] = br.readLine();
			}

			DFS(0,0);
			System.out.println(max);
		}

	}

	private static void DFS(int start, int k) {
		if (k == K) {
			int cnt=0; 
			for (String word : words) {
				boolean check = true;
				for (int i = 0; i < word.length(); i++) {
					if(!visited[word.charAt(i)-'a']) {
						check = false;
						break;
					}
				}
				if(check) cnt++;
			}
			max = Math.max(max, cnt);
			return;
		}
		for (int i = start; i < 26; i++) {
			if (!visited[i]) {
				visited[i] = true;
				DFS(i, k + 1);
				visited[i] = false;
			}
		}

	}

}
