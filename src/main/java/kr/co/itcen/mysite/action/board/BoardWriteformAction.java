package kr.co.itcen.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.itcen.mysite.vo.BoardVo;
import kr.co.itcen.web.WebUtils;
import kr.co.itcen.web.mvc.Action;

public class BoardWriteformAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long g_no;
		Long o_no;
		Long depth;
		
		if(request.getParameter("g_no") == null) {
			g_no = 0L;
			o_no = 1L;
			depth = 0L;
		}else {
			g_no = Long.parseLong(request.getParameter("g_no"));
			o_no = Long.parseLong(request.getParameter("o_no"));
			depth = Long.parseLong(request.getParameter("depth"));
		}
		
		BoardVo vo = new BoardVo();
		vo.setG_no(g_no);
		vo.setO_no(o_no);
		vo.setDepth(depth);

		request.setAttribute("vo", vo);

		
		WebUtils.forward(request, response, "/WEB-INF/views/board/write.jsp");

	}

}
