package M0413;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 암호키 {
	static int MAX = 1000000;
	static boolean prime[];
	static ArrayList<Integer> list = new ArrayList<>();

	public static void main(String[] args) throws NumberFormatException, IOException {

		prime = new boolean[MAX + 1];
		prime[0] = true;
		prime[1] = true;

		for (int i = 2; i * i <= MAX; i++) {
			if (!prime[i]) {
				for (int j = i * i; j <= MAX; j += i) {
					prime[j] = true;
				}
			}
		}

		for (int i = 2; i <= MAX; i++) {
			if (!prime[i])
				list.add(i);
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		while (T-- > 0) {
			long N = Long.parseLong(br.readLine());
			boolean flag = false;
			for (int i = 0; i < list.size(); i++) {
				if (N % list.get(i) == 0) {
					sb.append("NO").append("\n");
					flag = true;
					break;
				}
			}
			if(!flag) {
				sb.append("YES").append("\n");
			}
		}
		System.out.println(sb);
	}

}
