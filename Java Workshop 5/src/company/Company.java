package company;

public class Company {
	private double salary;
	private double annualIncome;
	private double bonus;
	private double afterTaxIncome;
	private double afterTaxBonus;
	
	public Company() {}

	public Company(double salary) {
		this.salary = salary;
		this.annualIncome = salary*12;
		this.afterTaxIncome = getIncome()*(1-0.1);
		this.bonus = salary*0.2*4;
		this.afterTaxBonus = getBonus()*(1-0.055);
	}
	
	public double getIncome() {
		return annualIncome;
	}
	public double getAfterTaxIncome() {
		return afterTaxIncome;
	}
	public double getBonus() {
		return bonus;
	}
	public double getAfterTaxBonus() {
		return afterTaxBonus;
	}
		
}
