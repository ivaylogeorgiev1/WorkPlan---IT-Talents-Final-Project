package bg.ittalents.controller.Servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bg.ittalents.model.DAO.IActivityDAO;
import bg.ittalents.model.POJO.Activity;
import bg.ittalents.model.POJO.Project;
import bg.ittalents.model.exception.DBException;

/**
 * Servlet implementation class RestAssignee
 */
@WebServlet("/RestAssignee")
public class RestAssignee extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RestAssignee() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		try {

			String json = new BufferedReader(new InputStreamReader(
					request.getInputStream())).readLine();
			System.out.println(json);
			Set<Activity> listWithSerachedActivities = new TreeSet<Activity>((
					a1, a2) -> a1.getId() - a2.getId());
			listWithSerachedActivities.addAll(IActivityDAO.getDAO("db")
					.getActivitiesByAssigneeName(json,((Project)(request.getSession().getAttribute("project"))).getId()));
			response.getWriter().append(
					new Gson().toJson(listWithSerachedActivities));
			// System.out.println(new Gson().toJson(((IActivityDAO.getDAO("db")
			// .getAllActivitiesContainingComment(json, 23)))));
			// System.out.println(new Gson().toJson(((IActivityDAO.getDAO("db")
			// .getActivitiesByAssigneeName(json, 23)))));
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
