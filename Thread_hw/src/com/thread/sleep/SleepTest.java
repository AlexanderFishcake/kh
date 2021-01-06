package com.thread.sleep;

public class SleepTest {
	public static void main(String[] args) {
		SleepTest st = new SleepTest();
		st.sendAphorism();
		System.out.println("메인종료");
	}
	
	public void sendAphorism() {
		String[] str=new String[10];
		str[0]="가는 날이 장날이다.";
		str[1]="가는 말이 고와야 오는 말이 곱다";
		str[2]="가랑비에 옷 전는 줄 모른다.";
		str[3]="가랑잎이 솔잎더러 바스락거린다고 한다.";
		str[4]="가재는 게 편이라.";
		str[5]="가지 많은 나무에 바람 잘 날 없다.";
		str[6]="간에 가 붙고 쓸개에 가 붙는다.";
		str[7]="간에 기별도 안 간다.";
		str[8]="간이 콩알만해지다.";
		str[9]="갈수록 태산.";
		   
		Thread thr = new Thread(new aphoThread(str));
		thr.start();
	}
}
