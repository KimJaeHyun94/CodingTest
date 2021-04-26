package M0424;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 강의실배정 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Lecture> pq = new PriorityQueue();
		PriorityQueue<Integer> room = new PriorityQueue();
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			pq.add(new Lecture(s,e));
		}
		
		Lecture f = pq.poll();
		room.add(f.end);
		
		while(!pq.isEmpty()) {
			
			if(room.peek()<=pq.peek().start) {
				room.poll();
			}
			room.add(pq.poll().end);
		}
		
		System.out.println(room.size());
		
		
	}
	
	static class Lecture implements Comparable<Lecture>{
		int start;
		int end;
		public Lecture(int start, int end) {
			this.start = start;
			this.end = end;
		}
		@Override
		public int compareTo(Lecture o) {
			return this.start-o.start;
		}
		
		
	}
}
