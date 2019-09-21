package kr.co.itcen.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.itcen.mysite.dao.BoardDao;
import kr.co.itcen.mysite.vo.UserVo;
import kr.co.itcen.web.WebUtils;
import kr.co.itcen.web.mvc.Action;

public class BoardWriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authUser");

				
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Long no = authUser.getNo();
		
		//게시글 insert 및 update실행돼야함
		new BoardDao().insertBoard(title, content, no);
		
		WebUtils.redirect(request, response, request.getContextPath() + "/board");
	}

}
