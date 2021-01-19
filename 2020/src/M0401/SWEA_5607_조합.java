package M0401;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_5607_조합 {
	static int N,R;
	static int mod =1234567891;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			long f[] = new long[N+1];
			f[0] = 1;
			for (int i = 1; i <= N; i++) {
				f[i] = f[i-1]*i % mod;
			}
			long sol = Solution((f[R]*f[N-R])%mod, mod-2);
			long result = (f[N]*sol)%mod;
			System.out.println("#"+tc+" "+result);
		}
	}
	private static long Solution(long n, int x) {
		if (x == 0) return 1;
        long tmp = Solution(n, x / 2);
        long ret = (tmp * tmp) % mod;
        if (x % 2 == 0) return ret;
        else return (ret * n) % mod;
    }
}
