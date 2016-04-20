package bg.ittalents.controller.newSpring;

import java.sql.SQLException;

import javax.activation.UnsupportedDataTypeException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bg.ittalents.model.DAO.IUserDAO;
import bg.ittalents.model.POJO.User;

@Scope("session")
@Controller
public class InIssueContoller {
	
	
	@RequestMapping(value = "/UpdateProfileCon", method = RequestMethod.GET)
	public String goToUpdateProfile(Model model) {
	
		return "forward:/UpdateProfile";
	}

	@RequestMapping(value = "/UpdateProfileCon", method = RequestMethod.POST)
	public String goUpdateProfileInDB(Model model) {
	
		return "profilePage";
	}

	@RequestMapping(value = "/IssueCon", method = RequestMethod.GET)
	public String goToIssue(Model model,HttpServletRequest request) {
		
		return "forward:/Issue";
	}

	@RequestMapping(value = "/IssueComments", method = RequestMethod.GET)
	public String comments(Model model,HttpServletRequest request) {
		
		return "forward:/CommentServlet";
	}
	
	@RequestMapping(value = "/IssueComments", method = RequestMethod.POST)
	public String commPost(Model model) {

		return "forward:/CommentServlet";
	}
	

	@RequestMapping(value = "/IssueHistory", method = RequestMethod.GET)
	public String issueHistory(Model model) {

		return "forward:/LogServlet";
	}
	
	@RequestMapping(value = "/IssueWorkLog", method = RequestMethod.GET)
	public String issueWprkLog(Model model) {

		return "forward:/WorkLogServlet";
	}
	
	@RequestMapping(value = "/IssueWorkLog", method = RequestMethod.POST)
	public String issueWorkLogPost(Model model) {

		return "forward:/WorkLogServlet";
	}
	
	

	@RequestMapping(value = "/CreateProjectCon", method = RequestMethod.GET)
	public String goTocreateProject(Model model) {

		return "forward:/CreateProjectServlet";
	}
	
	@RequestMapping(value = "/CreateProjectCon", method = RequestMethod.POST)
	public String goTocreateProjectAgain(Model model,HttpSession session) {
		
		User user = (User) (session.getAttribute("user"));
		
		try {
			model.addAttribute("employeesInOrg",
					IUserDAO.getDAO("db").getAllUsersForOrganization(user.getOrganizationId()));
		} catch (SQLException | UnsupportedDataTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "createproject";
	}
	
	
	
	
	


}
