package sam180309;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Baek_teaching1062 {

	public static String[] wordset;
	public static int n, k, maxcnt=0;
	public static char[] teachchar;
	public static boolean[] check;
	public static HashSet<Character> hash;
	
	public static void teaching(HashSet<Character> tchar, int idx, int cnt) {
		int count = 0;
		if(cnt == k) {
			boolean check2;
			for(int i=0; i<n; i++) {
				check2 = false;
				for(int j=0; j<wordset[i].length(); j++) {
					if(!tchar.contains(wordset[i].charAt(j))){
						check2 = true;
						break;
					}
				}
				if(!check2)
					count++;
			}
			
			if(maxcnt<count)
				maxcnt = count;
			
			return;
		}
		
		for(int i= idx; i<hash.size(); i++) {
			if(!check[i]) {
				check[i] = true;
				tchar.add(teachchar[i]);
				
				teaching(tchar, i, cnt+1);
				
				check[i] = false;
				tchar.remove(teachchar[i]);
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] temp = in.readLine().split(" ");
		n = Integer.parseInt(temp[0]);
		k = Integer.parseInt(temp[1]);
		
		wordset = new String[n];
		hash = new HashSet<>();
		
		
		String word;
		int compcnt = 0, j=0;
		for(int i=0; i<n; i++) {
			word = in.readLine();
			word = word.replaceAll("[antic]", "");
			int wsize = word.length();
			
			if(wsize==0)
				compcnt++;
			else {
				wordset[j++] = word;
				for(int t=0; t<wsize; t++)
					hash.add(word.charAt(t));
			}
		}
		
		if(k<5) 
			compcnt = maxcnt = 0;
		else{
			k-=5;
		
			int hsize = hash.size();
			teachchar = new char[hsize];
			check = new boolean[hsize];
			
			int i=0;
			for(char c:hash)
				teachchar[i++] = c;
			
			if(hsize<k) {
				k=hsize;
			}
			
			teaching(new HashSet<>(), 0 , 0);
		}
		System.out.println(compcnt+maxcnt);
		in.close();
	}

}
