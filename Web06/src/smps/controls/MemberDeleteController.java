package smps.controls;

import java.util.Map;

import smps.bind.DataBinding;
import spms.dao.MemberDao;

public class MemberDeleteController implements Controller, DataBinding {
	MemberDao memberDao;
	
	public MemberDeleteController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		memberDao.delete((int) model.get("no"));
		return "redirect:list.do";
	}
	@Override
	public Object[] getDataBinders() {
		return new Object[] {
				"no", Integer.class
		};
	}



}
