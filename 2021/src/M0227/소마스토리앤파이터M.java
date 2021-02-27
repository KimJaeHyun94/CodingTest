package M0227;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 소마스토리앤파이터M {
	static int N;
	static List<Integer> graph[];
	static char arr[];
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		N = Integer.parseInt(br.readLine());
		String str[] = line.split(" ");
		int M = str.length;
		graph = new List[M];
		arr = new char[M];

		for (int i = 0; i < M; i++) {
			arr[i] = str[i].charAt(0);
		}

		for (int i = 0; i < M; i++) {
			graph[i] = new ArrayList<>();
		}

		boolean visited[] = new boolean[M];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char a = st.nextToken().charAt(0);
			char b = st.nextToken().charAt(0);
			int h = 0, q = 0;
			for (int j = 1; j <= N; j++) {
				if (arr[j] == a)
					h = j;
				else if (arr[j] == b)
					q = j;
			}
			visited[q] = true;
			graph[h].add(q);
		}
		for (int i = 0; i < M; i++) {
			if (!visited[i] && !graph[i].isEmpty()) {
				sol(i, "");
			}
		}
	}

	private static void sol(Integer cnt, String str) {
		str += arr[cnt];
		if (graph[cnt].isEmpty()) {
			System.out.println(str);
		} else {
			for (int child : graph[cnt]) {
				sol(child, str+" ");
			}
		}
	}
}
