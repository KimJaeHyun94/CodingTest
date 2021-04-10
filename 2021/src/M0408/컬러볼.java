package M0408;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
/**
 * 
 * @author 김재현
 * @see https://ksh-code.tistory.com/60
 * 
 */
public class 컬러볼 {
	static int N;
	static ArrayList<Ball> list;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int color = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			list.add(new Ball(i, color, weight));
			
		}
		Collections.sort(list);
		
		int total = 0;
		int color[] = new int[N+1];
		int score[] = new int[N+1];
		for (int i = 0, j=0; i < N; i++) {
			for (; list.get(j).weight< list.get(i).weight; j++) {
				total+=list.get(j).weight;
				color[list.get(j).color]+=list.get(j).weight;
			}
			score[list.get(i).idx] +=total-color[list.get(i).color];
		}
		
		for (int i = 1; i <= N; i++) {
			System.out.println(score[i]);
		}
	}
	static class Ball implements Comparable <Ball>{
		int idx;
		int color;
		int weight;
		public Ball(int idx, int color, int weight) {
			this.idx = idx;
			this.color = color;
			this.weight = weight;
		}
		@Override
		public int compareTo(Ball b) {
			return this.weight-b.weight;
		}
		
	}
}
