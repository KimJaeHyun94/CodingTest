import java.util.Scanner;

public class Solution1258_행렬찾기_김재현 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int T=sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N=sc.nextInt();
			int arr[][]=new int[N][N];
			
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr.length; j++) {
					arr[i][j]=sc.nextInt();
				}
			}
			int xcount=0;
			int ycount=0;
			int X[]=new int[N];
			int Y[]=new int[N];
			int cnt=0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(arr[i][j]!=0) {
						
						for (int j2 = j+1; j2 < N; j2++) {
							if(arr[i][j2]==0) {
								xcount=j2-j;
								break;
							}
							else if(j2==N-1) {
								xcount=j2-j+1;
								break;
							}
						}

						for (int j2 = i+1; j2 < N; j2++) {
							if(arr[j2][j]==0) {
								ycount=j2-i;
								break;
							}
							else if(j2==N-1) {
								ycount=j2-i+1;
								break;
							}
						}
						
						for (int j2 = 0; j2 <= ycount-1; j2++) {
							for (int k = 0; k <= xcount-1; k++) {
								arr[i+j2][j+k]=0;
							}
						}
						X[cnt]=xcount;
						Y[cnt]=ycount;
						cnt++;
					}
				}
			}
			
			System.out.print("#"+tc+" "+cnt);
			
			for (int i = 0; i < cnt-1; i++) {
				for (int j = i+1; j < cnt; j++) {
					if(X[i]*Y[i]>X[j]*Y[j]) {
						swap(X,i,j);
						swap(Y,i,j);
					}
					else if(X[i]*Y[i]==X[j]*Y[j] && Y[i]>Y[j]) {
						swap(X,i,j);
						swap(Y,i,j);
					}
				}
			}
			for (int i = 0; i < cnt; i++) {
				System.out.print(" "+Y[i]+" "+X[i]);
			}
			System.out.println();
		}
	}
	public static void swap(int arr[],int i, int j) {
		int temp=arr[i];
		arr[i]=arr[j];
		arr[j]=temp;
	}
}
