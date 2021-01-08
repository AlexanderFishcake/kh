package ncs.test10;

public class Secretary extends Employee implements Bonus{

	public Secretary() {}

	public Secretary(String name, int number, String department, int salary) {
		super(name, number, department, salary);
	}

	@Override
	public void incentive(int pay) {
		this.setSalary(this.getSalary()+pay*80/100);
	}

	@Override
	public double tax() {
		return this.getSalary()*10/100;
	}
	

}
