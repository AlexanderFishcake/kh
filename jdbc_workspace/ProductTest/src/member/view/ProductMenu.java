package member.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import member.controller.ProductController;
import member.model.vo.Product;
import member.model.vo.ProductIO;

public class ProductMenu {
	
	private Scanner sc = new Scanner(System.in);
	private ProductController productController = new ProductController();
	
	public void mainMenu() {
		String mainMenu = "***** 상품재고관리프로그램 *****\r\n" + 
				"1. 전체상품조회\r\n" + 
				"2. 상품아이디검색\r\n" + 
				"3. 상품명검색\r\n" + 
				"4. 상품추가\r\n" + 
				"5. 상품정보변경\r\n" + 
				"6. 상품삭제\r\n" + 
				"7. 상품입/출고 메뉴\r\n" + 
				"9. 프로그램종료\r\n" + 
				"선택 : ";
		List<Product> list= new ArrayList<>();
		String inputStr = null;
		Product product=null;
		int result = 0;
		String msg = null;
		
		while(true){
			System.out.print(mainMenu);
			int choice = sc.nextInt();
			switch(choice) {
			case 1:
				list = productController.selectAll();
				displayProductList(list);
				break;
			case 2:
				System.out.print("상품의 아이디를 입력하세요 :");
				inputStr = sc.next();
				product = productController.selectById(inputStr);
				displayProduct(product);
				break;
			case 3:
				System.out.print("상품의 이름을 입력하세요 :");
				inputStr = sc.next();
				list = productController.selectByName(inputStr);
				displayProductList(list);
				break;
			case 4:
				product = inputProduct();
				System.out.println("입력한 정보 : "+product);
				result = productController.insertProduct(product);
				msg = result > 0 ? ">>>상품 추가 완료!" : ">>>상품 추가 실패";
				System.out.println(msg);
				break;
			case 5:
				updateMenu();
				break;
			case 6:
				System.out.print("상품의 아이디를 입력하세요 :");
				inputStr = sc.next();
				result = productController.deleteById(inputStr);
				msg = result > 0 ? ">>>상품 삭제 완료!" : ">>>상품 삭제 실패";
				System.out.println(msg);
				break;
			case 7:
				ioMenu();
				break;
			case 9:
				System.out.print("정말 끝내시겠습니까?(y/n) : ");
				if(sc.next().charAt(0) == 'y')
					return;
				break;
			default :
				System.out.println("잘못 입력하셨습니다.");
			}
		}
	}

	private Product inputProduct() {
		//id name price desc stock
		Product product = new Product();
		System.out.println("새로운 상품정보를 입력하세요.");
		System.out.print("상품 ID : ");
		product.setProductId(sc.next());
		System.out.print("상품명 : ");
		product.setProductName(sc.next());
		System.out.print("가격 : ");
		product.setPrice(sc.nextInt());
		
		sc.nextLine();
		System.out.print("설명 : ");
		product.setDescription(sc.nextLine());
		System.out.print("재고량 : ");
		product.setStock(sc.nextInt());
		
		return product;
	}

	private void displayProduct(Product product) {
		if(product == null)
			System.out.println(">>>> 조회된 상품이 없습니다.");
		else
			System.out.println(product);
	}

	private void displayProductList(List<Product> list) {
		if(list != null && !list.isEmpty()) {
			for(int i = 0; i < list.size(); i++)
				System.out.println((i + 1) + " : " + list.get(i));
		}
		else
			System.out.println(">>> 조회된 상품이 없습니다.");
	}
	private void ioMenu() {
		String ioMenu = "***** 상품입출고메뉴*****\r\n" + 
				"1. 전체입출고내역조회\r\n" + 
				"2. 상품입고\r\n" + 
				"3. 상품출고\r\n" + 
				"9. 메인메뉴로 돌아가기\r\n" +
				"선택 : ";
		List<ProductIO> list = new ArrayList<>();
		ProductIO productIO = null;
		int result = 0;
		String msg = null;
		
		while(true){
			System.out.print(ioMenu);
			int choice = sc.nextInt();
			switch(choice) {
			case 1:
				list = productController.selectAllIO();
				displayProductIOList(list);
				break;
			case 2:
				System.out.println("=======상품 입고=======");
				productIO = inputProductIO('I');
				result = productController.insertProduct(productIO);
				msg = result > 0 ? ">>>상품 입고 완료!" : ">>>상품 입고 실패";
				System.out.println(msg);
				break;
			case 3:
				System.out.println("=======상품 출고=======");
				productIO = inputProductIO('O');
				result = productController.insertProduct(productIO);
				msg = result > 0 ? ">>>상품 출고 완료!" : ">>>상품 출고 실패";
				System.out.println(msg);
				break;
			case 9:
				return;
			}
		}
	}

	private ProductIO inputProductIO(char status) {
		//id amount
		ProductIO productIO = new ProductIO();
		System.out.print("상품 ID : ");
		String id = sc.next();
		productIO.setProductId(id);
		System.out.print("입/출고량 : ");
		int amount = sc.nextInt();
		productIO.setAmount(amount);
		productIO.setStatus(status);
		if(status=='O') {
			Product product = productController.selectById(id);
			int stock = product.getStock();
			if(stock-amount<0) {
				System.out.println(">>>주의! 재고보다 많이 출고할 수 없습니다.");
				//매개변수로 null을 넣어주면 ProductDao에서 NullPointerException 발생.
				return null;
			}
		}
		return productIO;
	}

	private void displayProductIOList(List<ProductIO> list) {
		if(list != null && !list.isEmpty()) {
			for(int i = 0; i < list.size(); i++)
				System.out.println((i + 1) + " : " + list.get(i));
		}
		else
			System.out.println(">>> 조회된 상품이 없습니다.");
		
	}

	private void updateMenu() {
		String ioMenu = "***** 상품정보변경메뉴 *****\r\n" + 
				"1.상품명변경\r\n" + 
				"2.가격변경\r\n" + 
				"3.설명변경\r\n" + 
				"9.메인메뉴로 돌아가기";
		String inputStr=null;
		int result = 0;
		String msg = null;
		
		System.out.print("변경할 상품의 ID를 입력하세요 :");
		inputStr = sc.next();		
		Product product = productController.selectById(inputStr);				
		if(product.getProductId() == null) {
			System.out.println("해당 상품이  존재하지 않습니다.");
			return;
		}
		System.out.println(product.toString());
		
		while(true){
			System.out.println(ioMenu);

			System.out.print("메뉴번호를 입력하세요 : ");
			int choice = sc.nextInt();
			
			switch(choice) {
			case 1:
				System.out.print("변경할 상품명을 입력하세요 : ");
				product.setProductName(sc.next());
				break;
			case 2:
				System.out.print("변경할 가격을 입력하세요 : ");
				product.setPrice(sc.nextInt());
				break;
			case 3:
				System.out.print("변경할 설명을 입력하세요 : ");
				product.setDescription(sc.nextLine());
				break;
			case 9:
				return;
			default :
				System.out.println("잘못 입력하셨습니다.");
			}
			result = productController.updateProduct(product);
			msg = result > 0 ? ">>>상품 수정 완료!" : ">>>상품 수정 실패";
			System.out.println(msg);
			
		}
		
	}
	/**
	 * 사용자에게 오류메세지 출력하기
	 * @param errorMsg
	 */
	public void displayError(String errorMsg) {
		System.err.println(errorMsg);
	}
}
