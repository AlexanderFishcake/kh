package mobile;

public class MobileTest {

	public static void main(String[] args) {
		// ������ Mobile ��ü ����
		Ltab lt = new Ltab("Ltab", 500, "AP_01");
		Otab ot = new Otab("Otab", 1000, "AND-20");
		// ������ ��ü�� ���� ���
		System.out.println("Mobile\tbattery\tos");
		System.out.println("--------------------");
		System.out.println(lt.toString());
		System.out.println(ot.toString());
		// ������ Mobile ��ü�� 10�о� ����		
		lt.charge(10);
		ot.charge(10);
		// 10�� ���� �� ��ü ���� ���
		System.out.println();
		System.out.println("10�� ����");
		System.out.println("Mobile\tbattery\tos");
		System.out.println("--------------------");
		System.out.println(lt.toString());
		System.out.println(ot.toString());
		// ������ Mobile ��ü�� 5�о� ��ȭ
		lt.operate(5);
		ot.operate(5);
		// 5�� ��ȭ �� ��ü ���� ���
		System.out.println();
		System.out.println("5�� ��ȭ");
		System.out.println("Mobile\tbattery\tos");
		System.out.println("--------------------");
		System.out.println(lt.toString());
		System.out.println(ot.toString());

	}

}
