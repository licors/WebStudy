package spms.controls;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import spms.bind.DataBinding;
import spms.dao.MemberDao;

@Component("/member/list.do")
public class MemberListController implements Controller, DataBinding {
	MemberDao memberDao;

	@Autowired
	public MemberListController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[] { "orderCond", String.class };
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderCond", model.get("orderCond"));
		model.put("members", memberDao.selectList(paramMap));
		return "/member/MemberList.jsp";
	}
}
