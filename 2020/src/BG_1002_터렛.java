import java.util.Scanner;

public class BG_1002_터렛 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int T=sc.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			int x1=sc.nextInt();
			int y1=sc.nextInt();
			int r1=sc.nextInt();
			int x2=sc.nextInt();
			int y2=sc.nextInt();
			int r2=sc.nextInt();
			
			double d=Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
			
			if(r1 == r2 && x1== x2 && y1 == y2)
				System.out.println(-1);
			else if(d>r1+r2 || Math.abs(r1-r2)>d)
				System.out.println(0);
			else if(d==r1+r2 || Math.abs(r1-r2)==d)
				System.out.println(1);
			else if(d<r1+r2)
				System.out.println(2);
		}	
	}
}
