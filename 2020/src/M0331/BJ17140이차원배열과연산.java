package M0331;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BJ17140이차원배열과연산 {
	static int R, C, K;
	static int Row = 3, Col = 3;
	static int map[][] = new int[101][101];
	static int check;
	static ArrayList<Node> list;
	static int num[] = new int[101];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		while (true) {
			if (map[R - 1][C - 1] == K) { // 정답
				break;
			}
			if (check > 100) // 범위 벗어나면 break
				break;
			check++;
			if (Col >= Row) { // R연산
				for (int i = 0; i < Col; i++) {
					list = new ArrayList<>();
					num = new int[101];
					for (int j = 0; j < Row; j++) {
						num[map[i][j]]++;
						map[i][j] = 0;
					}
					for (int j = 1; j < num.length; j++) {
						if (num[j] != 0)
							list.add(new Node(j, num[j]));
					}
					Collections.sort(list);

					int k = 0;
					for (Node n : list) {
						map[i][k++] = n.value;
						map[i][k++] = n.cnt;
					}
					Row = Math.max(Row, k);
				}
			} else { // C연산
				for (int i = 0; i < Row; i++) {
					list = new ArrayList<>();
					num = new int[101];
					for (int j = 0; j < Col; j++) {
						num[map[j][i]]++;
						map[j][i] = 0;
					}
					for (int j = 1; j < num.length; j++) {
						if (num[j] != 0)
							list.add(new Node(j, num[j]));
					}
					Collections.sort(list);
					int k = 0;

					for (Node n : list) {
						map[k++][i] = n.value;
						map[k++][i] = n.cnt;
					}
					Col = Math.max(Col, k);
				}
			}
		}
		if (check <= 100)
			System.out.println(check);
		else
			System.out.println(-1);
	}

	static class Node implements Comparable<Node> {
		int value;
		int cnt;

		public Node(int value, int cnt) {
			this.value = value;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Node O) {
			if (this.cnt == O.cnt)
				return this.value - O.value;
			return this.cnt - O.cnt;
		}
	}
}
