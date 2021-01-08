package ncs.test4;

import java.util.Scanner;

public class ProductTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("상품 정보를 입력하세요");
		System.out.print("상품명 : ");
		String name = sc.nextLine();
		System.out.print("가격 : ");
		int price = sc.nextInt();
		System.out.print("수량 : ");
		int quantity = sc.nextInt();
		
		Product pr = new Product(name, price, quantity);
		System.out.println(pr.information());
		System.out.println("총 구매 가격 : "+pr.getPrice()*pr.getQuantity()+" 원");

	}

}
