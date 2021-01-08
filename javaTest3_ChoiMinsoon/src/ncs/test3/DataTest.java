package ncs.test3;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DataTest {

	public static void main(String[] args) {
		Calendar past = GregorianCalendar.getInstance();
		Calendar today = GregorianCalendar.getInstance();
		past.set(1987, Calendar.MAY, 27);
		
		double pa = past.getTimeInMillis();
		double to = today.getTimeInMillis();
		double diff = to-pa;
		double age = (diff/1000/60/60/24/365);
		char[] yoil = {'일','월','화','수','목','금','토'};
		
		System.out.printf("생일 : %d년 %d월 %d일 %c요일%n",
				past.get(Calendar.YEAR),
				past.get(Calendar.MONTH)+1,
				past.get(Calendar.DATE),
				yoil[past.get(Calendar.DAY_OF_WEEK)-1]);
		System.out.printf("현재 : %d년 %d월 %d일%n",
				today.get(Calendar.YEAR),
				today.get(Calendar.MONTH)+1,
				today.get(Calendar.DATE));
		System.out.printf("나이 : %d세",(int)age+1);
	}

}
