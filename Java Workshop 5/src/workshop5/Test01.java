package workshop5;

public class Test01 {

	public static void main(String[] args) {

		char[] arr = args[0].toCharArray();
		for(int i=0;i<arr.length/2;i++) {
			char temp = arr[i];
			arr[i]=arr[arr.length-1-i];
			arr[arr.length-1-i]=temp;	
		}
		
		System.out.println(arr);
	}
}
