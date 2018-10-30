package spms.controls;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import spms.bind.DataBinding;
import spms.dao.ProjectDao;

@Component("/project/delete.do")
public class ProjectDeleteController implements Controller, DataBinding {
	ProjectDao projectDao;
	
	@Autowired
	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		projectDao.delete((int) model.get("no"));
		return "redirect:list.do";
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[] {
				"no", Integer.class
		};
	}

}
