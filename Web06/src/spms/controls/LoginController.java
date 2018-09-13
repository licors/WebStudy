package spms.controls;

import java.util.Map;

import javax.servlet.http.HttpSession;

import spms.bind.DataBinding;
import spms.dao.MemberDao;
import spms.vo.Member;

public class LoginController implements Controller, DataBinding {
	MemberDao memberDao;

	public LoginController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Member loginInfo = (Member) model.get("loginInfo");
		if (loginInfo.getEmail() == null) {
			return "/auth/LogInForm.jsp";
		} else {
			loginInfo = (Member) model.get("loginInfo");
			Member member = memberDao.exist(loginInfo.getEmail(), loginInfo.getPassword());
			if (member != null) {
				HttpSession session = (HttpSession) model.get("session");
				session.setAttribute("member", member);
				return "redirect:../member/list.do";
			} else {
				return "/auth/LogInFail.jsp";
			}
		}
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[] { "loginInfo", spms.vo.Member.class };
	}

}
