import java.util.Scanner;

public class Main3109_빵집_김재현 {
	private static int dir[][]= {{-1,1},{0,1},{1,1}};
	private static int C;
	private static int R;
	private static char arr[][];
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		R=sc.nextInt();
		C=sc.nextInt();
		int count=0;
		arr=new char[R][C];
		for (int i = 0; i < R; i++) {
			String str=sc.next();
			for (int j = 0; j < C; j++) {
				arr[i][j]=str.charAt(j);
			}
		}
		for (int i = 0; i < R; i++) {
			if(DFS(i,0))
				count++;
		}
		System.out.println(count);
	}
	
	public static boolean DFS(int i, int j) {
		arr[i][j]='x';
		if(j==C-1)
			return true;
		for (int j2 = 0; j2 < dir.length; j2++) {
			int di=i+dir[j2][0];
			int dj=j+dir[j2][1];
			
			if(di>=0&& dj>=0 && di<R && dj<C) {
				if(arr[di][dj]=='.') {
					boolean flag = DFS(di,dj);
					if(flag)
						return flag;
				}
			}
		}
		return false;
	}
}
