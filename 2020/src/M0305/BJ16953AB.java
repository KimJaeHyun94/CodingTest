package M0305;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ16953AB {
	static long N,M;
	static int min=Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Long.parseLong(st.nextToken());
		M = Long.parseLong(st.nextToken());
		BFS(N,1);
		System.out.println(-1);
	}

	private static void BFS(long n, int cnt) {
		Queue<pair> q=new LinkedList<>();
		q.add(new pair(n,cnt));
		while(!q.isEmpty()) {
			pair temp=q.poll();
			long num=temp.n;
			int count=temp.cnt;
			if(num==M) {
				System.out.println(count);
				System.exit(0);
			}
			if(num*2<=M) {
				q.add(new pair(num*2,count+1));
			}
			if(num*10+1<=M) {
				q.add(new pair(num*10+1,count+1));
			}
		}
	}
	static class pair{
		long n;
		int cnt;
		public pair(long n, int cnt) {
			super();
			this.n = n;
			this.cnt = cnt;
		}
		
	}

}
