package M0506;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 소가길을건너간이유2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();

		Cow cow[] = new Cow[26];
		for (int i = 0; i < 26; i++) {
			cow[i] = new Cow();
		}
		for (int i = 0; i < 52; i++) {
			char ch = str.charAt(i);
			int now = ch - 'A';

			if (cow[now].s == 0) {
				cow[now].s = i + 1;
			} else
				cow[now].e = i + 1;

		}

		int cnt = 0;
		for (int i = 0; i < 26; i++) {
			for (int j = 0; j < 26; j++) {
				if (cow[i].s < cow[j].s && cow[j].s < cow[i].e && cow[i].e < cow[j].e) {
					cnt++;
				}
			}
		}
		System.out.println(cnt);

	}

	static class Cow {
		int s;
		int e;

		public Cow() {
		}

		public Cow(int s, int e) {
			this.s = s;
			this.e = e;
		}
	}
}
