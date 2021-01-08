package ncs.test10;

public class Sales extends Employee implements Bonus{

	public Sales() {
		// TODO Auto-generated constructor stub
	}

	public Sales(String name, int number, String department, int salary) {
		super(name, number, department, salary);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void incentive(int pay) {
		this.setSalary(this.getSalary()+pay*120/100);
	}

	@Override
	public double tax() {
		return (double)this.getSalary()*13/100;
	}
	
}
