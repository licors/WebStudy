package spms.controls;

import java.util.Map;

import spms.annotation.Component;
import spms.dao.ProjectDao;

@Component("/project/list.do")
public class ProjectListController implements Controller{
	ProjectDao ProjectDao;
	
	public ProjectListController setProjectDao(ProjectDao projectDao) {
		this.ProjectDao = projectDao;
		return this;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		model.put("projects", ProjectDao.selectList());
		return "/project/ProjectList.jsp";
	}

}
