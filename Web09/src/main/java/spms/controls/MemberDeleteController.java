package spms.controls;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import spms.bind.DataBinding;
import spms.dao.MemberDao;

@Component("/member/delete.do")
public class MemberDeleteController implements Controller, DataBinding {
	MemberDao memberDao;
	
	@Autowired
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
