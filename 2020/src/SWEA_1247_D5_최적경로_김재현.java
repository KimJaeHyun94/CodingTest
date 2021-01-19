import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1247_D5_최적경로_김재현 {
	private static int N;
	private static customer[] graph;
	private static customer company;
	private static customer house;
	private static int min=Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int T=Integer.parseInt(st.nextToken());
		
		for (int tc = 1; tc <= T; tc++) {
			st=new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			graph=new customer[N];
			st=new StringTokenizer(br.readLine());
			min=Integer.MAX_VALUE;
			
			company=new customer(Integer.parseInt(st.nextToken()),(Integer.parseInt(st.nextToken())));
			house=new customer(Integer.parseInt(st.nextToken()),(Integer.parseInt(st.nextToken())));
			
			for (int i = 0; i < N; i++) {
				graph[i]=new customer(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			} 
			dfs(0,new customer[N] ,new boolean[N]);
			System.out.println("#"+tc+" "+min);
		}

	}
	
	private static void dfs(int index, customer[] temp, boolean[] visited) {
		if(index==N) {
			int sum=0;
			for (int i = 0; i < N-1; i++) {
				sum+=Math.abs(temp[i].x-temp[i+1].x)+Math.abs(temp[i].y-temp[i+1].y);
			}
			sum+=Math.abs(company.y-temp[0].y)
					+Math.abs(company.x-temp[0].x)+Math.abs(house.y-temp[N-1].y)
					+Math.abs(house.x-temp[N-1].x);
			min=Math.min(min, sum);
			return;
		}
		else {
			for (int i = 0; i < N; i++) {
				if(!visited[i]) {
					visited[i]=true;
					temp[index]=graph[i];
					dfs(index+1,temp,visited);
					visited[i]=false;
				}
			}
		}
	}

	static class customer{
		int x;
		int y;
		
		public customer(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
