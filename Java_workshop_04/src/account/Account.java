package account;

public class Account {
	private String account;
	private int balance;
	private double interestRate;

	public Account() {}

	public Account(String account, int balance, double interestRate) {
		super();
		this.account = account;
		this.balance = balance;
		this.interestRate = interestRate;
	}
	//계좌정보 리턴
	public String getAccount() {
		return account;
	}
	//계좌정보 세팅
	public void setAccount(String account) {
		this.account = account;
	}
	//잔액정보 리턴
	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	
	
	@Override
	public String toString() {
		return "Account [account=" + account + ", balance=" + balance + ", interestRate=" + interestRate + "]";
	}

	//현재 잔액을 기준으로 이자 계산
	public double calculateInterest() {
		return balance*interestRate/100;
	}
	//입금을 통해 잔액정보 증가
	public void deposit(int money) {
		balance+=money;
	}
	//출금을 통해 잔액정보 감소
	public void withdraw(int money) {
		balance-=money;
	}
	public void accountInfo() {
		System.out.println("계좌번호: "+account+" 잔액: "+balance+" 이자율: "+interestRate+"%");
	}
}
