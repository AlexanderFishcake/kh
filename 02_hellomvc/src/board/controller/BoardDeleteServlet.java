package board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.exception.BoardException;
import board.model.service.BoardService;

/**
 * Servlet implementation class BoardDeleteServlet
 */
@WebServlet("/board/boardDelete")
public class BoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BoardService boardService = new BoardService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = 0;
		try {
			no = Integer.parseInt(request.getParameter("no"));
			int result = boardService.deleteBoard(no);
			
			System.out.println("처리결과 = "+result);

			HttpSession	session = request.getSession();
			
			String url = request.getContextPath();
			String msg;
			
			msg = (result==1)? "게시물이 삭제되었습니다" : "게시물 삭제에 실패했습니다.";
			url += (result==1)? "/board/boardList" : "/board/boardView?no="+no;

			//3. DML요청 : 리다이렉트 & 사용자 피드백(alert:등록되었습니다)
			// /mvc/board/boardList
			session.setAttribute("msg", msg);
			response.sendRedirect(url);
		} catch (Exception e) {
			//예외 로깅
			e.printStackTrace();
			//예외페이지 전환을 위해서 was에 예외던짐.
			throw e;
		}
		
	}

}
