package com.io.test4.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Scanner;

import com.io.test4.model.vo.Book;

public class BookManager {
	Scanner sc = new Scanner(System.in);

	public BookManager() {
	}
	/**
	 * 	1. Book 객체 배열 선언, 5개 초기화함 //샘플데이터 임의 작성
		2. "books.dat" 파일에 객체 기록 저장함
		3. try with resource 문 사용할 것
		4. "books.dat 에 저장 완료!" 출력
	 */
	public void fileSave() {
		final int NUM = 5;
		Book[] book = new Book[NUM];
		Calendar[] cal = new Calendar[NUM];
		for(int i=0;i<NUM;i++)
			cal[i]= Calendar.getInstance();
		cal[0].set(2018, Calendar.DECEMBER, 4);
		cal[1].set(2019, Calendar.FEBRUARY, 5);
		cal[2].set(2018, Calendar.OCTOBER, 2);
		cal[3].set(2018, Calendar.JUNE, 5);
		cal[4].set(2018, Calendar.FEBRUARY, 6);
		book[0] = new Book("Milkman:A Novel", "Anna Burns", 11,	cal[0], 0.21);
		book[1] = new Book("Directorate S", "Steve Coll", 19,	cal[1], 0.01);
		book[2] = new Book("Belonging", "Nora Krug", 21,	cal[2], 0.28);
		book[3] = new Book("Flash", "Christopher Bonanos", 14,	cal[3], 0.54);
		book[4] = new Book("Feel Free", "Zadie Smith", 9,	cal[4], 0.1);
		
//		for(int i=0;i<NUM;i++)
//			System.out.println(book[i].toString());
		String fileName = "books.dat";
		try (
				FileWriter fw = new FileWriter(fileName);
				){
			for(Book arr : book) {
				fw.write(arr.toString()+"\n");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(fileName+"에 저장 완료!");
	}
	
	/**
	 * 	1. Book 객체 배열 선언 : 10개
		2. "books.dat" 파일에서 데이터 읽어서 배열에 저장함
		3. 객체 정보를 화면에 출력함
		4. try with resource 문 사용할 것
		5. "books.dat 읽기 완료!" 출력
	 */
	public void fileRead() {
		final int NUM = 10;
		Book[] book = new Book[NUM];
		Calendar[] cal = new Calendar[NUM];
		for(int i=0;i<NUM;i++)
			cal[i]= Calendar.getInstance();
		
		String fileName = "books.dat";
		String line;
		try (FileReader fr = new FileReader(fileName);
				BufferedReader br = new BufferedReader(fr);
				){
			while((line = br.readLine())!=null) {
				System.out.println(line);
			}

			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println(fileName+"읽기 완료!");
	}

}
