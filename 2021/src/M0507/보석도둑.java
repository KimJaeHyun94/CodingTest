package M0507;

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

		Arrays.sort(je);
		Arrays.sort(bag);

		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder()); // 큰 것 부터 집어넣기

		int idx = 0;
		long sum = 0;
		for (int i = 0; i < K; i++) {
			while (idx < N && je[idx].m <= bag[i]) {
				pq.add(je[idx++].v); // 가격 넣기
			}

			if(!pq.isEmpty()) {
				sum += pq.poll();
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
