package workshop4;

public class Test02 {

	public static void main(String[] args) {
		int[] arr3 = new int[5];
		int sum=0;
		double avg;
		boolean flag=false;
		
		for(int i=0;i<5;i++) {
			flag=false;
			arr3[i] = (int)(Math.random()*10)+1;
			if(i>0) {
				for(int j=0;j<i;j++) {
					if(arr3[i]==arr3[i-j-1]) {	//이전 배열에 같은 숫자가 있으면
						i--;					//반복문의 i를 늘리지 않음=다시 실행
						flag=true;				//flag==true면 sum을 계산하지 않음
						break;
					}
				}
			}
			if(flag!=true) {
				System.out.println("arr["+i+"]="+arr3[i]);
				sum+=arr3[i];
			}
		}
		avg=(double)sum/5;
		
		System.out.println("sum="+sum);
		System.out.println("avg="+avg);
	}

}
