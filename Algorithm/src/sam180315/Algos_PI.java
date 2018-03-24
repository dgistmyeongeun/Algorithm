package sam180315;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Algos_PI {
	private static int N;
	private static int[] cache;
	private static String input="";
	private static int INF = 999;

	private static int classify(int start, int next) {
		String M=input.substring(start, next+1);
		
		for(int i=0; i<M.length()-1; i++) {
			if(M.charAt(i)!=M.charAt(i+1))
				break;
			
			if(i==M.length()-2)
				return 1;
		}
		
		boolean check = true;
		for(int i=0; i<M.length()-1; i++) {
			if(M.charAt(i)-M.charAt(i+1) != M.charAt(0)-M.charAt(1))
				check = false;
		}
		
		if(check == true && Math.abs(M.charAt(1)-M.charAt(0))==1)
			return 2;
		
		boolean alter = true;
		for(int i=0; i<M.length(); i++) {
			if(M.charAt(i)!=M.charAt(i%2))
				alter = false;
		}
		if(alter)
			return 4;
		if(check)
			return 5;
		
		return 10;
		
	}
	 private static int memorize(int start)
	 {
	  if(start==N)return 0;
	  if(cache[start]!=-1)return cache[start];
	  
	  int ret=INF;
	  
	  for(int next=start+3;next<=start+5;next++){
	   if(next<=input.length())
	   ret=Math.min(ret, memorize(next)+classify(start,next-1));
	  }
	  return cache[start]=ret;
	 }
	
	public static void main(String[] args) throws IOException{
		  BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		  N=Integer.parseInt(reader.readLine());
		  cache=new int[N];
		  Arrays.fill(cache, -1);
		  input=reader.readLine();
		  System.out.println(memorize(0));
	}
}
