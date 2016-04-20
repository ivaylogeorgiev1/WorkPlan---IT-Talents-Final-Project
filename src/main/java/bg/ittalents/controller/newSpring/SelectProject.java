package bg.ittalents.controller.newSpring;

import javax.activation.UnsupportedDataTypeException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import bg.ittalents.model.DAO.IProjectDAO;
import bg.ittalents.model.DAO.IUserDAO;
import bg.ittalents.model.POJO.Activity;
import bg.ittalents.model.POJO.Project;
import bg.ittalents.model.POJO.User;
import bg.ittalents.model.exception.DBException;
import bg.ittalents.model.exception.WorkPlanDAOException;

@Controller
@Scope("session")
@SessionAttributes({ "project", "user", "issue" })

public class SelectProject {


	
	@RequestMapping(value = "/SelectProject", method = RequestMethod.GET)
	public String selectProjectForProjecBoard(Model model, HttpServletRequest request,
			@RequestParam(value = "projectId", required = false) Integer projectId, HttpSession session) {

		if (!SelectProject.logged(session)) {
			return "forward:normalLogin";
		}
		
		if (projectId==null) {
			return "forward:home";
		}

		User user = (User) (session.getAttribute("user"));

		Project project = null;

		try {
			project = IProjectDAO.getDAO("db").getProjectById(projectId);
		} catch (UnsupportedDataTypeException | WorkPlanDAOException | DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		user.setLastProjectId(project.getId());

		try {
			System.out.println(IUserDAO.getDAO("db").updateUser(user));
		} catch (UnsupportedDataTypeException | DBException | WorkPlanDAOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		request.getSession().setAttribute("activeSprint", null);
		model.addAttribute("user", user);
		model.addAttribute("project", project);
		return "forward:ProjectBoard";

	}

	@RequestMapping(value = "/SelectProjectForIssue", method = RequestMethod.GET)
	public String selectProjectForIssue(Model model, HttpSession session,
			@RequestParam(value="projectId",required = false) Integer projectId) {
		
		if (!SelectProject.logged(session)) {
			return "forward:normalLogin";
		}
		
		if (projectId==null) {
			return "forward:home";
		}

		User user = (User) (session.getAttribute("user"));
		Project project = null;

		try {
			project = IProjectDAO.getDAO("db").getProjectById(projectId);
		} catch (UnsupportedDataTypeException | WorkPlanDAOException | DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		user.setLastProjectId(project.getId());

		try {
			System.out.println(IUserDAO.getDAO("db").updateUser(user));
		} catch (UnsupportedDataTypeException | DBException | WorkPlanDAOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		model.addAttribute("user", user);
		model.addAttribute("project", project);
		return "forward:CreateIssue";

	}

	@RequestMapping(value = "/SelectProjectForUpdateIssue", method = RequestMethod.GET)
	public String selectProjectForUpdateIssue(Model model, HttpSession session,
			@RequestParam(value="projectId",required = false) Integer projectId){

		if (!SelectProject.logged(session)) {
			return "forward:normalLogin";
		}
		
		if (projectId==null) {
			return "forward:home";
		}

		User user = (User) (session.getAttribute("user"));
		Activity newIssue = (Activity)(session.getAttribute("issue"));
		
		
		Project project = null;

		try {
			project = IProjectDAO.getDAO("db").getProjectById(projectId);
		} catch (UnsupportedDataTypeException | WorkPlanDAOException | DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		user.setLastProjectId(project.getId());

		try {
			IUserDAO.getDAO("db").updateUser(user);
		} catch (UnsupportedDataTypeException | DBException | WorkPlanDAOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		model.addAttribute("user", user);
		model.addAttribute("project", project);

		String temp = "redirect:UpdateIssue?issueId=" + newIssue.getId();
		return temp;

	}

	static boolean logged(HttpSession session) {
		User user = (User) (session.getAttribute("user"));
		if (user == null || user.getPassword() == null || user.getEmail() == null) {
			return false;
		}
		return true;

	}

}
