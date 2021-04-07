package M0403;

public class test1 {
	public static void main(String[] args) {
		int[] gift_cards = {5,4,5,4,5};
		int[] wants = {1,2,3,5,4};
		
		System.out.println(solution(gift_cards, wants));
	}
	public static int solution(int[] gift_cards, int[] wants) {
        int answer = 0;
        
        int n = wants.length;
        for (int i = 0; i < n-1; i++) {
        	if(wants[i]!=gift_cards[i]) {
        		for (int j = i+1; j < n; j++) {
					if(wants[i]==gift_cards[j]) {
						int temp = gift_cards[i];
						gift_cards[i] = gift_cards[j];
						gift_cards[j] = temp; 
					}
				}
        	}
		}
        
        for (int i = 0; i < n; i++) {
			if(gift_cards[i]!=wants[i]) {
				answer++;
			}
		}
        
        
        return answer;
    }
}
