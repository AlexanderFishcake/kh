package company;

public class Test02 {

	public static void main(String[] args) {
		Company cp = new Company(Double.parseDouble(args[0]));
		System.out.println("�� �⺻�� �� : "+cp.getIncome()+" ���� : "+cp.getAfterTaxIncome());
		System.out.println("�� ���ʽ� �� : "+cp.getBonus()+" ���� : "+cp.getAfterTaxBonus());
		System.out.println("�� ���޾� �� : "
						+(cp.getAfterTaxIncome()+cp.getAfterTaxBonus()));
		
	}

}
