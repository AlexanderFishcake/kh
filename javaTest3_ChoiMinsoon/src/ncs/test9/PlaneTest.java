package ncs.test9;

public class PlaneTest {

	public static void main(String[] args) {
		// Airplane 과 Cargoplane 객체 생성
		Airplane ap = new Airplane("L747", 1000);
		Cargoplane cp = new Cargoplane("C40",1000);
		// 생성된 객체의 정보 출력
		System.out.println("Plane\tfuelSize"
				+"\n------------------"
				+"\n" + ap.getPlaneName()+ "\t" + ap.getFuelSize()
				+"\n" + cp.getPlaneName()+ "\t" + cp.getFuelSize()
				);
		// Airplane 과 Cargoplane 객체에 100 씩 운항
		System.out.println("100 운항");
		ap.flight(100);
		cp.flight(100);
		// 100 운항 후 객체 정보 출력	
		System.out.println("Plane\tfuelSize"
				+"\n------------------"
				+"\n" + ap.getPlaneName()+ "\t" + ap.getFuelSize()
				+"\n" + cp.getPlaneName()+ "\t" + cp.getFuelSize()
				);
		//Airplane 과 Cargoplane 객체에 200 씩 주유
		System.out.println("200 주유");
		ap.refuel(200);
		cp.refuel(200);
		// 200 주유 후 객체 정보 출력
		System.out.println("Plane\tfuelSize"
				+"\n------------------"
				+"\n" + ap.getPlaneName()+ "\t" + ap.getFuelSize()
				+"\n" + cp.getPlaneName()+ "\t" + cp.getFuelSize()
				);
	}

}
