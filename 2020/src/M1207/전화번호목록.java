package M1207;

import java.util.Arrays;
class Solution {
   public boolean solution(String[] phone_book) {
		boolean answer = true;	
        Arrays.sort(phone_book);
		outer :for (int i = 0; i < phone_book.length-1; i++) {
			for (int j = i+1; j < phone_book.length; j++) {
				if(phone_book[i].length()<=phone_book[j].length()) {
					String str = phone_book[j].substring(0, phone_book[i].length());
					if(phone_book[i].equals(str))
					{
						answer = false;
						break outer;
					}
				}
			}
		}
		return answer;
	}
}