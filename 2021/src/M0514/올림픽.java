package M0514;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class 올림픽 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		World arr[] = new World[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			arr[i] = new World(n, g, s, b, 0);
		}

		Arrays.sort(arr);
		arr[0].r = 1;
		int ans = 1;

		for (int i = 1; i < N; i++) {
			if (arr[i].g == arr[i - 1].g && arr[i].s == arr[i - 1].s && arr[i].b == arr[i - 1].b) {
				arr[i].r = arr[i - 1].r;
			} else {
				arr[i].r = i + 1;
			}
			if (arr[i].n == K) {
				ans = arr[i].r;
				break;
			}
		}

		System.out.println(ans);

	}

	static class World implements Comparable<World> {
		int n;
		int g;
		int s;
		int b;
		int r;

		public World(int n, int g, int s, int b, int r) {
			super();
			this.n = n;
			this.g = g;
			this.s = s;
			this.b = b;
			this.r = r;
		}

		@Override
		public int compareTo(World o) {
			if (this.g == o.g) {
				if (this.s == o.s) {
					return o.b-this.b;
				} else
					return o.s-this.s;
			}
			return o.g-this.g;
		}

	}
}
