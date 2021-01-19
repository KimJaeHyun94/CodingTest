package M0513;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class hwalgo0513_서울_13반_김재현 {
	static int T, N, M;
	static int count;
	static int needs[];
	static long[] memo;
	static List<Integer> graph[];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int f, s;
		N = sc.nextInt();
		M = sc.nextInt();
		graph = new List[N+1];
		needs = new int[N+1];
		
		for (int i = 1; i < N+1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			f = sc.nextInt();
			s = sc.nextInt();
			graph[f].add(s);
			needs[s]++;
		}
		bfs();
	
	}
	private static void bfs() {
		Queue<Integer> queue = new LinkedList();
		for (int i = 1; i < N+1; i++) {
			if(needs[i] == 0) {
				queue.add(i);
			}
		}
		while(!queue.isEmpty()) {
			int v = queue.poll();
			System.out.print(v+ " ");
			
			for (int nextV : graph[v]) {
				needs[nextV]--;
				
				if(needs[nextV]==0) {
					queue.add(nextV);
				}
			}
		}
	}
}