package com.collection.map.student;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class StudentProperties {
	public static void main(String[] args) {
		StudentProperties sp = new StudentProperties();
		sp.printConsole(sp.readFile());
		sp.saveXMLFile(sp.readFile());

		
	}
		
	public List<Student> readFile(){
		
		String fileName = "score.txt";
		Properties prop = new Properties();
		try {
//			prop.load(new BufferedReader(new InputStreamReader(new FileInputStream(fileName))));	//<-이건 1바이트기반
//			prop.load(new FileReader(fileName));
			prop.load(new BufferedReader(new FileReader(fileName)));//	<-이게 정석
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//list를 새로 만들어서 저장해놓은 prop의 내용물을 채워넣는다. 
		List<Student> mList = new ArrayList<>();
		Enumeration<?> en = prop.propertyNames();
		//Enumeration은 forEach가 안되나?
//		for(Enumeration<?> aa : en)
		while(en.hasMoreElements()) {
			String idx = (String)en.nextElement();
			String value = prop.getProperty(idx);

			String[] str;
			str = value.split(",");
//			//str에 저장된 문자열을 각 자료형 필드의 값으로 형변환 대입 저장함
			//이거 파일에 해당 자료 없으면 바로 에러나는거 아닌가?
			mList.add(new Student(Integer.parseInt(str[0]),
					str[1],
					Integer.parseInt(str[2]),
					Integer.parseInt(str[3]),
					Integer.parseInt(str[4])));
		}
		
		return mList;
		
	}
	
	public void printConsole(List<Student> stdtList){
		int korSum=0;
		int engSum=0;
		int mathSum=0;
		for(Student st : stdtList) {
			System.out.println(st.toString());
			korSum+=st.getKor();
			engSum+=st.getEng();
			mathSum+=st.getMath();
		}
		System.out.println("국어평균 : "+korSum/stdtList.size()+
				"\t영어평균 : "+engSum/stdtList.size()+
				"\t수학평균 : "+mathSum/stdtList.size()
				);
		
	}
	/**
	 * xml파일에 출력하려면 prop으로 변환해야하는데
	 * 이 메소드가 인자로 받는건 List<Student>고..
	 * readFile이 prop->list였다면
	 * saveXMLFile은 list->prop해아 한다.
	 */
	public void saveXMLFile(List<Student> stdtList){
		Properties prop = new Properties();
		//iterator나 forEach로 내용물에 접근하자
		//!!!===번호필드를 key로===!!!
		Iterator<Student> iter = stdtList.iterator();
		while(iter.hasNext()) {
			//NoSuchElementException 을 피하기 위해서. 이거 iter.next할때마다 요소를 불러온다.
			Student buf = iter.next();
			String key = Integer.toString(buf.getSno());
			Student value = buf;
			prop.setProperty(key,value.toString());	//value.tostring외에 방법이 있나?
		}
		
		String fileName = "student.xml"; 
		try {
			prop.storeToXML(new FileOutputStream(fileName), fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
