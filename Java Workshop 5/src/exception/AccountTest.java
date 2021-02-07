package exception;

public class AccountTest {
	public static void main(String[] args) {
		Account account;
		// account 객체 생성
		account = new Account("441-0290-1203", 500000, 7.3);
		// account 기본 정보 출력
		System.out.println("계좌정보 : " + account.getAccount()+
				" " + account.getBalance()+
				" " + account.getInterestRate());
		// account 에 -10원 입금 – Exception 처리
		account.deposit(-10);
		// account 에 600000원 출금 – Exception 처리
		account.withdraw(600000);
		// 이자 출력 – 현재 잔고를 기준으로 고객에게 줄 이자 금액을 출력 한다
		System.out.println("이자 : "+account.calculateInterest());
	}
}
