package kr.co.itcen.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.itcen.mysite.dao.BoardDao;
import kr.co.itcen.mysite.vo.BoardVo;
import kr.co.itcen.mysite.vo.UserVo;
import kr.co.itcen.web.WebUtils;
import kr.co.itcen.web.mvc.Action;

public class BoardWriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		Long user_no = authUser.getNo();

		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Long g_no = Long.parseLong(request.getParameter("g_no"));
		Long o_no = Long.parseLong(request.getParameter("o_no"));
		Long depth = Long.parseLong(request.getParameter("depth"));
		
		
		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContents(content);
		vo.setG_no(g_no);
		vo.setO_no(o_no);
		vo.setDepth(depth);
		vo.setUser_no(user_no);
		
		
		//게시글 insert 및 update실행돼야함
		new BoardDao().insertBoard(vo);
		
		WebUtils.redirect(request, response, request.getContextPath() + "/board");
	}

}
