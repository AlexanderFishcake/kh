package workshop4;

public class Test01 {

	public static void main(String[] args) {
		int[][] arr2 = {
				{ 5, 5},
				{10,10,10,10,10},
				{20,20,20},
				{30,30,30,30}
				};
		int total = 0;
		int count=0;
		for(int i=0;i<arr2.length;i++) {
			for(int j=0;j<arr2[i].length;j++) {
				total+=arr2[i][j];
				count++;
			}
		}
		System.out.println("total="+total);
		System.out.println("average = "+(double)total/count);
	}

}
