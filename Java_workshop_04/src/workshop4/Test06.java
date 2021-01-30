package workshop4;

public class Test06 {
	public static void main(String[] args) {
		int input1=Integer.parseInt(args[0]);
		int input2=Integer.parseInt(args[0]);
		int sum=0;
		double avg;
		if(args.length!=2) {
			System.out.println("다시 입력하세요");
		}
		if(!(input1>=1 && input2<=5)) {
			System.out.println("숫자를 확인하세요");
		}
		
		int[][] arr = new int [input1][input2];
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr[i].length;j++) {
				arr[i][j]=(int)(Math.random()*5+1);
				System.out.print(arr[i][j]+" ");
				sum+=arr[i][j];
			}
			System.out.println();
		}
		avg=(double)sum/(input1*input2);
		System.out.println("sum= "+sum);
		System.out.println("avg= "+avg);
	}
}
