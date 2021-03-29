package M0325;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 네트워크연결 {
	static int parents[];
	static int N;
	static int lines[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < T; i++) {
			N = Integer.parseInt(br.readLine());
			parents = new int[N + 1];
			lines = new int[N + 1];

			for (int p = 1; p <= N; p++) {
				parents[p] = p;
			}
			while (true) {
				String line = br.readLine();
				String str[] = line.split(" ");
				if (str[0].equals("O")) {
					break;
				}
				if (str[0].equals("E")) {
					int to = Integer.parseInt(str[1]);
					findSet(to);
					sb.append(lines[to]).append("\n");
				} else {
					int a = Integer.parseInt(str[1]);
					int b = Integer.parseInt(str[2]);

					union(a, b);
				}
			}

		}
		System.out.println(sb);
	}

	public static void union(int a, int b) {
		parents[a] = b;
		lines[a] = Math.abs((a - b)) % 1000;
	}

	private static int findSet(int x) {
		if (x == parents[x]) {
			return x;
		} else {
			lines[x] += lines[parents[x]];
			parents[x] = findSet(parents[x]);
			return parents[x];
		}
	}
}
