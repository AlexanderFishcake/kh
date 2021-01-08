package ncs.test10;

public class Company {

	public static void main(String[] args) {
		Employee[] employees = new Employee[2];
		// 1번의 사용 데이터를 기반으로 객체를 생성하여 배열에 넣는다 .
		employees[0] = new Secretary("Hilery",1,"secretary",800);
		employees[1] = new Sales("Clinten", 1, "sales     ", 1200);
		// 모든 객체의 기본 정보를 출력한다 (for 문을 이용하여 출력한다.)
		System.out.println("name\tdepartment\tsalary");
		System.out.println("------------------------------");
		for(Employee arr : employees) {
			System.out.println(arr.getName()+"\t"+arr.getDepartment()+"\t"+arr.getSalary());
		}
		// 모든 객체에 인센티브 100 씩 지급한 급여를 계산하고 다시 객체의 salary에 넣는다 .
		System.out.println();
		System.out.println("인센티브 100 지급");
		//다시 자식타입의 변수에 담으면 자식타입의 자원사용이 가능하다
		Secretary sec = (Secretary)employees[0];
		Sales sal = (Sales)employees[1];
		sec.incentive(100);
		sal.incentive(100);
		
		// 모든 객체의 정보와 세금을 출력한다 (for 문을 이용하여 출력한다.)
		System.out.println("name\tdepartment\tsalary\ttax");
		System.out.println("------------------------------------");
		System.out.println(sec.getName()+"\t"+sec.getDepartment()+"\t"+sec.getSalary()+"\t"+sec.tax());
		System.out.println(sal.getName()+"\t"+sal.getDepartment()+"\t"+sal.getSalary()+"\t"+sal.tax());

	}

}
