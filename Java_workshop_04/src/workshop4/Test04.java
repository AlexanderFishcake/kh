package workshop4;

public class Test04 {
	
	public static void main(String[] args) {
		Calc ca= new Calc();
		int result=ca.calculate(Integer.parseInt(args[0]));
		
		System.out.println("입력값: "+args[0]);
		System.out.print("짝수: ");
		for(int i=1;i<Integer.parseInt(args[0]);i++) {
			if(i%2==0)
				System.out.print(i+" ");
		}
		System.out.println();
		System.out.println("결과: "+result);
	}
}
