package spms.controls;

import java.util.HashMap;
import java.util.Map;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.ProjectDao;

@Component("/project/list.do")
public class ProjectListController implements Controller, DataBinding{
	ProjectDao ProjectDao;
	
	public ProjectListController setProjectDao(ProjectDao projectDao) {
		this.ProjectDao = projectDao;
		return this;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderCond", model.get("orderCond"));
		model.put("projects", ProjectDao.selectList(paramMap));
		return "/project/ProjectList.jsp";
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[] {
				"orderCond", String.class
		};
	}
}
