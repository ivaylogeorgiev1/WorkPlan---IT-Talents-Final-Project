package bg.ittalents.controller.newSpring;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;

import bg.ittalents.model.DAO.IActivityDAO;
import bg.ittalents.model.POJO.Activity;
import bg.ittalents.model.exception.DBException;
import bg.ittalents.model.exception.WorkPlanDAOException;

@Controller
public class SearchController {

	@RequestMapping(value = "/SearchPage", method = RequestMethod.GET)
	public String comments(Model model) {
		return "search";
	}

	@RequestMapping(value = "/Searcha", method = RequestMethod.POST)
	public void comments(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		try {

			String json = new BufferedReader(new InputStreamReader(request.getInputStream())).readLine();
			System.out.println(json);
			Set<Activity> listWithSerachedActivityes = new TreeSet<Activity>((a1, a2) -> a1.getId() - a2.getId());
			listWithSerachedActivityes.addAll(IActivityDAO.getDAO("db").getActivitiesByAssigneeName(json, 23));
			listWithSerachedActivityes.addAll(IActivityDAO.getDAO("db").getAllActivitiesContainingComment(json, 23));
			response.getWriter().append(new Gson().toJson(listWithSerachedActivityes));
			// System.out.println(new Gson().toJson(((IActivityDAO.getDAO("db")
			// .getAllActivitiesContainingComment(json, 23)))));
			// System.out.println(new Gson().toJson(((IActivityDAO.getDAO("db")
			// .getActivitiesByAssigneeName(json, 23)))));
		} catch (DBException | WorkPlanDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
