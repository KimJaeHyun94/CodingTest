package M0223;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 동굴탐험위상정렬 {
	static List<Integer>[] graph;

	public static void main(String[] args) {
		System.out.println(solution(9,
				new int[][] { { 0, 1 }, { 0, 3 }, { 0, 7 }, { 8, 1 }, { 3, 6 }, { 1, 2 }, { 4, 7 }, { 7, 5 } },
				new int[][] { { 8, 5 }, { 6, 7 }, { 4, 1 } }));
	}

	public static boolean solution(int n, int[][] path, int[][] order) {
		graph = new List[n];

		for (int i = 0; i < n; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < path.length; i++) {
			graph[path[i][0]].add(path[i][1]);
			graph[path[i][1]].add(path[i][0]);
		}

		graph = directGraph(graph);

		return topological(graph, order);
	}

	private static boolean topological(List<Integer>[] graph2, int[][] order) {
		Queue<Integer> q = new LinkedList<>();
		int [] degree = new int[graph.length];   //위상 배열
		
		for (int i = 0; i < graph.length; i++) {
			for (int child : graph[i]) {    
				degree[child]++;
			}
		}
		
		for (int i = 0; i < order.length; i++) {
            graph[order[i][0]].add(order[i][1]);
            degree[order[i][1]]++;  // 후행 정점에 대한 간선의 수 증가
        }
		
		for(int i = 0; i < graph.length; i++) {
            if(degree[i] == 0) q.offer(i);   //위상이 0인 것부터 큐에 집어넣는다
        }
		int count = 0;
		
		while(!q.isEmpty()) {
			int temp = q.poll();
			count++;
			
			for (int child : graph[temp]) {
				if(--degree[child]==0) {   //위상이 0인 자식들을 넣어주고 0이면 하나 감소시킨다. 
   					q.add(child);
				}
			}
		}
		if(count==graph.length) {
			return true;
		}
		else
		return false;
	}

	private static List<Integer>[] directGraph(List<Integer>[] graph) { // 단방향 그래프 만들기
		Queue<Integer> q = new LinkedList<>();
		List<Integer>[] directedgraph = new ArrayList[graph.length];
		for (int i = 0; i < directedgraph.length; i++) {
			directedgraph[i] = new ArrayList<>();
		}
		boolean visited[] = new boolean[graph.length];
		q.add(0);
		visited[0] = true; // 0을 출발 지점으로 만든다

		while (!q.isEmpty()) {
			int temp = q.poll();

			for (int child : graph[temp]) {
				if (visited[child])
					continue;

				directedgraph[temp].add(child);
				visited[child] = true;
				q.add(child);
			}
		}

		return directedgraph;
	}
}
