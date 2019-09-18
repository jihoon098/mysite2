package kr.co.itcen.mysite.action.guestbook;

import kr.co.itcen.web.mvc.Action;
import kr.co.itcen.web.mvc.ActionFactory;

public class GuestbookActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		
		Action action = null;
		
		if("deleteform".equals(actionName)) {
			action = new GuestbookDeleteformAction();
		} else if("delete".equals(actionName)) {
			action = new GuestbookDeleteAction();
		} else if("add".equals(actionName)) {
			action = new GuestbookAddAction();
		} else {
			action = new GuestbookListAction();
		}
		
		return action;
	}

}
