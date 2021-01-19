import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ2617구슬찾기 {
	private static int N;
	private static int M;
	private static List<Integer>[] graph;
	private static List<Integer>[] graph2;
	private static boolean visited[];
	private static int cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new List[N + 1];
		graph2 = new List[N + 1];

		for (int i = 1; i < graph.length; i++) {
			graph[i] = new ArrayList<>();
			graph2[i] = new ArrayList<>();
		}
	
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			graph[a].add(b);
			graph2[b].add(a);
		}

		int check[] = new int[N];
		int check2[] = new int[N];
		for (int i = 0; i < N; i++) {
			cnt = 0; visited = new boolean[N + 1];
			DFS(i + 1,graph); check[i] = cnt;
			cnt = 0; visited = new boolean[N + 1];
			DFS(i + 1,graph2); check2[i] = cnt;
		}
	
		int count = 0;
		int mid = (N + 1) / 2;
		for (int i = 0; i < N; i++) {
			if (check[i] >= mid || check2[i] >= mid) {
				count++;
			}
		}
		System.out.println(count);
	}

	private static void DFS(int r, List<Integer>[] graph) {
		visited[r] = true;

		List<Integer> childs = graph[r];
		for (int i = 0; i < childs.size(); i++) {
			Integer child = childs.get(i);
			if (!visited[child]) {
				cnt++;
				DFS(child,graph);
			}
		}
	}
}
