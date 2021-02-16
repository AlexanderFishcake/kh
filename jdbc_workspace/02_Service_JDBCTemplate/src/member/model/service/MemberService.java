package member.model.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import common.JDBCTemplate;
import member.model.dao.MemberDao;
import member.model.vo.Member;

import static common.JDBCTemplate.*;
/**
 * Service
 * 1. DriverClass등록(최초1회)
 * 2. Connection객체 생성 url, user, password
 * 2-1. 자동커밋 false 설정
 * --------Dao 요청----------
 * 6. 트랜잭션처리(DML) commit/rollback
 * 7. 자원반납(conn)
 *
 * Dao
 * 3. PreparedStatement 객체 생성(미완성쿼리)
 * 3-1) 미완성쿼리의 '?'에 값 대입
 * 4. 실행 DML(executeUpdate) -> int , DQL(executeQuery) -> REsultSet
 * 4-1) ResultSet -> Java객체 옮겨담기
 * 5. 자원반납(생성역순 rset - pstmt)
 *
 */
public class MemberService {
	
	private MemberDao memberDao = new MemberDao();
	
	public List<Member> selectAll(){
//		Connection conn = JDBCTemplate.getConnection();	//12번째줄 static import를 해서 메소드명 쓸 필요 없게!
		Connection conn = getConnection();
		List<Member> list = memberDao.selectAll(conn);
//		JDBCTemplate.close(conn);
		close(conn);
		return list;
	}
	
	/*
	 * Service
	 * 1. DriverClass등록(최초1회)
	 * 2. Connection객체 생성 url, user, password
	 * 2-1. 자동커밋 false 설정
	 * --------Dao 요청----------
	 * 6. 트랜잭션처리(DML) commit/rollback
	 * 7. 자원반납(conn)
	 */
	public List<Member> selectAll_() {
		String driverClass = "oracle.jdbc.OracleDriver"; //ojdbc에서 제공하는 클래스
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "student";
		String password = "student";
		Connection conn = null;
		List<Member> list = null;
		
		try {
			//1. DriverClass등록(최초1회)
			Class.forName(driverClass);
			//2. Connection객체 생성 url, user, password
			conn = DriverManager.getConnection(url, user, password);
			//2-1. 자동커밋 false 설정
			conn.setAutoCommit(false);
			
			//--------Dao 요청----------
			//Connection객체 전달
			list = memberDao.selectAll(conn);
			//6. 트랜잭션처리(DML) commit/rollback
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//7. 자원반납(conn)	
			try {
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list;
	}

	public int insertMember(Member member) {
		Connection conn = getConnection();
		int result = memberDao.insertMember(conn,member);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		return result;
	}

	public Member selectId(String memberId) {
		Connection conn = getConnection();
		//--------Dao 요청----------
		Member member = memberDao.selectId(conn,memberId);
		//6. 트랜잭션처리(DML) commit/rollback		
		if(member!=null) commit(conn);
		else rollback(conn);
		//7. 자원반납(conn)
		close(conn);
		return member;
	}

	public int updateMember(int i, String inputValue, Member member) {
		Connection conn = getConnection();
		int result = memberDao.updateMember(conn, member, i, inputValue);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		return result;
	}

}
