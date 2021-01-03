package com.io.test2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class MyNote {
	
	private Scanner sc = new Scanner(System.in);

	public MyNote() {}

	public void fileSave() {
		StringBuilder sb = new StringBuilder();
		String input=" ";
		char yn=' ';
		FileWriter fw = null;
		String fileName=" ";
		
		while(true) {
			System.out.print("파일에 저장할 내용을 입력하십시오 : ");
			input=sc.nextLine();
			if(input.equals("exit"))
				break;
			sb=new StringBuilder(input);
			System.out.print("저장하시겠습니까? (y/n): ");
			yn = sc.next().charAt(0);
			sc.nextLine();
			if(Character.isUpperCase(yn))
				yn+=32;
			if(yn=='y') {
				System.out.print("저장할 파일명 : ");
				fileName = sc.nextLine();
				try {
					fw = new FileWriter(fileName);
					fw.write(sb.toString());
					System.out.println(fileName+"파일에 성공적으로 저장하였습니다.");
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						fw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
		}
	}
	
	public void fileOpen() {
		String fileName;
		
		System.out.print("열기할 파일명 : ");
		fileName = sc.next();
		FileInputStream fis=null;
		String str = " ";
		try {
			fis = new FileInputStream(fileName);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			while((str = br.readLine())!=null) {
				System.out.println(str);
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	public void fileAppend() {
		String fileName;
		String input=" ";
		StringBuilder str=new StringBuilder();
		System.out.print("수정할 파일명 : ");
		fileName = sc.nextLine();
		FileReader fr = null;
		FileWriter fw = null;
		String line;
		while(true) {
			try {
				fr = new FileReader(fileName);
				
				BufferedReader br = new BufferedReader(fr);
				while((line = br.readLine()) != null) {
					System.out.println(line);
					str.append(line);	//한줄씩 출력하면서 내용물을 저장해놓음
					}
				System.out.print("파일에 추가할 내용을 입력하시오 : ");
				input = sc.nextLine();
				//exit자리
					if(input=="exit") {
						break;
					}
				str.append(input);
				
				System.out.print("저장하시겠습니까? (y/n): ");
				char yn = sc.next().charAt(0);
				if(Character.isUpperCase(yn))
					yn+=32;
				if(yn=='y') {
					fw = new FileWriter(fileName);
					fw.write(str.toString());
					System.out.println(fileName+"파일의 내용이 변경되었습니다.");
				}
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					fr.close();
					fw.close();	//이거 yn='y'아니면 연 적이 없어서 close도 오류난다..
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
