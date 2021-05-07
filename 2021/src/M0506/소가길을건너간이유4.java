package M0506;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 소가길을건너간이유4 {
	static int C, N;
	static int chicken[];
	static PriorityQueue<Cow> pq;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		chicken = new int[C];
		for (int i = 0; i < C; i++) {
			chicken[i] = Integer.parseInt(br.readLine());
		}
		
		
		Arrays.sort(chicken);
		pq = new PriorityQueue<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			pq.add(new Cow(s,e));
		}
		
		int ans = 0;
		while(!pq.isEmpty()) {
			boolean flag = false;
			for (int i = 0; i < C; i++) {
				if(chicken[i]>=pq.peek().start && chicken[i]<=pq.peek().end && chicken[i]>0)
				{
					flag = true;
					pq.poll();
					ans++;
					chicken[i] = 0;
					break;
				}
			}
			if(!flag) {
				pq.poll();
			}
		}
		System.out.println(ans);
	}

	static class Cow implements Comparable<Cow> {
		int start;
		int end;

		public Cow(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Cow o) {
			if(this.end==o.end)
				return this.start-o.start;
			return this.end - o.end;
		}

	}
}
