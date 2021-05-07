package M0506;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class 소가길을건너간이유1 {
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		ArrayList<Cow> list= new ArrayList<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			list.add(new Cow(s,e));
		}
		
		Collections.sort(list);
		
		int cnt = 0;
		for (int i = 1; i < N; i++) {
			Cow p = list.get(i-1);
			Cow c = list.get(i);
			
			if(p.s==c.s && p.e!=c.e) {
				cnt++;
			}
		}
		System.out.println(cnt);
	}

	static class Cow implements Comparable<Cow> {
		int s;
		int e;

		public Cow(int s, int e) {
			this.s = s;
			this.e = e;
		}

		@Override
		public int compareTo(Cow o) {
			return this.s - o.s;
		}

	}
}
