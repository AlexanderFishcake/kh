package ncs.test6;

import java.util.Scanner;

public class ExceptionTest {

	public static void main(String[] args) {
		Calculator ca = new Calculator();
		Scanner sc = new Scanner(System.in);
		System.out.print("2부터 5 사이의 정수를 입력하세요 : ");
		int data = sc.nextInt();
		double result = ca.getSum(data);
		
		System.out.println("결과값 : "+result);

	}

}
