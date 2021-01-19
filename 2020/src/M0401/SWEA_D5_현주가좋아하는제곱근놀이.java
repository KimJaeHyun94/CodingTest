package M0401;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_D5_현주가좋아하는제곱근놀이 {
	static long N;
	static int check;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Long.parseLong(br.readLine());
			check = 0;
			while (N!=2) {
				if (Math.sqrt(N) % 1 == 0) {
					N = (long) Math.sqrt(N);	
				} else {
					int next = (int)Math.sqrt(N)+1;
					check+=next*next - N;
					N = next;
				}
				check++;
			}
			System.out.println("#" + tc + " " + check);
		}
	}
}
