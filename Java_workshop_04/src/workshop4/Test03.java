package workshop4;

public class Test03 {

	public static void main(String[] args) {
		int[] arr = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
		for(int i=0;i<arr.length-1;i++) {
			for(int j=0;j<=i;j++) {
				if(arr[i-j]<arr[i-j+1]) {
					int temp=arr[i-j];
					arr[i-j]=arr[i-j+1];
					arr[i-j+1]=temp;
				}
				
			}
		}
		for(int i=0;i<arr.length;i++) {
			System.out.println(arr[i]);
		}
	}
}
