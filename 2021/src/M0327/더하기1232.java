package M0327;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 더하기1232 {
	static int n, k;
	static int ch=0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		DFS(0, "");
		System.out.println(-1);
	}

	private static void DFS(int cnt, String str) {
		if(cnt>n) {
			return;
		}
		if(cnt==n) {
			ch++;
			if(ch==k) {
				for (int i = 0; i < str.length()-1; i++) {
					System.out.print(str.charAt(i)+"+");
				}
				System.out.println(str.charAt(str.length()-1));
				System.exit(0);
			}
		}
		
		for (int i = 1; i <= 3; i++) {
			str += i;
			DFS(cnt + i, str);
			str = str.substring(0, str.length() - 1);
		}

	}
}
