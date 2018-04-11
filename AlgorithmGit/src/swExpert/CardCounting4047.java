package swExpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CardCounting4047 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TestCase = Integer.parseInt(br.readLine());
		int S,D,H,C,val,val2;
		char c;
		boolean arr[][];
		
		for(int tc=1; tc<=TestCase; tc++) {
			arr = new boolean[5][14];
			String s = br.readLine();
			boolean error = false;
			S=13;
			D=13;
			H=13;
			C = 13;
			
			for(int i=0; i<s.length()-2; i+=3) {
				
				if(S<0 || D<0 || H<0 ||C<0)
					break;
				c = s.charAt(i);
				val = (s.charAt(i+1)-48)*10;
				val2 = s.charAt(i+2)-48+val;
				
				
				switch(c){
				case 'S':
					if(arr[0][val2]) {
						error=true;
						break;
					}
					arr[0][val2] = true;
					S--;
					break;
				case 'D':
					if(arr[1][val2]) {
						error=true;
						break;
					}
					arr[1][val2] = true;
					D--;
					break;
				case 'H':
					if(arr[2][val2]) {
						error=true;
						break;
					}
					arr[2][val2] = true;
					H--;
					break;
				case 'C':
					if(arr[3][val2]) {
						error=true;
						break;
					}
					arr[3][val2] = true;
					C--;
					break;
				}
				
				if(error)
					break;
			}
			
			System.out.print("#" + tc+" ");
			
			if(error)
				System.out.println("ERROR");
			else {
				System.out.println(S+" "+D+" "+H +" "+C);
			}
			
		}
	}

}
