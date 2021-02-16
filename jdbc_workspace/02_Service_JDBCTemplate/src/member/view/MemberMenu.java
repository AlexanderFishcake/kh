package member.view;

import java.util.List;
import java.util.Scanner;

import member.controller.MemberController;
import member.model.vo.Member;

public class MemberMenu {
	
	private Scanner sc = new Scanner(System.in);
	private MemberController memberController = new MemberController();

	public void mainMenu() {
		String menu = "===========회원 관리 프로그램==========\n"
				+ "1.회원 전체조회\n"
				+ "2.회원 아이디조회\n"
				+ "3.회원 이름조회\n"
				+ "4.회원 가입\n"
				+ "5.회원 정보변경\n"
				+ "6.회원 탈퇴\n"
				+ "0.프로그램 끝내기\n"
				+ "---------------------------------\n"
				+ "선택 : ";
		do {
			System.out.print(menu);
			int choice = sc.nextInt();
			List<Member> list = null;
			
			Member member;
			int result;
			String msg;
			String memberId;
			
			switch(choice) {
			case 1 :
				list = memberController.selectAll();
				displayMemberList(list);
				break;
			case 2 :
				memberId = inputId();
				member = memberController.selectId(memberId);
				displayMember(member);
				break;
			case 3 : break;
			case 4 :
				//1. 신규회원정보 입력 -> Member 객체
				member = inputMember();
				//2. controller에 회원가입 요청(메소드호출) ->int리턴(처리된 행의 개수)
				result = memberController.insertMember(member);
				//3. int에 따른 분기처리
				msg = result>0 ? "회원가입 성공!" : "회원가입 실패"; 
				displayMsg(msg);
				break;
			case 5 :
				memberId = inputId();
				member = memberController.selectId(memberId);
				displayMember(member);
				if(member!=null) {
					updateMenu(member);
				}
				break;
			case 6 : break;
			case 0 :
				System.out.println("정말 끝내시겠습니까?(y/n) : ");
				if(sc.next().charAt(0)=='y')
					return;
				break;
			default : 
				System.out.println("잘못 입력하셨습니다.");
			}
			
		}while(true);
		
	}
	private String inputId() {
		System.out.println("회원아이디를 입력하세요");
		System.out.print("아이디 : ");
		return sc.next();
	}
	private void displayMember(Member member) {
		if(member == null)
			System.out.println(">>>>조회된 회원이 없습니다.");
		else {
			System.out.println("***************************************");
			System.out.println(member);
			System.out.println("***************************************");
		}
		
	}
	private void updateMenu(Member member) {
		String menu = "****** 회원 정보 변경 메뉴******\r\n" + 
				"1. 암호변경\r\n" + 
				"2. 이메일변경\r\n" + 
				"3. 전화번호변경\r\n" + 
				"4. 주소 변경\r\n" + 
				"9. 메인메뉴 돌아가기\r\n"+
				"---------------------------------\n"+
				"선택 : ";
		while(true) {
			System.out.print(menu);
			int choice = sc.nextInt();

			switch(choice) {
			case 1:
				subMenuSwitchCase(choice, member); break;
			case 2:
				subMenuSwitchCase(choice, member); break;
			case 3:
				subMenuSwitchCase(choice, member); break;
			case 4:
				subMenuSwitchCase(choice, member); break;
			case 9: return;			
			default : 
				System.out.println("잘못 입력하셨습니다.");
			}
		}
	}
	
	private void subMenuSwitchCase(int choice, Member member) {
		String menuName=null;
		switch(choice) {
		case 1:
			menuName = "암호"; break;
		case 2:
			menuName = "이메일"; break;
		case 3:
			menuName = "전화번호"; break;
		case 4:
			menuName = "주소"; break;
		}
		String inputValue = inputUpdateValue(menuName);
		int result = memberController.updateMember(choice, inputValue, member);
		String msg = result>0? "정보수정 성공!" : "정보수정 실패";
		displayMsg(msg);
		member = memberController.selectId(member.getMemberId());
		displayMember(member);		
	}
	private String inputUpdateValue(String str) {
		System.out.print("새로운 "+str+"을(를) 입력하세요 : ");		
		return sc.next();
	}
	private void displayMsg(String msg) {
		System.out.println(">>>처리결과 : " + msg);
	}
	private Member inputMember() {
		System.out.println("새로운 회원정보를 입력하세요.");
		Member member = new Member();
		System.out.print("아이디 : ");
		member.setMemberId(sc.next());
		System.out.print("이름 : ");
		member.setMemberName(sc.next());
		System.out.print("비밀번호 : ");
		member.setPassword(sc.next());
		System.out.print("나이 : ");
		member.setAge(sc.nextInt());
		System.out.print("성별(M/F) : ");
		member.setGender(String.valueOf(sc.next().toUpperCase().charAt(0)));
		System.out.print("이메일 : ");
		member.setEmail(sc.next());
		System.out.print("전화번호(-제외) : ");
		member.setPhone(sc.next());
		sc.nextLine(); //버퍼에 남은 개행문자 날리기용(next계열 - nextLine)
		
		System.out.print("주소 : ");
		member.setAddress(sc.nextLine());
		System.out.print("취미(,로 나열할 것) : ");
		member.setHobby(sc.next());
		
		return member;
	}
	/**
	 * n행의 회원정보 출력
	 * @param list
	 */
	private void displayMemberList(List<Member> list) {
		if(list!=null && !list.isEmpty()) {
			System.out.println("================================================");
			for(int i=0; i<list.size();i++) {
				System.out.println((i+1)+" + "+list.get(i));
			}
			System.out.println("================================================");			
		}
		else {
			System.out.println(">>>조회된 회원정보가 없습니다.");
		}
		
	}

}
