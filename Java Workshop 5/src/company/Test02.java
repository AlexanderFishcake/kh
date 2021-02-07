package company;

public class Test02 {

	public static void main(String[] args) {
		Company cp = new Company(Double.parseDouble(args[0]));
		System.out.println("연 기본급 합 : "+cp.getIncome()+" 세후 : "+cp.getAfterTaxIncome());
		System.out.println("연 보너스 합 : "+cp.getBonus()+" 세후 : "+cp.getAfterTaxBonus());
		System.out.println("연 지급액 합 : "
						+(cp.getAfterTaxIncome()+cp.getAfterTaxBonus()));
		
	}

}
