package M0506;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 소가길을건너간이유3 {
	static int N;
	static PriorityQueue<Cow> pq;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		pq = new PriorityQueue<>();
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			pq.add(new Cow(s,e));
		}
		
		int time = 0;
		
		while(!pq.isEmpty()) {
			Cow cur = pq.poll();
			int s = cur.start;
			int e = cur.end;
			
			if(time<s) {
				time = s;
			}
			
			time+=e;
		}
		System.out.println(time);
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
			if(this.start==o.start)
				return this.end-o.end;
			return this.start-o.start;
		}

	}
}
