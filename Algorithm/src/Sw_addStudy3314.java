import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sw_addStudy3314 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TestCase = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for(int tc=1; tc<=TestCase; tc++) {
			st = new StringTokenizer(br.readLine());
			int adds=0;
			int temp;
			for(int i=0; i<5; i++) {
				temp = Integer.parseInt(st.nextToken());
				if(temp<40)
					adds += 40;
				else {
					adds += temp;
				}
			}
			
			System.out.println("#" + tc + " " + (adds/5));
			
		}
	}

}
