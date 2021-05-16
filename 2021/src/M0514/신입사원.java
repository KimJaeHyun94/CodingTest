package M0514;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class 신입사원 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			
			ArrayList<Score> list = new ArrayList<>();
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int m = Integer.parseInt(st.nextToken());
				
				list.add(new Score(s,m));
			}
			
			Collections.sort(list);
			
			int rank = 1;
			int check = list.get(0).m;			//인터뷰 성적
			
			for (int i = 1; i < list.size(); i++) {
				if(list.get(i).m < check) {			//기존 인터뷰 심사 성적보다 높은 성적 채용
					rank++;
					check = list.get(i).m;
				}
			}
			sb.append(rank).append("\n");
		}
		System.out.println(sb);
	}

	static class Score implements Comparable<Score> {
		int s; // 서류 심사
		int m; // 면접 시험

		public Score(int s, int m) {
			this.s = s;
			this.m = m;
		}

		@Override
		public int compareTo(Score o) {
			return this.s - o.s;
		}

	}
}
