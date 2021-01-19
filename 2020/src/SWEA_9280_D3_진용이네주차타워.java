import java.util.Scanner;

public class SWEA_9280_D3_진용이네주차타워 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int T=sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int n=sc.nextInt();
			int m=sc.nextInt();
			int R_i[]=new int[n];
			int W_i[]=new int[m];
			for (int i = 0; i < n; i++) {
				R_i[i]=sc.nextInt();
			}
			for (int i = 0; i < m; i++) {
				W_i[i]=sc.nextInt();
			}
			int visited[]=new int[n];
			int sum=0;
			int loading[]=new int[m+5];
			int cnt=0;
			boolean flag=false;
			for (int i = 0; i < 2*m; i++) {
				int k=sc.nextInt();
				flag=true;
				if(k>0) {
					for (int j = 0; j < visited.length; j++) {
						if(visited[j]==0) {
							visited[j]=k;
							sum+=R_i[j]*W_i[k-1];
							flag=false;
							break;
						}
					}
					if(flag) {
						loading[cnt++]=k;
					}
				}
				else {
					for (int j = 0; j < visited.length; j++) {
						if(visited[j]==Math.abs(k)) {
							visited[j]=0;
						}
					}
					forout:
					for (int j = 0; j < cnt; j++) {
						for (int j2 = 0; j2 < visited.length; j2++) {
							if(visited[j2]==0 && loading[j]!=0) {
								sum+=W_i[loading[j]-1]*R_i[j2];
								visited[j2]=loading[j];
								loading[j]=0;
								break forout;
							}
						}
					}
				}
			}
			System.out.println("#"+tc+" "+sum);
		}
	}
}
