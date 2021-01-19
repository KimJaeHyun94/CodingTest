import java.util.Scanner;

public class SWEA_9317_석찬이의받아쓰기 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int T=sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N=sc.nextInt();
			String str=sc.next();
			String str2=sc.next();
			int cnt=0;
			for (int i = 0; i < N; i++) {
				if(str.charAt(i)==str2.charAt(i))
					cnt++;
			}
			System.out.println("#"+tc+" "+cnt);
		}
	}
}
