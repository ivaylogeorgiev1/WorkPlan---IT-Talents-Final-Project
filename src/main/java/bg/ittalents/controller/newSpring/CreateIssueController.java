package bg.ittalents.controller.newSpring;

import java.sql.SQLException;
import java.util.List;

import javax.activation.UnsupportedDataTypeException;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import bg.ittalents.model.DAO.IActivityDAO;
import bg.ittalents.model.DAO.ILogDAO;
import bg.ittalents.model.DAO.IProjectDAO;
import bg.ittalents.model.DAO.ISprintDAO;
import bg.ittalents.model.DAO.IUserDAO;
import bg.ittalents.model.POJO.Activity;
import bg.ittalents.model.POJO.Log;
import bg.ittalents.model.POJO.Project;
import bg.ittalents.model.POJO.User;
import bg.ittalents.model.exception.DBException;
import bg.ittalents.model.exception.WorkPlanDAOException;
@Scope("session")
@SessionAttributes({ "project", "user", "projects", "issue" })
@Controller


public class CreateIssueController {

	@RequestMapping(value = "/CreateIssue", method = RequestMethod.GET)
	public String goToCreateIssue(Model model, HttpSession session) {
		
		if (!SelectProject.logged(session)) {
			return "forward:normalLogin";
		}

		User user = (User) (session.getAttribute("user"));
		Project project = (Project) (session.getAttribute("project"));
		
		if (project==null) {
			return "forward:./CreateProjectCon";
		}
		try {
			model.addAttribute("projects", IProjectDAO.getDAO("db").getAllProjectsByOrg(user.getOrganizationId()));
		} catch (DBException | UnsupportedDataTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<User> usersByOrg = null;
		try {
			usersByOrg = IUserDAO.getDAO("db").getAllUsersForOrganization(user.getOrganizationId());

			model.addAttribute("usersByOrg", usersByOrg);
		} catch (SQLException | UnsupportedDataTypeException e) {
			e.printStackTrace();
		}

		try {
			model.addAttribute("issuesForProject", IActivityDAO.getDAO("db").getActivitiesByProject(project.getId()));
		} catch (UnsupportedDataTypeException | DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			model.addAttribute("sprintsForProject", ISprintDAO.getDAO("db").getAllNotFinishedSprintByProjectID(project.getId()));
		} catch (UnsupportedDataTypeException | DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		model.addAttribute("issue", new Activity());
		return "createIssue";
	}

	@RequestMapping(value = "/CreateIssue", method = RequestMethod.POST)
	public String createIssueInDb(Model model, HttpSession session, @ModelAttribute("issue") Activity issue) {

		
		if (!SelectProject.logged(session)) {
			return "forward:normalLogin";
		}

		Project project = (Project) (session.getAttribute("project"));
//		Activity issue = (Activity) (session.getAttribute("issue"));
		
		
		try {
			project = IProjectDAO.getDAO("db").incrementIssuecount(project);
		} catch (UnsupportedDataTypeException | WorkPlanDAOException | DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		issue.setIssueKey(project.getKey() + "-" + project.getIssueCount());
		issue.setProjectID(project.getId());

		System.out.println(issue);

		try {
			IActivityDAO.getDAO("db").addActivity(issue);
		} catch (UnsupportedDataTypeException | WorkPlanDAOException | DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "redirect:ProjectBoard";
	}

	@RequestMapping(value = "/UpdateIssue", method = RequestMethod.GET)
	public String goToUpdateIssue(Model model,@RequestParam(value = "issueId", required = false) Integer issueId,
			HttpSession session) {

		
		if (!SelectProject.logged(session)) {
			return "forward:normalLogin";
		}

		
		if (issueId==null) {
			return "forward:home";
		}
		User user = (User) (session.getAttribute("user"));
		Project project = (Project) (session.getAttribute("project"));
		
		Activity activity = null;

		try {
			activity = IActivityDAO.getDAO("db").getActivityByID(issueId);
		} catch (NumberFormatException | UnsupportedDataTypeException | WorkPlanDAOException | DBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			model.addAttribute("projects", IProjectDAO.getDAO("db").getAllProjectsByOrg(user.getOrganizationId()));
		} catch (DBException | UnsupportedDataTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<User> usersByOrg = null;
		try {
			usersByOrg = IUserDAO.getDAO("db").getAllUsersForOrganization(user.getOrganizationId());

			model.addAttribute("usersByOrg", usersByOrg);
		} catch (SQLException | UnsupportedDataTypeException e) {
			e.printStackTrace();
		}

		try {
			model.addAttribute("issuesForProject", IActivityDAO.getDAO("db").getActivitiesByProject(project.getId()));
		} catch (UnsupportedDataTypeException | DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			model.addAttribute("sprintsForProject", ISprintDAO.getDAO("db").getAllNotFinishedSprintByProjectID(project.getId()));
		} catch (UnsupportedDataTypeException | DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (activity.getPrioriy() == 1) {
			model.addAttribute("priorityName", "Lowest");
		}
		if (activity.getPrioriy() == 2) {
			model.addAttribute("priorityName", "Low");
		}
		if (activity.getPrioriy() == 3) {
			model.addAttribute("priorityName", "Normal");
		}
		if (activity.getPrioriy() == 4) {
			model.addAttribute("priorityName", "High");
		}
		if (activity.getPrioriy() == 5) {
			model.addAttribute("priorityName", "Highest");
		}

		model.addAttribute("issue", activity);
		return "updateIssue";
	}

	@RequestMapping(value = "/UpdateIssueInDB", method = RequestMethod.POST)
	public String updateIssueIndb(Model model, @ModelAttribute("user") User user,
			@ModelAttribute("project") Project project, @ModelAttribute("issue") Activity newIssue) {

		
		
		Activity oldIssue = null;
		try {
			oldIssue = IActivityDAO.getDAO("db").getActivityByID(newIssue.getId());
		} catch (NumberFormatException | UnsupportedDataTypeException | WorkPlanDAOException | DBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//Type log
		if (!oldIssue.getType().equals(newIssue.getType())) {

			String action = "changed `Type` field from: " + oldIssue.getType() + " to: " + newIssue.getType();

			Log log = new Log(newIssue.getId(), user.getId(), action,newIssue.getIssueKey(),user.getFullname());

			try {
				ILogDAO.getDAO("db").addLog(log);
			} catch (UnsupportedDataTypeException | WorkPlanDAOException | DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		//Estimate log
		if (oldIssue.getEstimate() != newIssue.getEstimate()) {

			String action = "changed `Estimate` field from: " + oldIssue.getEstimate() + " to: "
					+ newIssue.getEstimate();

			Log log = new Log(newIssue.getId(), user.getId(), action,newIssue.getIssueKey(),user.getFullname());

			try {
				ILogDAO.getDAO("db").addLog(log);
			} catch (UnsupportedDataTypeException | WorkPlanDAOException | DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		//Summary log
		if (!oldIssue.getSummary().equals(newIssue.getSummary())) {

			String action = "changed `Summary` field from: " + oldIssue.getSummary() + " to: " + newIssue.getSummary();

			Log log = new Log(newIssue.getId(), user.getId(), action,newIssue.getIssueKey(),user.getFullname());

			try {
				ILogDAO.getDAO("db").addLog(log);
			} catch (UnsupportedDataTypeException | WorkPlanDAOException | DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		//Reporter log
		if (oldIssue.getReportedID() != newIssue.getReportedID()) {
			User oldReporter = null;
			User newReporter = null;

			try {
				oldReporter = IUserDAO.getDAO("db").getUserById(oldIssue.getReportedID());
				newReporter = IUserDAO.getDAO("db").getUserById(newIssue.getReportedID());
			} catch (UnsupportedDataTypeException | DBException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			String action = "changed `Reporter` field from: " + oldReporter.getFullname() + " to: "
					+ newReporter.getFullname();

			Log log = new Log(newIssue.getId(), user.getId(), action,newIssue.getIssueKey(),user.getFullname());

			try {
				ILogDAO.getDAO("db").addLog(log);
			} catch (UnsupportedDataTypeException | WorkPlanDAOException | DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		//Description log
		if (!oldIssue.getDescription().equals(newIssue.getDescription())) {

			String action = "changed `Description` field from: " + oldIssue.getDescription() + " to: "
					+ newIssue.getDescription();

			Log log = new Log(newIssue.getId(), user.getId(), action,newIssue.getIssueKey(),user.getFullname());

			try {
				ILogDAO.getDAO("db").addLog(log);
			} catch (UnsupportedDataTypeException | WorkPlanDAOException | DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		//Priority log
		if (oldIssue.getPrioriy() != newIssue.getPrioriy()) {

			String action = "changed `Priority` field from: " + Activity.getPriorityName(oldIssue.getPrioriy())
					+ " to: " + Activity.getPriorityName(newIssue.getPrioriy());

			Log log = new Log(newIssue.getId(), user.getId(), action,newIssue.getIssueKey(),user.getFullname());

			try {
				ILogDAO.getDAO("db").addLog(log);
			} catch (UnsupportedDataTypeException | WorkPlanDAOException | DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		//Connected Type log
		if (!oldIssue.getConnectedType().equals(newIssue.getConnectedType())) {

			String action = "changed `Connected Type` field from: " + oldIssue.getConnectedType() + " to: "
					+ newIssue.getConnectedType();

			Log log = new Log(newIssue.getId(), user.getId(), action,newIssue.getIssueKey(),user.getFullname());

			try {
				ILogDAO.getDAO("db").addLog(log);
			} catch (UnsupportedDataTypeException | WorkPlanDAOException | DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		//Connected TO log
		if (oldIssue.getConnectedToID() != newIssue.getConnectedToID()) {

			String oldConnectedName = "";

			if (oldIssue.getConnectedToID() != null && oldIssue.getConnectedToID() > 0) {
				try {
					oldConnectedName = IActivityDAO.getDAO("db").getActivityByID(oldIssue.getConnectedToID()).getIssueKey();
				} catch (UnsupportedDataTypeException | WorkPlanDAOException | DBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			String newConnectedName = "";

			if (newIssue.getConnectedToID() != null && newIssue.getConnectedToID() > 0) {
				try {
					newConnectedName = IActivityDAO.getDAO("db").getActivityByID(newIssue.getConnectedToID()).getIssueKey();
				} catch (UnsupportedDataTypeException | WorkPlanDAOException | DBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			String action = "changed `Connected Issue` field from: " + oldConnectedName + " to: "
					+ newConnectedName;

			Log log = new Log(newIssue.getId(), user.getId(), action,newIssue.getIssueKey(),user.getFullname());

			try {
				ILogDAO.getDAO("db").addLog(log);
			} catch (UnsupportedDataTypeException | WorkPlanDAOException | DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		//Asignee log
		if (oldIssue.getAssigneeID() != newIssue.getAssigneeID()) {

			String oldAssigneeName = "";

			if (oldIssue.getAssigneeID() > 0) {
				try {
					oldAssigneeName = IUserDAO.getDAO("db").getUserById(oldIssue.getAssigneeID()).getFullname();
				} catch (UnsupportedDataTypeException | DBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			String newAssigneeName = "";

			if (newIssue.getAssigneeID() > 0) {
				try {
					newAssigneeName = IUserDAO.getDAO("db").getUserById(newIssue.getAssigneeID()).getFullname();
				} catch (UnsupportedDataTypeException | DBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			String action = "changed `Asignee` field from: " + oldAssigneeName + " to: "
					+ newAssigneeName;

			Log log = new Log(newIssue.getId(), user.getId(), action,newIssue.getIssueKey(),user.getFullname());

			try {
				ILogDAO.getDAO("db").addLog(log);
			} catch (UnsupportedDataTypeException | WorkPlanDAOException | DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		//Sprint TO log
		if (oldIssue.getSprintID() != newIssue.getSprintID()) {

			String oldSprintName = "";

			if (oldIssue.getSprintID()!=null&&oldIssue.getSprintID() > 0) {
				try {
					oldSprintName = ISprintDAO.getDAO("db").getSprintById(oldIssue.getSprintID()).getName();
				} catch (UnsupportedDataTypeException | DBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			String newSprintName = "";

			if (newIssue.getSprintID()!=null&&newIssue.getSprintID() > 0) {
				try {
					newSprintName = ISprintDAO.getDAO("db").getSprintById(newIssue.getSprintID()).getName();
				} catch (UnsupportedDataTypeException | DBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			String action = "changed `Asignee` field from: " + oldSprintName + " to: "
					+ newSprintName;

			Log log = new Log(newIssue.getId(), user.getId(), action,newIssue.getIssueKey(),user.getFullname());

			try {
				ILogDAO.getDAO("db").addLog(log);
			} catch (UnsupportedDataTypeException | WorkPlanDAOException | DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		if (newIssue.getProjectID()!=project.getId()) {
			newIssue.setProjectID(project.getId());
			Project oldProject = null;

			try {
				oldProject = IProjectDAO.getDAO("db").getProjectById(newIssue.getProjectID());
			} catch (UnsupportedDataTypeException | DBException | WorkPlanDAOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			String action = "changed `Project` for this issue: " + oldProject.getName() + " to: "
					+ project.getName();

			Log log = new Log(newIssue.getId(), user.getId(), action,newIssue.getIssueKey(),user.getFullname());

			try {
				ILogDAO.getDAO("db").addLog(log);
			} catch (UnsupportedDataTypeException | WorkPlanDAOException | DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
		//UPDATE ALL
		try {
			IActivityDAO.getDAO("db").updateActivity(newIssue);
		} catch (UnsupportedDataTypeException | DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 System.out.println(oldIssue);
		 System.out.println(newIssue);

		String temp = "redirect:IssueCon?id=" + newIssue.getId();
		return temp;
	}

}
