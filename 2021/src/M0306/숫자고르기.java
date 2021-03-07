package M0306;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class 숫자고르기 {
	static int N;
	static int map[];
	static int ans;
	static HashSet<Integer> set;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N+1];
		for (int i = 1; i <= N; i++) {
			map[i] = Integer.parseInt(br.readLine());
		}
		set = new HashSet<>();
		for (int i = 1; i <= N; i++) {
			ans = i;
			DFS(i, 0);
		}
	
		ArrayList<Integer> list = new ArrayList(set);
		Collections.sort(list);
		System.out.println(list.size());
		for (Integer child : list) {
			System.out.println(child);
		}

	}
	private static void DFS(int idx, int cnt) {
		if(map[idx]==ans) {
			set.add(ans);
		}
		if(cnt>=N) {
			return;
		}
		
		DFS(map[idx], cnt+1);
	}

}
