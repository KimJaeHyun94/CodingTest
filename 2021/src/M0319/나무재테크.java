package M0319;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class 나무재테크 {
	static int N, M, K;
	static int A[][];
	static int dirs[][] = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };
	static ArrayList<Tree> list;
	static ArrayList<Tree> death;
	static ArrayList<Tree> live;
	static int food[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		A = new int[N][N];
		food = new int[N][N];
		list = new ArrayList<>();

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				A[r][c] = Integer.parseInt(st.nextToken()); // 양분 추가분
				food[r][c] = 5; // 초기 양분
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int age = Integer.parseInt(st.nextToken());

			list.add(new Tree(r, c, age));

		}

		for (int i = 0; i < K; i++) { // K년이 지난 후

			death = new ArrayList<>();
			live = new ArrayList<>();
			Collections.sort(list);
			for (Tree tree : list) { // 봄
				int r = tree.r;
				int c = tree.c;
				int age = tree.age;

				if (food[r][c] < age) { // 양분을 못 먹으면 죽는다.
					death.add(tree);
					continue;
				}

				food[r][c] -= age; // 나이만큼 양분을 먹는다.
				tree.age += 1;
				live.add(tree);
			}
			list.clear();
			list.addAll(live);

			for (Tree tree : death) {
				food[tree.r][tree.c] += tree.age / 2;
			}

			for (int s = 0; s < list.size(); s++) { // 가을
				Tree tree = list.get(s);
				int r = tree.r;
				int c = tree.c;

				if (tree.age % 5 == 0) {
					for (int d = 0; d < 8; d++) {
						int dr = r + dirs[d][0];
						int dc = c + dirs[d][1];

						if (isOK(dr, dc)) {
							list.add(new Tree(dr, dc, 1));
						}
					}
				}
			}

			for (int r = 0; r < N; r++) { // 겨울에 양분 공급
				for (int c = 0; c < N; c++) {
					food[r][c] += A[r][c];
				}
			}
		}
		System.out.println(list.size());
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < N && dc >= 0 && dc < N;
	}

	static class Tree implements Comparable<Tree> {
		int r;
		int c;
		int age;

		public Tree(int r, int c, int age) {
			this.r = r;
			this.c = c;
			this.age = age;
		}

		@Override
		public int compareTo(Tree t) {
			return this.age - t.age;
		}

	}
}
