package spms.controls;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import spms.bind.DataBinding;
import spms.dao.ProjectDao;
import spms.vo.Project;

@Component("/project/add.do")
public class ProjectAddController implements Controller, DataBinding{
	ProjectDao projectDao;
	
	@Autowired
	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Project project = (Project) model.get("project");
		if(project.getTitle() == null) {
			return "/project/ProjectForm.jsp";
		} else {
			projectDao.insert(project);
			return "redirect:list.do";
		}
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[] {
			"project", spms.vo.Project.class
		};
	}

}
