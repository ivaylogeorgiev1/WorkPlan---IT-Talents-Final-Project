package bg.ittalents.controller.newSpring;

import java.util.List;

import javax.activation.UnsupportedDataTypeException;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bg.ittalents.model.DAO.IActivityDAO;
import bg.ittalents.model.DAO.IOrganizationDAO;
import bg.ittalents.model.DAO.IProjectDAO;
import bg.ittalents.model.POJO.Activity;
import bg.ittalents.model.POJO.Organization;
import bg.ittalents.model.POJO.Project;
import bg.ittalents.model.exception.DBException;
import bg.ittalents.model.exception.WorkPlanDAOException;

@org.springframework.web.bind.annotation.RestController
public class RestController {

	@RequestMapping(value = "/WorkPlanAPI/organization-{organ_id}", method = RequestMethod.GET)
	public List<Project> allprojects(@PathVariable("organ_id") Integer organID)
			throws WorkPlanDAOException {
		try {
			return (List<Project>) IProjectDAO.getDAO("db")
					.getAllProjectsByOrg(organID);
		} catch (UnsupportedDataTypeException | DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WorkPlanDAOException(
					"There is some problem! Please try again later!", e);
		}
	}

	@RequestMapping(value = "/WorkPlanAPI/organization-{organ_id}/project-{project_id}", method = RequestMethod.GET)
	public List<Activity> allActivitiyes(
			@PathVariable("project_id") Integer project_id)
			throws WorkPlanDAOException {
		try {
			return (List<Activity>) IActivityDAO.getDAO("db")
					.getActivitiesByProject(project_id);
		} catch (UnsupportedDataTypeException | DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WorkPlanDAOException(
					"There is some problem! Please try again later!", e);

		}

	}

	@RequestMapping(value = "/WorkPlanAPI/AllOrganizations", method = RequestMethod.GET)
	public List<Organization> AllOrganizations() throws WorkPlanDAOException {
		try {
			return (List<Organization>) IOrganizationDAO.getDAO("db")
					.getAllOrganizations();
		} catch (UnsupportedDataTypeException | DBException e) {
			e.printStackTrace();
			throw new WorkPlanDAOException(
					"There is some problem! Please try again later!", e);

		}

	}
}
