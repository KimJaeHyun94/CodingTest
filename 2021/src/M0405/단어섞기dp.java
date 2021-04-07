package M0405;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 
 * @author 김재현
 * @see https://velog.io/@embeddedjune/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%EB%B0%B1%EC%A4%80-DP-9177-%EB%8B%A8%EC%96%B4-%EC%84%9E%EA%B8%B0
 */
public class 단어섞기dp {
	static String A, B, C;
	static int alen, blen;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= TC; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			A = st.nextToken();
			B = st.nextToken();
			C = st.nextToken();
			alen = A.length();
			blen = B.length();
			boolean memo[][] = new boolean[alen + 1][blen + 1];
			memo[0][0] = true;
			for (int i = 1; i <= alen; i++) {
				memo[i][0] = (A.charAt(i - 1) == C.charAt(i - 1)) ? memo[i - 1][0] : false;
			}
			for (int i = 1; i <= blen; i++) {
				memo[0][i] = (B.charAt(i - 1) == C.charAt(i - 1)) ? memo[0][i - 1] : false;
			}
			for (int i = 1; i <= alen; i++) {
				for (int j = 1; j <= blen; j++) {
					char a = A.charAt(i - 1);
					char b = B.charAt(j - 1);
					char c = C.charAt(i + j - 1);
					if (a != c && b != c)
						memo[i][j] = false;
					else if (a == c && b != c)
						memo[i][j] = memo[i - 1][j];
					else if (a != c && b == c)
						memo[i][j] = memo[i][j - 1];
					else
						memo[i][j] = memo[i - 1][j] || memo[i][j - 1];
				}
			}
			if (memo[alen][blen]) {
				sb.append("Data set ").append(t).append(": yes").append("\n");
			} else
				sb.append("Data set ").append(t).append(": no").append("\n");
		}
		System.out.println(sb);
	}
}
