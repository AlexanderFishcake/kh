package com.io.test2;

import java.util.Scanner;

public class TestMyNote {
	public static void main(String[] args) {
		
		TestMyNote tm = new TestMyNote();
		tm.menu();
		
	}
	
	public static void menu() {
		MyNote mn = new MyNote();
		Scanner sc = new Scanner(System.in);
		boolean flag = false;
		while(true)
		{
			System.out.println("******   MyNote  *******\r\n" + 
					"1. 노트 새로 만들기\r\n" + 
					"2. 노트 열기\r\n" + 
					"3. 노트 열어서 수정하기\r\n" + 
					"4. 끝내기");
			System.out.print("메뉴 선택 : ");
			int num = sc.nextInt();
			switch(num) {
			case 1:
				mn.fileSave(); break;
			case 2:
				mn.fileOpen(); break;
			case 3:
				mn.fileAppend(); break;
			case 4:
				flag=true; break;
			}
			if(flag==true) {
				flag=false;
				break;
			}
		}
		System.out.println("종료.");
	}

}
