package 슬라이딩윈도우;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 문자해독 {
	static int g, s;
	static String W, S;
	static int word[], sen[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		g = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());

		W = br.readLine();
		S = br.readLine();

		word = new int[52];
		sen = new int[52];

		for (int i = 0; i < g; i++) {
			char ch = W.charAt(i);
			if (ch < 'a')
				word[ch - 'A']++;
			else
				word[ch - 'a' + 26]++;
		}

		int len = 0, ans = 0, from = 0;
		for (int i = 0; i < s; i++) {
			char ch = S.charAt(i);
			if (ch < 'a')
				sen[ch - 'A']++;
			else
				sen[ch - 'a' + 26]++;
			len++;
			
			if (len == g) {
				if (check()) {
					ans++;
				}
				
				char co = S.charAt(from);
				if(co<'a') {
					sen[co-'A']--;
				}else
					sen[co-'a'+26]--;
				from++;
				len--;
			}
		}
		System.out.println(ans);
	}

	private static boolean check() {
		for (int i = 0; i < 52; i++)
			if (word[i] != sen[i])
				return false;
		return true;
	}
}
