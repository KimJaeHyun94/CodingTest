
import java.util.Arrays;
import java.util.Scanner;

public class BJ17413_단어뒤집기2 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String str=sc.nextLine();
		
		char arr[]=str.toCharArray();
		String str2="";
		String str3="";
		
		for (int i = 0; i < arr.length; i++) {
			if(arr[i]=='<') {
				str3=reverseString(str3);
				str2=str2.concat(str3);
				for (int j = i; j < arr.length; j++) {
					if(arr[j]!='>') {
						str2=str2.concat((Character.toString(arr[j])));
						str3="";
					}
					else {
						str2=str2.concat((Character.toString(arr[j])));
						i=j;
						break;
					}
				}
			}
			else if(arr[i]==' ') {
				if(i==arr.length-1) {
					str3=str3.concat((Character.toString(arr[i])));
				}
				str2=str2.concat(reverseString(str3));
				str2=str2.concat(" ");
				str3="";
			}
			else if(i==arr.length-1) {
				str3=str3.concat((Character.toString(arr[i])));
				str2=str2.concat(reverseString(str3));
			}
			else {
				str3=str3.concat((Character.toString(arr[i])));
				
			}
		}
		System.out.println(str2);
	}
	public static String reverseString(String s) {
		return(new StringBuffer(s).reverse().toString());
	}
}