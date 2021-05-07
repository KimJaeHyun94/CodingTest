package M0505;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 계보복원가호석 {
	static String arr[];
	static int N, M;
	static int[] degree;
	static List<Integer>[] graph;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		HashMap<String, Integer> map = new HashMap<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new String[N];
		for (int i = 0; i < N; i++) {
			arr[i] = st.nextToken();
		}

		Arrays.sort(arr);

		for (int i = 0; i < N; i++) {
			map.put(arr[i], i);
		}
		graph = new List[N];
		degree = new int[N];

		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
		}
		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = map.get(st.nextToken());
			int v2 = map.get(st.nextToken());
			graph[v2].add(v1);
			degree[v1]++;
		}

		BFS();

	}

	private static void BFS() {
		Queue<Integer> q = new LinkedList<>();
		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			if (degree[i] == 0) {
				q.add(i);
				list.add(arr[i]);
			}
		}
		sb.append(q.size()).append("\n"); // 각 가문의 개수
		for (String string : list) {
			sb.append(string + " ");
		}
		sb.append("\n");

		List<Integer>[] ans = new List[N];
		
		for (int i = 0; i < N; i++) {
			ans[i] = new ArrayList<>();
		}
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			for (Integer child : graph[cur]) {
				if (--degree[child] == 0) {
					q.add(child);
					ans[cur].add(child);		//차수가 없는 넘들 넣는다.
				}
			}
		}

		for (int i = 0; i < ans.length; i++) {
			sb.append(arr[i]+" "+ans[i].size()+" ");
			ArrayList<Integer> a = (ArrayList<Integer>) ans[i];
			Collections.sort(a);
			for (Integer child : a) {
				sb.append(arr[child]+" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);

	}
}
