package smps.controls;

import java.util.Map;

import spms.dao.MemberDao;
import spms.vo.Member;

public class MemberAddController implements Controller {
	MemberDao memberDao;
	
	public MemberAddController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		if (model.get("member") == null) {
			return "/member/MemberForm.jsp";
		} else {
			memberDao.insert((Member) model.get("member"));
			return "redirect:list.do";
		}
	}
}
