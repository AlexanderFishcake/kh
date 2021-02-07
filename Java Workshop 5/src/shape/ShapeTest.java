package shape;

public class ShapeTest {
	public static void main(String[] args) {
		Shape shape[] = new Shape[6];
		// 1. ��ü����
		shape[0] = new Triangle(7, 5, "Blue");
		shape[1] = new Rectangle(4, 6, "Blue");		
		shape[2] = new Triangle(6, 7, "Red");
		shape[3] = new Rectangle(8, 3, "Red");		
		shape[4] = new Triangle(9, 8, "White");
		shape[5] = new Rectangle(5, 7, "White");
		
		// 2. ��� ��ü�� ���� ������ ���� ������ for Loop�� �̿��Ͽ� ȭ�鿡 ���
		System.out.println("�⺻����");
		for(int i=0; i<shape.length; i++) {
			System.out.println(shape[i].toString());
		}
		
		// 3. setResize�Լ��� �̿��Ͽ� 5�� �Է� �ϰ� ����� ���� �� ȭ�鿡 ���
		System.out.println();
		System.out.println("����� ���� �� ����");
		for(int i=0; i<shape.length; i++) {
			if(shape[i].getClass().getSimpleName().equals("Triangle")) {
				((Triangle) shape[i]).setResize(5);
			}
			else if(shape[i].getClass().getSimpleName().equals("Rectangle")){
				((Rectangle)shape[i]).setResize(5);
			}
			System.out.println(shape[i].toString());
		}
		
	}
}
