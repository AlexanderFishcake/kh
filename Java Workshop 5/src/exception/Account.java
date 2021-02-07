package exception;

public class Account {
	private String account;
	private double balance;
	private double interestRate;
	
	public Account() {}
	public Account(String account, double balance, double interestRate) {
		this.account = account;
		this.balance = balance;
		this.interestRate = interestRate;
	}
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	
	public double calculateInterest() {
		return balance*interestRate/100;
	}
	public void deposit(double money) {
		try {
			if(money<0)
				throw new Exception();
			balance+=money;
		} catch (Exception e) {
			System.out.println("�Ա� �ݾ��� 0���� �����ϴ�.");
		}
	}
	public void withdraw(double money) {
		try {
			if(money<0)
				throw new Exception();
			if(balance-money<0)
				throw new Exception();
			balance-=money;
		} catch (Exception e) {
			System.out.println("�ݾ��� 0���� ���ų� ���� �ܾ׺��� �����ϴ�.");
		}
	}
}
