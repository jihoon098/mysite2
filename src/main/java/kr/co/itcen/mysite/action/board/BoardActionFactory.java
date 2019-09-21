package kr.co.itcen.mysite.action.board;

import kr.co.itcen.web.mvc.Action;
import kr.co.itcen.web.mvc.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		if("writeform".equals(actionName)) {
			action = new BoardWriteformAction();
		} else if("write".equals(actionName)) {
			action = new BoardWriteAction();		
		} else if("view".equals(actionName)) {
			action = new BoardViewAction();		
		} else if("modifyform".equals(actionName)) {
			action = new BoardModifyformAction();
		} else if("modify".equals(actionName)) {
			action = new BoardModifyAction();
		} else if("delete".equals(actionName)) {
			action = new BoardDeleteAction();
		} else {
			/* default(list) */
			action = new BoardListAction();
		}
		
		return action;
	}
}