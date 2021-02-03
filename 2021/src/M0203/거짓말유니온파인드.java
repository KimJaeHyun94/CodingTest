package M0203;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 거짓말유니온파인드 {
	static int[] parents;
	static int N, M;
	static List<Integer>[] Party;
	static int Truth[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		parents = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			makeSet(i);
		}
		st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken()); // 진실을 아는 사람의 수
		Truth = new int[T + 1];
		for (int i = 1; i <= T; i++) {
			Truth[i] = Integer.parseInt(st.nextToken());; // 진실을 아는 사람들의 번호
		}
		
		Party = new List[M+1];
		for (int i = 1; i <= M; i++) {
			Party[i] = new ArrayList<>();
		}

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			for (int j = 0; j < num; j++) {
				Party[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		for (int i = 1; i <= M; i++) {
			int cur = Party[i].get(0);
			for (int j = 1; j < Party[i].size(); j++) {
				union(cur, Party[i].get(j));
			}
		}
		int ans = 0;
		for (int i = 1; i <= M; i++) {
			boolean flag = true;
			int cur = Party[i].get(0);
			for (int j = 0; j < Truth.length; j++) {
				if(findSet(cur)==findSet(Truth[j])) { //부모가 같지 않다면 break
					flag = false;
					break;
				}
			}
			if(flag)
				ans++;
		}
		System.out.println(ans);
	}

	static void makeSet(int x) {
		parents[x] = x;
	}

	static int findSet(int x) {
		if (x == parents[x]) {
			return x;
		} else {
			parents[x] = findSet(parents[x]);
			return parents[x];
		}
	}

	static void union(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);
		parents[py] = px;    //같은 파티에 참여한 사람을 모두 같은 부모로 묶는다.
	}
}
