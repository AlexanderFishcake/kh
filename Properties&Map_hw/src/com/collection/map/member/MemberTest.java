package com.collection.map.member;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 이 과제 containskey containsvalue 써야 됐던게 아니었을까
 *
 */
public class MemberTest {
	//회원Map은 필드로 지정하고.
	Map<String,Member> map = new HashMap<String,Member>();
	
	public MemberTest() {
	}
	public MemberTest(Map<String, Member> map) {
		this.map = map;
	}

	public static void main(String[] args) {
		//MemberTest생성자에서 map요소 추가할 것.
		//아랫줄 왜 안되지..
//		MemberTest mt = new MemberTest(new HashMap<"honggd", new Member("honggd","1234","홍길동",35,"01012341234")> );
		
		MemberTest mt = new MemberTest();
		mt.map.put("honggd", new Member("honggd","1234","홍길동",35,"01012341234"));
		mt.map.put("sinsa", new Member("sinsa","1234","신사임당",50,"01012341234"));
		mt.map.put("leess", new Member("leess","1234","이순신",43,"01012341234"));
		mt.map.put("yooon", new Member("yooon","1234","윤봉길",37,"01012341234"));
		mt.map.put("jangbg", new Member("jangbg","1234","장보고",29,"01012341234"));
		mt.map.put("honggd", new Member("honggd","1234","홍길동",35,"01012341234"));
		

//		mt.test1();
//		System.out.println(mt.isUserExist("sinsa"));
//		mt.test2();
//		mt.test3();
		mt.test4();
	}

	//map의 모든 member객체 정보 출력
	private void test1() {
		//map은 list처럼 순서를 정해줄 수가 없어서 key값을 따로 뽑은 keySet으로 조작해야한다.
		Set<String> keyset = map.keySet();
		Iterator<String> iter = keyset.iterator();
		while(iter.hasNext()) {
			String key = iter.next();
			Member value = map.get(key);
			System.out.printf("key = %s\tvalue = %s%n",key,value);
		}
	}
	
	/**
	 * 아이디가 존재하는지 비교하려면
	 * 하나씩 접근해서 해당 map>Member>userId랑 비교해야된다.
	 * 근데 그 값이랑 비교해도 같은 취급을 하려면
	 * 또 hashCode랑 equals 오버라이딩해야되나?
	 * 어 안해도 되네...String의 equals를 써서 그런가?
	 */
	private boolean isUserExist(String userId) {
		Set<String> keyset = map.keySet();
		Iterator<String> iter = keyset.iterator();
		while(iter.hasNext()) {
			String key = iter.next();
			Member value = map.get(key);
			if(value.getUserId().equals(userId))
				return true;
		}
		return false;
	}
	/**
	다음 두 아이디를 테스트하세요.
	- jangbg : true 리턴
	- sejong : false 리턴
	 */
	private void test2() {
		System.out.println(isUserExist("jangbg"));
		System.out.println(isUserExist("sejong"));
	}
	
	//yooon아이디 조회 후 [수정]..이거 replace 쓰는게 맞을까
	private void test3() {
		String testid = "yooon";
		
		Set<String> keyset = map.keySet();
		Iterator<String> iter = keyset.iterator();
		while(iter.hasNext()) {
			String key = iter.next();
			Member value = map.get(key);
			if(value.getUserId().equals(testid)){
				map.replace(key, new Member("yooon", "5678", "윤동주", 27, "01034563456"));
			}
		}
		test1();	//내용물 확인
	}
	
	/**
	 * 	current modification exception이거 대체 뭐냐
	 *  remove를 하면 전체 size가 바껴서 오류가 뜬다는건가?
	 *  값이 아니라 iterator를 삭제해서 접근을 못해게 하면 된다는데
	 *  그럼 그 값 자체는 삭제가 된게 아닌데...
	 */
	private void test4() {
		String testid = "honggd";
		
		Set<String> keyset = map.keySet();
		Iterator<String> iter = keyset.iterator();
		while(iter.hasNext()) {
			String key = iter.next();
			Member value = map.get(key);
			if(value.getUserId().equals(testid)){
//				map.remove(key);
//				break;	//바로 탈출 안하면 다음 값부터 current modification exception
				iter.remove();
			}
		}
		test1();	//내용물 확인
	}

}
