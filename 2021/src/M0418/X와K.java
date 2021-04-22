package M0418;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class X와K {
	static long X, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		X = Long.parseLong(st.nextToken());
		K = Long.parseLong(st.nextToken());

		long num = 1;
		long result = 0;
		ArrayList<Long> list = new ArrayList<>();
		while ((long) Math.pow(2, list.size()) <= K) {
			if ((X | num) != X) {
				list.add(num);
			}
			num *= 2;
		}
		
		for (int i = list.size() - 1; i >= 0; i--) {
			if (K == 0) {
				break;
			} else {
				if (K >= (long) Math.pow(2, i)) {
					result += list.get(i);
					K -= (long) Math.pow(2, i);
				}
			}
		}
		System.out.println(result);
	}
}
