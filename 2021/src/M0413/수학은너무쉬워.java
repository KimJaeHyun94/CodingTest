package M0413;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 수학은너무쉬워 {
	static int N;
	static int MAX = 1000000 + 1;
	static ArrayList<Integer> prime;
	static int visited[]; // 전체 들어있는 소수의 개수
	static int v[][]; // 각 소수의 개수

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		prime = new ArrayList<>();
		boolean[] cache = new boolean[MAX];

		cache[0] = cache[1] = false;

		for (int i = 2; i < MAX; i++) {
			cache[i] = true;
		}
		for (int i = 2; i * i < MAX; i++) {
			if (cache[i]) {
				for (int j = i * i; j < MAX; j += i) {
					if (cache[j]) {
						cache[j] = false;
					}
				}
			}
		}
		for (int i = 2; i < MAX; i++) {
			if(cache[i]) prime.add(i);
		}
		
		visited = new int[prime.size() + 1];
		v = new int[N + 1][prime.size() + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			for (int j = 0; j < prime.size(); j++) {
				while (num % prime.get(j) == 0) {
					v[i][j]++;
					visited[j]++;
					num /= prime.get(j);
					if (num == 1)
						break;
				}
				if (num == 1)
					break;
			}
		}

		int result = 1;// 최대공약수
		int cnt = 0;

		for (int i = 0; i < prime.size(); i++) {
			int temp = visited[i] / N;
			for (int j = 0; j < temp; j++) {
				result *= prime.get(i);
			}
			for (int j = 0; j < N; j++) {
				if (temp > v[j][i])
					cnt += temp - v[j][i];
			}
		}
		System.out.println(result + " " + cnt);
		return;
	}
}
