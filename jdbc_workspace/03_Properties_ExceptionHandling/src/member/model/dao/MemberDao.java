package member.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import member.model.exception.MemberException;
import member.model.vo.Member;
import member.model.vo.MemberDel;


public class MemberDao {
	
	private Properties prop = new Properties();
	
	/**
	 * 1. MemberDao객체 생성시(최초 1회)
	 * member-query.properties의 내용을 읽어서 prop에 저장한다.
	 * 
	 * 2. dao메소드 호출시마다 prop으로부터 query를 꺼내 가져다 사용한다.
	 * 
	 * 
	 */
	public MemberDao() {
		String fileName = "resources/member-query.properties";
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/*
	 * Dao
	 * 3. PreparedStatement 객체 생성(미완성쿼리)
	 * 3-1) 미완성쿼리의 '?'에 값 대입
	 * 4. 실행 DML(executeUpdate) -> int , DQL(executeQuery) -> REsultSet
	 * 4-1) ResultSet -> Java객체 옮겨담기
	 * 5. 자원반납(생성역순 rset - pstmt)
	 *
	 */
	public List<Member> selectAll(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectAll");
		List<Member> list = null;

		try {
			//3. PreparedStatement 객체 생성(미완성쿼리)
			pstmt = conn.prepareStatement(sql);
			//3-1) 미완성쿼리의 '?'에 값 대입
			//4. 실행 DML(executeUpdate) -> int , DQL(executeQuery) -> REsultSet
			rset = pstmt.executeQuery();
			//4-1) ResultSet -> Java객체 옮겨담기
			list = new ArrayList<>();
			while(rset.next()) {
				String memberId = rset.getString("member_id");
//				String memberId = rset.getString("member_idddddddd");//일부러 오류냄
				String password = rset.getString("password");
				String memberName = rset.getString("member_name");
				String gender = rset.getString("gender");
				int age = rset.getInt("age");
				String email = rset.getString("email");
				String phone = rset.getString("phone");
				String address = rset.getString("address");
				String hobby = rset.getString("hobby");
				Date enrollDate = rset.getDate("enroll_Date");
				Member member = new Member(memberId, password, memberName, gender, age, email, phone, address, hobby, enrollDate);
				list.add(member);
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			//예외를 전환 : RuntimeException, 의미분명한 커스텀 예외객체로 전환
			throw new MemberException("회원 전체 조회", e);
		} finally {
			//5. 자원반납(생성역순 rset - pstmt)
			close(rset);
			close(pstmt);

		}
		
		return list;
	}

	public int insertMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertMember");
		int result = 0;

		try {
			//3. PreparedStatement 객체 생성(미완성쿼리)
			pstmt = conn.prepareStatement(sql);
			//3-1) 미완성쿼리의 '?'에 값 대입
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getGender());
			pstmt.setInt(5, member.getAge());
			pstmt.setString(6, member.getEmail());
			pstmt.setString(7, member.getPhone());
			pstmt.setString(8, member.getAddress());
			pstmt.setString(9, member.getHobby());			
			//4. 실행 DML(executeUpdate) -> int , DQL(executeQuery) -> REsultSet
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		//5. 자원반납(pstmt)
		close(pstmt);
		
		return result;
	}

	public Member selectId(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
//		String sql = "select * from member where member_id = ?";
		String sql = prop.getProperty("selectId");
		ResultSet rset=null;
		Member member = null;
		try {
			//3. PreparedStatement 객체 생성(미완성쿼리)
			pstmt = conn.prepareStatement(sql);
			//3-1) 미완성쿼리의 '?'에 값 대입
			pstmt.setString(1, memberId);
			//4. 실행 DML(executeUpdate) -> int , DQL(executeQuery) -> REsultSet
			rset = pstmt.executeQuery();
			//4-1) ResultSet -> Java객체 옮겨담기
			while(rset.next()) {
//				String memberId = rset.getString("member_id");
				String password = rset.getString("password");
				String memberName = rset.getString("member_name");
				String gender = rset.getString("gender");
				int age = rset.getInt("age");
				String email = rset.getString("email");
				String phone = rset.getString("phone");
				String address = rset.getString("address");
				String hobby = rset.getString("hobby");
				Date enrollDate = rset.getDate("enroll_Date");
				member = new Member(memberId, password, memberName, gender, age, email, phone, address, hobby, enrollDate);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//5. 자원반납(생성역순 rset - pstmt)
		close(rset);
		close(pstmt);
		
		return member;
	}
	/**
	 * 
	 * 
	 * @param i : 1=password, 2=email, 3=phone, 4=address
	 * @param inputValue
	 * @param member
	 * @return
	 */
	public int updateMember(Connection conn, Member member, int i, String inputValue) {
		PreparedStatement pstmt = null;
		String sql = null;
		int result = 0;
		switch(i) {
		case 1 :
//			sql = "update member set password = ? where member_id = ?";
			sql = prop.getProperty("updateMember_01");
			break;
		case 2 :
//			sql = "update member set email = ? where member_id = ?";
			sql = prop.getProperty("updateMember_02");
			break;
		case 3 :
//			sql = "update member set phone = ? where member_id = ?";
			sql = prop.getProperty("updateMember_03");
			break;
		case 4 :
//			sql = "update member set address = ? where member_id = ?";
			sql = prop.getProperty("updateMember_04");
			break;		
		}
		
		try {
			//3. PreparedStatement 객체 생성(미완성쿼리)
			pstmt = conn.prepareStatement(sql);
			//3-1) 미완성쿼리의 '?'에 값 대입
			pstmt.setString(1, inputValue);
			pstmt.setString(2, member.getMemberId());
			//4. 실행 DML(executeUpdate) -> int , DQL(executeQuery) -> REsultSet
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close(pstmt);

		return result;
	}

	public List<Member> selectName(Connection conn, String memberName) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
//		String sql = "select * from member where member_name like ?";
		String sql = prop.getProperty("selectName");
		List<Member> list = null;

		try {
			//3. PreparedStatement 객체 생성(미완성쿼리)
			pstmt = conn.prepareStatement(sql);
			//3-1) 미완성쿼리의 '?'에 값 대입
			pstmt.setString(1, '%' + memberName + '%');
			//4. 실행 DML(executeUpdate) -> int , DQL(executeQuery) -> REsultSet
			rset = pstmt.executeQuery();
			//4-1) ResultSet -> Java객체 옮겨담기
			list = new ArrayList<>();
			while(rset.next()) {
				String memberId = rset.getString("member_id");
				String password = rset.getString("password");
//				String memberName = rset.getString("member_name");
				String gender = rset.getString("gender");
				int age = rset.getInt("age");
				String email = rset.getString("email");
				String phone = rset.getString("phone");
				String address = rset.getString("address");
				String hobby = rset.getString("hobby");
				Date enrollDate = rset.getDate("enroll_Date");
				Member member = new Member(memberId, password, memberName, gender, age, email, phone, address, hobby, enrollDate);
				list.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//5. 자원반납(생성역순 rset - pstmt)
			close(rset);
			close(pstmt);
		}
		
		return list;
	}


	public int deleteId(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
//		String sql = "delete from member where member_id = ?";
		String sql = prop.getProperty("deleteId");
		int result = 0;

		try {
			//3. PreparedStatement 객체 생성(미완성쿼리)
			pstmt = conn.prepareStatement(sql);
			//3-1) 미완성쿼리의 '?'에 값 대입
			pstmt.setString(1, memberId);
			//4. 실행 DML(executeUpdate) -> int , DQL(executeQuery) -> REsultSet
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		//5. 자원반납(pstmt)
		close(pstmt);
		
		return result;
	}


	public List<MemberDel> selectDel(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<MemberDel> list= null;
		MemberDel memberDel = null;
		String sql = prop.getProperty("selectDel");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			list = new ArrayList<>();
			
			while(rset.next()) {
				String memberId = rset.getString("member_id");
				String password = rset.getString("password");
				String memberName = rset.getString("member_name");
				String gender = rset.getString("gender");
				int age = rset.getInt("age");
				String email= rset.getString("email");
				String phone = rset.getString("phone");
				String address = rset.getString("address");
				String hobby = rset.getString("hobby");
				Date enrollDate = rset.getDate("enroll_date");
				Date delDate = rset.getDate("del_date");
						
				memberDel = new MemberDel(memberId, password, memberName, gender, age, email, phone, address, hobby, enrollDate, delDate);
				list.add(memberDel);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}




}
