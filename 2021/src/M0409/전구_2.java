package M0409;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 전구_2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int arr[] = new int[N + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Node[] swlist = new Node[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int sws = Integer.parseInt(st.nextToken());
			swlist[i] = new Node(sws, i);
		}
		Arrays.sort(swlist);
		int index[] = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			int left = 1;
			int right = N + 1;

			while (left < right) {
				int mid = (left + right) >> 1;

				if (arr[i] < swlist[mid].sw)
					right = mid;
				else if (arr[i] > swlist[mid].sw) {
					left = mid + 1;
				} else {
					index[i] = swlist[mid].idx;
					break;
				}
			}
		}

		int lis[] = new int[N + 1];
		int path[] = new int[N + 1];
		// LIS
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < i; j++) {
				if (index[j] < index[i] && lis[i] < lis[j] + 1) {
					lis[i] = lis[j] + 1;
					path[i] = j;
				}
			}
		}
		int ans_num = 0, point = 1;

		for (int i = 1; i <= N; i++) {
			if (ans_num < lis[i]) {
				ans_num = lis[i];
				point = i;
			}
		}

		int all[] = new int[N + 1];
		int tmp = path[point], cnt = 0;
		all[++cnt] = arr[point];
		while (tmp > 0) {
			all[++cnt] = arr[tmp];
			tmp = path[tmp];
		}
		// 추적 끝

		// 정답 출력 시작
		System.out.println(ans_num);
		Arrays.sort(all, 0, cnt + 1);
		for (int i = 1; i <= cnt; i++)
			System.out.print(all[i] + " ");
	}

	static class Node implements Comparable<Node> {
		int sw;
		int idx;

		public Node(int sw, int idx) {
			this.sw = sw;
			this.idx = idx;
		}


		@Override
	      public int compareTo(Node o) {
	         if (this != null && o != null) {
	            return this.sw - o.sw;
	         }
	         return 0;
	      }
	}
}