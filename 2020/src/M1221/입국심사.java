package M1221;


public class 입국심사 {
	public static void main(String[] args) {
		
	}
	public long solution(int n, int[] times) {
        long answer = Long.MAX_VALUE;
        
        long left = 0;
        long right = (long) n * times[times.length - 1];
		
        while(left<=right) {
        	long mid = (left+right)/2;
        	long cnt = 0;
        	
        	for (int i = 0; i < times.length; i++) {
				cnt+=mid/times[0];
			}
        	
        	if(cnt>=n) {
        		answer=Math.min(answer, mid);
        		right = mid-1;
        	}else
        	{
        		left = mid+1;
        	}
        }
   
        
        return answer;
    }
}