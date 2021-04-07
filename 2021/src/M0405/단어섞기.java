package M0405;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class 단어섞기 {
	static boolean flag = false;
	static boolean isable = false;
	static int N;
	static int alen, blen;
	static char A[], B[], C[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= TC; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			A = st.nextToken().toCharArray();
			B = st.nextToken().toCharArray();
			C = st.nextToken().toCharArray();

			flag = false;
			isable = true;
			N = C.length;
			alen = A.length;
			blen = B.length;
			if (alen + blen != N) {
				sb.append("Data set ").append(t).append(": no").append("\n");
				continue;
			}
			HashSet<Character> set = new HashSet<>();
			for (int i = 0; i < A.length; i++) {
				set.add(A[i]);
			}
			for (int i = 0; i < B.length; i++) {
				set.add(B[i]);
			}
			for (int i = 0; i < C.length; i++) {
				if (!set.contains(C[i])) {
					isable = false;
					break;
				}
			}

			if (!isable) {
				sb.append("Data set ").append(t).append(": no").append("\n");
			} else {
				DFS(0, 0, 0);
				if (flag) {
					sb.append("Data set ").append(t).append(": yes").append("\n");
				} else {
					sb.append("Data set ").append(t).append(": no").append("\n");
				}
			}
		}
		System.out.println(sb);
	}

	private static void DFS(int adx, int bdx, int cnt) {
		if (flag)
			return;
		if (cnt == N) {
			flag = true;
			return;
		}
		if (adx < alen) {
			if (A[adx] == C[cnt]) {
				DFS(adx + 1, bdx, cnt + 1);
			}
		}
		if (bdx < blen) {
			if (B[bdx] == C[cnt]) {
				DFS(adx, bdx + 1, cnt + 1);
			}
		}

	}
}
