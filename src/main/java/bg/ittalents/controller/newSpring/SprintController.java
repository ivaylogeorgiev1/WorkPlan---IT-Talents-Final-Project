package bg.ittalents.controller.newSpring;

import javax.activation.UnsupportedDataTypeException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import bg.ittalents.model.DAO.ISprintDAO;
import bg.ittalents.model.POJO.Project;
import bg.ittalents.model.exception.DBException;



@SessionAttributes("project")
@Controller
public class SprintController {

	@RequestMapping(value = "/FinishedSprints", method = RequestMethod.GET)
	public String finishedSprints(Model model, Project project) {
		try {
			model.addAttribute("finishedSprints", ISprintDAO.getDAO("db")
					.getAllFinishedSprints(project.getId()));
		} catch (UnsupportedDataTypeException | DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "finishedsprints";
	}
}
