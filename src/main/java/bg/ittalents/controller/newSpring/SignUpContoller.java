package bg.ittalents.controller.newSpring;

import javax.activation.UnsupportedDataTypeException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import bg.ittalents.model.DAO.IUserDAO;
import bg.ittalents.model.POJO.User;
import bg.ittalents.model.exception.DBException;


@SessionAttributes("user")
@Controller
public class SignUpContoller {

	@RequestMapping(value="/index",method = RequestMethod.GET)
	public String goToIndex(Model model) {
		User user=new User();
		user.setAdmin(1);
		model.addAttribute("user", user);
		return "index";
	}	
	
	@RequestMapping(value="/normalLogin",method = RequestMethod.GET)
	public String goToNormalLogin(Model model) {
		User user=new User();
		model.addAttribute("user", user);
		return "normalLogin";
	}	
	
	@RequestMapping(value="/index",method = RequestMethod.POST)
	public String signUp(@ModelAttribute User user, @RequestParam("repPassword") String rep,Model model) {
		
		
		if (user.getAdmin()==0) {
			
			if (user.getFullname().trim().length() < 5) {
				model.addAttribute("errorMessage", "Fullname must me at least 5 symbols");
				
				return "index";
			}
			if (!user.getPassword().equals(rep)) {
				model.addAttribute("errorMessage", "Passwords do no match please use the button!");
				return "index";
			}
			if (!User.isPaswordStrong(user.getPassword())) {
				model.addAttribute("errorMessage", "Password must contain 5 symbols and at least one number and leter");
				return "index";
			}
			
			return "/moreDetails";
			
		}
		
		
		
		System.out.println(user);
		
		if (user.getFullname().trim().length() < 5) {
			model.addAttribute("errorMessage", "Fullname must me at least 5 symbols");
			model.addAttribute("user", new User(1));
			
			return "index";
		}
		
		if (user.getUsername().trim().length() < 5) {
			model.addAttribute("errorMessage", "Username must me at least 5 symbols");
			model.addAttribute("user", new User(1));
			return "index";
		}

		if (user.getUsername().contains(" ")) {
			model.addAttribute("errorMessage", "No spaces in username allowed");
			model.addAttribute("user", new User(1));
			return "index";
		}

		if (!User.isMailValid(user.getEmail())) {
			model.addAttribute("errorMessage", "Invalid e-mail! Try Again");
			model.addAttribute("user", new User(1));
			return "index";
		}

		if (!user.getPassword().equals(rep)) {
			model.addAttribute("errorMessage", "Passwords do no match please use the button!");
			model.addAttribute("user", new User(1));
			return "index";
		}
		if (!User.isPaswordStrong(user.getPassword())) {
			model.addAttribute("errorMessage", "Password must contain 5 symbols and at least one number and leter");
			model.addAttribute("user", new User(1));
			return "index";
		}

		
			try {
				if (IUserDAO.getDAO("db").isThereSuchAUser(user.getEmail())) {
					model.addAttribute("errorMessage", "User with such mail exists !!!");
					model.addAttribute("user", new User(1));
					return "index";
				}
			} catch (UnsupportedDataTypeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

		user.setAdmin(1);

		return "/moreDetails";

	}	


}
