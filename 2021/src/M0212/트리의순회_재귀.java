package M0212;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author AKKabiyo
 * @See https://dundung.tistory.com/47
 */
public class 트리의순회_재귀 {
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
		if (instart > inend || poststart > postend)
			return;
		int root = postOrder[postend]; // 마지막 번째가 루트이다
		
		sb.append(root + " ");
		int rootidx = position[root]; // 루트의 인덱스
		solution(instart, rootidx - 1, poststart, poststart - instart + rootidx - 1);
		solution(rootidx + 1, inend, postend - inend + rootidx, postend - 1);
	}
}
