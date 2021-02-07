package workshop5;

public class Test03 {

	public static void main(String[] args) {
		int[][] arr2 = {
				 {20, 30, 10},
				 {50, 40, 60},
				 {80, 80, 90}
				 };
		for(int i=0;i<arr2.length;i++) {
			int temp = arr2[i][0];
			arr2[i][0] = arr2[i][2];
			arr2[i][2] = temp;
		}

		int[] temp = arr2[0];
		arr2[0] =arr2[2];
		arr2[2] = temp;

		for(int i=0;i<arr2.length;i++) {
			for(int j=0;j<arr2[i].length;j++) {
				System.out.print(arr2[i][j]+" ");
			}
		}

	}

}
