package smps.controls;

import java.util.Map;

import spms.dao.MemberDao;
import spms.vo.Member;

public class MemberUpdateController implements Controller {
	MemberDao memberDao;
	
	public MemberUpdateController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		if (model.get("member") == null) {
			Member member = memberDao.selectOne((int) model.get("no"));
			model.put("member", member);
			return "/member/MemberUpdateForm.jsp";
		} else {
			memberDao.update((Member) model.get("member"));
			return "redirect:list.do";
		}
	}
}
