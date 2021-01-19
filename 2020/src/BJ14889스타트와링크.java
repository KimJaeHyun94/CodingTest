import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14889스타트와링크 {
	private static int N;
	private static int arr[][];
	static boolean[] visited;
	static int[] temp;
	static int min=Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		arr=new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		visited=new boolean[N];
		newpermuation(1,0);
		System.out.println(min);

	}
	private static void newpermuation(int start, int r) {
		if(r==N/2) {
			min=Math.min(check(),min);
			return;
		}
		else {
			for (int i = start; i < N; i++) {
				if(!visited[i]) {
					visited[i]=true;
					newpermuation(i+1,r+1);
					visited[i]=false;
				}
			}
		}
	}
	private static int check() {
		int start=0;
		int link=0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(visited[i] && visited[j]) {
					start+=arr[i][j];
				}
				if(!visited[i] && !visited[j]) {
					link+=arr[i][j];
				}
			}
		}
		return Math.abs(start-link);
	}
}
