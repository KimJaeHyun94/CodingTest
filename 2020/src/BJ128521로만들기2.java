import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ128521로만들기2 {
	static int n;
	static boolean[] visited;

	static class Pair {
		int val;
		int dep;
		ArrayList<Integer> parents;

		public Pair(int val, int dep, ArrayList<Integer> parents) {
			this.val = val;
			this.dep = dep;
			this.parents = parents;
		}

	}

	public static Pair bfs() {
		Queue<Pair> q = new LinkedList<>();
		q.offer(new Pair(n, 0, new ArrayList<Integer>()));
		visited[n] = true;
		while (!q.isEmpty()) {
			Pair p = q.poll();
			int val = p.val;
			int dep = p.dep;
			if (val == 1) {
				p.parents.add(1);
				return p;
			}
			if (val % 2 == 0 && !visited[val / 2]) {
				ArrayList<Integer> temp = new ArrayList<>();
				temp.addAll(p.parents);
				temp.add(val);
				q.offer(new Pair(val / 2, dep + 1, temp));
				visited[val / 2] = true;
			}
			if (val % 3 == 0 && !visited[val / 3]) {
				ArrayList<Integer> temp = new ArrayList<>();
				temp.addAll(p.parents);
				temp.add(val);
				q.offer(new Pair(val / 3, dep + 1, temp));
				visited[val / 3] = true;

			}
			if (val - 1 >= 1 && !visited[val - 1]) {
				ArrayList<Integer> temp = new ArrayList<>();
				temp.addAll(p.parents);
				temp.add(val);
				q.offer(new Pair(val - 1, dep + 1, temp));
				visited[val - 1] = true;
			}
		}
		return null;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		visited = new boolean[1000001];
		visited[0] = true;
		Pair pair = bfs();
		System.out.println(pair.dep);
		for (int i = 0; i < pair.parents.size(); i++) {
			System.out.print(pair.parents.get(i) + " ");
		}
		System.out.println();
	}
}