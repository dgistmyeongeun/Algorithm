package sam180307;
import java.util.HashMap;
import java.util.Scanner;

public class sam_3752 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int tc = sc.nextInt();
		int test = 1;
		while(tc-- > 0) {
			int exam = sc.nextInt();
			int arr[] = new int[exam];
			int addall = 0;
			for(int i=0; i<exam; i++) {
				arr[i] = sc.nextInt();
				addall += arr[i];
			}

			HashMap<Integer, Boolean> map = new HashMap<>();
			int add = 0;
			int counttime = (int)(exam/2+0.5)+1;
			for (int i = 0; i < (1 << (counttime)); i++){
		        for (int j = 0; j < counttime; j++){
		            if ((i & (1 << j))!=0){
		               add += arr[j];
		            }
		        }
		    
		        map.put(add, true);
		        map.put(addall-add, true);
		        add=0;
		    }
			
			System.out.println("#"+(test++) +" "+map.size());
		}
	}
}
