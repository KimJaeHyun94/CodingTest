package M0504;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 트리의순회 {
	static int N;
	static int inOrder[];
	static int postOrder[];
	static int position[];
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		inOrder = new int[N + 1];
		postOrder = new int[N + 1];
		position = new int[N + 1];
		for (int i = 1; i <= N; i++) { // l -> 본 -> r
			inOrder[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			postOrder[i] = Integer.parseInt(st.nextToken()); // l -> r -> 본 루트의 번호를 알 수 있다.
		}
		for (int i = 1; i <= N; i++) {
			position[inOrder[i]] = i;
		}

		solution(1, N, 1, N);
		System.out.println(sb);
	}

	private static void solution(int instart, int inend, int poststart, int postend) {
		if (instart > inend || poststart > postend) {
			return;
		}
		
		int root= postOrder[postend];
		
		sb.append(root+" ");
		int idx = position[root];
		solution(instart, idx-1, poststart, poststart-instart+idx-1);
		solution(idx+1, inend, postend-inend+idx, postend-1);
	}
}
