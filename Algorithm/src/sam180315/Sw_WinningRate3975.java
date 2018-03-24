package sam180315;

import java.util.Scanner;

public class Sw_WinningRate3975 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int TestCase = sc.nextInt();
		sc.nextLine();
		String line[];
		
		for(int tc=1; tc<=TestCase; tc++) {
			line = sc.nextLine().split(" ");
			int aw = Integer.parseInt(line[0]);
			int at = Integer.parseInt(line[1]);
			int bw = Integer.parseInt(line[2]);
			int bt = Integer.parseInt(line[3]);
			
			System.out.print("#"+tc+" ");
			if((double)aw/at > (double)bw/bt)
				System.out.println("ALICE");
			else if((double)aw/at < (double)bw/bt)
				System.out.println("BOB");
			else
				System.out.println("DRAW");
		}
	}

}
