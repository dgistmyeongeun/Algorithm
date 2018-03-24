package sam180312;

import java.io.IOException;
import java.util.Scanner;

public class Baek_wordcount1152 {

	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		String[] word = sc.nextLine().split(" ");
		
		int count = word.length;
		for(int i=0; i<word.length; i++) {
			if(word[i].equals("")) {
				count--;
			}
		}
		
		System.out.println(count);
	}

}
