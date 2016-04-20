package bg.ittalents.controller.Servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import bg.ittalents.model.DAO.INFO;
import bg.ittalents.model.DAO.IUserDAO;
import bg.ittalents.model.POJO.User;
import bg.ittalents.model.exception.DBException;
import bg.ittalents.model.exception.WorkPlanDAOException;

/**
 * Servlet implementation class UpdateProfileDetailsS
 */
@WebServlet("/UpdateProfile")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 20, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB

public class ProfileUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProfileUpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ImageServletFromId.isLogged(request, response);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/views/jsp/profilePage.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ImageServletFromId.isLogged(request, response);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("./UpdateProfileCon");
		User user = (User) request.getSession().getAttribute("user");
		

		if (request.getParameter("action").equals("password")) {
			String oldPassword = request.getParameter("oldPassword");
			String newPassword = request.getParameter("newPassword");

			if (user.getPassword().equals(newPassword)) {

				request.setAttribute("errorMessage", "Old password equals new pasword there is no point!");
				dispatcher.forward(request, response);
				return;

			}
			if (user.getPassword().equals(oldPassword)) {
				if (!User.isPaswordStrong(newPassword)) {
					request.setAttribute("errorMessage",
							"New password must contain 5 symbols and at least one number and leter");
					dispatcher.forward(request, response);
					return;
				}
				user.setPassword(newPassword);
				try {
					IUserDAO.getDAO("db").updateUser(user);
				} catch (DBException | WorkPlanDAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("errorMessage", "Password updated succesfully");
				dispatcher.forward(request, response);

			}
			request.setAttribute("errorMessage", "Old password wrong!");
			dispatcher.forward(request, response);

		}
		
		
		if (request.getParameter("action").equals("details")) {
			String fullname = request.getParameter("fullname");
			String username = request.getParameter("username");
			String email = request.getParameter("email");

			if (user.getFullname().equals(fullname) && user.getUsername().equals(username)
					&& user.getEmail().equals(email)) {

				request.setAttribute("errorMessage", "Nothing to update YOU human");
				dispatcher.forward(request, response);

			}

			if (fullname != null && !user.getFullname().equals(fullname)) {

				if (fullname.trim().length() < 5) {
					request.setAttribute("errorMessage", "Full name must me at least 5 symbols");
					dispatcher.forward(request, response);
				}

				user.setFullname(fullname);
			}

			if (username != null && !user.getUsername().equals(username)) {
				if (username.trim().length() < 5) {
					request.setAttribute("errorMessage", "Username must me at least 5 symbols");
					dispatcher.forward(request, response);
				}

				if (username.contains(" ")) {
					request.setAttribute("errorMessage", "No spaces in username allowed");
					dispatcher.forward(request, response);
				}

				user.setUsername(username);

			}

			if (email != null && !user.getEmail().equals(email)) {
				if (!User.isMailValid(email)) {
					request.setAttribute("errorMessage", "Invalid e-mail! Try Again");
					dispatcher.forward(request, response);
				}

				try {
					if (IUserDAO.getDAO("db").isThereSuchAUser(email)) {
						request.setAttribute("errorMessage", "User with such mail exists !!!");
						dispatcher.forward(request, response);
					}
				} catch (DBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				user.setEmail(email);

			}

			try {
				IUserDAO.getDAO("db").updateUser(user);
			} catch (DBException | WorkPlanDAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("errorMessage", "Profile succesfully updated");
			dispatcher.forward(request, response);
			// response.sendRedirect("./adminProfilePage.jsp");
		}

		if (request.getParameter("action").equals("avatar")) {
			Integer avatarID = Integer.parseInt(request.getParameter("avatarID"));

			if (avatarID != 0) {

				user.setAvatarPath("avatars" + File.separator + avatarID + ".jpg");

				try {
					IUserDAO.getDAO("db").updateUser(user);
				} catch (DBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (WorkPlanDAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("errorMessage", "Avatar succesfully updated");
				dispatcher.forward(request, response);
				return;

			}
			File fileSaveDir = new File(
					INFO.IMAGES_PATH + File.separator + "customAvatars" + File.separator + user.getId());

			if (!fileSaveDir.exists()) {
				fileSaveDir.mkdir();
			}

			File oldfile = new File(fileSaveDir + File.separator + "avatar.jpg");

			System.out.println(oldfile.delete());
			System.out.println(request.getParts());

			OutputStream o = new FileOutputStream(oldfile);

			for (Part part : request.getParts()) {
				System.out.println(part.getContentType());
				if (part.getContentType() == null || !part.getContentType().contains("image"))
					continue;
				// part.write(fileSaveDir + File.separator + "avatar.jpg");
				InputStream s = part.getInputStream();
				System.out.println("Writing part");

				int x = s.read();
				while (x != -1) {
					if (x != -1)
						o.write(x);
					x = s.read();
				}
				s.close();
			}
			o.close();
			
			user.setAvatarPath("customAvatars" + File.separator + user.getId() + File.separator + "avatar.jpg");
			
			try {
				IUserDAO.getDAO("db").updateUser(user);
			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WorkPlanDAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			request.setAttribute("errorMessage", "Avatar succesfully updated");
			dispatcher.forward(request, response);
		}
	}

}
