package spms.controls;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import spms.bind.DataBinding;
import spms.dao.MemberDao;
import spms.vo.Member;

@Component("/member/add.do")
public class MemberAddController implements Controller, DataBinding {
	MemberDao memberDao;
	
	@Autowired
	public MemberAddController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Member member = (Member) model.get("member");
		if (member.getEmail() == null) {
			return "/member/MemberForm.jsp";
		} else {
			memberDao.insert(member);
			return "redirect:list.do";
		}
	}
	@Override
	public Object[] getDataBinders() {
		return new Object[] {
				"member", spms.vo.Member.class
		};
	}
}
