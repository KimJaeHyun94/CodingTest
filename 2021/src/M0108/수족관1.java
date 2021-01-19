package M0108;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
/*
 * @author AKKabiyo
 * @See https://jaimemin.tistory.com/1200#recentComments
 */
public class 수족관1 {
	static int MAX = 40001;
	static int[] depth;
	static ArrayList<Integer> hole;
	static int[] surface;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		depth = new int[MAX];
		surface = new int[MAX];
		int answer = 0;
		br.readLine(); // 처음 0,0 처리
		for (int i = 0; i < N / 2 - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			for (int j = x; j < x2; j++) {
				depth[j] = y;
			}
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int row = Integer.parseInt(st.nextToken());
		
		int K = Integer.parseInt(br.readLine());
		hole = new ArrayList<>();

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());

			hole.add(a);
		}

		for (int p = 0; p < hole.size(); p++) {
			int x = hole.get(p);
			int height = depth[x];
			surface[x] = height;
	
			for (int i = x - 1; i >= 0; i--) {
				height = Math.min(height, depth[i]);
				surface[i] = Math.max(surface[i], height);
			}

			height = depth[x];
			
			for (int i = x + 1; i < row; i++) {
				height = Math.min(height, depth[i]);
				surface[i] = Math.max(surface[i], height);
			}
			
		} 
		
		for (int i = 0; i < row; i++) {
			answer += depth[i] - surface[i];
		}
		System.out.println(answer);
		
	}

}
