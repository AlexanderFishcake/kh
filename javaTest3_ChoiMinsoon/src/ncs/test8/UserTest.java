package ncs.test8;

public class UserTest {

	public static void main(String[] args) {
		String[] data = new String[3];
		data[0] = "user01 pass01 김철수 32 M 010-1234-5678";
		data[1] = "user02 pass02 이영희 25 F 010-5555-7777";
		data[2] = "user03 pass03 황진이 20 F 010-9874-5632";
		
		User[] users = new User[3];
		String[] str = new String[data[0].length()];
		
		System.out.println("users list-----------------------------");
		for(int i=0;i<data.length;i++) {
			str = data[i].split(" ");
			users[i] = new User(str[0], str[1], str[2], Integer.parseInt(str[3]), str[4].charAt(0), str[5]);
			System.out.println(users[i].toString());
		}
		User[] copyUsers = new User[users.length];
		for(int i=0;i<copyUsers.length;i++) {
			copyUsers[i]=users[i];
		}
		System.out.println("copyUsers-----------------------------");
		for(User arr : copyUsers) {
			System.out.println(arr.toString());
		}
		System.out.println("비교결과-----------------------------");
		for(int i=0;i<users.length;i++) {
			System.out.println(users[i].equals(copyUsers[i]));
		}

	}

}
