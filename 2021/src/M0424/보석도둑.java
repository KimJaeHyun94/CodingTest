package M0424;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 보석도둑 {
	static int N, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		Jewerly je[] = new Jewerly[N];
		int bag[] = new int[K];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			je[i] = new Jewerly(m, v);
		}

		for (int i = 0; i < K; i++) {
			bag[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(je); // 무게 기준으로
		Arrays.sort(bag); // 무게 기준으로

		long sum = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());

		int idx = 0;
		for (int i = 0; i < K; i++) {

			while (idx < N && je[idx].m <= bag[i]) { // 가방에 넣을 수 있다면
				pq.add(je[idx++].v);
			}

			if (!pq.isEmpty()) {
				int n = pq.poll();
				sum += n;
			}

		}
		System.out.println(sum);
	}

	static class Jewerly implements Comparable<Jewerly> {
		int m;
		int v;

		public Jewerly(int m, int v) {
			this.m = m;
			this.v = v;
		}

		@Override
		public int compareTo(Jewerly o) {
			return this.m - o.m;
		}
	}
}
