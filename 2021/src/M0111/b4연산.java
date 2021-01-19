package M0111;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class b4연산 {
	static int S, T;
	static char cal[] = { '*', '+', '-', '/' };
	static boolean flag = false;
	static String ans;
	static Set<Long> visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		visited = new HashSet<Long>();
		if (S == T) {
			System.out.println(0);
		}else {
			BFS();
			if (flag) {
				System.out.println(ans.substring(1));
			} else {
				System.out.println(-1);
			}
		}
	}

	private static void BFS() {
		Queue<four> q = new LinkedList<>();
		q.add(new four(S, " "));
		visited.add((long)S);
		while (!q.isEmpty()) {
			four temp = q.poll();
			long n = temp.n;
			if (n == (long)T) {
				flag = true;
				ans = temp.str;
				return;
			}

			for (int d = 0; d < cal.length; d++) {
				if (d == 3 && n == 0) {
					continue;
				}
				long sum = calucalte(d, n);
				if (isOK(sum) && !visited.contains(sum)) {
					visited.add(sum);
					q.add(new four(sum, temp.str + cal[d]));
				}
			}
		}
	}

	private static boolean isOK(long sum) {
		return 0 <= sum && sum <= 1000000000;
	}

	private static long calucalte(int d, long n) {
		if (d == 0) {
			return n * n;
		} else if (d == 1) {
			return n + n;
		} else if (d == 2) {
			return n - n;
		} else {
			return n / n;
		}
	}

	static class four {
		long n;
		String str;

		public four(long n, String str) {
			this.n = n;
			this.str = str;
		}

	}
}
