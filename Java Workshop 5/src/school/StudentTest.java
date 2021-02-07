package school;

public class StudentTest {

	public static void main(String[] args) {
		Student studentArray [] = new Student[3];
		int sumAge = 0;
		int sumHeight = 0;
		int sumWeight = 0;
		
		// Student 객체를 3개 생성하여 배열에 넣는다
		studentArray[0] = new Student("홍길동", 15, 171, 81);
		studentArray[1] = new Student("한사람", 13, 183, 72);
		studentArray[2] = new Student("임걱정", 16, 175, 65);
		
		// 배열에 있는 객체 정보를 모두 출력 한다 – for 문을 이용 할 것
		System.out.println("이름\t나이\t신장\t몸무게");
		for(int i=0; i<studentArray.length;i++) {
			System.out.println(studentArray[i].getName()+"\t"
					+studentArray[i].getAge()+"\t"
					+studentArray[i].getHeight()+"\t"
					+studentArray[i].getWeight());
			sumAge += studentArray[i].getAge();
			sumHeight += studentArray[i].getHeight();
			sumWeight += studentArray[i].getWeight();
		}
		System.out.println();
		// 나이의 평균 출력
		System.out.printf("나이의 평균 : %.2f %n",sumAge/3.0);
		// 신장의 평균 출력
		System.out.printf("신장의 평균 : %.2f %n", sumHeight/3.0);
		// 몸무게의 평균 출력
		System.out.printf("몸무게의 평균 : %.2f %n", sumWeight/3.0);
	}

}
